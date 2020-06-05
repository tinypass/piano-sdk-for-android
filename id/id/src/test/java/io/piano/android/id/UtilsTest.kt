package io.piano.android.id

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.*

class UtilsTest {
    private val body = ResponseBody.create(null, "{}")

    @Test
    fun validateResponseUnsuccessful() {
        val response = Response.error<Any>(400, body)
        with(assertFailsWith<PianoIdException> { Utils.validateResponse(response) }) {
            assertNotNull(cause)
            assertTrue { cause is HttpException }
            assertEquals(response, (cause as HttpException).response())
        }
    }

    @Test
    fun validateResponseWithNullBody() {
        val response = Response.success<Any>(null)
        with(assertFailsWith<PianoIdException> { Utils.validateResponse(response) }) {
            assertNull(cause)
        }
    }

    @Test
    fun validateResponse() {
        val response = Response.success<Any>(body)
        assertEquals(body, Utils.validateResponse(response))
    }

    @Test
    fun wrapException() {
        val exc = PianoIdException()
        assertEquals(exc, Utils.wrapException(exc))
        val exc2 = NullPointerException()
        assertEquals(exc2, Utils.wrapException(exc2).cause)
    }
}