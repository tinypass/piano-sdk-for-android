package io.piano.android.id.google

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.piano.android.id.PianoIdOAuthProvider

@Suppress("unused") // Public API.
public class GoogleOAuthProvider : PianoIdOAuthProvider {
    override val name: String = NAME

    override fun buildIntent(context: Context, extras: Bundle): Intent =
        Intent(context, GoogleSignInActivity::class.java)

    internal companion object {
        internal const val NAME = "google"
    }
}
