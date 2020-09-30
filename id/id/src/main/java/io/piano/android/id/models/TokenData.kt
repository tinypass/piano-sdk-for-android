package io.piano.android.id.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TokenData(
    val iss: String,
    val exp: Long
)
