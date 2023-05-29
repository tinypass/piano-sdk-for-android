package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserSegmentsContainer(
    @JvmField
    @Json(name = "STANDARD")
    val standard: UserSegmentsInfo?,
    @JvmField
    @Json(name = "COMPOSER1X")
    val composer1x: UserSegmentsInfo?
)
