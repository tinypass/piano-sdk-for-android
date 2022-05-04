package io.piano.android.showform

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class EventData(val event: String, val params: String? = null)
