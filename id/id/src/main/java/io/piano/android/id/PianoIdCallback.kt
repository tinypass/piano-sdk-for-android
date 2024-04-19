package io.piano.android.id

import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.models.PianoIdAuthResult

public typealias PianoIdAuthCallback = (PianoIdAuthResult) -> Unit
public typealias PianoIdFuncCallback<T> = (Result<T>) -> Unit

/**
 * Callback interface
 * @param <T> Type of success data
 */
public interface PianoIdCallback<T> {
    /**
     * Called when operation has completed successfully.
     *
     * @param data result data
     */
    public fun onSuccess(data: T) {
    }

    /**
     * Called when operation has completed with error.
     * @param exception thrown exception
     */
    public fun onFailure(exception: PianoIdException) {
    }

    public companion object {
        @JvmStatic
        public fun <T> PianoIdCallback<T>.asResultCallback(): PianoIdFuncCallback<T> = { result ->
            result.onSuccess {
                onSuccess(it)
            }.onFailure {
                onFailure(it.toPianoIdException())
            }
        }
    }
}
