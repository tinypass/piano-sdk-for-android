package io.piano.android.id

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultRegistryOwner
import io.piano.android.id.models.OAuthResult

public interface PianoIdOAuthProvider {
    /**
     * Get OAuth-provider name
     *
     * @return OAuth-provider name
     */
    public val name: String

    /**
     * Starts login process
     *
     * @param activity Activity, that started login process. You can use it as [Context] or [ActivityResultRegistryOwner]
     * @param clientId ClientId for OAuth provider, supplied by backend
     * @return successful result with token or cancelled result
     * @throws [Exception] if something goes wrong in login process
     */
    public suspend fun login(activity: ComponentActivity, clientId: String): OAuthResult
}
