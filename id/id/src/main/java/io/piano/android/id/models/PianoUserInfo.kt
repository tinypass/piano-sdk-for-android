package io.piano.android.id.models

public class PianoUserInfo(
    public val formName: String,
) {
    internal val customFields = mutableMapOf<String, String>()
    public fun customField(name: String, value: String): PianoUserInfo = apply { customFields[name] = value }
    public fun customField(name: String, value: Boolean): PianoUserInfo = apply {
        customFields[name] = value.toString()
    }
    public fun customField(name: String, value: Int): PianoUserInfo = apply { customFields[name] = value.toString() }
    public fun customField(name: String, value: Double): PianoUserInfo = apply { customFields[name] = value.toString() }
    public fun customField(name: String, value: Collection<String>): PianoUserInfo = apply {
        customFields[name] = value.joinToString(prefix = "[", postfix = "]") { """"$it"""" }
    }
}

internal fun PianoUserInfo.toProfileUpdateRequest() = ProfileUpdateRequest(
    formName,
    customFields.map {
        CustomField(fieldName = it.key, value = it.value)
    },
)
