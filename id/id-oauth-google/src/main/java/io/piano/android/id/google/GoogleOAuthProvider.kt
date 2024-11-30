package io.piano.android.id.google

import androidx.activity.ComponentActivity
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import io.piano.android.id.PianoIdOAuthProvider
import io.piano.android.id.models.OAuthCancelledResult
import io.piano.android.id.models.OAuthResult
import io.piano.android.id.models.OAuthSuccessResult

@Suppress("unused") // Public API.
public class GoogleOAuthProvider : PianoIdOAuthProvider {
    override val name: String = NAME

    override suspend fun login(activity: ComponentActivity, clientId: String): OAuthResult {
        try {
            val googleIdOption = GetSignInWithGoogleOption.Builder(clientId).build()
            val request = GetCredentialRequest(credentialOptions = listOf(googleIdOption))
            val credentialManager = CredentialManager.create(activity)
            val credential = credentialManager.getCredential(activity, request).credential

            check(credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                "Unsupported credential type '${credential.type}'"
            }

            return OAuthSuccessResult(
                name,
                GoogleIdTokenCredential.createFrom(credential.data).idToken,
            )
        } catch (e: GetCredentialCancellationException) {
            return OAuthCancelledResult
        }
    }

    private companion object {
        private const val NAME = "google"
    }
}
