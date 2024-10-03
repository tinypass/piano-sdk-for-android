package io.piano.android.composer.model.events

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.piano.android.composer.model.DelayBy
import io.piano.android.composer.model.DisplayMode
import io.piano.android.composer.model.User

/**
 * Represents various event types that can be used in the Composer SDK.
 *
 * The `EventType` class is a sealed class that defines different types of events that can be used in the
 * Composer SDK. Each event type is represented by a specific subclass of `EventType`, and additional data
 * may be associated with each event type.
 */
public sealed class EventType

/**
 * Interface representing common properties of events that involve showing content.
 */
public interface BaseShowType {
    public val containerSelector: String?
    public val displayMode: DisplayMode
    public val showCloseButton: Boolean
}

/**
 * Represents an event type to execute an experience.
 *
 * @property user The user associated with the experience execution.
 */
@JsonClass(generateAdapter = true)
public data class ExperienceExecute(
    val user: User?,
) : EventType()

/**
 * Represents an event type for a metered paywall view.
 *
 * @property meterName The name of the meter.
 * @property views The number of views made by the user.
 * @property viewsLeft The remaining number of views allowed for the meter.
 * @property maxViews The maximum number of views allowed for the meter.
 * @property totalViews The total number of views made during the meter's lifetime.
 * @property incremented A boolean flag indicating if the meter view count has been incremented.
 * @property state The state of the meter, either [MeterState.ACTIVE] or [MeterState.EXPIRED].
 */
@JsonClass(generateAdapter = true)
public data class Meter(
    val meterName: String,
    val views: Int,
    val viewsLeft: Int,
    val maxViews: Int,
    val totalViews: Int,
    val incremented: Boolean,
    val state: MeterState = MeterState.ACTIVE,
) : EventType() {
    /**
     * Enumeration representing the state of the meter.
     */
    @JsonClass(generateAdapter = false)
    public enum class MeterState {
        @Json(name = "active")
        ACTIVE,

        @Json(name = "expired")
        EXPIRED,
    }
}

/**
 * Represents an event type for non-site related events.
 */
public object NonSite : EventType()

/**
 * Represents an event type to set response variables.
 *
 * @property responseVariables The map of response variables to be set.
 */
@JsonClass(generateAdapter = true)
public data class SetResponseVariable(
    val responseVariables: Map<String, Any>,
) : EventType()

/**
 * Represents an event type to show a form.
 *
 * @property formName The name of the form to be shown.
 * @property hideCompletedFields A boolean flag indicating whether completed fields should be hidden in the form.
 * @property containerSelector The CSS selector of the container to display the form in.
 * @property displayMode The display mode for the form.
 * @property showCloseButton A boolean flag indicating whether to show a close button for the form.
 */
@JsonClass(generateAdapter = true)
public data class ShowForm(
    val formName: String,
    val hideCompletedFields: Boolean,
    override val containerSelector: String,
    override val displayMode: DisplayMode,
    override val showCloseButton: Boolean,
) : EventType(),
    BaseShowType

/**
 * Represents an event type to show a login prompt.
 *
 * @property userProvider The user provider for login.
 */
@JsonClass(generateAdapter = true)
public data class ShowLogin(
    val userProvider: String,
) : EventType()

/**
 * Represents an event type to show recommendations.
 *
 * @property widgetId The ID of the widget used for recommendations.
 * @property placeholder The placeholder string for recommendations.
 * @property containerSelector The CSS selector of the container to display the recommendations in.
 * @property displayMode The display mode for the recommendations.
 * @property showCloseButton A boolean flag indicating whether to show a close button for the recommendations.
 * @property siteId The ID of the site associated with the recommendations.
 * @property type The type of recommendations.
 */
@JsonClass(generateAdapter = true)
public data class ShowRecommendations(
    val widgetId: String,
    val placeholder: String?,
    override val containerSelector: String,
    override val displayMode: DisplayMode,
    override val showCloseButton: Boolean,
    val siteId: String,
    val type: String,
) : EventType(),
    BaseShowType

/**
 * Represents an event type to show a template.
 *
 * @property templateId The ID of the template to be shown.
 * @property templateVariantId The ID of the template variant to be shown, if applicable.
 * @property displayMode The display mode for the template.
 * @property containerSelector The CSS selector of the container to display the template in.
 * @property delayBy The delay time for showing the template.
 * @property showCloseButton A boolean flag indicating whether to show a close button for the template.
 * @property url The URL associated with the template, if applicable.
 */
@JsonClass(generateAdapter = true)
public data class ShowTemplate(
    val templateId: String,
    val templateVariantId: String?,
    override val displayMode: DisplayMode,
    override val containerSelector: String?,
    val delayBy: DelayBy,
    override val showCloseButton: Boolean,
    val url: String? = null,
) : EventType(),
    BaseShowType

/**
 * Represents an event type for user segmentation.
 *
 * @property state A boolean flag indicating the state of the user segmentation.
 */
public data class UserSegment(
    val state: Boolean,
) : EventType()
