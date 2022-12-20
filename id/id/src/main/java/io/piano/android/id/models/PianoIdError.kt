package io.piano.android.id.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PianoIdError(
    val message: String
)
