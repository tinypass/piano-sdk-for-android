package io.piano.android.showhelper

import android.os.Build
import android.view.View
import android.webkit.WebView
import androidx.annotation.UiThread
import androidx.fragment.app.FragmentActivity
import io.piano.android.composer.model.DisplayMode
import io.piano.android.composer.model.events.BaseShowType
import timber.log.Timber

abstract class BaseShowController<T : BaseShowType, V : BaseJsInterface> constructor(
    protected val eventData: T,
    protected val jsInterface: V
) {

    abstract val url: String
    abstract val fragmentTag: String
    abstract val fragmentProvider: () -> BaseShowDialogFragment

    protected abstract fun WebView.configure()
    protected open fun processDelay(activity: FragmentActivity, showFunction: () -> Unit) = showFunction()

    protected open fun checkPrerequisites(callback: (Boolean) -> Unit) = callback(true)

    @JvmOverloads
    @Suppress("unused") // Public API.
    @UiThread
    fun show(
        activity: FragmentActivity,
        inlineWebViewProvider: (FragmentActivity, String) -> WebView? = defaultWebViewProvider
    ) {
        checkPrerequisites { canShow ->
            if (canShow) {
                when (eventData.displayMode) {
                    DisplayMode.MODAL -> showModal(activity)
                    DisplayMode.INLINE -> showInline(activity, inlineWebViewProvider)
                    else -> Timber.w("Unknown display mode %s", eventData.displayMode)
                }
            } else {
                Timber.w("Showing forbidden due to prerequisites ")
            }
        }
    }

    @Suppress("unused") // Public API.
    @UiThread
    abstract fun close(data: String? = null)

    private fun showInline(
        activity: FragmentActivity,
        webViewProvider: (FragmentActivity, String) -> WebView?
    ) = eventData.containerSelector
        .takeUnless { it.isNullOrEmpty() }
        ?.let { id ->
            runCatching {
                val webView = requireNotNull(webViewProvider(activity, id)) {
                    "Can't find WebView with id $id"
                }
                webView.configure()
                processDelay(activity) {
                    webView.loadUrl(url)
                    webView.visibility = View.VISIBLE
                }
            }.onFailure {
                Timber.e(it)
            }.getOrNull()
        }

    private fun showModal(
        activity: FragmentActivity
    ) = fragmentProvider().apply {
        isCancelable = eventData.showCloseButton
        javascriptInterface = jsInterface
        processDelay(activity) {
            show(activity.supportFragmentManager, fragmentTag)
        }
    }

    companion object {
        fun WebView.executeJavascriptCode(code: String) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                evaluateJavascript(code, null)
            else loadUrl("javascript:$code")

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
