package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CookieObject(
    @JvmField
    @Json(name = "cookie_value")
    val value: String,
)
