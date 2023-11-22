package io.piano.android.composer

import java.util.Date
import kotlin.random.Random

object PageViewIdProvider {
    fun getPageViewId(date: Date): String = buildString {
        append(formatDate(date))
        while (length < MAX_LENGTH) {
            append(generateRandomString())
        }
    }.take(MAX_LENGTH)

    internal fun formatDate(date: Date) = date.time.toString(RADIX)

    internal fun generateRandomString() = Random.nextInt(Int.MAX_VALUE).toString(RADIX)

    private const val RADIX = 36
    private const val MAX_LENGTH = 16
}
