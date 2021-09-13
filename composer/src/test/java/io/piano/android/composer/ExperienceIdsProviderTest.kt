package io.piano.android.composer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExperienceIdsProviderTest {
    private val prefsStorage: PrefsStorage = mock()
    private val pageViewIdProvider: PageViewIdProvider = mock() {
        on { getPageViewId(any()) } doReturn DUMMY_STRING
    }

    private val experienceIdsProvider = spy(ExperienceIdsProvider(prefsStorage, pageViewIdProvider))

    @Test
    fun getVisitId() {
        val date = Date()
        whenever(prefsStorage.visitTimeout).doReturn(0)
        whenever(prefsStorage.visitTimestamp).doReturn(date.time + 1)
        whenever(prefsStorage.visitId).doReturn(DUMMY_STRING)
        doReturn(date.time).`when`(experienceIdsProvider).getServerMidnightTimestamp(any())
        assertEquals(DUMMY_STRING, experienceIdsProvider.getVisitId(date))
        assertFalse { experienceIdsProvider.isVisitIdGenerated }
        verify(prefsStorage).visitTimestamp
        verify(prefsStorage).visitTimeout
        verify(experienceIdsProvider).getServerMidnightTimestamp(date)
        verify(prefsStorage).visitId
        verify(prefsStorage).setVisitDate(date)
    }

    @Test
    fun getVisitIdExpiredByTimeout() {
        val date = Date()
        whenever(prefsStorage.visitTimeout).doReturn(0)
        whenever(prefsStorage.visitTimestamp).doReturn(0)
        doReturn(DUMMY_STRING).`when`(experienceIdsProvider).generateVisitId(any())
        assertEquals(DUMMY_STRING, experienceIdsProvider.getVisitId(date))
        verify(prefsStorage).visitTimestamp
        verify(prefsStorage).visitTimeout
        verify(experienceIdsProvider, never()).getServerMidnightTimestamp(any<Date>())
        verify(prefsStorage, never()).visitId
        verify(prefsStorage, never()).setVisitDate(any<Date>())
    }

    @Test
    fun getVisitIdExpiredByServerTime() {
        val date = Date()
        whenever(prefsStorage.visitTimeout).doReturn(0)
        whenever(prefsStorage.visitTimestamp).doReturn(date.time + 1)
        doReturn(date.time + 10).`when`(experienceIdsProvider).getServerMidnightTimestamp(any())
        doReturn(DUMMY_STRING).`when`(experienceIdsProvider).generateVisitId(any())
        assertEquals(DUMMY_STRING, experienceIdsProvider.getVisitId(date))
        verify(prefsStorage).visitTimestamp
        verify(prefsStorage).visitTimeout
        verify(experienceIdsProvider).getServerMidnightTimestamp(date)
        verify(prefsStorage, never()).visitId
        verify(prefsStorage, never()).setVisitDate(any<Date>())
    }

    @Test
    fun getVisitIdNull() {
        val date = Date()
        whenever(prefsStorage.visitTimeout).doReturn(0)
        whenever(prefsStorage.visitTimestamp).doReturn(date.time + 1)
        whenever(prefsStorage.visitId).doReturn(null)
        doReturn(date.time).`when`(experienceIdsProvider).getServerMidnightTimestamp(any())
        doReturn(DUMMY_STRING).`when`(experienceIdsProvider).generateVisitId(any())
        assertEquals(DUMMY_STRING, experienceIdsProvider.getVisitId(date))
        verify(prefsStorage).visitTimestamp
        verify(prefsStorage).visitTimeout
        verify(experienceIdsProvider).getServerMidnightTimestamp(date)
        verify(prefsStorage).visitId
    }

    @Test
    fun generateVisitId() {
        val date = Date()
        with(experienceIdsProvider.generateVisitId(date)) {
            assertEquals(ExperienceIdsProvider.VISIT_ID_PREFIX + DUMMY_STRING, this)
            assertTrue { experienceIdsProvider.isVisitIdGenerated }
            verify(prefsStorage).visitId = this
            verify(prefsStorage).setVisitDate(date)
        }
    }

    @Test
    fun getServerMidnightTimestamp() {
        val timeZone = TimeZone.getDefault()
        whenever(prefsStorage.serverTimezoneOffset).doReturn(timeZone.rawOffset)
        val date = Date()
        val milliSeconds = with(Calendar.getInstance(timeZone)) {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            timeInMillis
        }
        assertEquals(milliSeconds, experienceIdsProvider.getServerMidnightTimestamp(date))
    }

    companion object {
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY"
    }
}
