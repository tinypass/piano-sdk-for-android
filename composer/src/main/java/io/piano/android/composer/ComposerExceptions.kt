package io.piano.android.composer

import java.io.IOException
import java.util.Date

class ComposerException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(cause: Throwable?) : super(cause) {}
}

class ComposerPolicyException : IOException {
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
