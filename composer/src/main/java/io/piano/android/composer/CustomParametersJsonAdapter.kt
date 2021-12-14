package io.piano.android.composer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import io.piano.android.composer.model.CustomParameters

class CustomParametersJsonAdapter(
    private val mapAdapter: JsonAdapter<Map<String, List<String>>>
) : JsonAdapter<CustomParameters>() {
    override fun fromJson(reader: JsonReader): CustomParameters {
        TODO("Not yet implemented")
    }

    override fun toJson(writer: JsonWriter, value: CustomParameters?) {
        requireNotNull(value)
        writer.beginObject()
            .apply {
                value.content.writeIfNotEmpty(writer, "content")
                value.user.writeIfNotEmpty(writer, "user")
                value.request.writeIfNotEmpty(writer, "request")
            }.endObject()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Map<String, List<String>>.writeIfNotEmpty(writer: JsonWriter, name: String) =
        takeUnless { it.isEmpty() }?.let {
            writer.name(name)
            mapAdapter.toJson(writer, it)
        }
}
