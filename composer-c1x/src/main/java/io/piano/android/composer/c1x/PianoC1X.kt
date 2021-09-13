package io.piano.android.composer.c1x

import android.content.Context
import com.cxense.cxensesdk.CxenseSdk
import io.piano.android.composer.Composer
import io.piano.android.composer.PageViewIdProvider
import java.util.Date
import java.util.concurrent.TimeUnit

object PianoC1X {
    /**
     * Initialize Composer C1X Integration
     * @param context Activity or Application context
     * @param siteId Your site ID
     * @param aid Your AID
     * @param endpoint Custom API endpoint, see predefined in {@link Composer.Endpoint}
     */
    @JvmStatic
    @JvmOverloads
    @Suppress("unused") // Public API.
    fun init(
        context: Context,
        siteId: String,
        aid: String,
        endpoint: Composer.Endpoint = Composer.Endpoint.PRODUCTION
    ) {
        require(siteId.isNotEmpty()) {
            "Site id can't be empty"
        }
        val cxensesdk = CxenseSdk.getInstance()
        cxensesdk.configuration.apply {
            eventsMergePeriod(30, TimeUnit.SECONDS)
            randomIdProvider = { PageViewIdProvider.getPageViewId(Date(it)) }
        }
        Composer.init(context, aid, endpoint)
        Composer.getInstance()
            .browserIdProvider { cxensesdk.defaultUserId ?: cxensesdk.userId }
            .addExperienceInterceptor(C1xInterceptor(siteId))
    }
}
