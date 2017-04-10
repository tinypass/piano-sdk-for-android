package io.piano.android.composer.showtemplate;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import io.piano.android.composer.Composer;

public class ComposerJs {

    private static final Handler HANDLER_MAIN_THREAD = new Handler(Looper.getMainLooper());

    public DialogFragment dialogFragment;
    public WebView webView;

    private String endpointUrl;
    private String trackingId;

    public ComposerJs() {
    }

    ComposerJs(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    ComposerJs(WebView webView) {
        this.webView = webView;
    }

    void init(String endpointUrl, String trackingId) {
        this.endpointUrl =  endpointUrl;
        this.trackingId = trackingId;
    }

    @JavascriptInterface
    public final void close(String eventData) {
        Composer.trackExternalEvent(endpointUrl, trackingId);

        closeOverridden(eventData);
    }

    @JavascriptInterface
    public void closeAndRefresh(String eventData) {
        closeOverridden(eventData);
    }

    @JavascriptInterface
    public void customEvent(String eventData) {
    }

    @JavascriptInterface
    public void register(String eventData) {
    }

    @JavascriptInterface
    public void login(String eventData) {
    }

    @JavascriptInterface
    public void logout(String eventData) {
    }

    public void closeOverridden(String eventData) {
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
        } else if (webView != null) {
            HANDLER_MAIN_THREAD.post(new Runnable() {
                @Override
                public void run() {
                    webView.setVisibility(View.GONE);
                    webView.loadUrl("about:blank");
                }
            });
        }
    }
}
