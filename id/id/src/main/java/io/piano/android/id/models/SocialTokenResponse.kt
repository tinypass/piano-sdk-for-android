package io.piano.android.id.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SocialTokenResponse(
    val oauthProvider: String,
    val code: String,
    val clientId: String
)
