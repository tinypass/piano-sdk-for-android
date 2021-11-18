package io.piano.android.id

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

internal class ObjectJsonAdapter(
    private val delegate: JsonAdapter<Any>,
    private val longAdapter: JsonAdapter<Long>
) : JsonAdapter<Any>() {
    override fun fromJson(reader: JsonReader): Any? {
        return if (reader.peek() == JsonReader.Token.NUMBER) {
            runCatching {
                longAdapter.fromJson(reader.peekJson()).also {
                    reader.skipValue()
                }
            }.recover {
                delegate.fromJson(reader)
            }.getOrNull()
        } else {
            delegate.fromJson(reader)
        }
    }

    override fun toJson(writer: JsonWriter, value: Any?) = delegate.toJson(writer, value)
}
