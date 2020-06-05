package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.Meter;

/**
 * Listener for {@link Meter} events
 */
public interface MeterListener extends EventTypeListener<Meter> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof Meter;
    }
}
