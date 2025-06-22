package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class SessionStats(
    @Json(name = "hit_limit") public val hitLimit: Boolean,
    @Json(name = "session_count") public val sessionCount: Int,
    @Json(name = "session_limit") public val sessionLimit: Int,
)
