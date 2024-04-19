package io.piano.android.composer.c1x

import android.content.DialogInterface
import android.os.Bundle
import android.webkit.WebView
import io.piano.android.composer.Composer
import io.piano.android.composer.c1x.ShowRecommendationsController.Companion.prepare
import io.piano.android.showhelper.BaseJsInterface
import io.piano.android.showhelper.BaseShowDialogFragment

public class ShowRecommendationsDialogFragment : BaseShowDialogFragment {
    public constructor() : super()
    public constructor(
        widgetId: String,
        siteId: String,
        trackingId: String,
    ) : super(ShowRecommendationsController.URL) {
        val args = arguments ?: Bundle()
        arguments = args.apply {
            putString(KEY_WIDGET_ID, widgetId)
            putString(KEY_SITE_ID, siteId)
            putString(KEY_TRACKING_ID, trackingId)
        }
    }

    private val widgetId: String by lazy {
        requireNotNull(arguments?.getString(KEY_WIDGET_ID))
    }
    private val siteId: String by lazy {
        requireNotNull(arguments?.getString(KEY_SITE_ID))
    }
    private val trackingId: String by lazy {
        arguments?.getString(KEY_TRACKING_ID) ?: ""
    }

    override fun WebView.configure(jsInterface: BaseJsInterface?) {
        prepare(
            jsInterface as? WidgetJs ?: WidgetJs(widgetId, siteId),
            this@ShowRecommendationsDialogFragment
        )
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Composer.getInstance().trackCloseEvent(trackingId)
    }

    private companion object {
        private const val KEY_WIDGET_ID = "widgetId"
        private const val KEY_SITE_ID = "siteId"
        private const val KEY_TRACKING_ID = "trackingId"
    }
}
