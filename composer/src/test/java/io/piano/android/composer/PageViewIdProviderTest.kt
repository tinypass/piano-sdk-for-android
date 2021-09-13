package io.piano.android.composer

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class PageViewIdProviderTest {
    private val composerPageViewIdProvider = spy(PageViewIdProvider)

    @Test
    fun getPageViewId() {
        doReturn(DUMMY_STRING).`when`(composerPageViewIdProvider)
            .generateRandomAlphaNumString(PageViewIdProvider.RANDOM_STRING_SIZE)
        doReturn(DUMMY_STRING2).`when`(composerPageViewIdProvider)
            .generateRandomAlphaNumString(PageViewIdProvider.HASH_SIZE)
        val date = Date()
        assertEquals(
            "${PageViewIdProvider.dateFormat.format(date)}-$DUMMY_STRING-$DUMMY_STRING2",
            composerPageViewIdProvider.getPageViewId(date)
        )
        verify(composerPageViewIdProvider).generateRandomAlphaNumString(PageViewIdProvider.RANDOM_STRING_SIZE)
        verify(composerPageViewIdProvider).generateRandomAlphaNumString(PageViewIdProvider.HASH_SIZE)
    }

    @Test
    fun generateRandomAlphaNumString() {
        val size = 5
        val randomStrings = List(size) {
            composerPageViewIdProvider.generateRandomAlphaNumString(PageViewIdProvider.HASH_SIZE)
        }
        assertEquals(size, randomStrings.toSet().size)
    }

    companion object {
        const val DUMMY_STRING = "DUMMY"
        const val DUMMY_STRING2 = "DUMMY"
    }
}
