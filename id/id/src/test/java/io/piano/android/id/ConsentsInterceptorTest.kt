package io.piano.android.id

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConsentsInterceptorTest {
    private val consentsDataProvider: ConsentsDataProvider = mock() {
        on { packedConsents } doReturn CONSENTS_VALUE
    }
    private val consentsInterceptor = ConsentsInterceptor(consentsDataProvider)
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
            .addInterceptor(consentsInterceptor)
            .build()
            .newCall(
                Request.Builder()
                    .url(mockWebServer.url("/"))
                    .build()
            ).execute()
        assertEquals(CONSENTS_VALUE, mockWebServer.takeRequest().getHeader(ConsentsInterceptor.CONSENTS_HEADER))
    }

    companion object {
        private const val CONSENTS_VALUE = "test consents"
    }
}
