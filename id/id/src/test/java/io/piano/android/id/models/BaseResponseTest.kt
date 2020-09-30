package io.piano.android.id.models

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BaseResponseTest {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun makeResponse(
        code: Int = 0,
        message: String? = null,
        validationErrors: Map<String, String>? = null
    ) =
        BaseResponse(code, message, validationErrors)

    @Test
    fun hasError() {
        assertTrue { makeResponse(code = 1).hasError }
        assertFalse { makeResponse(code = 0).hasError }
    }

    @Test
    fun getError() {
        assertEquals("$MSG; $MSG", makeResponse(validationErrors = mapOf("test" to MSG, "test2" to MSG)).error)
        assertEquals(MSG, makeResponse(validationErrors = mapOf(BaseResponse.KEY_MESSAGE to MSG)).error)
        assertEquals("${BaseResponse.ERROR_TEMPLATE}1", makeResponse(code = 1).error)
    }

    companion object {
        const val MSG = "1234"
    }
}
