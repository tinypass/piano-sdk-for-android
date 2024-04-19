package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class ProfileUpdateRequest(
    @Json(name = "form_name")
    val formName: String,
    @Json(name = "custom_field_values")
    val customFields: List<CustomField>,
)
