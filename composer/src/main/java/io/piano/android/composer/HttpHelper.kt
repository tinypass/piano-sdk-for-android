package io.piano.android.composer

import androidx.annotation.RestrictTo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.composer.model.ActiveMeter
import io.piano.android.composer.model.CustomParameters
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.ShowTemplate
import java.util.Calendar
import java.util.concurrent.TimeUnit

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class HttpHelper(
    private val experienceIdsProvider: ExperienceIdsProvider,
    private val prefsStorage: PrefsStorage,
    moshi: Moshi,
    private val userAgent: String
) {
    private val mapAdapter: JsonAdapter<Map<String, String?>> = moshi.adapter(
        Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            String::class.java
        )
    )

    private val activeMetersAdapter = moshi.adapter<List<ActiveMeter>>(
        Types.newParameterizedType(
            List::class.java,
            ActiveMeter::class.java
        )
    )

    private val customParametersAdapter = moshi.adapter(CustomParameters::class.java)

    internal fun convertExperienceRequest(
        request: ExperienceRequest,
        aid: String,
        userToken: String?
    ): Map<String, String> =
        with(request) {
            val calendar = Calendar.getInstance()
            val offset = TimeUnit.MILLISECONDS.toMinutes(calendar.timeZone.getOffset(calendar.timeInMillis).toLong())
            val date = calendar.time
            toMinimalSequence() + sequenceOf(
                PARAM_AID to aid,
                PARAM_USER_AGENT to userAgent,
                PARAM_PROTOCOL_VERSION to VALUE_PROTOCOL_VERSION.toString(),
                PARAM_TIMEZONE_OFFSET to offset.toString(),
                PARAM_PAGEVIEW_ID to experienceIdsProvider.getPageViewId(date),
                PARAM_VISIT_ID to experienceIdsProvider.getVisitId(date),
                PARAM_NEW_VISIT to experienceIdsProvider.isVisitIdGenerated.toString(),
                PARAM_SUBMIT_TYPE to VALUE_MANUAL_SUBMIT_TYPE,
                PARAM_SDK_VERSION to BuildConfig.VERSION_NAME,
                PARAM_XBUILDER_BROWSER_COOKIE to prefsStorage.xbuilderBrowserCookie.orEmpty(),
                PARAM_TP_BROWSER_COOKIE to prefsStorage.tpBrowserCookie.orEmpty(),
                PARAM_TP_ACCESS_COOKIE to prefsStorage.tpAccessCookie.orEmpty(),
                PARAM_USER_TOKEN to userToken.orEmpty(),
                PARAM_URL to url.orEmpty(),
                PARAM_REFERRER to referer.orEmpty(),
                PARAM_CONTENT_CREATED to contentCreated.orEmpty(),
                PARAM_CONTENT_NATIVE to (contentIsNative?.toString() ?: ""),
                PARAM_CUSTOM_PARAMS to customParameters?.takeUnless { it.isEmpty() }
                    ?.let { customParametersAdapter.toJson(it) }
                    .orEmpty()
            ).filterNotEmptyValues()
        }.toMap()

    internal fun processExperienceResponse(response: ExperienceResponse) =
        with(response) {
            xbCookie?.let { prefsStorage.xbuilderBrowserCookie = it.value }
            tbCookie?.let { prefsStorage.tpBrowserCookie = it.value }
            taCookie?.let { prefsStorage.tpAccessCookie = it.value }
            visitTimeoutMinutes?.let { prefsStorage.visitTimeout = TimeUnit.MILLISECONDS.convert(it, TimeUnit.MINUTES) }
            prefsStorage.serverTimezoneOffset = timeZoneOffsetMillis
        }

    internal fun buildEventTracking(trackingId: String): Map<String, String> =
        mapOf(
            PARAM_EVENT_TRACKING_ID to trackingId,
            PARAM_EVENT_TYPE to VALUE_EXTERNAL_EVENT_TYPE,
            PARAM_EVENT_GROUP_ID to VALUE_CLOSE_GROUP_ID
        )

    internal fun buildShowTemplateParameters(
        showTemplateEvent: Event<ShowTemplate>,
        experienceRequest: ExperienceRequest,
        aid: String,
        userToken: String?,
        gaClientId: String?
    ): Map<String, String> =
        with(showTemplateEvent) {
            experienceRequest.toMinimalSequence() + sequenceOf(
                PARAM_AID to aid,
                PARAM_USER_TOKEN to userToken.orEmpty(),
                PARAM_GA_CLIENT_ID to gaClientId.orEmpty(),
                PARAM_OS to VALUE_ANDROID_OS,
                PARAM_DISPLAY_MODE to ShowTemplate.DisplayMode.INLINE.mode,
                PARAM_SHOW_TEMPLATE_TRACKING_ID to eventExecutionContext.trackingId,
                PARAM_TEMPLATE_ID to eventData.templateId,
                PARAM_TEMPLATE_VARIANT_ID to eventData.templateVariantId.orEmpty(),
                PARAM_ACTIVE_METERS to eventExecutionContext.activeMeters.takeUnless { it.isNullOrEmpty() }
                    ?.let { activeMetersAdapter.toJson(it) }.orEmpty()
            ).filterNotEmptyValues()
        }.toMap()

    private fun ExperienceRequest.toMinimalSequence(): Sequence<Pair<String, String>> =
        sequenceOf(
            PARAM_DEBUG to isDebug.toString(),
            PARAM_URL to url.orEmpty(),
            PARAM_CONTENT_AUTHOR to contentAuthor.orEmpty(),
            PARAM_CONTENT_SECTION to contentSection.orEmpty(),
            PARAM_ZONE to zone.orEmpty(),
            PARAM_CUSTOM_VARIABLES to customVariables.takeUnless { it.isEmpty() }
                ?.let { mapAdapter.toJson(it) }
                .orEmpty(),
            PARAM_TAGS to tags.takeUnless { it.isEmpty() }
                ?.joinToString(separator = ",")
                .orEmpty()
        ).filterNotEmptyValues()

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Sequence<Pair<String, String>>.filterNotEmptyValues(): Sequence<Pair<String, String>> =
        filter { it.second.isNotEmpty() }

    companion object {
        // Experience request constants
        internal const val PARAM_AID = "aid"
        internal const val PARAM_DEBUG = "debug"
        internal const val PARAM_USER_AGENT = "user_agent"
        internal const val PARAM_PROTOCOL_VERSION = "protocol_version"
        internal const val PARAM_TIMEZONE_OFFSET = "timezone_offset"
        internal const val PARAM_PAGEVIEW_ID = "pageview_id"
        internal const val PARAM_VISIT_ID = "visit_id"
        internal const val PARAM_NEW_VISIT = "new_visit"
        internal const val PARAM_SUBMIT_TYPE = "submit_type"
        internal const val PARAM_SDK_VERSION = "sdk_version"
        internal const val PARAM_XBUILDER_BROWSER_COOKIE = "xbc"
        internal const val PARAM_TP_BROWSER_COOKIE = "tbc"
        internal const val PARAM_TP_ACCESS_COOKIE = "tac"
        internal const val PARAM_CUSTOM_VARIABLES = "custom_variables"
        internal const val PARAM_USER_TOKEN = "user_token"
        internal const val PARAM_URL = "url"
        internal const val PARAM_REFERRER = "referer"
        internal const val PARAM_TAGS = "tags"
        internal const val PARAM_ZONE = "zone"
        internal const val PARAM_CONTENT_CREATED = "content_created"
        internal const val PARAM_CONTENT_AUTHOR = "content_author"
        internal const val PARAM_CONTENT_SECTION = "content_section"
        internal const val PARAM_CONTENT_NATIVE = "content_is_native"
        internal const val PARAM_CUSTOM_PARAMS = "custom_params"

        private const val VALUE_PROTOCOL_VERSION = 1
        internal const val VALUE_MANUAL_SUBMIT_TYPE = "manual"

        // External events tracking constants
        internal const val PARAM_EVENT_TRACKING_ID = "tracking_id"
        internal const val PARAM_EVENT_TYPE = "event_type"
        internal const val PARAM_EVENT_GROUP_ID = "event_group_id"

        internal const val VALUE_EXTERNAL_EVENT_TYPE = "EXTERNAL_EVENT"
        internal const val VALUE_CLOSE_GROUP_ID = "close"

        // Show template url constants
        internal const val PARAM_TEMPLATE_ID = "templateId"
        internal const val PARAM_TEMPLATE_VARIANT_ID = "templateVariantId"
        internal const val PARAM_ACTIVE_METERS = "activeMeters"
        internal const val PARAM_DISPLAY_MODE = "displayMode"
        internal const val PARAM_SHOW_TEMPLATE_TRACKING_ID = "trackingId"
        internal const val PARAM_GA_CLIENT_ID = "gaClientId"
        internal const val PARAM_OS = "os"

        internal const val VALUE_ANDROID_OS = "android"
    }
}
