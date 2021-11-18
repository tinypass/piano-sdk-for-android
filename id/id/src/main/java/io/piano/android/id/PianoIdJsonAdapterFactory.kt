package io.piano.android.id

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.piano.android.id.models.PianoIdToken
import java.lang.reflect.Type

class PianoIdJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        when (type) {
            PianoIdToken::class.java -> PianoIdTokenJsonAdapter(moshi)
            Any::class.java -> ObjectJsonAdapter(
                moshi.nextAdapter(this, Any::class.java, annotations),
                moshi.adapter(Long::class.java)
            )
            else -> null
        }
}
