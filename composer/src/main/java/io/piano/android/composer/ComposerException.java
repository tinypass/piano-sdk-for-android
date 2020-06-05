package io.piano.android.composer;

public class ComposerException extends RuntimeException {
    public ComposerException() {
    }

    public ComposerException(String message) {
        super(message);
    }

    public ComposerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComposerException(Throwable cause) {
        super(cause);
    }
}
