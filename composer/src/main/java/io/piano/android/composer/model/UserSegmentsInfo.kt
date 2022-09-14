package io.piano.android.composer.model

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
class UserSegmentsInfo(
    @JvmField val segments: List<String>,
    @JvmField val expiresAt: Date?
)
