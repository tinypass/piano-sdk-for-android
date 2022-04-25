package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LinkedSocialAccountsData(
    @Json(name = "facebook_linked")
    val facebookLinked: Boolean,
    @Json(name = "google_linked")
    val googleLinked: Boolean,
    @Json(name = "twitter_linked")
    val twitterLinked: Boolean,
    @Json(name = "linked_in_linked")
    val linkedInLinked: Boolean,
    @Json(name = "apple_linked")
    val appleLinked: Boolean,
    @Json(name = "password_available")
    val passwordAvailable: Boolean
)
