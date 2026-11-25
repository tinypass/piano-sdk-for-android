package io.piano.android.composer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.piano.android.api.anon.api.ExposureApi
import io.piano.android.common.DeviceIdProvider
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.ExceptionListener
import io.piano.android.composer.listeners.ExperienceExecuteListener
import io.piano.android.composer.listeners.NonSiteListener
import io.piano.android.composer.listeners.ShowTemplateListener
import io.piano.android.composer.listeners.UserSegmentListener
import io.piano.android.composer.model.CustomParameters
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.EdgeCookies
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.EventExecutionContext
import io.piano.android.composer.model.EventsContainer
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.UserSegmentsContainer
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ExperienceExecute
import io.piano.android.composer.model.events.NonSite
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.composer.model.events.UserSegment
import io.piano.android.consents.PianoConsents
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ComposerTest {
    private val experienceCall: Call<Data<ExperienceResponse>> = mock()
    private val composerApi: ComposerApi = mock {
        on { getExperience(any()) } doReturn experienceCall
    }
    private val generalApi: GeneralApi = mock()
    private val exposureApi: ExposureApi = mock()
    private val prefsStorage: PrefsStorage = mock {
        on { tpAccessCookie } doReturn DUMMY_STRING
    }
    private val httpHelper: HttpHelper = mock {
        on { convertExperienceRequest(any(), anyOrNull(), anyOrNull(), anyOrNull(), any(), any()) } doReturn mapOf()
        on { buildEventTracking(any(), any(), any(), any(), any()) } doReturn mapOf()
        on {
            buildShowTemplateParameters(
                any(),
                any(),
                any(),
                anyOrNull(),
                anyOrNull(),
                any(),
            )
        } doReturn mapOf(
            DUMMY_STRING to DUMMY_STRING2,
        )
    }
    private val edgeCookiesProvider: EdgeCookiesProvider = mock {
        on { edgeCookies } doReturn EdgeCookies(
            DUMMY_STRING,
            DUMMY_STRING2,
            DUMMY_STRING,
            DUMMY_STRING2,
            DUMMY_STRING,
            DUMMY_STRING2,
        )
    }
    private val pianoConsents: PianoConsents = mock {
        on { consents } doReturn emptyMap()
    }
    private val deviceIdProvider: DeviceIdProvider = mock {
        on { deviceId } doReturn DUMMY_STRING
    }
    private val composer: Composer = spy(
        Composer(
            composerApi,
            generalApi,
            exposureApi,
            httpHelper,
            prefsStorage,
            DUMMY_AID,
            Composer.Endpoint.SANDBOX,
            edgeCookiesProvider,
            pianoConsents,
            deviceIdProvider,
        ),
    )

    private val experienceRequest: ExperienceRequest = mock()
    private val resultListeners = listOf(
        mock<ShowTemplateListener>(),
        mock<UserSegmentListener>(),
    )
    private val exceptionListener: ExceptionListener = mock()

    private fun verifyExceptionListenerArgument(exc: Throwable?, times: Int = 1) {
        val exceptionCaptor = argumentCaptor<ComposerException>()
        verify(exceptionListener, times(times)).onException(exceptionCaptor.capture())
        with(exceptionCaptor.lastValue) {
            assertEquals(exc, cause)
        }
    }

    private fun getExperienceWithCallbackCheck(callbackTest: (Callback<Data<ExperienceResponse>>) -> Unit) {
        doNothing().`when`(composer).processExperienceResponse(any(), any(), any(), anyOrNull(), any())
        composer.getExperience(experienceRequest, resultListeners, exceptionListener)
        verify(httpHelper).convertExperienceRequest(any(), anyOrNull(), anyOrNull(), anyOrNull(), any(), any())
        verify(composerApi).getExperience(any())
        val callbackCaptor = argumentCaptor<Callback<Data<ExperienceResponse>>>()
        verify(experienceCall).enqueue(callbackCaptor.capture())
        callbackTest(callbackCaptor.lastValue)
    }

    @Test
    fun emptyAidInit() {
        assertFailsWith<IllegalArgumentException> {
            Composer(
                composerApi,
                generalApi,
                exposureApi,
                httpHelper,
                prefsStorage,
                "",
                Composer.Endpoint.SANDBOX,
                edgeCookiesProvider,
                pianoConsents,
                deviceIdProvider,
            )
        }
    }

    @Test
    fun getExperienceResponseException() = getExperienceWithCallbackCheck {
        val response = Response.success<Data<ExperienceResponse>>(null)
        it.onResponse(experienceCall, response)
        verifyExceptionListenerArgument(null)
        verify(composer, never()).processExperienceResponse(any(), any(), any(), anyOrNull(), any())
    }

    @Test
    fun getExperienceResponseContainsErrors() = getExperienceWithCallbackCheck {
        val response = Response.success(
            Data<ExperienceResponse>(
                mock(),
                listOf(
                    mock(),
                    mock(),
                ),
            ),
        )
        it.onResponse(experienceCall, response)
        verifyExceptionListenerArgument(null)
        verify(composer, never()).processExperienceResponse(any(), any(), any(), anyOrNull(), any())
    }

    @Test
    fun getExperienceResponseSuccess() = getExperienceWithCallbackCheck {
        val experienceResponse: ExperienceResponse = mock()
        val response = Response.success(
            Data(experienceResponse, emptyList()),
        )
        it.onResponse(experienceCall, response)
        verify(composer).processExperienceResponse(
            experienceRequest,
            experienceResponse,
            resultListeners,
            null,
            exceptionListener,
        )
        verify(exceptionListener, never()).onException(any())
    }

    @Test
    fun getExperienceFailure() = getExperienceWithCallbackCheck {
        val exc = RuntimeException()
        it.onFailure(experienceCall, exc)
        verifyExceptionListenerArgument(exc)
        verify(composer, never()).processExperienceResponse(any(), any(), any(), anyOrNull(), any())
    }

    @Test
    fun processExperienceResponse() {
        val eventTypes = listOf(
            ShowTemplate("", "", mock(), null, mock(), true),
            mock<ExperienceExecute>(),
            mock<UserSegment>(),
        )
        val response = ExperienceResponse(
            null,
            null,
            null,
            0,
            0,
            null,
            null,
            null,
            EventsContainer(
                eventTypes.map {
                    Event(mock(), mock(), it)
                },
            ),
        )
        val iterations = eventTypes.size

        val exc = RuntimeException()
        val showTemplateListener = mock<ShowTemplateListener> {
            on { canProcess(any()) } doReturn true
        }
        val experienceExecuteListener = mock<ExperienceExecuteListener> {
            on { canProcess(any()) } doReturn false
        }
        val userSegmentListener = mock<UserSegmentListener> {
            on { canProcess(any()) } doReturn true
            on { onExecuted(any()) } doThrow exc
        }
        val listeners = listOf(
            showTemplateListener,
            experienceExecuteListener,
            userSegmentListener,
        )

        composer.processExperienceResponse(
            experienceRequest,
            response,
            listeners,
            null,
            exceptionListener,
        )
        verify(httpHelper).afterExecute(experienceRequest, response)
        verify(showTemplateListener, times(iterations)).canProcess(any())
        verify(showTemplateListener, times(iterations)).onExecuted(any())
        verify(experienceExecuteListener, times(iterations)).canProcess(any())
        verify(experienceExecuteListener, never()).onExecuted(any())
        verify(userSegmentListener, times(iterations)).canProcess(any())
        verify(userSegmentListener, times(iterations)).onExecuted(any())
        verifyExceptionListenerArgument(exc, iterations)
    }

    @Test
    fun processExperienceResponseNoListeners() {
        val experienceResponse = ExperienceResponse(
            null,
            null,
            null,
            0,
            0,
            null,
            null,
            null,
            EventsContainer(listOf(Event(mock(), mock(), mock()))),
        )
        composer.processExperienceResponse(
            experienceRequest,
            experienceResponse,
            listOf(),
            null,
            exceptionListener,
        )
        verify(httpHelper).afterExecute(experienceRequest, experienceResponse)
        verify(exceptionListener, never()).onException(any())
    }

    @Test
    fun processExperienceResponseNoEvents() {
        val response = ExperienceResponse(
            null,
            null,
            null,
            0,
            0,
            null,
            null,
            null,
            EventsContainer(emptyList()),
        )
        composer.processExperienceResponse(
            experienceRequest,
            response,
            resultListeners,
            null,
            exceptionListener,
        )
        verify(httpHelper).afterExecute(experienceRequest, response)
        for (listener in resultListeners) {
            verify(listener, never()).canProcess(any())
            @Suppress("UNCHECKED_CAST")
            verify(listener as EventTypeListener<EventType>, never()).onExecuted(any())
        }
        verify(exceptionListener, never()).onException(any())
    }

    @Test
    fun trackExternalEvents() {
        val call: Call<ResponseBody> = mock()
        whenever(generalApi.trackExternalEvent(any())).doReturn(call)
        composer.trackCloseEvent(DUMMY_STRING)
        verify(httpHelper).buildEventTracking(
            DUMMY_STRING,
            Composer.EVENT_TYPE_EXTERNAL_EVENT,
            Composer.EVENT_GROUP_CLOSE,
            emptyMap(),
        )
        composer.trackRecommendationsDisplay(DUMMY_STRING)
        verify(httpHelper).buildEventTracking(
            DUMMY_STRING,
            Composer.EVENT_TYPE_EXTERNAL_EVENT,
            Composer.EVENT_GROUP_INIT,
            emptyMap(),
            Composer.CX_CUSTOM_PARAMS,
        )
        composer.trackRecommendationsClick(DUMMY_STRING)
        verify(httpHelper).buildEventTracking(
            DUMMY_STRING,
            Composer.EVENT_TYPE_EXTERNAL_LINK,
            Composer.EVENT_GROUP_CLICK,
            emptyMap(),
            Composer.CX_CUSTOM_PARAMS,
        )
        verify(generalApi, times(3)).trackExternalEvent(any())
        verify(call, times(3)).enqueue(any())
    }

    @Test
    fun logExposureWithoutContext() {
        val call = stubExposureLog()
        val event = createEvent()
        composer.logExposure(event)
        verifyExposureLog(browserId = "")

        val termIds = listOf(DUMMY_STRING, DUMMY_STRING2)
        whenever(httpHelper.consentsToJson(any())).doReturn(DUMMY_JSON2)
        composer.userToken(DUMMY_STRING2)
        composer.browserIdProvider { DUMMY_STRING2 }
        composer.logExposure(event, DUMMY_STRING, DUMMY_STRING2, termIds)
        verifyExposureLog(
            browserId = DUMMY_STRING2,
            userToken = DUMMY_STRING2,
            cookieConsents = DUMMY_JSON2,
            externalOfferId = DUMMY_STRING,
            externalTemplateId = DUMMY_STRING2,
            externalTermIds = termIds,
        )
        verify(call, times(2)).enqueue(any())
    }

    @Test
    fun logExposureWithExperienceContext() {
        val call = stubExposureLog()
        val customParameters = CustomParameters().content(DUMMY_STRING, DUMMY_STRING2)
        val tags = listOf(DUMMY_STRING, DUMMY_STRING2)
        val request = ExperienceRequest.Builder()
            .url(DUMMY_URL)
            .referer(DUMMY_REFERER)
            .title(DUMMY_TITLE)
            .contentAuthor(DUMMY_AUTHOR)
            .contentCreated(DUMMY_CREATED)
            .contentSection(DUMMY_SECTION)
            .contentType(DUMMY_TYPE)
            .tags(tags)
            .customParams(customParameters)
            .build()
        whenever(httpHelper.customParametersToJson(customParameters)).doReturn(DUMMY_JSON)
        composer.userToken(DUMMY_STRING2)
        var receivedEvent: Event<NonSite>? = null
        composer.processExperienceResponse(
            request,
            createExperienceResponse(DUMMY_BROWSER_ID, createEvent()),
            listOf(NonSiteListener { receivedEvent = it }),
            null,
            exceptionListener,
        )
        // A parallel response and a new user token mustn't affect the exposure of the received event
        composer.processExperienceResponse(
            experienceRequest,
            createExperienceResponse(DUMMY_STRING),
            emptyList(),
            null,
            exceptionListener,
        )
        composer.userToken(null)

        composer.logExposure(assertNotNull(receivedEvent))
        verifyExposureLog(
            browserId = DUMMY_BROWSER_ID,
            userToken = DUMMY_STRING2,
            pageTitle = DUMMY_TITLE,
            url = DUMMY_URL,
            referer = DUMMY_REFERER,
            contentAuthor = DUMMY_AUTHOR,
            contentCreated = DUMMY_CREATED,
            contentSection = DUMMY_SECTION,
            contentType = DUMMY_TYPE,
            tags = tags,
            customParams = DUMMY_JSON,
        )
        verify(call).enqueue(any())
    }

    private fun stubExposureLog(): Call<ResponseBody> = mock<Call<ResponseBody>>().also {
        whenever(
            exposureApi.log(
                any(), any(), any(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(),
                anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(),
                anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(),
            ),
        ).doReturn(it)
    }

    private fun createEvent(): Event<NonSite> = Event(
        mock(),
        EventExecutionContext(
            DUMMY_STRING2,
            DUMMY_STRING2,
            DUMMY_STRING,
            null,
            null,
            null,
            null,
            DUMMY_STRING2,
            UserSegmentsContainer(null, null),
            null,
            null,
            null,
            null,
        ),
        NonSite,
    )

    private fun createExperienceResponse(browserId: String, vararg events: Event<EventType>) = ExperienceResponse(
        null,
        null,
        null,
        0,
        0,
        browserId,
        null,
        null,
        EventsContainer(events.toList()),
    )

    private fun verifyExposureLog(
        browserId: String,
        userToken: String? = null,
        pageTitle: String? = null,
        url: String? = null,
        referer: String? = null,
        contentAuthor: String? = null,
        contentCreated: String? = null,
        contentSection: String? = null,
        contentType: String? = null,
        tags: List<String>? = null,
        cookieConsents: String? = null,
        customParams: String? = null,
        externalOfferId: String? = null,
        externalTemplateId: String? = null,
        externalTermIds: List<String>? = null,
    ) = verify(exposureApi).log(
        aid = eq(DUMMY_AID),
        trackingId = eq(DUMMY_STRING),
        browserId = eq(browserId),
        userToken = eq(userToken),
        externalAuthCookieName = isNull(),
        pageTitle = eq(pageTitle),
        url = eq(url),
        referer = eq(referer),
        contentAuthor = eq(contentAuthor),
        contentCreated = eq(contentCreated),
        contentSection = eq(contentSection),
        contentType = eq(contentType),
        tags = eq(tags),
        previousUserSegments = isNull(),
        userState = isNull(),
        clientHints = isNull(),
        cookieConsents = eq(cookieConsents),
        previousUserSegments2 = isNull(),
        customParams = eq(customParams),
        externalOfferId = eq(externalOfferId),
        externalTemplateId = eq(externalTemplateId),
        externalTermIds = eq(externalTermIds),
    )

    @Test
    fun trackCustomFormImpression() {
        val call: Call<ResponseBody> = mock()
        whenever(generalApi.customFormImpression(any())).doReturn(call)
        composer.trackCustomFormImpression(DUMMY_STRING, DUMMY_STRING2)
        verify(httpHelper).buildCustomFormTracking(DUMMY_AID, DUMMY_STRING, DUMMY_STRING2, null, emptyMap())
        verify(generalApi).customFormImpression(any())
        verify(call).enqueue(any())
    }

    @Test
    fun trackCustomFormSubmission() {
        val call: Call<ResponseBody> = mock()
        whenever(generalApi.customFormSubmission(any())).doReturn(call)
        composer.trackCustomFormSubmission(DUMMY_STRING, DUMMY_STRING2)
        verify(httpHelper).buildCustomFormTracking(DUMMY_AID, DUMMY_STRING, DUMMY_STRING2, null, emptyMap())
        verify(generalApi).customFormSubmission(any())
        verify(call).enqueue(any())
    }

    companion object {
        private const val DUMMY_AID = "AID"
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY2"
        private const val DUMMY_JSON = """{"key":"value"}"""
        private const val DUMMY_JSON2 = """{"key2":"value2"}"""
        private const val DUMMY_BROWSER_ID = "BID"
        private const val DUMMY_URL = "https://example.com/article"
        private const val DUMMY_REFERER = "https://example.com"
        private const val DUMMY_TITLE = "Title"
        private const val DUMMY_AUTHOR = "Author"
        private const val DUMMY_CREATED = "2026-11-25T00:00:00Z"
        private const val DUMMY_SECTION = "Section"
        private const val DUMMY_TYPE = "article"
    }
}
