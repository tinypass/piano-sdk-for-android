package io.piano.android.composer

import android.util.Base64
import com.squareup.moshi.JsonAdapter
import io.piano.android.composer.model.ConsentModeWrapper
import io.piano.android.composer.model.EdgeCookies
import io.piano.android.composer.model.PcusContainer
import io.piano.android.composer.model.PprvContainer
import io.piano.android.composer.model.UserSegmentsContainer
import io.piano.android.consents.PianoConsents

internal class EdgeCookiesProvider(
    private val prefsStorage: PrefsStorage,
    private val pianoConsents: PianoConsents?,
    private val pprvAdapter: JsonAdapter<PprvContainer>,
    private val pcusAdapter: JsonAdapter<PcusContainer>,
) {
    private var lastUserSegmentsContainer: UserSegmentsContainer? = null
    private var lastUserToken: String? = null

    val edgeCookies: EdgeCookies
        get() = EdgeCookies(
            prefsStorage.tpAccessCookie,
            prefsStorage.tpBrowserCookie,
            prefsStorage.xbuilderBrowserCookie,
            lastUserToken.orEmpty(),
            buildPprvValue(),
            lastUserSegmentsContainer?.let { pcusAdapter.toJson(PcusContainer(it)) }?.toBase64String().orEmpty(),
        )

    internal fun userSegments(userSegmentsContainer: UserSegmentsContainer?) {
        lastUserSegmentsContainer = userSegmentsContainer
    }

    internal fun userToken(userToken: String?) {
        lastUserToken = userToken
    }

    private fun buildPprvValue() = pianoConsents?.let {
        pprvAdapter.toJson(
            PprvContainer(
                it.consents.values.flatMap { c ->
                    c.products.map {
                        it.id to ConsentModeWrapper(c.mode)
                    }
                }.toMap(),
                it.productsToPurposesMapping.mapKeys { it.key.id },
            ),
        )
    }?.toBase64String().orEmpty()

    @Suppress("NOTHING_TO_INLINE")
    private inline fun String.toBase64String() = Base64.encodeToString(toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)
}
