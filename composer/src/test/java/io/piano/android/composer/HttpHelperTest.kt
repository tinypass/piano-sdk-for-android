package io.piano.android.composer

import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import io.piano.android.composer.model.*
import io.piano.android.composer.model.events.ShowTemplate
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpHelperTest {
    private val experienceIdsProvider: ExperienceIdsProvider = mock()
    private val prefsStorage: PrefsStorage = mock()
    private val gson: Gson = mock()
    private val customParameters: CustomParameters = mock() {
        on { isEmpty } doReturn false
    }
    private val experienceRequest: ExperienceRequest = mock() {
        on { customVariables } doReturn mapOf("1" to "2")
        on { tags } doReturn listOf(DUMMY_STRING, DUMMY_STRING)
        on { customParameters } doReturn customParameters
    }

    private val httpHelper: HttpHelper = spy(
        HttpHelper(
            experienceIdsProvider,
            prefsStorage,
            gson,
            DUMMY_STRING
        )
    ).apply {
        doReturn(JOINED_DUMMY).`when`(this).joinToString(any())
    }

    @Test
    fun convertExperienceRequest() {
        with(httpHelper.convertExperienceRequest(experienceRequest, DUMMY_STRING, null)) {
            assertEquals(10, size)
            assertEquals(DUMMY_STRING, this[HttpHelper.PARAM_AID])
            verify(httpHelper).filterNullValues(this)
        }
        verify(experienceIdsProvider).getPageViewId(any())
        verify(experienceIdsProvider).getVisitId(any())
        verify(experienceIdsProvider).isVisitIdRegenerated
        verify(prefsStorage).xbuilderBrowserCookie
        verify(prefsStorage).tpBrowserCookie
        verify(prefsStorage).tpAccessCookie
        verify(gson).toJson(any<Map<String, String>>())
        verify(gson).toJson(customParameters)
    }

    @Test
    fun processExperienceResponse() {
        val experienceResponse = ExperienceResponse().apply {
            xbCookie = CookieObject().apply { value = DUMMY_STRING }
            tbCookie = CookieObject().apply { value = DUMMY_STRING2 }
            taCookie = CookieObject().apply { value = JOINED_DUMMY }
            visitTimeoutMinutes = 1
            timeZoneOffsetMillis = 30000
        }
        httpHelper.processExperienceResponse(experienceResponse)
        verify(prefsStorage).xbuilderBrowserCookie = DUMMY_STRING
        verify(prefsStorage).tpBrowserCookie = DUMMY_STRING2
        verify(prefsStorage).tpAccessCookie = JOINED_DUMMY
        verify(prefsStorage).visitTimeout = 60000
        verify(prefsStorage).serverTimezoneOffset = 30000
    }

    @Test
    fun buildEventTracking() {
        with(httpHelper.buildEventTracking(DUMMY_STRING)) {
            assertEquals(3, size)
            assertEquals(DUMMY_STRING, this[HttpHelper.PARAM_EVENT_TRACKING_ID])
        }
    }

    @Test
    fun buildShowTemplateParameters() {
        val meters: List<ActiveMeter> = listOf(mock())
        val showTemplateEvent = Event<ShowTemplate>(
            mock(),
            EventExecutionContext().apply {
                trackingId = DUMMY_STRING
                activeMeters = meters
            },
            ShowTemplate().apply { templateId = DUMMY_STRING2 }
        )
        with(
            httpHelper.buildShowTemplateParameters(
                showTemplateEvent,
                experienceRequest,
                DUMMY_STRING,
                null,
                null
            )
        ) {
            assertEquals(7, size)
            assertEquals(DUMMY_STRING, this[HttpHelper.PARAM_AID])
        }
        verify(gson).toJson(meters)
        verify(gson).toJson(any<Map<String, String>>())
    }

    @Test
    fun filterNullValues() {
        val map = mutableMapOf(
            "1" to "1",
            "2" to 2,
            "3" to null,
            "4" to Any()
        )
        httpHelper.filterNullValues(map)
        assertEquals(3, map.size)
    }

    companion object {
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY2"
        const val JOINED_DUMMY = "DUMMY,DUMMY,DUMMY"
    }
}