package io.piano.android.id

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlin.test.Test

class PianoIdJavascriptDelegateTest {
    private val jsInterface: PianoIdJsInterface = mock()
    private val publicJsInterface: PianoIdJs = mock()
    private val pianoIdJavascriptDelegate = PianoIdJavascriptDelegate(jsInterface, publicJsInterface)

    @Test
    fun socialLogin() {
        pianoIdJavascriptDelegate.socialLogin(DUMMY)
        verify(jsInterface).socialLogin(DUMMY)
    }

    @Test
    fun registerSuccess() {
        pianoIdJavascriptDelegate.registerSuccess(DUMMY)
        verify(jsInterface).registerSuccess(DUMMY)
    }

    @Test
    fun loginSuccess() {
        pianoIdJavascriptDelegate.loginSuccess(DUMMY)
        verify(jsInterface).loginSuccess(DUMMY)
    }

    @Test
    fun cancel() {
        pianoIdJavascriptDelegate.cancel()
        verify(jsInterface).cancel()
    }

    @Test
    fun customEvent() {
        pianoIdJavascriptDelegate.customEvent(DUMMY)
        verify(publicJsInterface).customEvent(DUMMY)
    }

    companion object {
        const val DUMMY = "dummy"
    }
}
