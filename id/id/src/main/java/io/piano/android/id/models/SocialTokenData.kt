package io.piano.android.id.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SocialTokenData(
    val oauthProvider: String,
    val socialToken: String,
    val clientId: String
)
