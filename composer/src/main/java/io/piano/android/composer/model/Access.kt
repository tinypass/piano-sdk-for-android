package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class Access(
    @JvmField
    @Json(name = "rid")
    public val resourceId: String = "",
    @JvmField public val resourceName: String = "",
    @JvmField public val expireDate: Int,
    @JvmField public val daysUntilExpiration: Int,
)
