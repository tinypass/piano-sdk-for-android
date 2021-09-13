package io.piano.android.composer

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PageViewIdProvider {
    fun getPageViewId(date: Date): String = listOf(
        dateFormat.format(date),
        generateRandomAlphaNumString(RANDOM_STRING_SIZE),
        generateRandomAlphaNumString(HASH_SIZE)
    ).joinToString(separator = "-")

    internal fun generateRandomAlphaNumString(length: Int): String =
        buildString(length) {
            for (i in 0 until length)
                append(ALLOWED_RANDOM_CHARS.random())
        }

    internal const val ALLOWED_RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private const val PAGE_VIEW_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    internal const val RANDOM_STRING_SIZE = 16
    internal const val HASH_SIZE = 32
    internal val dateFormat = SimpleDateFormat(PAGE_VIEW_DATE_FORMAT, Locale.US)
}
