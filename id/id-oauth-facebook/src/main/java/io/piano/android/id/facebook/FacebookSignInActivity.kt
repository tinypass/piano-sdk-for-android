package io.piano.android.id.facebook

import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.piano.android.id.PianoOAuthActivity

class FacebookSignInActivity : PianoOAuthActivity() {
    private val fbCallbackManager = CallbackManager.Factory.create()
    private val callback = object : FacebookCallback<LoginResult> {
        override fun onCancel() {
            setResult(RESULT_CANCELED)
            unregisterCallbacksAndFinish()
        }

        override fun onError(error: FacebookException) {
            setFailureResult(error)
            unregisterCallbacksAndFinish()
        }

        override fun onSuccess(result: LoginResult) {
            if ("email" in result.recentlyGrantedPermissions) {
                setSuccessResult(FacebookOAuthProvider.NAME, result.accessToken.token)
            } else {
                setResult(RESULT_CANCELED)
            }
            unregisterCallbacksAndFinish()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun unregisterCallbacksAndFinish() {
        LoginManager.getInstance().unregisterCallback(fbCallbackManager)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runCatching {
            with(LoginManager.getInstance()) {
                registerCallback(fbCallbackManager, callback)
                logOut()
                logInWithReadPermissions(this@FacebookSignInActivity, fbCallbackManager, listOf("email"))
            }
        }.onFailure {
            setFailureResult(it)
            finish()
        }
    }
}
