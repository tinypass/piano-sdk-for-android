package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Access(
    @JvmField
    @Json(name = "rid")
    val resourceId: String = "",
    @JvmField val resourceName: String = "",
    @JvmField val expireDate: Int,
    @JvmField val daysUntilExpiration: Int,
)
