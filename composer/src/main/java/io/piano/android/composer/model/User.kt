package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @JvmField
    @Json(name = "uid")
    val userId: String,
    @JvmField val firstName: String?,
    @JvmField val lastName: String?,
    @JvmField val email: String,
)
