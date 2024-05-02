package io.piano.android.id.models

import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.PianoIdException

public sealed class PianoIdAuthResult {
    public val isSuccess: Boolean
        get() = this is PianoIdAuthSuccessResult
    public val isFailure: Boolean
        get() = this is PianoIdAuthFailureResult

    @Suppress("NOTHING_TO_INLINE")
    internal companion object {
        internal inline fun success(token: PianoIdToken?, isNewUser: Boolean): PianoIdAuthResult =
            PianoIdAuthSuccessResult(token, isNewUser)
        internal inline fun failure(throwable: Throwable): PianoIdAuthResult =
            PianoIdAuthFailureResult(throwable.toPianoIdException())
    }
}

public class PianoIdAuthSuccessResult internal constructor(
    public val token: PianoIdToken?,
    public val isNewUser: Boolean,
) : PianoIdAuthResult()

public class PianoIdAuthFailureResult internal constructor(
    public val exception: PianoIdException,
) : PianoIdAuthResult()
