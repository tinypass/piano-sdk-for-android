package io.piano.android.composer.c1x

import android.view.View
import android.webkit.JavascriptInterface
import androidx.annotation.UiThread
import com.cxense.cxensesdk.CxenseSdk
import io.piano.android.composer.PageViewIdProvider
import io.piano.android.showhelper.BaseJsInterface
import java.util.Date

class WidgetJs(
    @get:JavascriptInterface
    val widgetId: String,
    @get:JavascriptInterface
    val siteId: String,
    @get:JavascriptInterface
    val renderTemplateUrl: String = "auto"
) : BaseJsInterface() {

    @get:JavascriptInterface
    val randomId: String
        get() = PageViewIdProvider.getPageViewId(Date())

    @get:JavascriptInterface
    val userId: String
        get() = CxenseSdk.getInstance().run { defaultUserId ?: userId }

    @UiThread
    fun close() {
        fragment?.dismissAllowingStateLoss()
            ?: webView?.apply {
                visibility = View.GONE
                loadUrl("about:blank")
            }
    }
}
