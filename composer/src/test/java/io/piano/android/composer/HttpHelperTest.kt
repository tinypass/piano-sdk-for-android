package io.piano.android.composer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.squareup.moshi.Moshi
import io.piano.android.composer.model.ActiveMeter
import io.piano.android.composer.model.CookieObject
import io.piano.android.composer.model.CustomParameters
import io.piano.android.composer.model.DelayBy
import io.piano.android.composer.model.DisplayMode
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.EventExecutionContext
import io.piano.android.composer.model.EventsContainer
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.ShowTemplate
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpHelperTest {
    private val experienceIdsProvider: ExperienceIdsProvider = mock() {
        on { getPageViewId(any()) } doReturn DUMMY_STRING
        on { getVisitId(any()) } doReturn DUMMY_STRING2
    }
    private val prefsStorage: PrefsStorage = mock() {
        on { tpAccessCookie } doReturn ComposerTest.DUMMY_STRING
        on { tpBrowserCookie } doReturn ComposerTest.DUMMY_STRING
        on { xbuilderBrowserCookie } doReturn ComposerTest.DUMMY_STRING
    }
    private val moshi: Moshi = Moshi.Builder()
        .add(ComposerJsonAdapterFactory())
        .add(EventJsonAdapterFactory())
        .build()
    private val customParameters: CustomParameters = mock() {
        on { isEmpty() } doReturn false
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
            moshi,
            DUMMY_STRING
        )
    )

    @Test
    fun convertExperienceRequest() {
        val requestMap = httpHelper.convertExperienceRequest(experienceRequest, DUMMY_STRING, { null }, null)
        requestMap.apply {
            assertEquals(17, size)
            assertEquals(DUMMY_STRING, this[HttpHelper.PARAM_AID])
        }
        verify(experienceIdsProvider).getPageViewId(any())
        verify(experienceIdsProvider).getVisitId(any())
        verify(experienceIdsProvider).isVisitIdGenerated
        verify(prefsStorage).xbuilderBrowserCookie
        verify(prefsStorage).tpBrowserCookie
        verify(prefsStorage).tpAccessCookie
    }

    @Test
    fun afterExecute() {
        val experienceResponse = ExperienceResponse(
            CookieObject(DUMMY_STRING2),
            CookieObject(DUMMY_STRING),
            CookieObject(JOINED_DUMMY),
            30000,
            1,
            null,
            null,
            EventsContainer(emptyList())
        )
        httpHelper.afterExecute(mock(), experienceResponse)
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
        val showTemplateEvent = Event(
            mock(),
            EventExecutionContext(
                DUMMY_STRING2,
                DUMMY_STRING2,
                DUMMY_STRING,
                null,
                null,
                null,
                DUMMY_STRING2,
                DUMMY_STRING2,
                null,
                meters
            ),
            ShowTemplate(
                DUMMY_STRING2,
                null,
                DisplayMode.MODAL,
                DUMMY_STRING,
                DelayBy(
                    DelayBy.DelayType.TIME,
                    0
                ),
                false,
                null
            )
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
            assertEquals(10, size)
            assertEquals(DUMMY_STRING, this[HttpHelper.PARAM_AID])
        }
    }

    companion object {
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY2"
        const val JOINED_DUMMY = "DUMMY,DUMMY,DUMMY"
    }
}
