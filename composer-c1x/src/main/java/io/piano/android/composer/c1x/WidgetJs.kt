package io.piano.android.composer.c1x

import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.annotation.UiThread
import io.piano.android.composer.PageViewIdProvider
import io.piano.android.cxense.CxenseSdk
import io.piano.android.showhelper.BaseJsInterface
import java.util.Date

public class WidgetJs(
    @get:JavascriptInterface
    public val widgetId: String,
    @get:JavascriptInterface
    public val siteId: String,
    @get:JavascriptInterface
    public val renderTemplateUrl: String = "auto",
) : BaseJsInterface() {

    internal fun init(dialogFragment: ShowRecommendationsDialogFragment?, webView: WebView?) {
        super.init(dialogFragment, webView)
    }

    @get:JavascriptInterface
    public val randomId: String
        get() = PageViewIdProvider.getPageViewId(Date())

    @get:JavascriptInterface
    public val userId: String
        get() = CxenseSdk.getInstance().run { defaultUserId ?: userId }

    @UiThread
    public fun close() {
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }
}
