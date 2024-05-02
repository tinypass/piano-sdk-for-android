package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class UserSegmentsContainer(
    @JvmField
    @Json(name = "STANDARD")
    public val standard: UserSegmentsInfo?,
    @JvmField
    @Json(name = "COMPOSER1X")
    public val composer1x: UserSegmentsInfo?,
)
