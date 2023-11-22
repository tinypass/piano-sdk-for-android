package io.piano.android.composer

import androidx.annotation.RestrictTo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.composer.model.ActiveMeter
import io.piano.android.composer.model.CustomParameters
import io.piano.android.composer.model.DisplayMode
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.consents.models.Consent
import io.piano.android.consents.models.Product
import io.piano.android.consents.models.Purpose
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class HttpHelper(
    private val experienceIdsProvider: ExperienceIdsProvider,
    private val prefsStorage: PrefsStorage,
    moshi: Moshi,
    private val userAgent: String,
) : ExperienceInterceptor {
    private val mapAdapter: JsonAdapter<Map<String, String>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                String::class.java,
                String::class.java
            )
        )
    }

    private val customVariablesAdapter: JsonAdapter<Map<String, List<String>?>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                String::class.java,
                Types.newParameterizedType(
                    List::class.java,
                    String::class.java
                )
            )
        )
    }

    private val activeMetersAdapter by lazy {
        moshi.adapter<List<ActiveMeter>>(
            Types.newParameterizedType(
                List::class.java,
                ActiveMeter::class.java
            )
        )
    }

    private val customParametersAdapter by lazy {
        moshi.adapter(CustomParameters::class.java)
    }

    private val consentModesAdapter: JsonAdapter<Map<Int, Int>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                Int::class.javaObjectType,
                Int::class.javaObjectType
            )
        )
    }

    private val consentPurposesAdapter: JsonAdapter<Map<Int, Purpose>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                Int::class.javaObjectType,
                Purpose::class.java
            )
        )
    }

    private val vxConsentAdapter: JsonAdapter<Map<Purpose, Consent>> by lazy {
        moshi.adapter(
            Types.newParameterizedType(
                Map::class.java,
                Purpose::class.java,
                Consent::class.java
            )
        )
    }

    internal fun convertExperienceRequest(
        request: ExperienceRequest,
        aid: String,
        browserIdProvider: () -> String?,
        userToken: String?,
        consents: Map<Purpose, Consent>,
        productsToPurposesMapping: Map<Product, Purpose>,
    ): Map<String, String> = with(request) {
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
            PARAM_SDK_VERSION to BuildConfig.SDK_VERSION,
            PARAM_EDGE_RESULT_COOKIE to edgeResult?.pcer.orEmpty(),
            PARAM_XBUILDER_BROWSER_COOKIE to edgeResult?.xbc.ifNullOrEmpty { prefsStorage.xbuilderBrowserCookie },
            PARAM_TP_BROWSER_COOKIE to edgeResult?.tbc.ifNullOrEmpty { prefsStorage.tpBrowserCookie },
            PARAM_TP_ACCESS_COOKIE to prefsStorage.tpAccessCookie,
            PARAM_USER_TOKEN to userToken.orEmpty(),
            PARAM_NEW_BID to browserIdProvider().orEmpty(),
            PARAM_REFERRER to referer.orEmpty(),
            PARAM_TITLE to title.orEmpty(),
            PARAM_DESCRIPTION to description.orEmpty(),
            PARAM_CONTENT_ID to contentId.orEmpty(),
            PARAM_CONTENT_TYPE to contentType.orEmpty(),
            PARAM_CONTENT_AUTHOR to contentAuthor.orEmpty(),
            PARAM_CONTENT_SECTION to contentSection.orEmpty(),
            PARAM_CONTENT_CREATED to contentCreated.orEmpty(),
            PARAM_CONTENT_NATIVE to (contentIsNative?.toString() ?: ""),
            PARAM_KEYWORDS to keywords.takeUnless { it.isEmpty() }
                ?.joinToString(separator = ",")
                .orEmpty(),
            PARAM_CUSTOM_VARIABLES to customVariables.takeUnless { it.isEmpty() }
                ?.let { customVariablesAdapter.toJson(it) }
                .orEmpty(),
            PARAM_CUSTOM_PARAMS to customParameters?.takeUnless { it.isEmpty() }
                ?.let { customParametersAdapter.toJson(it) }
                .orEmpty(),
            PARAM_CONSENT_MODES to consents.values
                .flatMap { c ->
                    c.products.map {
                        it.id to c.mode.id
                    }
                }.toMap()
                .takeUnless { it.isEmpty() }
                ?.let { consentModesAdapter.toJson(it) }
                .orEmpty(),
            PARAM_CONSENT_PURPOSES to productsToPurposesMapping.mapKeys { it.key.id }.takeUnless { it.isEmpty() }
                ?.let { consentPurposesAdapter.toJson(it) }
                .orEmpty()
        ).filterNotEmptyValues()
    }.toMap()

    override fun afterExecute(request: ExperienceRequest, response: ExperienceResponse) = with(response) {
        prefsStorage.xbuilderBrowserCookie = xbCookie?.value.orEmpty()
        prefsStorage.tpBrowserCookie = tbCookie?.value.orEmpty()
        taCookie?.value.takeUnless { it.isNullOrEmpty() }?.also { prefsStorage.tpAccessCookie = it }
        visitTimeoutMinutes?.let { prefsStorage.visitTimeout = TimeUnit.MILLISECONDS.convert(it, TimeUnit.MINUTES) }
        prefsStorage.serverTimezoneOffset = timeZoneOffsetMillis
    }

    internal fun buildEventTracking(
        trackingId: String,
        eventType: String,
        eventGroup: String,
        consents: Map<Purpose, Consent>,
        customParameters: Map<String, String> = emptyMap(),
    ): Map<String, String> = mapOf(
        PARAM_EVENT_TRACKING_ID to trackingId,
        PARAM_EVENT_TYPE to eventType,
        PARAM_EVENT_GROUP_ID to eventGroup,
        PARAM_EVENT_CUSTOM_PARAMS to customParameters.takeUnless { it.isEmpty() }
            ?.let { mapAdapter.toJson(it) }.orEmpty(),
        PARAM_EVENT_COOKIE_CONSENTS to consents.takeUnless { it.isEmpty() }
            ?.let { vxConsentAdapter.toJson(it) }.orEmpty()
    )

    internal fun buildCustomFormTracking(
        aid: String,
        customFormName: String,
        trackingId: String,
        userToken: String?,
        consents: Map<Purpose, Consent>,
    ): Map<String, String> = sequenceOf(
        PARAM_AID to aid,
        PARAM_USER_TOKEN to userToken.orEmpty(),
        PARAM_PAGEVIEW_ID to experienceIdsProvider.getPageViewId(Date()),
        PARAM_EVENT_TRACKING_ID to trackingId,
        PARAM_CUSTOM_FORM_NAME to customFormName,
        PARAM_EVENT_COOKIE_CONSENTS to consents.takeUnless { it.isEmpty() }
            ?.let { vxConsentAdapter.toJson(it) }.orEmpty()
    ).filterNotEmptyValues().toMap()

    internal fun buildShowTemplateParameters(
        showTemplateEvent: Event<ShowTemplate>,
        experienceRequest: ExperienceRequest,
        aid: String,
        userToken: String?,
        gaClientId: String?,
        consents: Map<Purpose, Consent>,
    ): Map<String, String> = with(showTemplateEvent) {
        experienceRequest.toMinimalSequence() + sequenceOf(
            PARAM_AID to aid,
            PARAM_SHOW_TEMPLATE_USER_TOKEN to userToken.orEmpty(),
            PARAM_GA_CLIENT_ID to gaClientId.orEmpty(),
            PARAM_OS to VALUE_ANDROID_OS,
            PARAM_DISPLAY_MODE to DisplayMode.INLINE.mode,
            PARAM_TP_BROWSER_COOKIE to prefsStorage.tpBrowserCookie.orEmpty(),
            PARAM_SHOW_CLOSE_BUTTON to eventData.showCloseButton.toString(),
            PARAM_SHOW_TEMPLATE_TRACKING_ID to eventExecutionContext.trackingId,
            PARAM_SHOW_TEMPLATE_CONTENT_AUTHOR to experienceRequest.contentAuthor.orEmpty(),
            PARAM_SHOW_TEMPLATE_CONTENT_SECTION to experienceRequest.contentSection.orEmpty(),
            PARAM_SHOW_TEMPLATE_CUSTOM_VARIABLES to experienceRequest.customVariables.takeUnless { it.isEmpty() }
                ?.let { customVariablesAdapter.toJson(it) }
                .orEmpty(),
            PARAM_TEMPLATE_ID to eventData.templateId,
            PARAM_TEMPLATE_VARIANT_ID to eventData.templateVariantId.orEmpty(),
            PARAM_ACTIVE_METERS to eventExecutionContext.activeMeters.takeUnless { it.isNullOrEmpty() }
                ?.let { activeMetersAdapter.toJson(it) }.orEmpty(),
            PARAM_EVENT_COOKIE_CONSENTS to consents.takeUnless { it.isEmpty() }
                ?.let { vxConsentAdapter.toJson(it) }.orEmpty()
        ).filterNotEmptyValues()
    }.toMap()

    private fun ExperienceRequest.toMinimalSequence(): Sequence<Pair<String, String>> = sequenceOf(
        PARAM_DEBUG to isDebug.toString(),
        PARAM_URL to url.orEmpty(),
        PARAM_ZONE to zone.orEmpty(),
        PARAM_TAGS to tags.takeUnless { it.isEmpty() }
            ?.joinToString(separator = ",")
            .orEmpty()
    ).filterNotEmptyValues()

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Sequence<Pair<String, String>>.filterNotEmptyValues(): Sequence<Pair<String, String>> =
        filter { it.second.isNotEmpty() }

    private inline fun String?.ifNullOrEmpty(defaultValue: () -> String): String =
        if (isNullOrEmpty()) defaultValue() else this

    companion object {
        // Experience request constants
        internal const val PARAM_AID = "aid"
        internal const val PARAM_NEW_BID = "new_bid"
        internal const val PARAM_DEBUG = "debug"
        internal const val PARAM_USER_AGENT = "user_agent"
        internal const val PARAM_PROTOCOL_VERSION = "protocol_version"
        internal const val PARAM_TIMEZONE_OFFSET = "timezone_offset"
        internal const val PARAM_PAGEVIEW_ID = "pageview_id"
        internal const val PARAM_VISIT_ID = "visit_id"
        internal const val PARAM_NEW_VISIT = "new_visit"
        internal const val PARAM_SUBMIT_TYPE = "submit_type"
        internal const val PARAM_SDK_VERSION = "sdk_version"
        internal const val PARAM_EDGE_RESULT_COOKIE = "edge_result"
        internal const val PARAM_XBUILDER_BROWSER_COOKIE = "xbc"
        internal const val PARAM_TP_BROWSER_COOKIE = "tbc"
        internal const val PARAM_TP_ACCESS_COOKIE = "tac"
        internal const val PARAM_CUSTOM_VARIABLES = "custom_variables"
        internal const val PARAM_USER_TOKEN = "user_token"
        internal const val PARAM_URL = "url"
        internal const val PARAM_REFERRER = "referer"
        internal const val PARAM_TAGS = "tags"
        internal const val PARAM_KEYWORDS = "keywords"
        internal const val PARAM_ZONE = "zone"
        internal const val PARAM_TITLE = "title"
        internal const val PARAM_DESCRIPTION = "description"
        internal const val PARAM_CONTENT_ID = "contentId"
        internal const val PARAM_CONTENT_TYPE = "contentType"
        internal const val PARAM_CONTENT_CREATED = "content_created"
        internal const val PARAM_CONTENT_AUTHOR = "content_author"
        internal const val PARAM_CONTENT_SECTION = "content_section"
        internal const val PARAM_CONTENT_NATIVE = "content_is_native"
        internal const val PARAM_CUSTOM_PARAMS = "custom_params"
        internal const val PARAM_CONSENT_MODES = "consent_modes"
        internal const val PARAM_CONSENT_PURPOSES = "consent_purposes"

        private const val VALUE_PROTOCOL_VERSION = 1
        internal const val VALUE_MANUAL_SUBMIT_TYPE = "manual"

        // External events tracking constants
        internal const val PARAM_EVENT_TRACKING_ID = "tracking_id"
        internal const val PARAM_EVENT_TYPE = "event_type"
        internal const val PARAM_EVENT_GROUP_ID = "event_group_id"
        internal const val PARAM_EVENT_CUSTOM_PARAMS = "custom_params"
        internal const val PARAM_EVENT_COOKIE_CONSENTS = "cookie_consents"

        // Custom form tracking constants
        internal const val PARAM_CUSTOM_FORM_NAME = "custom_form_name"

        // Show template url constants
        internal const val PARAM_TEMPLATE_ID = "templateId"
        internal const val PARAM_TEMPLATE_VARIANT_ID = "templateVariantId"
        internal const val PARAM_ACTIVE_METERS = "activeMeters"
        internal const val PARAM_DISPLAY_MODE = "displayMode"
        internal const val PARAM_SHOW_TEMPLATE_TRACKING_ID = "trackingId"
        internal const val PARAM_SHOW_TEMPLATE_USER_TOKEN = "userToken"
        internal const val PARAM_SHOW_TEMPLATE_CUSTOM_VARIABLES = "customVariables"
        internal const val PARAM_SHOW_TEMPLATE_CONTENT_AUTHOR = "contentAuthor"
        internal const val PARAM_SHOW_TEMPLATE_CONTENT_SECTION = "contentSection"
        internal const val PARAM_GA_CLIENT_ID = "gaClientId"
        internal const val PARAM_OS = "os"
        internal const val PARAM_SHOW_CLOSE_BUTTON = "showCloseButton"

        internal const val VALUE_ANDROID_OS = "android"
    }
}
