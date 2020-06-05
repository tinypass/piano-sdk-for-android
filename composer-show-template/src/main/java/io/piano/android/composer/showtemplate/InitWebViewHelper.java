package io.piano.android.composer.showtemplate;

import android.os.Build;
import android.webkit.WebView;

import androidx.fragment.app.DialogFragment;

class InitWebViewHelper {

    private static final String JAVASCRIPT_INTERFACE = "PianoAndroid";

    static void initWebView(DialogFragment dialogFragment, WebView webView, Object javascriptInterface, String trackingId) {
        webView.getSettings().setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }

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
            composerJs.init(trackingId);
        }

        webView.addJavascriptInterface(javascriptInterface, JAVASCRIPT_INTERFACE);
    }
}
