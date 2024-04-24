package io.piano.android.id.google

import android.os.Bundle
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import io.piano.android.id.PianoId
import io.piano.android.id.PianoOAuthActivity
import kotlinx.coroutines.launch

public class GoogleSignInActivity : PianoOAuthActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            try {
                val googleIdOption = GetSignInWithGoogleOption.Builder(requireNotNull(intent.getStringExtra(PianoId.KEY_CLIENT_ID)))
                    .build()
                val request = GetCredentialRequest(
                    credentialOptions = listOf(googleIdOption)
                )
                val credentialManager = CredentialManager.create(this)
                lifecycleScope.launch {
                    try {
                        val credential = credentialManager.getCredential(this@GoogleSignInActivity, request).credential
                        check(credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                            "Unsupported credential type '${credential.type}'"
                        }
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        setSuccessResult(GoogleOAuthProvider.NAME, googleIdTokenCredential.idToken)
                    } catch (e: GetCredentialCancellationException) {
                        setResult(RESULT_CANCELED)
                    } catch (e: Exception) {
                        setFailureResult(e)
                    } finally {
                        finish()
                    }
                }
            } catch (e: Exception) {
                setFailureResult(e)
                finish()
            }
        }
    }
}
