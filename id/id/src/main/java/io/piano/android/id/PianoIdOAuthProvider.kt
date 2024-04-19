package io.piano.android.id

import android.content.Context
import android.content.Intent
import android.os.Bundle

public interface PianoIdOAuthProvider {
    /**
     * Get OAuth-provider name
     *
     * @return OAuth-provider name
     */
    public val name: String

    /**
     * Gets intent for starting sign in process
     *
     * @param context Context for building instance
     * @param extras  Bundle, which will be added to intent before start. You can modify, if you want
     * @return `Intent` instance for starting
     */
    public fun buildIntent(context: Context, extras: Bundle): Intent
}
