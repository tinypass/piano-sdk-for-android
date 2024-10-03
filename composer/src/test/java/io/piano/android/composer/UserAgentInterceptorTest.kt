package io.piano.android.composer

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class UserAgentInterceptorTest : BaseInterceptorTest() {
    private val userAgentInterceptor = UserAgentInterceptor(USER_AGENT_VALUE)

    @Test
    fun intercept() {
        mockWebServer.enqueue(MockResponse())
        OkHttpClient.Builder()
            .addInterceptor(userAgentInterceptor)
            .build()
            .newCall(
                Request.Builder()
                    .url(mockWebServer.url("/"))
                    .build(),
            ).execute()
        assertEquals(USER_AGENT_VALUE, mockWebServer.takeRequest().getHeader("User-Agent"))
    }

    companion object {
        private const val USER_AGENT_VALUE = "test user agent"
    }
}
