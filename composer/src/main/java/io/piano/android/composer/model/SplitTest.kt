package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SplitTest(
    @JvmField val variantId: String,
    @JvmField val variantName: String
)
