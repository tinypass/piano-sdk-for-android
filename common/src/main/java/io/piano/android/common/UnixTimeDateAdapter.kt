package io.piano.android.common

import androidx.annotation.RestrictTo
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public object UnixTimeDateAdapter {
    @ToJson
    public fun toJson(date: Date): Long = date.time / 1000

    @FromJson
    public fun fromJson(time: Long): Date = Date(time * 1000)
}
