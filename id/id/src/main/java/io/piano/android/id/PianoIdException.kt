package io.piano.android.id

/**
 * This class encapsulates exceptions, which were occurred at authorization process
 */
class PianoIdException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(throwable: Throwable?) : super(throwable)
}
