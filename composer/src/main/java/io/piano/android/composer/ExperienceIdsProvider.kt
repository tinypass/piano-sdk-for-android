package io.piano.android.composer

import androidx.annotation.RestrictTo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class ExperienceIdsProvider(
    private val prefsStorage: PrefsStorage
) {
    internal var isVisitIdGenerated = false
        private set

    internal fun generateRandomAlphaNumString(length: Int): String =
        buildString(length) {
            for (i in 0 until length)
                append(ALLOWED_RANDOM_CHARS.random())
        }

    internal fun getPageViewId(date: Date): String = listOf(
        dateFormat.format(date),
        generateRandomAlphaNumString(RANDOM_STRING_SIZE),
        generateRandomAlphaNumString(HASH_SIZE)
    ).joinToString(separator = "-")

    internal fun getVisitId(date: Date): String {
        val visitIdTimestamp = prefsStorage.visitTimestamp
        val timeout = prefsStorage.visitTimeout
        return if (
            visitIdTimestamp < date.time - timeout ||
            visitIdTimestamp < getServerMidnightTimestamp(date)
        ) {
            generateVisitId(date)
        } else {
            val visitId = prefsStorage.visitId ?: generateVisitId(date)
            visitId.also {
                prefsStorage.setVisitDate(date)
                isVisitIdGenerated = false
            }
        }
    }

    internal fun generateVisitId(date: Date): String =
        (VISIT_ID_PREFIX + getPageViewId(date)).also {
            prefsStorage.visitId = it
            prefsStorage.setVisitDate(date)
            isVisitIdGenerated = true
        }

    internal fun getServerMidnightTimestamp(date: Date): Long {
        val timeZone = TimeZone.getAvailableIDs(prefsStorage.serverTimezoneOffset)
            ?.firstOrNull()
            ?.let { TimeZone.getTimeZone(it) }
            ?: TimeZone.getDefault()
        return Calendar.getInstance(timeZone)
            .apply {
                time = date
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
    }

    companion object {
        internal const val ALLOWED_RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        private const val PAGE_VIEW_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        internal const val VISIT_ID_PREFIX = "v-"
        internal const val RANDOM_STRING_SIZE = 16
        internal const val HASH_SIZE = 32
        internal val dateFormat = SimpleDateFormat(PAGE_VIEW_DATE_FORMAT, Locale.US)
    }
}
