package io.piano.android.id;

import android.webkit.JavascriptInterface;

import java.lang.ref.WeakReference;

class PianoIdJavascriptDelegate implements PianoIdJsInterface {
    private final WeakReference<PianoIdJsInterface> reference;

    PianoIdJavascriptDelegate(PianoIdJsInterface delegate) {
        this.reference = new WeakReference<>(delegate);
    }

    @JavascriptInterface
    public void socialLogin(String payload) {
        PianoIdJsInterface delegate = reference.get();
        if (delegate != null)
            delegate.socialLogin(payload);
    }

    @JavascriptInterface
    public void loginSuccess(String payload) {
        PianoIdJsInterface delegate = reference.get();
        if (delegate != null)
            delegate.loginSuccess(payload);
    }

    @JavascriptInterface
    public void cancel() {
        PianoIdJsInterface delegate = reference.get();
        if (delegate != null)
            delegate.cancel();
    }
}
