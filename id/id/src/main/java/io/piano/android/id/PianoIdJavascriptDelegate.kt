package io.piano.android.id

import android.webkit.JavascriptInterface
import java.lang.ref.WeakReference

internal class PianoIdJavascriptDelegate(delegate: PianoIdJsInterface) : PianoIdJsInterface {
    private val reference: WeakReference<PianoIdJsInterface> = WeakReference(delegate)

    @JavascriptInterface
    override fun socialLogin(payload: String?) {
        reference.get()?.socialLogin(payload)
    }

    @JavascriptInterface
    override fun loginSuccess(payload: String?) {
        reference.get()?.loginSuccess(payload)
    }

    @JavascriptInterface
    override fun cancel() {
        reference.get()?.cancel()
    }
}
