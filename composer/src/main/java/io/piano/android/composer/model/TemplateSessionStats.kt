package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class TemplateSessionStats(
    @Json(name = "hit_limit") val hitLimit: Boolean,
    @Json(name = "session_count") val sessionCount: Int,
    @Json(name = "session_limit") val sessionLimit: Int,
)

internal fun SessionStats.toTemplateSessionStats() = TemplateSessionStats(hitLimit, sessionCount, sessionLimit)
