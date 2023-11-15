package io.piano.android.id

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class ConsentsInterceptor(
    private val consentsDataProvider: ConsentsDataProvider,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request().addConsents())

    internal fun Request.addConsents(): Request =
        consentsDataProvider.packedConsents?.let {
            newBuilder().header(CONSENTS_HEADER, it).build()
        } ?: this

    companion object {
        internal const val CONSENTS_HEADER = "Pn-Consents"
    }
}
