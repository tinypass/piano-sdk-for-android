package android.util

/**
 * implementation for tests
 */
@Suppress("unused")
open class SparseArray<E> {
    private val map = mutableMapOf<Int, E>()

    fun append(key: Int, value: E) = put(key, value)

    fun delete(key: Int) {
        map.remove(key)
    }

    fun put(key: Int, value: E) {
        map[key] = value
    }

    operator fun get(key: Int): E? {
        return map[key]
    }

    fun get(key: Int, defaultValue: E): E {
        return get(key) ?: defaultValue
    }
}
