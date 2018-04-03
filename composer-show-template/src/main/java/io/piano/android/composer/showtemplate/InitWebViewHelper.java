package io.piano.android.composer.showtemplate;

import android.support.v4.app.DialogFragment;
import android.webkit.WebView;

class InitWebViewHelper {

    private static final String JAVASCRIPT_INTERFACE = "PianoAndroid";

    static void initWebView(DialogFragment dialogFragment, WebView webView, Object javascriptInterface, String endpointUrl, String trackingId) {
        webView.getSettings().setJavaScriptEnabled(true);

        if (javascriptInterface == null) {
            if (dialogFragment == null) {
                javascriptInterface = new ComposerJs(webView);
            } else {
                javascriptInterface = new ComposerJs(dialogFragment);
            }
        } else if (javascriptInterface instanceof ComposerJs) {
            ComposerJs composerJs = (ComposerJs) javascriptInterface;
            composerJs.dialogFragment = dialogFragment;
            composerJs.webView = webView;
        }

        if (javascriptInterface instanceof ComposerJs) {
            ComposerJs composerJs = (ComposerJs) javascriptInterface;
            composerJs.init(endpointUrl, trackingId);
        }

        webView.addJavascriptInterface(javascriptInterface, JAVASCRIPT_INTERFACE);
    }
}
