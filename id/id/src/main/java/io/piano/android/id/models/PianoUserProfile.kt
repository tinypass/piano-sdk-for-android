package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
public class PianoUserProfile(
    public val email: String,
    public val uid: String,
    @Json(name = "first_name")
    public val firstName: String?,
    @Json(name = "last_name")
    public val lastName: String?,
    public val aid: String,
    public val updated: Date,
    public val token: String?,
    @Json(name = "linked_social_accounts")
    public val linkedSocialAccounts: List<String>?,
    @Json(name = "password_available")
    public val passwordAvailable: Boolean,
    @Json(name = "custom_field_values")
    public val customFields: List<CustomField>?,
    @Json(name = "has_all_custom_field_values_filled")
    public val allCustomFieldsFilled: Boolean?,
    @Json(name = "need_resend_confirmation_email")
    public val needResendConfirmationEmail: Boolean,
    @Json(name = "changed_email")
    public val changedEmail: Boolean,
    public val passwordless: Boolean,
)
