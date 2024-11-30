package io.piano.android.id

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class UserAgentInterceptor(
    private val userAgent: String,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request()
            .newBuilder()
            .header("User-Agent", userAgent)
            .build(),
    )
}
