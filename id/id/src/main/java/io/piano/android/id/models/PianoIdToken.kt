package io.piano.android.id.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.Date

@Parcelize
public class PianoIdToken(
    @JvmField public val accessToken: String,
    @JvmField public val refreshToken: String = "",
    @JvmField public val info: @RawValue Map<String, Any> = emptyMap(),
) : Parcelable {
    @IgnoredOnParcel
    @JvmField
    public val expiresInTimestamp: Long = getInfoField("exp") ?: 0

    @IgnoredOnParcel
    @JvmField
    public val emailConfirmationRequired: Boolean = getInfoField("email_confirmation_required") ?: false

    @IgnoredOnParcel
    @JvmField
    public val expiresIn: Date = Date(expiresInTimestamp * 1000)

    @Suppress("UNCHECKED_CAST")
    public fun <T> getInfoField(name: String): T? = info[name] as? T
}
