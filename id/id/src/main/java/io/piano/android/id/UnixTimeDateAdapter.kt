package io.piano.android.id

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

object UnixTimeDateAdapter {
    @ToJson
    fun toJson(date: Date): Long = date.time / 1000

    @FromJson
    fun fromJson(time: Long): Date = Date(time * 1000)
}
