package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.ShowTemplate;

/**
 * Listener for {@link ShowTemplate} events
 */
public interface ShowTemplateListener extends EventTypeListener<ShowTemplate> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof ShowTemplate;
    }
}
