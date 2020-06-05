package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.ShowLogin;

/**
 * Listener for {@link ShowLogin} events
 */
public interface ShowLoginListener extends EventTypeListener<ShowLogin> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof ShowLogin;
    }
}
