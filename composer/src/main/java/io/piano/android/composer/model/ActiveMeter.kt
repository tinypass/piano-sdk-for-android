package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class ActiveMeter(
    @JvmField public val meterName: String,
    @JvmField public val views: Int,
    @JvmField public val viewsLeft: Int = 0,
    @JvmField public val maxViews: Int = 0,
    @JvmField public val totalViews: Int,
)
