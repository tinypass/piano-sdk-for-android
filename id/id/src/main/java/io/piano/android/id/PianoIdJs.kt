package io.piano.android.id

import android.webkit.JavascriptInterface

public interface PianoIdJs {
    @JavascriptInterface
    public fun customEvent(eventData: String)
}
