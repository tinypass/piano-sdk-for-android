package io.piano.android.composer

import java.io.IOException
import java.util.Date

/**
 * Basic exception, wraps other exceptions for simplifying `catch`.
 */
public class ComposerException : RuntimeException {
    public constructor() {}
    public constructor(message: String?) : super(message) {}
    public constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    public constructor(cause: Throwable?) : super(cause) {}
}

/**
 * Exception, that thrown if app calls experiences too frequently.
 */
public class ComposerPolicyException : IOException {
    /**
     * Date until requests limiting policy is active.
     */
    public val policyActiveUntilDate: Date

    public constructor(date: Date) : super(MSG) {
        policyActiveUntilDate = date
    }

    public constructor(date: Date, cause: Throwable?) : super(MSG, cause) {
        policyActiveUntilDate = date
    }
    private companion object {
        private const val MSG = "Request policy is active"
    }
}
