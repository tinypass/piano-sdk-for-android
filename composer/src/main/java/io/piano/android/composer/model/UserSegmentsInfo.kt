package io.piano.android.composer.model

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
public class UserSegmentsInfo(
    @JvmField public val segments: List<String>,
    @JvmField public val expiresAt: Date?,
)
