package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class DelayBy(
    @JvmField public val type: DelayType?,
    @JvmField public val value: Int,
) {
    @JsonClass(generateAdapter = false)
    public enum class DelayType {
        @Json(name = "time")
        TIME,

        @Json(name = "scroll")
        SCROLL,
    }

    public val isDelayedByTime: Boolean
        get() = type == DelayType.TIME && value > 0

    public val isDelayedByScroll: Boolean
        get() = type == DelayType.SCROLL && value > 0
}
