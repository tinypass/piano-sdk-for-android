package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class ExperienceResponse(
    @JvmField
    @Json(name = "tbc")
    public val tbCookie: CookieObject?,
    @JvmField
    @Json(name = "xbc")
    public val xbCookie: CookieObject?,
    @JvmField
    @Json(name = "tac")
    public val taCookie: CookieObject?,
    @JvmField
    @Json(name = "timezone_offset")
    public val timeZoneOffsetMillis: Int,
    @JvmField
    @Json(name = "visit_timeout")
    public val visitTimeoutMinutes: Long?,
    @JvmField
    @Json(name = "bid")
    public val browserId: String?,
    @JvmField
    @Json(name = "uid")
    public val userId: String?,
    @JvmField public val cxenseCustomerPrefix: String?,
    @JvmField public val result: EventsContainer,
)
