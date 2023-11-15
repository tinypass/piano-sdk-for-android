package io.piano.android.showform

import android.annotation.SuppressLint
import android.webkit.WebView
import com.squareup.moshi.Moshi
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowForm
import io.piano.android.id.FormHelper
import io.piano.android.id.PianoId
import io.piano.android.showhelper.BaseShowController
import timber.log.Timber

/**
 * A controller for displaying a custom form using the Piano ID API.
 *
 * This controller is responsible for showing a custom form specified by the [ShowForm] event.
 * It extends the [BaseShowController] with [ShowForm] data and handles form display.
 *
 * @param event The [Event] containing the [ShowForm] data to be displayed.
 * @param initialToken Initial value for user access token.
 * @param loginCallback Callback, which be called if current user access token is invalid.
 */
class ShowFormController(event: Event<ShowForm>, initialToken: String = "", private val loginCallback: () -> Unit) :
    BaseShowController<ShowForm, ShowFormJs>(
        event.eventData,
        ShowFormJs(event.eventData.formName, event.eventExecutionContext.trackingId, loginCallback)
    ) {
    private var checkProfileAtTokenChange: Boolean = false
    private val trackingId = event.eventExecutionContext.trackingId
    override val url: String by lazy {
        with(eventData) {
            FormHelper.buildUrl(formName, hideCompletedFields, trackingId)
        }
    }
    override val fragmentTag = FRAGMENT_TAG
    override val fragmentProvider = { ShowFormDialogFragment(url, eventData.formName, trackingId) }

    init {
        updateToken(initialToken)
    }

    private fun checkFormNotFilled(accessToken: String, callback: (Boolean) -> Unit) {
        if (accessToken.isEmpty()) {
            callback(true)
        } else {
            PianoId.getInstance().getUserInfo(accessToken, eventData.formName) { r ->
                val shouldHideForm = r.onFailure {
                    Timber.w(it)
                }.getOrNull()?.allCustomFieldsFilled ?: false
                callback(!shouldHideForm)
            }
        }
    }

    override fun checkPrerequisites(callback: (Boolean) -> Unit) {
        checkFormNotFilled(jsInterface.token) { formCanBeShown ->
            checkProfileAtTokenChange = formCanBeShown
            callback(formCanBeShown)
        }
    }

    override fun close(data: String?) = jsInterface.close()

    override fun WebView.configure() = prepare(jsInterface, loginCallback = loginCallback)

    /**
     * Updates user token for form.
     *
     * @param userToken The updated user token
     */
    fun updateToken(userToken: String) {
        jsInterface.token = userToken
        if (checkProfileAtTokenChange) {
            checkFormNotFilled(userToken) { formNotFilled ->
                if (!formNotFilled) {
                    close()
                }
            }
        }
    }

    companion object {
        private const val FRAGMENT_TAG = "ShowFormDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "PianoIDMobileSDK"
        internal val eventAdapter = Moshi.Builder().build().adapter(EventData::class.java)

        @SuppressLint("SetJavaScriptEnabled")
        internal fun WebView.prepare(
            jsInterface: ShowFormJs,
            dialogFragment: ShowFormDialogFragment? = null,
            loginCallback: () -> Unit = jsInterface.loginCallback,
        ) {
            settings.javaScriptEnabled = true
            jsInterface.init(dialogFragment, this, loginCallback)
            addJavascriptInterface(jsInterface, JAVASCRIPT_INTERFACE)
        }
    }
}
