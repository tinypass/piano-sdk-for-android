package io.piano.android.showform

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import io.piano.android.composer.Composer
import io.piano.android.showhelper.BaseJsInterface
import timber.log.Timber
import kotlin.properties.Delegates

class ShowFormJs(
    private val formName: String,
    private val trackingId: String,
    internal var loginCallback: () -> Unit = {}
) : BaseJsInterface() {
    internal var token: String by Delegates.observable("") { _, oldValue, newValue ->
        if (isReady && oldValue != newValue)
            updateToken()
    }
    private var isReady: Boolean = false

    internal fun init(dialogFragment: ShowFormDialogFragment?, webView: WebView?, loginCallback: () -> Unit) {
        super.init(dialogFragment, webView)
        this.loginCallback = loginCallback
    }

    private fun updateToken() {
        executeJavascript(
            """PianoIDMobileSDK.messageCallback('{"event":"setToken","params":"$token"}')""",
            200
        )
    }

    @JavascriptInterface
    fun postMessage(data: String) {
        ShowFormController.eventAdapter.fromJson(data)?.apply {
            when (event) {
                "formSend" -> {
                    Composer.getInstance().trackCustomFormSubmission(formName, trackingId)
                    close()
                }
                "formSkip" -> close()
                "stateReady" -> {
                    isReady = true
                    Composer.getInstance().trackCustomFormImpression(formName, trackingId)
                    updateToken()
                }
                "tokenRejected" -> loginCallback.invoke()
            }
        } ?: Timber.d("Can't parse $data")
    }

    @UiThread
    fun close() {
        Composer.getInstance().trackExternalEvent(trackingId)
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }
}
