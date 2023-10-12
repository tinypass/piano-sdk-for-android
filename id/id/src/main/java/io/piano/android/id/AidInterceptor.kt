package io.piano.android.id

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class AidInterceptor(private val aid: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header(AID_HEADER, aid)
                .build()
        )
    }
    companion object {
        internal const val AID_HEADER = "piano-app-id"
    }
}
