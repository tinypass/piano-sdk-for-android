package io.piano.android.composer.showtemplate

import android.content.DialogInterface
import android.os.Bundle
import android.webkit.WebView
import io.piano.android.composer.showtemplate.ShowTemplateController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

/**
 * Dialog fragment for displaying Piano Composer templates in a dialog.
 *
 * This class extends [BaseShowDialogFragment] to handle displaying Piano Composer templates in a
 * dialog. It provides functionality for setting up the dialog with the template URL and tracking ID.
 * The template can be displayed in a WebView, and interaction with the JavaScript interface is
 * handled using [ComposerJs].
 *
 * @constructor Creates a new instance of [ShowTemplateDialogFragment].
 *
 * @property url The URL of the Piano Composer template to be displayed.
 * @property trackingId The tracking ID associated with the template.
 */
public class ShowTemplateDialogFragment : BaseShowDialogFragment {
    public constructor() : super()

    /**
     * Creates a new instance of [ShowTemplateDialogFragment] with the provided URL and tracking ID.
     *
     * @param url The URL of the Piano Composer template to be displayed.
     * @param trackingId The tracking ID associated with the template.
     */
    public constructor(url: String, trackingId: String) : super(url) {
        val args = arguments ?: Bundle()
        arguments = args.apply {
            putString(KEY_TRACKING_ID, trackingId)
        }
    }

    /** The tracking ID associated with the template. */
    private val trackingId: String by lazy {
        arguments?.getString(KEY_TRACKING_ID) ?: ""
    }

    /**
     * Configures the WebView with the specified JavaScript interface.
     *
     * This function is called to configure the WebView for displaying the template. It sets up
     * the WebView with the [ShowTemplateDialogFragment] instance, [jsInterface], and [trackingId].
     *
     * @param jsInterface The JavaScript interface used for communication with the template.
     */
    override fun WebView.configure(jsInterface: BaseJsInterface?): Unit =
        prepare(this@ShowTemplateDialogFragment, jsInterface as? ComposerJs, trackingId)

    /**
     * Called when the dialog is canceled.
     *
     * This function is called when the dialog is canceled. It invokes the `close` function of the
     * [ComposerJs] interface, effectively closing the template and performing any additional
     * actions defined in the JavaScript code.
     *
     * @param dialog The DialogInterface that was canceled.
     */
    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        (jsInterface as? ComposerJs)?.close(null)
    }

    /**
     * Companion object holding constant values for the [ShowTemplateDialogFragment].
     */
    private companion object {
        private const val KEY_TRACKING_ID = "trackingId"
    }
}
