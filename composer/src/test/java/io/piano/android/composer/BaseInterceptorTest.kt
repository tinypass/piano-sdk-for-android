package io.piano.android.composer

import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

open class BaseInterceptorTest {
    protected lateinit var mockWebServer: MockWebServer

    @BeforeTest
    open fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
    }

    @AfterTest
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}
