package io.piano.android.composer.model.events

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.piano.android.composer.model.DelayBy
import io.piano.android.composer.model.User

sealed class EventType

@JsonClass(generateAdapter = true)
data class ExperienceExecute(
    @JvmField val user: User?
) : EventType()

@JsonClass(generateAdapter = true)
data class Meter(
    @JvmField val meterName: String,
    @JvmField val views: Int,
    @JvmField val viewsLeft: Int,
    @JvmField val maxViews: Int,
    @JvmField val totalViews: Int,
    @JvmField val incremented: Boolean,
    @JvmField val state: MeterState = MeterState.ACTIVE
) : EventType() {
    @JsonClass(generateAdapter = false)
    enum class MeterState {
        @Json(name = "active") ACTIVE,
        @Json(name = "expired") EXPIRED
    }
}

object NonSite : EventType()

@JsonClass(generateAdapter = true)
data class SetResponseVariable(
    @JvmField val responseVariables: Map<String, Any>
) : EventType()

@JsonClass(generateAdapter = true)
data class ShowLogin(
    @JvmField val userProvider: String
) : EventType()

@JsonClass(generateAdapter = true)
data class ShowTemplate(
    @JvmField val templateId: String,
    @JvmField val templateVariantId: String?,
    @JvmField val displayMode: DisplayMode,
    @JvmField val containerSelector: String?,
    @JvmField val delayBy: DelayBy,
    @JvmField val showCloseButton: Boolean,
    @JvmField val url: String? = null
) : EventType() {
    @JsonClass(generateAdapter = false)
    enum class DisplayMode(val mode: String) {
        @Json(name = "modal")
        MODAL("modal"),

        @Json(name = "inline")
        INLINE("inline");

        override fun toString(): String {
            return mode
        }
    }
}

data class UserSegment(
    @JvmField val state: Boolean
) : EventType()
