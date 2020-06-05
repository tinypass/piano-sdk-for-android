package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import io.piano.android.composer.model.events.EventType;

@Keep
public class Event<T extends EventType> {
    @NonNull
    public EventModuleParams eventModuleParams;
    @NonNull
    public EventExecutionContext eventExecutionContext;
    @NonNull
    public T eventData;

    public Event(
            @NonNull EventModuleParams eventModuleParams,
            @NonNull EventExecutionContext eventExecutionContext,
            @NonNull T eventData
    ) {
        this.eventModuleParams = eventModuleParams;
        this.eventExecutionContext = eventExecutionContext;
        this.eventData = eventData;
    }
}
