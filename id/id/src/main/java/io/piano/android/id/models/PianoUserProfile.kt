package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
class PianoUserProfile(
    val email: String,
    val uid: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val aid: String,
    val updated: Date,
    val token: String?,
    @Json(name = "linked_social_accounts")
    val linkedSocialAccounts: LinkedSocialAccountsData?,
    @Json(name = "custom_field_values")
    val customFields: List<CustomField>?,
    @Json(name = "has_all_custom_field_values_filled")
    val allCustomFieldsFilled: Boolean?,
    @Json(name = "need_resend_confirmation_email")
    val needResendConfirmationEmail: Boolean,
    @Json(name = "changed_email")
    val changedEmail: Boolean,
    val passwordless: Boolean
)
