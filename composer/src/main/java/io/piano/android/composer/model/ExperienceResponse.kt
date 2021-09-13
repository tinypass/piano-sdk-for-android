package io.piano.android.composer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ExperienceResponse(
    @JvmField @Json(name = "tbc") val tbCookie: CookieObject?,
    @JvmField @Json(name = "xbc") val xbCookie: CookieObject?,
    @JvmField @Json(name = "tac") val taCookie: CookieObject?,
    @JvmField @Json(name = "timezone_offset") val timeZoneOffsetMillis: Int,
    @JvmField @Json(name = "visit_timeout") val visitTimeoutMinutes: Long?,
    @JvmField @Json(name = "uid") val userId: String?,
    @JvmField val cxenseCustomerPrefix: String?,
    @JvmField val result: EventsContainer
)
