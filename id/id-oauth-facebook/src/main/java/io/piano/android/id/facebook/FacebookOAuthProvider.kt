package io.piano.android.id.facebook

import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.piano.android.id.PianoIdOAuthProvider
import io.piano.android.id.models.OAuthCancelledResult
import io.piano.android.id.models.OAuthResult
import io.piano.android.id.models.OAuthSuccessResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Suppress("unused") // Public API.
public class FacebookOAuthProvider : PianoIdOAuthProvider {
    private val fbCallbackManager by lazy { CallbackManager.Factory.create() }
    private val loginManager by lazy { LoginManager.getInstance() }

    override val name: String = NAME

    override suspend fun login(activity: ComponentActivity, clientId: String): OAuthResult =
        suspendCancellableCoroutine { continuation ->
            val callback = object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    if (continuation.isActive) {
                        continuation.resume(OAuthCancelledResult)
                    }
                }

                override fun onError(error: FacebookException) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(error)
                    }
                }

                override fun onSuccess(result: LoginResult) {
                    if (continuation.isActive) {
                        val r = if ("email" in result.recentlyGrantedPermissions) {
                            OAuthSuccessResult(name, result.accessToken.token)
                        } else {
                            OAuthCancelledResult
                        }
                        continuation.resume(r)
                    }
                }
            }
            runCatching {
                loginManager.registerCallback(fbCallbackManager, callback)
                loginManager.logOut()
                loginManager.logInWithReadPermissions(activity, fbCallbackManager, listOf("email"))
            }.onFailure {
                continuation.resumeWithException(it)
            }

            continuation.invokeOnCancellation {
                loginManager.unregisterCallback(fbCallbackManager)
            }
        }

    private companion object {
        private const val NAME = "facebook"
    }
}
