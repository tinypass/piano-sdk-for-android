package io.piano.android.composer.model

/**
 * Represents custom parameters that can be used to customize and enhance Composer requests.
 *
 * The `CustomParameters` class allows you to set custom parameters in three categories: content, user,
 * and request. These custom parameters can be used to tailor Composer requests and influence the behavior
 * of the responses.
 *
 * @property content A mutable map of custom parameters specific to content.
 * @property user A mutable map of custom parameters specific to the user.
 * @property request A mutable map of custom parameters specific to the request.
 */
public class CustomParameters {
    internal val content = mutableMapOf<String, MutableList<String>>()
    internal val user = mutableMapOf<String, MutableList<String>>()
    internal val request = mutableMapOf<String, MutableList<String>>()

    /**
     * Checks whether the custom parameters are empty.
     *
     * @return `true` if all custom parameter categories (content, user, and request) are empty,
     *         `false` otherwise.
     */
    public fun isEmpty(): Boolean = content.isEmpty() && user.isEmpty() && request.isEmpty()

    /**
     * Adds a custom parameter to the content category.
     *
     * @param key The key of the custom parameter.
     * @param value The value of the custom parameter.
     * @return The updated `CustomParameters` instance.
     */
    public fun content(key: String, value: String): CustomParameters = apply { content.putValueForKey(key, value) }

    /**
     * Adds a custom parameter to the user category.
     *
     * @param key The key of the custom parameter.
     * @param value The value of the custom parameter.
     * @return The updated `CustomParameters` instance.
     */
    public fun user(key: String, value: String): CustomParameters = apply { user.putValueForKey(key, value) }

    /**
     * Adds a custom parameter to the request category.
     *
     * @param key The key of the custom parameter.
     * @param value The value of the custom parameter.
     * @return The updated `CustomParameters` instance.
     */
    public fun request(key: String, value: String): CustomParameters = apply { request.putValueForKey(key, value) }

    /**
     * Internal function to add a key-value pair to a mutable map of strings to mutable lists of strings.
     * If the key does not exist in the map, a new list is created and associated with the key.
     *
     * @param key The key of the custom parameter.
     * @param value The value of the custom parameter.
     */
    private fun MutableMap<String, MutableList<String>>.putValueForKey(key: String, value: String) {
        val values = get(key) ?: mutableListOf<String>().also {
            put(key, it)
        }
        values.add(value)
    }
}
