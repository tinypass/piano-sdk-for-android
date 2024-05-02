package io.piano.android.composer.model

import com.squareup.moshi.JsonClass
import io.piano.android.composer.model.events.EventType

@JsonClass(generateAdapter = true)
public class EventsContainer(
    public val events: List<Event<EventType>>,
)
