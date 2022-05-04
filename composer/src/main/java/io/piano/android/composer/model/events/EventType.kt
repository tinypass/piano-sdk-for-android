package io.piano.android.composer.model.events

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.piano.android.composer.model.DelayBy
import io.piano.android.composer.model.DisplayMode
import io.piano.android.composer.model.User

sealed class EventType

interface BaseShowType {
    val containerSelector: String?
    val displayMode: DisplayMode
    val showCloseButton: Boolean
}

@JsonClass(generateAdapter = true)
data class ExperienceExecute(
    val user: User?
) : EventType()

@JsonClass(generateAdapter = true)
data class Meter(
    val meterName: String,
    val views: Int,
    val viewsLeft: Int,
    val maxViews: Int,
    val totalViews: Int,
    val incremented: Boolean,
    val state: MeterState = MeterState.ACTIVE
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
    val responseVariables: Map<String, Any>
) : EventType()

@JsonClass(generateAdapter = true)
data class ShowForm(
    val formName: String,
    val hideCompletedFields: Boolean,
    override val containerSelector: String,
    override val displayMode: DisplayMode,
    override val showCloseButton: Boolean,
) : EventType(), BaseShowType

@JsonClass(generateAdapter = true)
data class ShowLogin(
    val userProvider: String
) : EventType()

@JsonClass(generateAdapter = true)
data class ShowRecommendations(
    val widgetId: String,
    val placeholder: String?,
    override val containerSelector: String,
    override val displayMode: DisplayMode,
    override val showCloseButton: Boolean,
    val siteId: String,
    val type: String
) : EventType(), BaseShowType

@JsonClass(generateAdapter = true)
data class ShowTemplate(
    val templateId: String,
    val templateVariantId: String?,
    override val displayMode: DisplayMode,
    override val containerSelector: String?,
    val delayBy: DelayBy,
    override val showCloseButton: Boolean,
    val url: String? = null
) : EventType(), BaseShowType

data class UserSegment(
    val state: Boolean
) : EventType()
