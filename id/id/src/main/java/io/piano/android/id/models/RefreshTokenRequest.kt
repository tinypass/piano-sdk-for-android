package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RefreshTokenRequest(
    @Json(name = "client_id") val aid: String,
    @Json(name = "refresh_token") val refreshToken: String,
    @Json(name = "grant_type") val grantType: String = "refresh_token"
)
