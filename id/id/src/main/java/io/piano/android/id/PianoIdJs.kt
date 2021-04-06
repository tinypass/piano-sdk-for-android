package io.piano.android.id

import android.webkit.JavascriptInterface

interface PianoIdJs {
    @JavascriptInterface
    fun customEvent(eventData: String)
}
