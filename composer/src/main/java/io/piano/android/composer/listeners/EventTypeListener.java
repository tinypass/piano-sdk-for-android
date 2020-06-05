package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.events.EventType;

/**
 * Generic event listener
 *
 * @param <T> event type
 */
public interface EventTypeListener<T extends EventType> {
    /**
     * Processes event
     *
     * @param event event object
     */
    void onExecuted(@NonNull Event<T> event);

    /**
     * Asks is this event type supported by listener
     *
     * @param eventType type of event
     * @return True, if this listener can process this type of events, otherwise False. Default answer is False
     */
    default boolean canProcess(@NonNull EventType eventType) {
        return false;
    }
}
