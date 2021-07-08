package io.piano.android.id

import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.models.PianoIdAuthResult

typealias PianoIdAuthCallback = (PianoIdAuthResult) -> Unit
typealias PianoIdFuncCallback<T> = (Result<T>) -> Unit

/**
 * Callback interface
 * @param <T> Type of success data
 */
interface PianoIdCallback<T> {
    /**
     * Called when operation has completed successfully.
     *
     * @param data result data
     */
    fun onSuccess(data: T) {
    }

    /**
     * Called when operation has completed with error.
     * @param exception thrown exception
     */
    fun onFailure(exception: PianoIdException) {
    }

    companion object {
        @JvmStatic
        fun <T> PianoIdCallback<T>.asResultCallback(): PianoIdFuncCallback<T> = { result ->
            result.onSuccess {
                onSuccess(it)
            }.onFailure {
                onFailure(it.toPianoIdException())
            }
        }
    }
}
