package io.piano.android.id.models

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
class PianoIdToken(
    @JvmField val accessToken: String,
    @JvmField val refreshToken: String = "",
    @JvmField val expiresInTimestamp: Long = 0
) : Parcelable {
    @IgnoredOnParcel
    @JvmField
    val expiresIn: Date = Date(expiresInTimestamp * 1000)
}
