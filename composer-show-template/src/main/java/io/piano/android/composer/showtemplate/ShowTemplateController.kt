package io.piano.android.composer.showtemplate

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.annotation.UiThread
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.showhelper.BaseShowController
import timber.log.Timber

/**
 * A controller for displaying a template using the Piano Composer API.
 *
 * This controller is responsible for showing a template specified by the [ShowTemplate] event.
 * It extends the [BaseShowController] with [ShowTemplate] data and [ComposerJs] JavaScript
 * interface. It handles the template display, delay, and interaction with the JavaScript interface.
 *
 * @param event The [Event] containing the [ShowTemplate] data to be displayed.
 * @param jsInterface The optional [ComposerJs] JavaScript interface for communication with the
 *                    displayed template. If not provided, a default instance of [ComposerJs] will
 *                    be used.
 */
class ShowTemplateController constructor(
    event: Event<ShowTemplate>,
    jsInterface: ComposerJs? = null
) : BaseShowController<ShowTemplate, ComposerJs>(event.eventData, jsInterface ?: ComposerJs()) {
    // Private properties
    private val trackingId = event.eventExecutionContext.trackingId

    // Overrides from BaseShowController
    override val url = event.eventData.url ?: "about:blank"
    override val fragmentTag = FRAGMENT_TAG
    override val fragmentProvider = {
        ShowTemplateDialogFragment(url, trackingId)
    }

    override fun WebView.configure() = prepare(null, jsInterface, trackingId)

    override fun processDelay(activity: FragmentActivity, showFunction: () -> Unit) {
        val func: () -> Unit = {
            if (!activity.isFinishing)
                showFunction()
        }
        eventData.delayBy.apply {
            if (isDelayedByTime) {
                val handler = Handler(Looper.getMainLooper())
                activity.lifecycle.addObserver(
                    object : DefaultLifecycleObserver {
                        override fun onPause(owner: LifecycleOwner) {
                            handler.removeCallbacksAndMessages(null)
                        }
                    }
                )
                handler.postDelayed(func, value * 1000L)
            } else func()
        }
    }

    /**
     * Closes the template via the JavaScript interface `close` function.
     *
     * @param data Optional data to be passed to the `close` function of the JavaScript interface.
     */
    @Suppress("unused") // Public API.
    @UiThread
    override fun close(data: String?) = jsInterface.close(data)

    /**
     * Sends a reload command to the template. This should be called when the user token has changed.
     *
     * @param userToken The updated user token to be sent to the JavaScript interface.
     */
    @Suppress("unused") // Public API.
    fun reloadWithToken(userToken: String) = jsInterface.updateToken(userToken)

    companion object {
        private const val FRAGMENT_TAG = "ShowTemplateDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "PianoAndroid"

        // Internal function for WebView preparation
        @SuppressLint("SetJavaScriptEnabled")
        internal fun WebView.prepare(
            dialogFragment: ShowTemplateDialogFragment? = null,
            javascriptInterface: ComposerJs?,
            trackingId: String
        ) {
            settings.javaScriptEnabled = true
            val jsInterface = javascriptInterface ?: ComposerJs()
            jsInterface.init(dialogFragment, this, trackingId)
            addJavascriptInterface(jsInterface, JAVASCRIPT_INTERFACE)
        }

        // Default WebView provider function
        @JvmStatic
        private val defaultWebViewProvider: (FragmentActivity, String) -> WebView? = { activity, webViewId ->
            activity.resources
                .getIdentifier(webViewId, "id", activity.packageName)
                .takeUnless { it == 0 }
                ?.let { id ->
                    runCatching {
                        activity.findViewById<WebView>(id)
                    }.onFailure {
                        Timber.e(it)
                    }.getOrNull()
                }
        }

        /**
         * Shows the template using the [ShowTemplateController].
         *
         * @param activity The [FragmentActivity] where the template will be displayed.
         * @param showTemplateEvent The [Event] containing the [ShowTemplate] data to be displayed.
         * @param javascriptInterface The optional [ComposerJs] JavaScript interface for
         *                            communication with the displayed template. If not provided,
         *                            a default instance of [ComposerJs] will be used.
         * @param inlineWebViewProvider The function to provide the [WebView] to show the template
         *                               inline within the activity. If not provided, a default
         *                               provider will be used.
         * @return The [ShowTemplateController] instance used for displaying the template.
         *
         * @deprecated Use the constructor and separated method `show()`.
         */
        @Deprecated(
            "Use constructor and separated method `show()`",
            ReplaceWith(
                "ShowTemplateController(showTemplateEvent, javascriptInterface)" +
                    ".apply { show(activity, inlineWebViewProvider) }"
            )
        )
        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        fun show(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: ComposerJs? = null,
            inlineWebViewProvider: (FragmentActivity, String) -> WebView? = defaultWebViewProvider
        ): ShowTemplateController = ShowTemplateController(showTemplateEvent, javascriptInterface).apply {
            show(activity, inlineWebViewProvider)
        }
    }
}
