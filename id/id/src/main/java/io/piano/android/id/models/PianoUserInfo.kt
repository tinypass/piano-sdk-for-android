package io.piano.android.id.models

class PianoUserInfo(
    val formName: String,
) {
    internal val customFields = mutableMapOf<String, String>()
    fun customField(name: String, value: String) = apply { customFields[name] = value }
    fun customField(name: String, value: Boolean) = apply { customFields[name] = value.toString() }
    fun customField(name: String, value: Int) = apply { customFields[name] = value.toString() }
    fun customField(name: String, value: Double) = apply { customFields[name] = value.toString() }
    fun customField(name: String, value: Collection<String>) = apply {
        customFields[name] = value.joinToString(prefix = "[", postfix = "]") { """"$it"""" }
    }
}

internal fun PianoUserInfo.toProfileUpdateRequest() =
    ProfileUpdateRequest(
        formName,
        customFields.map {
            CustomField(fieldName = it.key, value = it.value)
        }
    )
