package io.piano.android.id

import android.webkit.JavascriptInterface

internal interface PianoIdJsInterface {
    @JavascriptInterface
    fun socialLogin(payload: String?)

    @JavascriptInterface
    fun loginSuccess(payload: String?)

    @JavascriptInterface
    fun cancel()
}
