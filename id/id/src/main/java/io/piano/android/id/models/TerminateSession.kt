package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class TerminateSession(
    @Json(name = "token") public val newToken: String,
    @Json(name = "session_count") public val sessionCount: Int,
    @Json(name = "session_limit") public val sessionLimit: String,
)
