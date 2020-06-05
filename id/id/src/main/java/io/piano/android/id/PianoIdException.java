package io.piano.android.id;

/**
 * This class encapsulates exceptions, which were occurred at authorization process
 */
public class PianoIdException extends RuntimeException {
    public PianoIdException() {
        super();
    }

    public PianoIdException(String message) {
        super(message);
    }

    public PianoIdException(Throwable throwable) {
        super(throwable);
    }
}
