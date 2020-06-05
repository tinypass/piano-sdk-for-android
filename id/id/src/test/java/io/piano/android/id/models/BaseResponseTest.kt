package io.piano.android.id.models

import com.nhaarman.mockitokotlin2.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BaseResponseTest {
    private val baseResponse = spy(BaseResponse()).apply {
        doReturn(MSG).`when`(this).joinValidationErrors(any())
    }

    @Test
    fun hasError() {
        baseResponse.code = 1
        assertTrue { baseResponse.hasError() }
        baseResponse.code = 0
        assertFalse { baseResponse.hasError() }
    }

    @Test
    fun getErrorWithoutErrorMessage() {
        baseResponse.validationErrors = mapOf("test" to MSG, "test2" to MSG)
        assertEquals(MSG, baseResponse.error)
        verify(baseResponse).joinValidationErrors(any())
    }

    @Test
    fun getErrorWithErrorMessage() {
        baseResponse.validationErrors = mapOf(BaseResponse.KEY_MESSAGE to MSG)
        assertEquals(MSG, baseResponse.error)
        verify(baseResponse, never()).joinValidationErrors(any())
    }

    @Test
    fun getErrorNoValidationErrorsAndEmptyMessage() {
        baseResponse.code = 1
        assertEquals(BaseResponse.ERROR_TEMPLATE + baseResponse.code, baseResponse.error)
        verify(baseResponse, never()).joinValidationErrors(any())
    }

    companion object {
        const val MSG = "1234"
    }
}