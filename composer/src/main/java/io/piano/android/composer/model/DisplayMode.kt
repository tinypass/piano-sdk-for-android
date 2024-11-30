package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
public enum class DisplayMode(
    public val mode: String,
) {
    @Json(name = "modal")
    MODAL("modal"),

    @Json(name = "inline")
    INLINE("inline"),
    ;

    override fun toString(): String = mode
}
