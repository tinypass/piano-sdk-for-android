package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.UserSegment;

/**
 * Listener for {@link UserSegment} events
 */
public interface UserSegmentListener extends EventTypeListener<UserSegment> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof UserSegment;
    }
}
