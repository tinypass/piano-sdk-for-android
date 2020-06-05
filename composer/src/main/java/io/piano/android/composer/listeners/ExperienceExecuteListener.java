package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.ExperienceExecute;

/**
 * Listener for {@link ExperienceExecute} events
 */
public interface ExperienceExecuteListener extends EventTypeListener<ExperienceExecute> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof ExperienceExecute;
    }
}
