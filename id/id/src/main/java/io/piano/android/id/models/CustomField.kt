package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
class CustomField(
    @Json(name = "field_name")
    val fieldName: String,
    val value: String,
    val created: Date,
    @Json(name = "email_creator")
    val emailCreator: String?,
    @Json(name = "sort_order")
    val sortOrder: Long
)
