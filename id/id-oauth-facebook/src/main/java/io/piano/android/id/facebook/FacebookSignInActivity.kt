package io.piano.android.id.facebook

import android.content.Intent
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.piano.android.id.PianoOAuthActivity

class FacebookSignInActivity : PianoOAuthActivity() {
    private val fbCallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runCatching {
            with(LoginManager.getInstance()) {
                logOut()
                logIn(this@FacebookSignInActivity, listOf("email"))
            }
        }.onFailure {
            setFailureResult(it)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            RESULT_OK ->
                runCatching {
                    with(LoginManager.getInstance()) {
                        registerCallback(
                            fbCallbackManager,
                            object : FacebookCallback<LoginResult> {
                                override fun onSuccess(loginResult: LoginResult) {
                                    if (!loginResult.recentlyGrantedPermissions.contains("email")) {
                                        onCancel()
                                        return
                                    }
                                    unregisterCallback(fbCallbackManager)
                                    setSuccessResult(FacebookOAuthProvider.NAME, loginResult.accessToken.token)
                                }

                                override fun onCancel() {
                                    unregisterCallback(fbCallbackManager)
                                    setResult(RESULT_CANCELED)
                                }

                                override fun onError(e: FacebookException) {
                                    unregisterCallback(fbCallbackManager)
                                    setFailureResult(e)
                                }
                            }
                        )
                    }
                    fbCallbackManager.onActivityResult(requestCode, resultCode, data)
                }.onFailure {
                    setFailureResult(it)
                }
            RESULT_CANCELED -> setResult(RESULT_CANCELED)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
        finish()
    }
}
