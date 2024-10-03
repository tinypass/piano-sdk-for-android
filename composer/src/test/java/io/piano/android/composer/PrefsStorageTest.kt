package io.piano.android.composer

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertEquals

class PrefsStorageTest {
    private val prefsEditor: SharedPreferences.Editor = mock {
        on { putLong(any(), any()) } doReturn mock
        on { putInt(any(), any()) } doReturn mock
        on { putString(any(), any()) } doReturn mock
    }
    private val prefs: SharedPreferences = mock {
        on { edit() } doReturn prefsEditor
    }
    private val context: Context = mock {
        on { getSharedPreferences(any(), any()) } doReturn prefs
    }
    private val prefsStorage = spy(PrefsStorage(context))

    @Test
    fun getValueForKey() {
        whenever(prefs.getString(KEY, null)).thenReturn(VALUE)
        assertEquals(VALUE, prefsStorage.getValueForKey(KEY))
        verify(prefs).getString(KEY, null)
    }

    @Test
    fun setValueForKey() {
        prefsStorage.setValueForKey(KEY, VALUE)
        verify(prefsEditor).putString(KEY, VALUE)
    }

    @Test
    fun getVisitId() {
        doReturn(DUMMY_STRING).`when`(prefsStorage).getValueForKey(any())
        assertEquals(DUMMY_STRING, prefsStorage.visitId)
        verify(prefsStorage).getValueForKey(PrefsStorage.KEY_VISIT_ID)
    }

    @Test
    fun setVisitId() {
        doNothing().`when`(prefsStorage).setValueForKey(any(), any())
        prefsStorage.visitId = DUMMY_STRING
        verify(prefsStorage).setValueForKey(PrefsStorage.KEY_VISIT_ID, DUMMY_STRING)
    }

    @Test
    fun getVisitTimestamp() {
        whenever(prefs.getLong(PrefsStorage.KEY_VISIT_ID_TIMESTAMP, 0)).thenReturn(DUMMY_LONG)
        assertEquals(DUMMY_LONG, prefsStorage.visitTimestamp)
        verify(prefs).getLong(PrefsStorage.KEY_VISIT_ID_TIMESTAMP, 0)
    }

    @Test
    fun setVisitDateByTimestamp() {
        prefsStorage.setVisitDate(DUMMY_LONG)
        verify(prefsEditor).putLong(PrefsStorage.KEY_VISIT_ID_TIMESTAMP, DUMMY_LONG)
    }

    @Test
    fun setVisitDateByDate() {
        doNothing().`when`(prefsStorage).setVisitDate(any<Long>())
        val date: Date = mock {
            on { time } doReturn DUMMY_LONG
        }
        prefsStorage.setVisitDate(date)
        verify(date).time
        verify(prefsStorage).setVisitDate(DUMMY_LONG)
    }

    @Test
    fun setVisitDateByNullDate() {
        doNothing().`when`(prefsStorage).setVisitDate(any<Long>())
        prefsStorage.setVisitDate(null)
        verify(prefsStorage).setVisitDate(0)
    }

    @Test
    fun getTpBrowserCookie() {
        doReturn(DUMMY_STRING).`when`(prefsStorage).getValueForKey(any())
        assertEquals(DUMMY_STRING, prefsStorage.tpBrowserCookie)
        verify(prefsStorage).getValueForKey(PrefsStorage.KEY_TP_BROWSER_COOKIE)
    }

    @Test
    fun setTpBrowserCookie() {
        doNothing().`when`(prefsStorage).setValueForKey(any(), any())
        prefsStorage.tpBrowserCookie = DUMMY_STRING
        verify(prefsStorage).setValueForKey(PrefsStorage.KEY_TP_BROWSER_COOKIE, DUMMY_STRING)
    }

    @Test
    fun getXbuilderBrowserCookie() {
        doReturn(DUMMY_STRING).`when`(prefsStorage).getValueForKey(any())
        assertEquals(DUMMY_STRING, prefsStorage.xbuilderBrowserCookie)
        verify(prefsStorage).getValueForKey(PrefsStorage.KEY_XBUILDER_BROWSER_COOKIE)
    }

    @Test
    fun setXbuilderBrowserCookie() {
        doNothing().`when`(prefsStorage).setValueForKey(any(), any())
        prefsStorage.xbuilderBrowserCookie = DUMMY_STRING
        verify(prefsStorage).setValueForKey(PrefsStorage.KEY_XBUILDER_BROWSER_COOKIE, DUMMY_STRING)
    }

    @Test
    fun getTpAccessCookie() {
        doReturn(DUMMY_STRING).`when`(prefsStorage).getValueForKey(any())
        assertEquals(DUMMY_STRING, prefsStorage.tpAccessCookie)
        verify(prefsStorage).getValueForKey(PrefsStorage.KEY_TP_ACCESS_COOKIE)
    }

    @Test
    fun setTpAccessCookie() {
        doNothing().`when`(prefsStorage).setValueForKey(any(), any())
        prefsStorage.tpAccessCookie = DUMMY_STRING
        verify(prefsStorage).setValueForKey(PrefsStorage.KEY_TP_ACCESS_COOKIE, DUMMY_STRING)
    }

    @Test
    fun getServerTimezoneOffset() {
        whenever(prefs.getInt(PrefsStorage.KEY_TIMEZONE_OFFSET, 0)).thenReturn(DUMMY_INT)
        assertEquals(DUMMY_INT, prefsStorage.serverTimezoneOffset)
        verify(prefs).getInt(PrefsStorage.KEY_TIMEZONE_OFFSET, 0)
    }

    @Test
    fun setServerTimezoneOffset() {
        prefsStorage.serverTimezoneOffset = DUMMY_INT
        verify(prefsEditor).putInt(PrefsStorage.KEY_TIMEZONE_OFFSET, DUMMY_INT)
    }

    @Test
    fun getVisitTimeout() {
        whenever(prefs.getLong(PrefsStorage.KEY_VISIT_TIMEOUT, PrefsStorage.VISIT_TIMEOUT_FALLBACK)).thenReturn(
            DUMMY_LONG,
        )
        assertEquals(DUMMY_LONG, prefsStorage.visitTimeout)
        verify(prefs).getLong(PrefsStorage.KEY_VISIT_TIMEOUT, PrefsStorage.VISIT_TIMEOUT_FALLBACK)
    }

    @Test
    fun setVisitTimeout() {
        prefsStorage.visitTimeout = DUMMY_LONG
        verify(prefsEditor).putLong(PrefsStorage.KEY_VISIT_TIMEOUT, DUMMY_LONG)
    }

    companion object {
        const val KEY = "KEY"
        const val VALUE = "VALUE"
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_INT = 1234
        const val DUMMY_LONG = 1234L
    }
}
