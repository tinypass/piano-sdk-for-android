package io.piano.android.composer.showtemplate

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebView
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowTemplate
import timber.log.Timber

class ShowTemplateController private constructor(
    private val jsInterface: ComposerJs,
    private val webView: WebView? = null,
    private val fragment: ShowTemplateDialogFragment? = null
) {
    init {
        require(webView != null || fragment != null) {
            "Can't create controller without webview or fragment"
        }
    }

    /**
     * Closes template via JS interface `close` function. It's an alias to `javascriptInterface.close(eventData)`
     */
    @Suppress("unused") // Public API.
    @UiThread
    fun close(eventData: String) = jsInterface.close(eventData)

    /**
     * Sends reload command to template. Should be called when user token has changed
     */
    @Suppress("unused") // Public API.
    fun reloadWithToken(userToken: String) {
        val view = fragment?.webView ?: webView
        view?.postDelayed(
            { view.executeJavascriptCode("piano.reloadTemplateWithUserToken('$userToken')") },
            300
        ) ?: Timber.w("We got null for webview")
    }

    companion object {
        private const val FRAGMENT_TAG = "ShowTemplateDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "PianoAndroid"

        internal fun WebView.executeJavascriptCode(code: String) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                evaluateJavascript(code, null)
            else loadUrl("javascript:$code")

        @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface")
        internal fun WebView.prepare(
            dialogFragment: DialogFragment? = null,
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

        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        fun show(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: ComposerJs? = null,
            inlineWebViewProvider: (FragmentActivity, String) -> WebView? = defaultWebViewProvider
        ): ShowTemplateController? {
            val jsInterface = javascriptInterface ?: ComposerJs()
            return when (showTemplateEvent.eventData.displayMode) {
                ShowTemplate.DisplayMode.MODAL -> showModal(activity, showTemplateEvent, jsInterface)
                ShowTemplate.DisplayMode.INLINE -> showInline(
                    activity,
                    showTemplateEvent,
                    jsInterface,
                    inlineWebViewProvider
                )
                else -> null.also {
                    Timber.w("Unknown display mode %s", showTemplateEvent.eventData.displayMode)
                }
            }
        }

        @JvmStatic
        private fun showInline(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: ComposerJs,
            webViewProvider: (FragmentActivity, String) -> WebView?
        ): ShowTemplateController? =
            with(showTemplateEvent) {
                eventData.containerSelector
                    .takeUnless { it.isNullOrEmpty() }
                    ?.let { id ->
                        runCatching {
                            val webView = requireNotNull(webViewProvider(activity, id)) {
                                "Can't find WebView with id $id"
                            }
                            webView.prepare(
                                javascriptInterface = javascriptInterface,
                                trackingId = eventExecutionContext.trackingId
                            )
                            ShowTemplateController(javascriptInterface, webView = webView).also {
                                processDelay(activity, eventData) {
                                    eventData.url?.let {
                                        webView.loadUrl(it)
                                        webView.visibility = View.VISIBLE
                                    }
                                }
                            }
                        }.onFailure {
                            Timber.e(it)
                        }.getOrNull()
                    }
            }

        @JvmStatic
        private fun showModal(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: ComposerJs
        ): ShowTemplateController =
            with(showTemplateEvent) {
                val dialogFragment =
                    ShowTemplateDialogFragment.newInstance(
                        eventData.url ?: "about:blank",
                        eventExecutionContext.trackingId
                    ).apply {
                        isCancelable = eventData.showCloseButton
                        jsInterface = javascriptInterface
                    }
                ShowTemplateController(javascriptInterface, fragment = dialogFragment).also {
                    processDelay(activity, eventData) {
                        dialogFragment.show(activity.supportFragmentManager, FRAGMENT_TAG)
                    }
                }
            }

        @JvmStatic
        private fun processDelay(activity: FragmentActivity, showTemplate: ShowTemplate, showFunction: () -> Unit) =
            showTemplate.delayBy.apply {
                val func: () -> Unit = {
                    if (!activity.isFinishing)
                        showFunction()
                }
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
}
