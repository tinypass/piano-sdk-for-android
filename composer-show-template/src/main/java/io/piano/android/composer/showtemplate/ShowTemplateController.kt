package io.piano.android.composer.showtemplate

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowTemplate
import timber.log.Timber

class ShowTemplateController private constructor(
    private val webView: WebView? = null,
    private val fragment: ShowTemplateDialogFragment? = null
) {
    init {
        require(webView != null || fragment != null) {
            "Can't create controller without webview or fragment"
        }
    }

    @Suppress("unused") // Public API.
    fun reloadWithToken(userToken: String) {
        val view = fragment?.webView ?: webView
        view?.executeJavascriptCode("piano.reloadTemplateWithUserToken('$userToken')")
            ?: Timber.w("We got null for webview")
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
            javascriptInterface: Any?,
            trackingId: String
        ) {
            settings.javaScriptEnabled = true
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
            }
            val jsInterface = when (javascriptInterface) {
                null -> ComposerJs().also { cJs ->
                    cJs.init(dialogFragment, this, trackingId)
                }
                is ComposerJs -> javascriptInterface.also { cJs ->
                    cJs.init(dialogFragment, this, trackingId)
                }
                else -> javascriptInterface
            }
            addJavascriptInterface(jsInterface, JAVASCRIPT_INTERFACE)
        }

        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        fun show(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: Any? = null
        ): ShowTemplateController? =
            when (showTemplateEvent.eventData.displayMode) {
                ShowTemplate.DisplayMode.MODAL -> showModal(activity, showTemplateEvent, javascriptInterface)
                ShowTemplate.DisplayMode.INLINE -> showInline(activity, showTemplateEvent, javascriptInterface)
                else -> null.also {
                    Timber.w("Unknown display mode %s", showTemplateEvent.eventData.displayMode)
                }
            }

        @JvmStatic
        private fun showInline(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: Any? = null
        ): ShowTemplateController? =
            with(showTemplateEvent) {
                eventData.containerSelector
                    .takeUnless { it.isNullOrEmpty() }
                    ?.let { cId ->
                        activity.resources
                            .getIdentifier(cId, "id", activity.packageName)
                            .takeUnless { it == 0 }
                            ?.let { id ->
                                runCatching {
                                    val webView: WebView = activity.findViewById(id)
                                    webView.prepare(
                                        javascriptInterface = javascriptInterface,
                                        trackingId = eventExecutionContext.trackingId
                                    )
                                    ShowTemplateController(webView = webView).also {
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
            }

        @JvmStatic
        private fun showModal(
            activity: FragmentActivity,
            showTemplateEvent: Event<ShowTemplate>,
            javascriptInterface: Any? = null
        ): ShowTemplateController =
            with(showTemplateEvent) {
                val dialogFragment =
                    ShowTemplateDialogFragment.newInstance(
                        eventData.url ?: "about:blank",
                        eventExecutionContext.trackingId
                    ).apply {
                        isCancelable = eventData.showCloseButton
                        javascriptInterface?.let {
                            jsInterface = it
                        }
                    }
                ShowTemplateController(fragment = dialogFragment).also {
                    processDelay(activity, eventData) {
                        dialogFragment.show(activity.supportFragmentManager, FRAGMENT_TAG)
                    }
                }
            }

        @JvmStatic
        private fun processDelay(activity: FragmentActivity, showTemplate: ShowTemplate, showFunction: () -> Unit) {
            val handler = Handler(Looper.getMainLooper())
            val func: () -> Unit = {
                if (!activity.isFinishing)
                    showFunction()
            }
            with(showTemplate.delayBy) {
                if (isDelayedByTime) {
                    activity.lifecycle.addObserver(
                        object : DefaultLifecycleObserver {
                            override fun onPause(owner: LifecycleOwner) {
                                handler.removeCallbacks(func)
                            }
                        }
                    )
                    handler.postDelayed(func, value * 1000L)
                } else func()
            }
        }
    }
}
