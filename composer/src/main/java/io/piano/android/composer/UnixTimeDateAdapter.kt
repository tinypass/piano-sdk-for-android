package io.piano.android.composer

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

internal object UnixTimeDateAdapter {
    @ToJson
    fun toJson(date: Date): Long = date.time / 1000

    @FromJson
    fun fromJson(time: Long): Date = Date(time * 1000)
}
