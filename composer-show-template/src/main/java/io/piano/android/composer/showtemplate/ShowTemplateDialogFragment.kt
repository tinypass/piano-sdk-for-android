package io.piano.android.composer.showtemplate

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import io.piano.android.composer.Composer
import io.piano.android.composer.showtemplate.ShowTemplateController.Companion.prepare

class ShowTemplateDialogFragment : AppCompatDialogFragment() {
    private val trackingId: String by lazy {
        arguments?.getString(KEY_TRACKING_ID) ?: ""
    }
    private val url: String by lazy {
        arguments?.getString(KEY_URL) ?: "about:blank"
    }
    internal lateinit var webView: WebView
    internal var jsInterface: Any? = null
    private var shouldLoadUrl = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shouldLoadUrl = savedInstanceState == null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        webView = LayoutInflater.from(context).inflate(R.layout.fragment_show_template, null) as WebView
        webView.prepare(this, jsInterface, trackingId)
        return AlertDialog.Builder(context)
            .setView(webView)
            .create()
    }

    override fun onStart() {
        super.onStart()
        if (shouldLoadUrl) {
            shouldLoadUrl = false
            webView.loadUrl(url)
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Composer.getInstance().trackExternalEvent(trackingId)
        (jsInterface as? ClosableJs)?.closeOverridden()
    }

    companion object {
        private const val KEY_URL = "url"
        private const val KEY_TRACKING_ID = "trackingId"

        @JvmStatic
        fun newInstance(url: String, trackingId: String): ShowTemplateDialogFragment =
            ShowTemplateDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_URL, url)
                    putString(KEY_TRACKING_ID, trackingId)
                }
            }
    }
}
