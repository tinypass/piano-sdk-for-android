package io.piano.android.composer.model

import com.squareup.moshi.JsonClass
import io.piano.android.consents.models.ConsentMode
import io.piano.android.consents.models.Purpose
import java.util.Collections

/**
 * Class for passing cookies to Edge CDN
 * @property tac Piano access cookie
 * @property tbc Piano browser id cookie
 * @property xbc Composer user context cookie
 * @property pprv Consent cookie
 * @property pcus User segment information cookie
 */
class EdgeCookies(
    val tac: String,
    val tbc: String,
    val xbc: String,
    val pprv: String,
    val pcus: String,
) {
    /**
     * Return all cookies as map, where key is cookie name
     */
    fun toMap(): Map<String, String> = Collections.unmodifiableMap(
        mapOf(
            "__tac" to tac,
            "__tbc" to tbc,
            "xbc" to xbc,
            "_pprv" to pprv,
            "_pcus" to pcus
        ).filterValues { it.isNotEmpty() }
    )
}

@JsonClass(generateAdapter = true)
internal class PprvContainer(
    val consent: Map<Int, ConsentMode>,
    val purposes: Map<Int, Purpose>,
)

@JsonClass(generateAdapter = true)
internal class PcusContainer(val userSegments: UserSegmentsContainer)
