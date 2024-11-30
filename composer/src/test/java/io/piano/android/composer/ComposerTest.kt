package io.piano.android.composer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.ExceptionListener
import io.piano.android.composer.listeners.ExperienceExecuteListener
import io.piano.android.composer.listeners.ShowTemplateListener
import io.piano.android.composer.listeners.UserSegmentListener
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.EdgeCookies
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.EventsContainer
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ExperienceExecute
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

class ComposerTest {
    private val experienceCall: Call<Data<ExperienceResponse>> = mock()
    private val composerApi: ComposerApi = mock {
        on { getExperience(any()) } doReturn experienceCall
    }
    private val generalApi: GeneralApi = mock()
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
    private val composer: Composer = spy(
        Composer(
            composerApi,
            generalApi,
            httpHelper,
            prefsStorage,
            DUMMY_AID,
            Composer.Endpoint.SANDBOX,
            edgeCookiesProvider,
            pianoConsents,
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
                httpHelper,
                prefsStorage,
                "",
                Composer.Endpoint.SANDBOX,
                edgeCookiesProvider,
                pianoConsents,
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
    }
}
