package io.piano.android.id

import com.nhaarman.mockitokotlin2.mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SignInContextTest {
    private val client: PianoIdClient = mock()
    private val signInContext = PianoIdClient.SignInContext(client)

    @Test
    fun disableSignUp() {
        assertFalse { signInContext.disableSignUp }
        assertEquals(signInContext, signInContext.disableSignUp())
        assertTrue { signInContext.disableSignUp }
    }

    @Test
    fun widget() {
        for (x in arrayOf(DUMMY, null, DUMMY)) {
            assertEquals(signInContext, signInContext.widget(x))
            assertEquals(x, signInContext.widget)
        }
    }

    companion object {
        const val DUMMY = "dummy"
    }
}
