package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class DisplayMode(val mode: String) {
    @Json(name = "modal")
    MODAL("modal"),

    @Json(name = "inline")
    INLINE("inline");

    override fun toString(): String {
        return mode
    }
}
