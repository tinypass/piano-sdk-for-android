package io.piano.android.id

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class ConsentsInterceptor(
    private val consentsDataProvider: ConsentsDataProvider,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request().addConsents())

    internal fun Request.addConsents(): Request =
        with(consentsDataProvider.packedConsents) {
            if (isEmpty()) {
                this@addConsents
            } else {
                newBuilder().apply {
                    forEach { (k, v) -> header(k, v) }
                }.build()
            }
        }
}
