package io.piano.android.showhelper

import android.webkit.WebView
import timber.log.Timber

abstract class BaseJsInterface {
    protected var fragment: BaseShowDialogFragment? = null
    protected var webView: WebView? = null

    protected fun init(dialogFragment: BaseShowDialogFragment?, webView: WebView?) {
        fragment = dialogFragment
        this.webView = webView
    }

    protected fun executeJavascript(code: String, delay: Long = 0) {
        val view = fragment?.webView ?: webView
        view?.also {
            val func = { it.evaluateJavascript(code, null) }
            if (delay > 0) it.postDelayed(func, delay) else func()
        } ?: Timber.w("We got null for webview")
    }
}
