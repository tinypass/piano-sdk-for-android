package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DelayBy(
    @JvmField val type: DelayType?,
    @JvmField val value: Int
) {
    @JsonClass(generateAdapter = false)
    enum class DelayType {
        @Json(name = "time") TIME,
        @Json(name = "scroll") SCROLL
    }

    val isDelayedByTime: Boolean
        get() = type == DelayType.TIME && value > 0

    val isDelayedByScroll: Boolean
        get() = type == DelayType.SCROLL && value > 0
}
