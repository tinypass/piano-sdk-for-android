package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ActiveMeter(
    @JvmField val meterName: String,
    @JvmField val views: Int,
    @JvmField val viewsLeft: Int,
    @JvmField val maxViews: Int,
    @JvmField val totalViews: Int
)
