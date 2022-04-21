package io.piano.android.composer.showtemplate

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import io.piano.android.composer.Composer
import io.piano.android.showhelper.BaseJsInterface

open class ComposerJs : BaseJsInterface() {
    private var trackId: String = ""

    internal fun init(dialogFragment: ShowTemplateDialogFragment?, webView: WebView?, trackingId: String) {
        super.init(dialogFragment, webView)
        trackId = trackingId
    }

    @JavascriptInterface
    @UiThread
    open fun close(eventData: String?) {
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
    fun closeOverridden(eventData: String? = null) {
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }

    internal fun updateToken(userToken: String) {
        executeJavascript("piano.reloadTemplateWithUserToken('$userToken')", 300)
    }
}
