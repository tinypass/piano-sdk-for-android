package io.piano.android.id

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AidInterceptorTest {
    private val aidInterceptor = AidInterceptor(AID_VALUE)
    private lateinit var mockWebServer: MockWebServer

    @BeforeTest
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
    }

    @AfterTest
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun intercept() {
        mockWebServer.enqueue(MockResponse())
        OkHttpClient.Builder()
            .addInterceptor(aidInterceptor)
            .build()
            .newCall(
                Request.Builder()
                    .url(mockWebServer.url("/"))
                    .build(),
            ).execute()
        assertEquals(AID_VALUE, mockWebServer.takeRequest().getHeader(AidInterceptor.AID_HEADER))
    }

    companion object {
        private const val AID_VALUE = "test aid"
    }
}
