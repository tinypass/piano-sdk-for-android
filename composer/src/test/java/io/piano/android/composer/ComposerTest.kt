package io.piano.android.composer

import com.nhaarman.mockitokotlin2.*
import io.piano.android.composer.listeners.*
import io.piano.android.composer.model.*
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ExperienceExecute
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.composer.model.events.UserSegment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse


class ComposerTest {
    private val experienceCall: Call<Data<ExperienceResponse>> = mock()
    private val api: Api = mock() {
        on { getExperience(any(), any()) } doReturn experienceCall
    }
    private val httpHelper: HttpHelper = mock() {
        on { convertExperienceRequest(any(), anyOrNull(), anyOrNull()) } doReturn mapOf()
        on { buildEventTracking(any()) } doReturn mapOf()
        on {
            buildShowTemplateParameters(
                any(),
                any(),
                any(),
                anyOrNull(),
                anyOrNull()
            )
        } doReturn mapOf(
            DUMMY_STRING to DUMMY_STRING2
        )
    }
    private val composer: Composer = spy(Composer(api, httpHelper, "AID", null)) {
        doReturn(DUMMY_STRING).`when`(mock).buildErrorMessage(any())
    }

    private val experienceRequest: ExperienceRequest = mock()
    private val resultListeners = listOf(
        mock<ShowTemplateListener>(),
        mock<UserSegmentListener>()
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
        doNothing().`when`(composer).processExperienceResponse(any(), any(), any(), any())
        composer.getExperience(experienceRequest, resultListeners, exceptionListener)
        verify(httpHelper).convertExperienceRequest(any(), anyOrNull(), anyOrNull())
        verify(api).getExperience(any(), any())
        val callbackCaptor = argumentCaptor<Callback<Data<ExperienceResponse>>>()
        verify(experienceCall).enqueue(callbackCaptor.capture())
        callbackTest(callbackCaptor.lastValue)
    }

    @Test
    fun getExperienceResponseException() =
        getExperienceWithCallbackCheck {
            val response = Response.success<Data<ExperienceResponse>>(null)
            it.onResponse(experienceCall, response)
            verifyExceptionListenerArgument(null)
            verify(composer, never()).processExperienceResponse(any(), any(), any(), any())
        }

    @Test
    fun getExperienceResponseContainsErrors() =
        getExperienceWithCallbackCheck {
            val response = Response.success(
                Data<ExperienceResponse>().apply {
                    data = mock()
                    errors = listOf(
                        mock(),
                        mock()
                    )
                }
            )
            it.onResponse(experienceCall, response)
            verifyExceptionListenerArgument(null)
            verify(composer).buildErrorMessage(any())
            verify(composer, never()).processExperienceResponse(any(), any(), any(), any())
        }

    @Test
    fun getExperienceResponseSuccess() =
        getExperienceWithCallbackCheck {
            val experienceResponse: ExperienceResponse = mock()
            val response = Response.success(
                Data<ExperienceResponse>().apply {
                    data = experienceResponse
                    errors = emptyList()
                }
            )
            it.onResponse(experienceCall, response)
            verify(composer).processExperienceResponse(
                experienceRequest,
                experienceResponse,
                resultListeners,
                exceptionListener
            )
            verify(composer, never()).buildErrorMessage(any())
            verify(exceptionListener, never()).onException(any())
        }

    @Test
    fun getExperienceFailure() =
        getExperienceWithCallbackCheck {
            val exc = RuntimeException()
            it.onFailure(experienceCall, exc)
            verifyExceptionListenerArgument(exc)
            verify(composer, never()).processExperienceResponse(any(), any(), any(), any())
        }


