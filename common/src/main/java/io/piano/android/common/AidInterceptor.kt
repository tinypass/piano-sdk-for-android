package io.piano.android.common

import androidx.annotation.RestrictTo
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class AidInterceptor(
    private val aid: String,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request()
            .newBuilder()
            .header(AID_HEADER, aid)
            .build(),
    )
    internal companion object {
        const val AID_HEADER = "piano-app-id"
    }
}
