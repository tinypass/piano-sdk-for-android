package io.piano.android.showhelper

import android.webkit.WebView

abstract class BaseJsInterface {
    protected var fragment: BaseShowDialogFragment? = null
    protected var webView: WebView? = null

    protected fun init(dialogFragment: BaseShowDialogFragment?, webView: WebView?) {
        fragment = dialogFragment
        this.webView = webView
    }
}
