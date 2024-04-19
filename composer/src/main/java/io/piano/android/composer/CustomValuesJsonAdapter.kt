package io.piano.android.composer

import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

internal object CustomValuesJsonAdapter {
    @ToJson
    fun toJson(writer: JsonWriter, value: Map<String, @JvmSuppressWildcards List<String>?>?) {
        requireNotNull(value)
        writer.beginObject()
            .apply {
                value.filterValues {
                    it != null
                }.forEach { (k, v) ->
                    name(k)
                    simplifiedValue(v!!)
                }
            }.endObject()
    }

    private fun JsonWriter.simplifiedValue(values: List<String>) {
        if (values.size < 2) {
            value(values.firstOrNull())
        } else {
            beginArray().apply {
                values.forEach { value(it) }
            }.endArray()
        }
    }
}
