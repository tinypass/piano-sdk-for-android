package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TokenData(
    val iss: String,
    val exp: Long,
    @Json(name = "email_confirmation_required") val emailConfirmationRequired: Boolean = false
)
