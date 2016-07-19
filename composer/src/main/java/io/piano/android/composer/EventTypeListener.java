package io.piano.android.composer;

import io.piano.android.composer.model.Event;

interface EventTypeListener<T extends Event> {

    void onExecuted(T event);
}
