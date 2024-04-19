package io.piano.android.id

/**
 * This class encapsulates exceptions, which were occurred at authorization process
 */
public class PianoIdException : RuntimeException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(throwable: Throwable?) : super(throwable)
}
