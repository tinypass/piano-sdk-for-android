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

class ShowTemplateController constructor(
    event: Event<ShowTemplate>,
    jsInterface: ComposerJs? = null
) : BaseShowController<ShowTemplate, ComposerJs>(event.eventData, jsInterface ?: ComposerJs()) {
    private val trackingId = event.eventExecutionContext.trackingId
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
     * Closes template via JS interface `close` function. It's an alias to `javascriptInterface.close(eventData)`
     */
    @Suppress("unused") // Public API.
    @UiThread
    override fun close(data: String?) = jsInterface.close(data)

    /**
     * Sends reload command to template. Should be called when user token has changed
     */
    @Suppress("unused") // Public API.
    fun reloadWithToken(userToken: String) = jsInterface.updateToken(userToken)

    companion object {
        private const val FRAGMENT_TAG = "ShowTemplateDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "PianoAndroid"

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
