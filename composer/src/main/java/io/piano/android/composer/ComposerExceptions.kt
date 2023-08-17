package io.piano.android.composer

import java.io.IOException
import java.util.Date

/**
 * Basic exception, wraps other exceptions for simplifying `catch`.
 */
class ComposerException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(cause: Throwable?) : super(cause) {}
}

/**
 * Exception, that thrown if app calls experiences too frequently.
 */
class ComposerPolicyException : IOException {
    /**
     * Date until requests limiting policy is active.
     */
    val policyActiveUntilDate: Date

    constructor(date: Date) : super(MSG) {
        policyActiveUntilDate = date
    }

    constructor(date: Date, cause: Throwable?) : super(MSG, cause) {
        policyActiveUntilDate = date
    }
    companion object {
        private const val MSG = "Request policy is active"
    }
}
