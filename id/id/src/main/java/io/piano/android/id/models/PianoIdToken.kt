package io.piano.android.id.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.Date

@Parcelize
class PianoIdToken(
    @JvmField val accessToken: String,
    @JvmField val refreshToken: String = "",
    @JvmField val info: @RawValue Map<String, Any> = emptyMap(),
) : Parcelable {
    @IgnoredOnParcel
    @JvmField
    val expiresInTimestamp: Long = getInfoField("exp") ?: 0

    @IgnoredOnParcel
    @JvmField
    val emailConfirmationRequired: Boolean = getInfoField("email_confirmation_required") ?: false

    @IgnoredOnParcel
    @JvmField
    val expiresIn: Date = Date(expiresInTimestamp * 1000)

    @Suppress("UNCHECKED_CAST")
    fun <T> getInfoField(name: String): T? = info[name] as? T
}
