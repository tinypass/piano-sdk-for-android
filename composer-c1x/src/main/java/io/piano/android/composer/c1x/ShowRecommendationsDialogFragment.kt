package io.piano.android.composer.c1x

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import io.piano.android.composer.c1x.ShowRecommendationsController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

class ShowRecommendationsDialogFragment : BaseShowDialogFragment {
    constructor() : super()
    constructor(widgetId: String, siteId: String) : super(ShowRecommendationsController.URL) {
        val args = arguments ?: Bundle()
        arguments = args.apply {
            putString(KEY_WIDGET_ID, widgetId)
            putString(KEY_SITE_ID, siteId)
        }
    }

    private val widgetId: String by lazy {
        requireNotNull(arguments?.getString(KEY_WIDGET_ID))
    }
    private val siteId: String by lazy {
        requireNotNull(arguments?.getString(KEY_SITE_ID))
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun WebView.configure(jsInterface: BaseJsInterface?) {
        prepare(jsInterface as? WidgetJs ?: WidgetJs(widgetId, siteId))
    }

    companion object {
        private const val KEY_WIDGET_ID = "widgetId"
        private const val KEY_SITE_ID = "siteId"
    }
}
