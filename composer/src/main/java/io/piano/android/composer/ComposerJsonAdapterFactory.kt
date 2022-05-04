package io.piano.android.composer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.composer.model.CustomParameters
import java.lang.reflect.Type

class ComposerJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        when (type) {
            CustomParameters::class.java -> CustomParametersJsonAdapter(
                moshi.adapter(
                    Types.newParameterizedType(
                        Map::class.java,
                        String::class.java,
                        Types.newParameterizedType(List::class.java, String::class.java)
                    )
                )
            )
            Any::class.java -> ObjectJsonAdapter(
                moshi.nextAdapter(this, Any::class.java, annotations),
                moshi.adapter(Long::class.java)
            )
            else -> null
        }
}
