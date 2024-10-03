package io.piano.android.composer.c1x

import android.annotation.SuppressLint
import android.webkit.WebView
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.ShowRecommendations
import io.piano.android.showhelper.BaseShowController

public class ShowRecommendationsController(
    event: Event<ShowRecommendations>,
) : BaseShowController<ShowRecommendations, WidgetJs>(
    event.eventData,
    with(event.eventData) { WidgetJs(widgetId, siteId) },
) {
    private val trackingId = event.eventExecutionContext.trackingId
    override val url: String = URL
    override val fragmentTag: String = FRAGMENT_TAG
    override val fragmentProvider: () -> ShowRecommendationsDialogFragment = {
        with(eventData) { ShowRecommendationsDialogFragment(widgetId, siteId, trackingId) }
    }

    override fun close(data: String?): Unit = jsInterface.close()

    override fun WebView.configure(): Unit = prepare(jsInterface)

    internal companion object {
        private const val FRAGMENT_TAG = "ShowRecommendationsDialogFragment"
        private const val JAVASCRIPT_INTERFACE = "cXNative"
        internal const val URL = "https://cdn.cxense.com/recommendations.html"

        @SuppressLint("SetJavaScriptEnabled")
        internal fun WebView.prepare(
            jsInterface: WidgetJs,
            dialogFragment: ShowRecommendationsDialogFragment? = null,
        ) {
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            jsInterface.init(dialogFragment, this)
            addJavascriptInterface(jsInterface, JAVASCRIPT_INTERFACE)
        }
    }
}
