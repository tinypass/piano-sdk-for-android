package io.piano.android.composer.model

import com.squareup.moshi.JsonClass
import io.piano.android.composer.model.events.EventType

@JsonClass(generateAdapter = true)
class EventsContainer(
    val events: List<Event<EventType>>
)
