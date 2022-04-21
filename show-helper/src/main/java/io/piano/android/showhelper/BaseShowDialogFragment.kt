package io.piano.android.showhelper

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

abstract class BaseShowDialogFragment() : AppCompatDialogFragment() {
    constructor(url: String) : this() {
        arguments = Bundle().apply {
            putString(KEY_URL, url)
        }
    }

    private val url: String by lazy {
        arguments?.getString(KEY_URL) ?: "about:blank"
    }
    internal var webView: WebView? = null
    internal var javascriptInterface: BaseJsInterface? = null
    private var shouldLoadUrl = false

    protected val jsInterface: BaseJsInterface?
        get() = javascriptInterface

    protected abstract fun WebView.configure(jsInterface: BaseJsInterface?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shouldLoadUrl = savedInstanceState == null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        webView = LayoutInflater.from(context).inflate(R.layout.fragment_show, null) as WebView
        webView?.configure(javascriptInterface)
        return AlertDialog.Builder(context)
            .setView(webView)
            .create()
    }

    override fun onStart() {
        super.onStart()
        if (shouldLoadUrl) {
            shouldLoadUrl = false
            webView?.loadUrl(url)
        }
    }

    companion object {
        private const val KEY_URL = "url"
    }
}
