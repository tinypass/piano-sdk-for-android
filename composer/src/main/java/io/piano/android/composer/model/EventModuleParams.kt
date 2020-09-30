package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EventModuleParams(
    @JvmField val moduleId: String,
    @JvmField val moduleName: String
)
