package io.piano.android.id.models

import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.PianoIdException

internal sealed class OAuthResult {
    @Suppress("NOTHING_TO_INLINE")
    companion object {
        inline fun success(jsCommand: String): OAuthResult =
            OAuthSuccessResult(jsCommand)

        inline fun failure(throwable: Throwable): OAuthResult =
            OAuthFailureResult(throwable.toPianoIdException())
    }
}

internal class OAuthSuccessResult constructor(
    val jsCommand: String
) : OAuthResult()

internal class OAuthFailureResult constructor(
    val exception: PianoIdException
) : OAuthResult()
