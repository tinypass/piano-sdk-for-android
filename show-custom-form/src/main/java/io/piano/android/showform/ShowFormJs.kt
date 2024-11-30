package io.piano.android.showform

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import io.piano.android.composer.Composer
import io.piano.android.id.FormHelper
import io.piano.android.showhelper.BaseJsInterface
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * JavaScript interface for communication with the Piano ID custom form.
 *
 * This class provides methods to interact with the JavaScript code embedded in Piano ID
 * custom forms. It extends [BaseJsInterface] to inherit common functionality for JavaScript
 * interfaces. It allows executing custom JavaScript functions and handling various events from
 * the form.
 */
public class ShowFormJs(
    private val formName: String,
    private val trackingId: String,
    internal var loginCallback: () -> Unit = {},
) : BaseJsInterface() {
    internal var token: String by Delegates.observable("") { _, oldValue, newValue ->
        if (isReady && oldValue != newValue) {
            updateToken()
        }
    }
    private var isReady: Boolean = false

    /**
     * Initializes the JavaScript interface.
     *
     * This function is used to initialize the JavaScript interface with the provided [trackingId].
     *
     * @param dialogFragment The [ShowFormDialogFragment] associated with the template,
     *                       if displayed in a dialog.
     * @param webView The [WebView] used for displaying the template.
     * @param loginCallback Callback, which be called if current user access token is invalid.
     */
    internal fun init(dialogFragment: ShowFormDialogFragment?, webView: WebView?, loginCallback: () -> Unit) {
        super.init(dialogFragment, webView)
        this.loginCallback = loginCallback
    }

    private fun updateToken() {
        executeJavascript(
            """PianoIDMobileSDK.messageCallback('{"event":"setToken","params":"$token"}')""",
            200,
        )
    }

    /**
     * Handles events from JavaScript.
     *
     * This function is called from the JavaScript code when an event is triggered in the form.
     *
     * @param data Data passed from the form to the function.
     */
    @JavascriptInterface
    public fun postMessage(data: String) {
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
                    executeJavascript(FormHelper.buildConsentsCode())
                    updateToken()
                }
                "tokenRejected" -> loginCallback()
            }
        } ?: Timber.d("Can't parse $data")
    }

    /**
     * Closes the template and performs additional actions.
     *
     * This function is called from the JavaScript code when the template's close function is
     * invoked. It triggers a close event tracking and performs additional close actions.
     *
     * @param eventData Optional data passed from the template to the close function.
     */
    @UiThread
    public fun close() {
        Composer.getInstance().trackCloseEvent(trackingId)
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }
}
