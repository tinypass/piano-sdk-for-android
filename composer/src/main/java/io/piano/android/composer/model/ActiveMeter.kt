package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class ActiveMeter(
    @JvmField public val meterName: String,
    @JvmField public val views: Int,
    @JvmField public val viewsLeft: Int,
    @JvmField public val maxViews: Int,
    @JvmField public val totalViews: Int,
)
