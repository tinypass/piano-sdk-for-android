package io.piano.android.id.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse(
    val code: Int,
    val message: String?,
    @Json(name = "validation_errors") val validationErrors: Map<String, String>?
) {
    val hasError: Boolean = code != 0

    val error: String = validationErrors?.let {
        it[KEY_MESSAGE] ?: it.values.joinToString(separator = "; ")
    } ?: message.takeUnless { it.isNullOrEmpty() } ?: ERROR_TEMPLATE + code

    companion object {
        internal const val KEY_MESSAGE = "message"
        internal const val ERROR_TEMPLATE = "Error "
    }
}
