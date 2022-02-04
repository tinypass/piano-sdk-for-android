package io.piano.android.composer

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RestrictTo
import java.util.Date
import java.util.concurrent.TimeUnit

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class PrefsStorage(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    internal fun clear() {
        prefs.edit().clear().apply()
    }

    internal fun getValueForKey(key: String): String? {
        return prefs.getString(key, null)
    }

    internal fun setValueForKey(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    var visitId: String?
        get() = getValueForKey(KEY_VISIT_ID)
        set(value) {
            setValueForKey(KEY_VISIT_ID, value)
        }

    val visitTimestamp: Long
        get() = prefs.getLong(KEY_VISIT_ID_TIMESTAMP, 0)

    fun setVisitDate(value: Long) {
        prefs.edit().putLong(KEY_VISIT_ID_TIMESTAMP, value).apply()
    }

    fun setVisitDate(value: Date?) {
        setVisitDate(value?.time ?: 0)
    }

    var tpBrowserCookie: String
        get() = getValueForKey(KEY_TP_BROWSER_COOKIE).orEmpty()
        set(value) {
            setValueForKey(KEY_TP_BROWSER_COOKIE, value)
        }

    var xbuilderBrowserCookie: String
        get() = getValueForKey(KEY_XBUILDER_BROWSER_COOKIE).orEmpty()
        set(value) {
            setValueForKey(KEY_XBUILDER_BROWSER_COOKIE, value)
        }

    var tpAccessCookie: String
        get() = getValueForKey(KEY_TP_ACCESS_COOKIE).orEmpty()
        set(value) {
            setValueForKey(KEY_TP_ACCESS_COOKIE, value)
        }

    var serverTimezoneOffset: Int
        get() = prefs.getInt(KEY_TIMEZONE_OFFSET, 0)
        set(milliseconds) {
            prefs.edit().putInt(KEY_TIMEZONE_OFFSET, milliseconds).apply()
        }

    var visitTimeout: Long
        get() = prefs.getLong(
            KEY_VISIT_TIMEOUT,
            VISIT_TIMEOUT_FALLBACK
        )
        set(milliseconds) {
            prefs.edit().putLong(KEY_VISIT_TIMEOUT, milliseconds).apply()
        }

    companion object {
        internal const val PREFS_NAME = "io.piano.android.composer"
        internal const val KEY_VISIT_ID = "visitId"
        internal const val KEY_VISIT_ID_TIMESTAMP = "visitIdTimestampMillis"
        internal const val KEY_TP_BROWSER_COOKIE = "tbc"
        internal const val KEY_XBUILDER_BROWSER_COOKIE = "xbc"
        internal const val KEY_TP_ACCESS_COOKIE = "tac"
        internal const val KEY_TIMEZONE_OFFSET = "timeZoneOffsetMillis"
        internal const val KEY_VISIT_TIMEOUT = "visitTimeoutMinutes"
        internal val VISIT_TIMEOUT_FALLBACK = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES)
    }
}
