package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HostResponse(
    @Json(name = "data") val host: String,
    code: Int,
    message: String?,
    validationErrors: Map<String, String>?
) : BaseResponse(code, message, validationErrors)
