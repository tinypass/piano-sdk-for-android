package io.piano.android.common

import androidx.annotation.RestrictTo
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class UserAgentInterceptor(
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
