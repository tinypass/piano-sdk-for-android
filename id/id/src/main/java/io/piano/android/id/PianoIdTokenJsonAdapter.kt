package io.piano.android.id

import android.util.Base64
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.internal.Util
import io.piano.android.id.models.PianoIdToken

class PianoIdTokenJsonAdapter(
    moshi: Moshi
) : JsonAdapter<PianoIdToken>() {
    private val options: JsonReader.Options = JsonReader.Options.of(
        ACCESS_TOKEN,
        ACCESS_TOKEN_CAMEL,
        REFRESH_TOKEN,
        REFRESH_TOKEN_CAMEL
    )

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)

    private val longAdapter: JsonAdapter<Long> by lazy { moshi.adapter(Long::class.java) }

    private val jwtAdapter: JsonAdapter<Map<String, Any>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                String::class.java,
                Any::class.java
            )
        )
    }

    override fun fromJson(reader: JsonReader): PianoIdToken {
        var accessToken: String? = null
        var refreshToken: String? = null
        return with(reader) {
            beginObject()
            while (hasNext()) {
                when (selectName(options)) {
                    0, 1 ->
                        accessToken = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                            ACCESS_TOKEN_CAMEL,
                            ACCESS_TOKEN,
                            reader
                        )
                    2, 3 ->
                        refreshToken = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                            REFRESH_TOKEN_CAMEL,
                            REFRESH_TOKEN,
                            reader
                        )
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            endObject()
            val jwtFields = jwtAdapter.fromJson(
                Base64.decode(accessToken!!.split("\\.".toRegex())[1], Base64.URL_SAFE).decodeToString()
            )
            PianoIdToken(
                accessToken ?: throw Util.missingProperty(ACCESS_TOKEN_CAMEL, ACCESS_TOKEN, reader),
                refreshToken ?: "",
                jwtFields ?: emptyMap()
            )
        }
    }

    override fun toJson(writer: JsonWriter, value: PianoIdToken?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.apply {
            beginObject()
                .name(ACCESS_TOKEN_CAMEL)
            stringAdapter.toJson(this, value.accessToken)
            name(REFRESH_TOKEN_CAMEL)
            stringAdapter.toJson(this, value.refreshToken)
            name(EXPIRES_IN_CAMEL)
            longAdapter.toJson(this, value.expiresInTimestamp)
            endObject()
        }
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val ACCESS_TOKEN_CAMEL = "accessToken"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val REFRESH_TOKEN_CAMEL = "refreshToken"
        private const val EXPIRES_IN_CAMEL = "expiresIn"

        @JvmField
        @Deprecated(
            "Use PianoIdJsonAdapterFactory directly, will be removed in future versions",
            ReplaceWith("PianoIdJsonAdapterFactory()")
        )
        val FACTORY = PianoIdJsonAdapterFactory()
    }
}
