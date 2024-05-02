package io.piano.android.showform

import android.os.Bundle
import android.webkit.WebView
import io.piano.android.showform.ShowFormController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

/**
 * Dialog fragment for displaying Piano ID custom forms in a dialog.
 *
 * This class extends [BaseShowDialogFragment] to handle displaying Piano ID custom forms in a
 * dialog. It provides functionality for setting up the dialog with the form URL, form name and tracking ID.
 * The form displayed in a WebView
 *
 * @constructor Creates a new instance of [ShowFormDialogFragment].
 *
 * @property url The URL of the Piano ID form to be displayed.
 * @property formName The name of the form.
 * @property trackingId The tracking ID associated with the form.
 */
public class ShowFormDialogFragment : BaseShowDialogFragment {
    public constructor() : super()

    /**
     * Creates a new instance of [ShowFormDialogFragment] with the provided URL and tracking ID.
     *
     * @param url The URL of the Piano ID custom form to be displayed.
     * @param formName The name of the Piano ID custom form.
     * @param trackingId The tracking ID associated with the template.
     */
    internal constructor(url: String, formName: String, trackingId: String) : super(url) {
        val args = arguments ?: Bundle()
        arguments = args.apply {
            putString(KEY_FORM_NAME, formName)
            putString(KEY_TRACKING_ID, trackingId)
        }
    }

    /** Name associated with the form. */
    private val formName: String by lazy {
        arguments?.getString(KEY_FORM_NAME) ?: ""
    }

    /** The tracking ID associated with the form. */
    private val trackingId: String by lazy {
        arguments?.getString(KEY_TRACKING_ID) ?: ""
    }

    /**
     * Configures the WebView with the specified JavaScript interface.
     *
     * This function is called to configure the WebView for displaying the template. It sets up
     * the WebView with the [ShowFormDialogFragment] instance, [jsInterface], [formName], and [trackingId].
     *
     * @param jsInterface The JavaScript interface used for communication with the form.
     */
    override fun WebView.configure(jsInterface: BaseJsInterface?) {
        val javascriptInterface = jsInterface as? ShowFormJs ?: ShowFormJs(formName, trackingId)
        prepare(
            javascriptInterface,
            this@ShowFormDialogFragment
        )
    }

    private companion object {
        private const val KEY_FORM_NAME = "formName"
        private const val KEY_TRACKING_ID = "trackingId"
    }
}
