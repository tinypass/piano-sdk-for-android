package io.piano.android.composer.showtemplate

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import io.piano.android.composer.Composer
import io.piano.android.showhelper.BaseJsInterface

/**
 * JavaScript interface for communication with the Piano Composer templates.
 *
 * This class provides methods to interact with the JavaScript code embedded in Piano Composer
 * templates. It extends [BaseJsInterface] to inherit common functionality for JavaScript
 * interfaces. It allows executing custom JavaScript functions and handling various events from
 * the templates.
 */
public open class ComposerJs : BaseJsInterface() {
    private var trackId: String = ""

    /**
     * Initializes the JavaScript interface.
     *
     * This function is used to initialize the JavaScript interface with the provided [trackingId].
     *
     * @param dialogFragment The [ShowTemplateDialogFragment] associated with the template,
     *                       if displayed in a dialog.
     * @param webView The [WebView] used for displaying the template.
     * @param trackingId The tracking ID associated with the template.
     */
    internal fun init(dialogFragment: ShowTemplateDialogFragment?, webView: WebView?, trackingId: String) {
        super.init(dialogFragment, webView)
        trackId = trackingId
    }

    /**
     * Closes the template and performs additional actions.
     *
     * This function is called from the JavaScript code when the template's close function is
     * invoked. It triggers a close event tracking and performs additional close actions.
     *
     * @param eventData Optional data passed from the template to the close function.
     */
    @JavascriptInterface
    @UiThread
    public open fun close(eventData: String?) {
        Composer.getInstance().trackCloseEvent(trackId)
        closeOverridden(eventData)
    }

    /**
     * Closes the template without triggering additional actions.
     *
     * This function is called from the JavaScript code when the template's close and refresh
     * function is invoked. It only performs the basic close action without any additional
     * event tracking.
     *
     * @param eventData Optional data passed from the template to the close function.
     */
    @JavascriptInterface
    @UiThread
    public open fun closeAndRefresh(eventData: String?) {
        // Perform basic close action without any additional event tracking
        closeOverridden(eventData)
    }

    /**
     * Handles custom events received from the template.
     *
     * This function is called from the JavaScript code when a custom event is triggered
     * in the template. It can be overridden in subclasses to handle specific custom events.
     *
     * @param eventData Data passed from the template to the custom event function.
     */
    @JavascriptInterface
    public open fun customEvent(eventData: String) {
        // Implementation details of handling custom events go here...
    }

    /**
     * Registers a user via the template.
     *
     * This function is called from the JavaScript code when the template's register function
     * is invoked. It can be overridden in subclasses to perform additional actions upon user
     * registration.
     *
     * @param eventData Data passed from the template to the register function.
     */
    @JavascriptInterface
    public open fun register(eventData: String) {
        // Implementation details of handling user registration go here...
    }

    /**
     * Logs in a user via the template.
     *
     * This function is called from the JavaScript code when the template's login function
     * is invoked. It can be overridden in subclasses to perform additional actions upon user
     * login.
     *
     * @param eventData Data passed from the template to the login function.
     */
    @JavascriptInterface
    public open fun login(eventData: String) {
        // Implementation details of handling user login go here...
    }

    /**
     * Logs out a user via the template.
     *
     * This function is called from the JavaScript code when the template's logout function
     * is invoked. It can be overridden in subclasses to perform additional actions upon user
     * logout.
     *
     * @param eventData Data passed from the template to the logout function.
     */
    @JavascriptInterface
    public open fun logout(eventData: String) {
        // Implementation details of handling user logout go here...
    }

    /**
     * Closes the template or the associated dialog.
     *
     * This function is called to close the template or the associated dialog if it is displayed.
     * If a dialog is present, it is dismissed; otherwise, the WebView is hidden and its content
     * is cleared by loading "about:blank".
     *
     * @param eventData Optional data passed from the template to the close function.
     */
    @UiThread
    public fun closeOverridden(eventData: String? = null) {
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }

    /**
     * Updates the user token in the template.
     *
     * This function is used to send a reload command to the template with an updated user token.
     * It is typically called when the user token has changed and needs to be updated in the
     * template to access personalized content.
     *
     * @param userToken The updated user token to be sent to the template.
     */
    internal fun updateToken(userToken: String) {
        executeJavascript("piano.reloadTemplateWithUserToken('$userToken')", 300)
    }
}
