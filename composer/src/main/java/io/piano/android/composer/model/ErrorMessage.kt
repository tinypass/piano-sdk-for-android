package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class ErrorMessage(
    @JvmField
    @Json(name = "msg")
    val message: String,
)
