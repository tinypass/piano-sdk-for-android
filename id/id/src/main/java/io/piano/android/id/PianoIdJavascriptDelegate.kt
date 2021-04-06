package io.piano.android.id

import android.webkit.JavascriptInterface
import java.lang.ref.WeakReference

internal class PianoIdJavascriptDelegate(
    internalDelegate: PianoIdJsInterface,
    publicDelegate: PianoIdJs?
) : PianoIdJsInterface, PianoIdJs {
    private val internalJsReference: WeakReference<PianoIdJsInterface> = WeakReference(internalDelegate)
    private val publicJsReference: WeakReference<PianoIdJs?> = WeakReference(publicDelegate)

    @JavascriptInterface
    override fun socialLogin(payload: String?) {
        internalJsReference.get()?.socialLogin(payload)
    }

    @JavascriptInterface
    override fun registerSuccess(payload: String?) {
        internalJsReference.get()?.registerSuccess(payload)
    }

    @JavascriptInterface
    override fun loginSuccess(payload: String?) {
        internalJsReference.get()?.loginSuccess(payload)
    }

    @JavascriptInterface
    override fun cancel() {
        internalJsReference.get()?.cancel()
    }

    @JavascriptInterface
    override fun customEvent(eventData: String) {
        publicJsReference.get()?.customEvent(eventData)
    }
}
