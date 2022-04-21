package io.piano.android.showform

import android.os.Bundle
import android.webkit.WebView
import io.piano.android.showform.ShowFormController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

class ShowFormDialogFragment : BaseShowDialogFragment {
    constructor() : super()
    constructor(url: String, trackingId: String) : super(url) {
        val args = arguments ?: Bundle()
        arguments = args.apply {
            putString(KEY_TRACKING_ID, trackingId)
        }
    }

    private val trackingId: String by lazy {
        arguments?.getString(KEY_TRACKING_ID) ?: ""
    }

    override fun WebView.configure(jsInterface: BaseJsInterface?) {
        val javascriptInterface = jsInterface as? ShowFormJs ?: ShowFormJs(trackingId)
        prepare(
            javascriptInterface,
            this@ShowFormDialogFragment
        )
    }

    companion object {
        private const val KEY_TRACKING_ID = "trackingId"
    }
}
