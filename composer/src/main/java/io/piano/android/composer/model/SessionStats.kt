package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class SessionStats(
    @Json(name = "hitLimit") public val hitLimit: Boolean,
    @Json(name = "sessionCount") public val sessionCount: Int,
    @Json(name = "sessionLimit") public val sessionLimit: Int,
)
