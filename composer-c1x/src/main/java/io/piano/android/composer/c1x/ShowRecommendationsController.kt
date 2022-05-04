package io.piano.android.composer.c1x

import android.annotation.SuppressLint
import android.webkit.WebView
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowRecommendations
import io.piano.android.showhelper.BaseShowController

class ShowRecommendationsController(event: Event<ShowRecommendations>) :
    BaseShowController<ShowRecommendations, WidgetJs>(
        event.eventData,
        with(event.eventData) { WidgetJs(widgetId, siteId) }
    ) {
    override val url = URL
    override val fragmentTag = FRAGMENT_TAG
    override val fragmentProvider = {
        with(eventData) { ShowRecommendationsDialogFragment(widgetId, siteId) }
    }

    override fun close(data: String?) = jsInterface.close()

    override fun WebView.configure() = prepare(jsInterface)

    companion object {
        private const val FRAGMENT_TAG = "ShowRecommendationsDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "cXNative"
        internal const val URL = "https://cdn.cxense.com/recommendations.html"

        @SuppressLint("SetJavaScriptEnabled")
        internal fun WebView.prepare(
            jsInterface: WidgetJs,
            dialogFragment: ShowRecommendationsDialogFragment? = null
        ) {
            settings.javaScriptEnabled = true
            jsInterface.init(dialogFragment, this)
            addJavascriptInterface(jsInterface, JAVASCRIPT_INTERFACE)
        }
    }
}
