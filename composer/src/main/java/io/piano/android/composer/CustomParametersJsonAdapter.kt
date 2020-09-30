package io.piano.android.composer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.composer.model.CustomParameters

class CustomParametersJsonAdapter(
    moshi: Moshi
) : JsonAdapter<CustomParameters>() {
    private val mapAdapter: JsonAdapter<Map<String, List<String>>> = moshi.adapter(
        Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            Types.newParameterizedType(List::class.java, String::class.java)
        )
    )

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

    companion object {
        val FACTORY = Factory { type, _, moshi ->
            takeIf { type == CustomParameters::class.java }?.let { CustomParametersJsonAdapter(moshi) }
        }
    }
}
