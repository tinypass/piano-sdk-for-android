package io.piano.android.id;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface PianoIdOAuthProvider {

    /**
     * Get OAuth-provider name
     *
     * @return OAuth-provider name
     */
    @NonNull
    String getName();

    /**
     * Gets intent for starting sign in process
     *
     * @param context Context for building instance
     * @param extras  Bundle, which will be added to intent before start. You can modify, if you want
     * @return {@code Intent} instance for starting
     */
    @NonNull
    Intent buildIntent(@NonNull Context context, @NonNull Bundle extras);
}
