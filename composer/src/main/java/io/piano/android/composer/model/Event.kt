package io.piano.android.composer.model

import io.piano.android.composer.model.events.EventType

public data class Event<out T : EventType>(
    @JvmField val eventModuleParams: EventModuleParams,
    @JvmField val eventExecutionContext: EventExecutionContext,
    @JvmField val eventData: T,
) {
    /**
     * Context of the experience execution, which returned this event. It's used for logging exposures.
     */
    internal var exposureContext: ExposureContext? = null
}
