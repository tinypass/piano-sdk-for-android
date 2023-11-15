package io.piano.android.id

import android.util.Base64
import com.squareup.moshi.JsonAdapter
import io.piano.android.consents.PianoConsents
import io.piano.android.consents.models.Consent
import io.piano.android.consents.models.Purpose
import io.piano.android.id.models.ConsentData

internal class ConsentsDataProvider(
    internal val pianoConsents: PianoConsents?,
    private val consentAdapter: JsonAdapter<Map<Purpose, Consent>>,
    private val packedConsentAdapter: JsonAdapter<List<ConsentData>>,
) {
    internal val consents: String
        get() = pianoConsents?.consents
            ?.takeUnless { it.isEmpty() }
            ?.let { consentAdapter.toJson(it) }
            .orEmpty()
            .replace("\"", "\\\\\"")

    internal val packedConsents: String?
        get() = pianoConsents?.consents?.map {
            ConsentData(it.key, it.value.mode, it.value.products)
        }?.takeUnless {
            it.isEmpty()
        }?.let {
            Base64.encode(
                packedConsentAdapter.toJson(it).encodeToByteArray(),
                Base64.URL_SAFE or Base64.NO_WRAP
            ).decodeToString()
        }
}
