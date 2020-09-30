package io.piano.android.composer.showtemplate

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import io.piano.android.composer.Composer

open class ComposerJs : ClosableJs {
    private var fragment: DialogFragment? = null
    private var webView: WebView? = null
    private var trackId: String = ""

    internal fun init(dialogFragment: DialogFragment?, webView: WebView?, trackingId: String) {
        fragment = dialogFragment
        this.webView = webView
        trackId = trackingId
    }

    @JavascriptInterface
    @UiThread
    open fun close(eventData: String) {
        Composer.getInstance().trackExternalEvent(trackId)
        closeOverridden(eventData)
    }

    @JavascriptInterface
    @UiThread
    open fun closeAndRefresh(eventData: String?) {
        closeOverridden(eventData)
    }

    @JavascriptInterface
    open fun customEvent(eventData: String) {
    }

    @JavascriptInterface
    open fun register(eventData: String) {
    }

    @JavascriptInterface
    open fun login(eventData: String) {
    }

    @JavascriptInterface
    open fun logout(eventData: String) {
    }

    @UiThread
    override fun closeOverridden(eventData: String?) {
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }
}
