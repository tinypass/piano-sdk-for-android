package io.piano.android.composer.model

class CustomParameters {
    val content = mutableMapOf<String, MutableList<String>>()
    val user = mutableMapOf<String, MutableList<String>>()
    val request = mutableMapOf<String, MutableList<String>>()

    fun isEmpty(): Boolean = content.isEmpty() && user.isEmpty() && request.isEmpty()

    fun content(key: String, value: String): CustomParameters =
        this.also {
            content.putValueForKey(key, value)
        }

    fun user(key: String, value: String): CustomParameters =
        this.also {
            content.putValueForKey(key, value)
        }

    fun request(key: String, value: String): CustomParameters =
        this.also {
            content.putValueForKey(key, value)
        }

    private fun MutableMap<String, MutableList<String>>.putValueForKey(key: String, value: String) {
        val values = get(key) ?: mutableListOf<String>().also {
            put(key, it)
        }
        values.add(value)
    }
}
