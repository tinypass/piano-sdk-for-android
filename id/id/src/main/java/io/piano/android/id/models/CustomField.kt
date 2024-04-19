package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
public class CustomField(
    @Json(name = "field_name")
    public val fieldName: String,
    public val value: String?,
    public val created: Date? = null,
    @Json(name = "email_creator")
    public val emailCreator: String? = null,
    @Json(name = "sort_order")
    public val sortOrder: Long? = null,
)
