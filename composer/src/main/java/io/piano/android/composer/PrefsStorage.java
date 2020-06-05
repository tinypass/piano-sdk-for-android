package io.piano.android.composer;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import java.util.Date;

@RestrictTo(RestrictTo.Scope.LIBRARY)
class PrefsStorage {
    private static final String PREFS_NAME = "io.piano.android.composer";
    static final String KEY_VISIT_ID = "visitId";
    static final String KEY_VISIT_ID_TIMESTAMP = "visitIdTimestampMillis";
    static final String KEY_TP_BROWSER_COOKIE = "tbc";
    static final String KEY_XBUILDER_BROWSER_COOKIE = "xbc";
    static final String KEY_TP_ACCESS_COOKIE = "tac";
    static final String KEY_TIMEZONE_OFFSET = "timeZoneOffsetMillis";
    static final String KEY_VISIT_TIMEOUT = "visitTimeoutMinutes";

    private final SharedPreferences prefs;

    PrefsStorage(@NonNull Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    String getValueForKey(@NonNull String key) {
        return prefs.getString(key, null);
    }

    void setValueForKey(@NonNull String key, @Nullable String value) {
        prefs.edit().putString(key, value).apply();
    }

    @Nullable
    String getVisitId() {
        return getValueForKey(KEY_VISIT_ID);
    }

    void setVisitId(@Nullable String value) {
        setValueForKey(KEY_VISIT_ID, value);
    }

    long getVisitTimestamp() {
        return prefs.getLong(KEY_VISIT_ID_TIMESTAMP, 0);
    }

    void setVisitDate(long value) {
        prefs.edit().putLong(KEY_VISIT_ID_TIMESTAMP, value).apply();
    }

    void setVisitDate(@Nullable Date value) {
        setVisitDate(value != null ? value.getTime() : 0);
    }

    @Nullable
    String getTpBrowserCookie() {
        return getValueForKey(KEY_TP_BROWSER_COOKIE);
    }

    void setTpBrowserCookie(@Nullable String value) {
        setValueForKey(KEY_TP_BROWSER_COOKIE, value);
    }

    @Nullable
    String getXbuilderBrowserCookie() {
        return getValueForKey(KEY_XBUILDER_BROWSER_COOKIE);
    }

    void setXbuilderBrowserCookie(@Nullable String value) {
        setValueForKey(KEY_XBUILDER_BROWSER_COOKIE, value);
    }

    @Nullable
    String getTpAccessCookie() {
        return getValueForKey(KEY_TP_ACCESS_COOKIE);
    }

    void setTpAccessCookie(@Nullable String value) {
        setValueForKey(KEY_TP_ACCESS_COOKIE, value);
    }

    int getServerTimezoneOffset() {
        return prefs.getInt(KEY_TIMEZONE_OFFSET, 0);
    }

    void setServerTimezoneOffset(int milliseconds) {
        prefs.edit().putInt(KEY_TIMEZONE_OFFSET, milliseconds).apply();
    }

    long getVisitTimeout() {
        return prefs.getLong(KEY_VISIT_TIMEOUT, Constants.VISIT_TIMEOUT_FALLBACK);
    }

    void setVisitTimeout(long milliseconds) {
        prefs.edit().putLong(KEY_VISIT_TIMEOUT, milliseconds).apply();
    }
}
