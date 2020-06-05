package io.piano.android.id

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.*
import org.junit.runner.RunWith
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PianoOAuthActivityTest {
    private val pianoIdClient: PianoIdClient = mock {
        on { saveException(any()) } doReturn CODE
    }
    private val activity = spy(TestOAuthActivity().apply {
        client = pianoIdClient
    })
    private val intentCaptor = argumentCaptor<Intent>()

    @Test
    fun setSuccessResult() {
        activity.setSuccessResult(DUMMY, DUMMY2)
        verify(activity).setResult(eq(Activity.RESULT_OK), intentCaptor.capture())
        with(intentCaptor.lastValue) {
            assertEquals(DUMMY, getStringExtra(PianoId.KEY_OAUTH_PROVIDER_NAME))
            assertEquals(DUMMY2, getStringExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN))
        }
    }

    @Test
    fun setFailureResult() {
        val throwable = RuntimeException()
        activity.setFailureResult(throwable)
        val excCaptor = argumentCaptor<PianoIdException>()
        verify(pianoIdClient).saveException(excCaptor.capture())
        assertEquals(throwable, excCaptor.lastValue.cause)
        verify(activity).setResult(eq(PianoId.RESULT_ERROR), intentCaptor.capture())
        with(intentCaptor.lastValue) {
            assertEquals(CODE, getIntExtra(PianoId.KEY_ERROR, 0))
        }
    }

    private class TestOAuthActivity : PianoOAuthActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        }
    }

    companion object {
        const val DUMMY = "dummy"
        const val DUMMY2 = "dummy2"
        const val CODE = 1234
    }
}