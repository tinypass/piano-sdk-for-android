package io.piano.android.id;

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
    default void onSuccess(T data) {
    }

    /**
     * Called when operation has completed with error.
     * @param exception thrown exception
     */
    default void onFailure(PianoIdException exception) {
    }
}
