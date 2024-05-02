package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class Data<T>(
    @Json(name = "models") val data: T,
    val errors: List<ErrorMessage>,
)
