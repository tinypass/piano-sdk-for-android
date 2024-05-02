package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class SplitTest(
    @JvmField public val variantId: String,
    @JvmField public val variantName: String,
)
