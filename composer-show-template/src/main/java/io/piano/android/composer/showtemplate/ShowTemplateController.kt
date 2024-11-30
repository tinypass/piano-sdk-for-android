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
public class ShowTemplateController constructor(
    event: Event<ShowTemplate>,
    jsInterface: ComposerJs? = null,
) : BaseShowController<ShowTemplate, ComposerJs>(event.eventData, jsInterface ?: ComposerJs()) {
    // Private properties
    private val trackingId = event.eventExecutionContext.trackingId

    // Overrides from BaseShowController
    override val url: String = event.eventData.url ?: "about:blank"
    override val fragmentTag: String = FRAGMENT_TAG
    override val fragmentProvider: () -> ShowTemplateDialogFragment = {
        ShowTemplateDialogFragment(url, trackingId)
    }

    override fun WebView.configure(): Unit = prepare(null, jsInterface, trackingId)

    override fun processDelay(activity: FragmentActivity, showFunction: () -> Unit) {
        val func: () -> Unit = {
            if (!activity.isFinishing) {
                showFunction()
            }
        }
        eventData.delayBy.apply {
            if (isDelayedByTime) {
                val handler = Handler(Looper.getMainLooper())
                activity.lifecycle.addObserver(
                    object : DefaultLifecycleObserver {
                        override fun onPause(owner: LifecycleOwner) {
                            handler.removeCallbacksAndMessages(null)
                        }
                    },
                )
                handler.postDelayed(func, value * 1000L)
            } else {
                func()
            }
        }
    }

    /**
     * Closes the template via the JavaScript interface `close` function.
     *
     * @param data Optional data to be passed to the `close` function of the JavaScript interface.
     */
    @Suppress("unused") // Public API.
    @UiThread
    override fun close(data: String?): Unit = jsInterface.close(data)

    /**
     * Sends a reload command to the template. This should be called when the user token has changed.
     *
     * @param userToken The updated user token to be sent to the JavaScript interface.
     */
    @Suppress("unused") // Public API.
    public fun reloadWithToken(userToken: String): Unit = jsInterface.updateToken(userToken)

    internal companion object {
        private const val FRAGMENT_TAG = "ShowTemplateDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "PianoAndroid"

        // Internal function for WebView preparation
        @SuppressLint("SetJavaScriptEnabled")
        internal fun WebView.prepare(
            dialogFragment: ShowTemplateDialogFragment? = null,
            javascriptInterface: ComposerJs?,
            trackingId: String,
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
    }
}
