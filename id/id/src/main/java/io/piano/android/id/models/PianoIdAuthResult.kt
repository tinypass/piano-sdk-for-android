package io.piano.android.id.models

import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.PianoIdException

sealed class PianoIdAuthResult {
    val isSuccess: Boolean
        get() = this is PianoIdAuthSuccessResult
    val isFailure: Boolean
        get() = this is PianoIdAuthFailureResult

    @Suppress("NOTHING_TO_INLINE")
    companion object {
        internal inline fun success(token: PianoIdToken?, isNewUser: Boolean): PianoIdAuthResult =
            PianoIdAuthSuccessResult(token, isNewUser)
        internal inline fun failure(throwable: Throwable): PianoIdAuthResult =
            PianoIdAuthFailureResult(throwable.toPianoIdException())
    }
}

class PianoIdAuthSuccessResult internal constructor(
    val token: PianoIdToken?,
    val isNewUser: Boolean
) : PianoIdAuthResult()

class PianoIdAuthFailureResult internal constructor(
    val exception: PianoIdException
) : PianoIdAuthResult()
