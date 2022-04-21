package io.piano.android.composer.showtemplate

import android.content.DialogInterface
import android.os.Bundle
import android.webkit.WebView
import io.piano.android.composer.Composer
import io.piano.android.composer.showtemplate.ShowTemplateController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

class ShowTemplateDialogFragment : BaseShowDialogFragment {
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

    override fun WebView.configure(jsInterface: BaseJsInterface?) =
        prepare(this@ShowTemplateDialogFragment, jsInterface as? ComposerJs, trackingId)

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Composer.getInstance().trackExternalEvent(trackingId)
        (jsInterface as? ComposerJs)?.closeOverridden()
    }

    companion object {
        private const val KEY_TRACKING_ID = "trackingId"
    }
}
