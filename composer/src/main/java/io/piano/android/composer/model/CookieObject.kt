package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class CookieObject(
    @JvmField
    @Json(name = "cookie_value")
    public val value: String,
)
