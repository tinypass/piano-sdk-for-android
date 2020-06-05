package io.piano.android.id;

import android.webkit.JavascriptInterface;

interface PianoIdJsInterface {
    @JavascriptInterface
    void socialLogin(String payload);

    @JavascriptInterface
    void loginSuccess(String payload);

    @JavascriptInterface
    void cancel();
}
