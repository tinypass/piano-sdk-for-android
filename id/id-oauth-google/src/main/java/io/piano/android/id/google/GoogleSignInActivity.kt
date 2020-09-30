package io.piano.android.id.google

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import io.piano.android.id.PianoId
import io.piano.android.id.PianoOAuthActivity

class GoogleSignInActivity : PianoOAuthActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken(intent.getStringExtra(PianoId.KEY_CLIENT_ID))
                .build()
            val signInIntent = GoogleSignIn.getClient(this, options).apply {
                signOut()
            }.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        } catch (e: Exception) {
            setFailureResult(e)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            try {
                val idToken = GoogleSignIn
                    .getSignedInAccountFromIntent(data)
                    .getResult(ApiException::class.java)
                    ?.idToken
                setSuccessResult(GoogleOAuthProvider.NAME, idToken)
            } catch (e: ApiException) {
                when (e.statusCode) {
                    GoogleSignInStatusCodes.CANCELED, GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> setResult(
                        RESULT_CANCELED
                    )
                    else -> setFailureResult(e)
                }
            } catch (e: Exception) {
                setFailureResult(e)
            }
        } else super.onActivityResult(requestCode, resultCode, data)
        finish()
    }

    companion object {
        private const val RC_SIGN_IN = 1
    }
}
