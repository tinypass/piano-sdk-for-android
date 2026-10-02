package io.piano.android.composer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

/**
 * Drops `null` values from JSON object before delegating, so default values of constructor parameters are used
 */
internal class SkipNullsJsonAdapter<T>(
    private val delegate: JsonAdapter<T>,
) : JsonAdapter<T>() {
    override fun fromJson(reader: JsonReader): T? = when (val value = reader.readJsonValue()) {
        is Map<*, *> -> delegate.fromJsonValue(value.filterValues { it != null })
        else -> delegate.fromJsonValue(value)
    }

    override fun toJson(writer: JsonWriter, value: T?) = delegate.toJson(writer, value)
}
