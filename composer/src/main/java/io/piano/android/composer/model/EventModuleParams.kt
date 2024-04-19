package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class EventModuleParams(
    @JvmField public val moduleId: String,
    @JvmField public val moduleName: String,
)
