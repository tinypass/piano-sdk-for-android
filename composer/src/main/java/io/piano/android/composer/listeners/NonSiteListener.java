package io.piano.android.composer.listeners;

import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.NonSite;

/**
 * Listener for {@link NonSite} events
 */
public interface NonSiteListener extends EventTypeListener<NonSite> {
    @Override
    default boolean canProcess(@NonNull EventType eventType) {
        return eventType instanceof NonSite;
    }
}
