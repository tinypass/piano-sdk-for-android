package io.piano.android.composer

import androidx.annotation.RestrictTo
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class ExperienceIdsProvider(
    private val prefsStorage: PrefsStorage,
    private val pageViewIdProvider: PageViewIdProvider
) {
    internal var isVisitIdGenerated = false
        private set

    internal fun getPageViewId(date: Date): String = pageViewIdProvider.getPageViewId(date)

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
        internal const val VISIT_ID_PREFIX = "v-"
    }
}