    @Test
    fun processExperienceResponse() {
        doNothing().`when`(composer).fillShowTemplateUrl(any(), any())
        val eventTypes = listOf(
            mock<ShowTemplate>(),
            mock<ExperienceExecute>(),
            mock<UserSegment>()
        )
        val response = ExperienceResponse().apply {
            result = EventsContainer().apply {
                events = eventTypes.map {
                    Event(mock(), mock(), it)
                }
            }
        }
        val iterations = eventTypes.size

        val exc = RuntimeException()
        val showTemplateListener = mock<ShowTemplateListener>() {
            on { canProcess(any()) } doReturn true
        }
        val experienceExecuteListener = mock<ExperienceExecuteListener>() {
            on { canProcess(any()) } doReturn false
        }
        val userSegmentListener = mock<UserSegmentListener>() {
            on { canProcess(any()) } doReturn true
            on { onExecuted(any()) } doThrow exc
        }
        val listeners = listOf(
            showTemplateListener,
            experienceExecuteListener,
            userSegmentListener
        )

        composer.processExperienceResponse(
            experienceRequest,
            response,
            listeners,
            exceptionListener
        )
        verify(httpHelper).processExperienceResponse(response)
        verify(composer).fillShowTemplateUrl(any(), any())
        verify(showTemplateListener, times(iterations)).canProcess(any())
        verify(showTemplateListener, times(iterations)).onExecuted(any())
        verify(experienceExecuteListener, times(iterations)).canProcess(any())
        verify(experienceExecuteListener, never()).onExecuted(any())
        verify(userSegmentListener, times(iterations)).canProcess(any())
        verify(userSegmentListener, times(iterations)).onExecuted(any())
        verifyExceptionListenerArgument(exc, iterations)
    }

    @Test
    fun processExperienceResponseException() {
        composer.processExperienceResponse(
            experienceRequest,
            mock(),
            resultListeners,
            exceptionListener
        )
        verify(httpHelper).processExperienceResponse(any())
        for (listener in resultListeners) {
            verify(listener, never()).canProcess(any())
            verify(listener as EventTypeListener<EventType>, never()).onExecuted(any())
        }
        verify(exceptionListener).onException(any())
    }

    @Test
    fun processExperienceResponseNoListeners() {
        val experienceResponse: ExperienceResponse = mock()
        composer.processExperienceResponse(
            experienceRequest,
            experienceResponse,
            listOf(),
            exceptionListener
        )
        verify(httpHelper).processExperienceResponse(experienceResponse)
        verify(exceptionListener, never()).onException(any())
    }

    @Test
    fun processExperienceResponseNoEvents() {
        val response = ExperienceResponse().apply {
            result = EventsContainer().apply { events = emptyList() }
        }
        composer.processExperienceResponse(
            experienceRequest,
            response,
            resultListeners,
            exceptionListener
        )
        verify(httpHelper).processExperienceResponse(response)
        for (listener in resultListeners) {
            verify(listener, never()).canProcess(any())
            verify(listener as EventTypeListener<EventType>, never()).onExecuted(any())
        }
        verify(exceptionListener, never()).onException(any())
    }

    @Test
    fun trackExternalEvent() {
        val call: Call<ResponseBody> = mock()
        whenever(api.trackExternalEvent(any(), any())).doReturn(call)
        composer.trackExternalEvent(DUMMY_STRING)
        verify(httpHelper).buildEventTracking(DUMMY_STRING)
        verify(api).trackExternalEvent(any(), any())
        verify(call).enqueue(any())
    }

    @Test
    fun fillShowTemplateUrl() {
        val event = Event<ShowTemplate>(mock(), mock(), ShowTemplate())
        composer.fillShowTemplateUrl(event, experienceRequest)
        with(event.eventData.url) {
            assertFalse { isNullOrEmpty() }
            assertEquals(true, this?.contains("$DUMMY_STRING=$DUMMY_STRING2"))
        }
        verify(httpHelper).buildShowTemplateParameters(
            any(),
            any(),
            any(),
            anyOrNull(),
            anyOrNull()
        )
    }

    companion object {
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY2"
    }
}