package io.piano.android.id.google

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import io.piano.android.id.PianoId
import io.piano.android.id.PianoOAuthActivity

class GoogleSignInActivity : PianoOAuthActivity() {
    private val signInResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        try {
            val idToken = GoogleSignIn
                .getSignedInAccountFromIntent(it.data)
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
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            try {
                val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .requestIdToken(requireNotNull(intent.getStringExtra(PianoId.KEY_CLIENT_ID)))
                    .build()
                val signInIntent = GoogleSignIn.getClient(this, options).apply {
                    signOut()
                }.signInIntent
                signInResult.launch(signInIntent)
            } catch (e: Exception) {
                setFailureResult(e)
                finish()
            }
        }
    }
}
