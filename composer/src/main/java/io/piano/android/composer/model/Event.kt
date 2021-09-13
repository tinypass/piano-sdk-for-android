package io.piano.android.composer.model

import io.piano.android.composer.model.events.EventType

data class Event<out T : EventType>(
    @JvmField val eventModuleParams: EventModuleParams,
    @JvmField val eventExecutionContext: EventExecutionContext,
    @JvmField val eventData: T
)
