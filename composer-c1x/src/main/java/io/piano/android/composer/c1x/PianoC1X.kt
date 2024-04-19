package io.piano.android.composer.c1x

import android.content.Context
import io.piano.android.composer.Composer
import io.piano.android.composer.Composer.Endpoint
import io.piano.android.composer.PageViewIdProvider
import io.piano.android.consents.PianoConsents
import io.piano.android.cxense.CxenseSdk
import java.util.Date
import java.util.concurrent.TimeUnit

public object PianoC1X {
    /**
     * Initialize Composer C1X Integration.
     * @param context The Activity or Application context.
     * @param siteId Your site ID.
     * @param aid Your Application ID (AID).
     * @param endpoint Custom API endpoint. Default is [Endpoint.PRODUCTION].
     * @param pianoConsents [PianoConsents] instance for managing user consent. Default is null.
     */
    @JvmStatic
    @JvmOverloads
    @Suppress("unused") // Public API.
    public fun init(
        context: Context,
        siteId: String,
        aid: String,
        endpoint: Composer.Endpoint = Composer.Endpoint.PRODUCTION,
        pianoConsents: PianoConsents? = null,
    ) {
        require(siteId.isNotEmpty()) {
            "Site id can't be empty"
        }
        val cxensesdk = CxenseSdk.getInstance()
        cxensesdk.configuration.apply {
            eventsMergePeriod(30, TimeUnit.SECONDS)
            randomIdProvider = { PageViewIdProvider.getPageViewId(Date(it)) }
        }
        Composer.init(context, aid, endpoint, pianoConsents)
        Composer.getInstance()
            .browserIdProvider { cxensesdk.defaultUserId ?: cxensesdk.userId }
            .addExperienceInterceptor(C1xInterceptor(siteId))
    }
}
