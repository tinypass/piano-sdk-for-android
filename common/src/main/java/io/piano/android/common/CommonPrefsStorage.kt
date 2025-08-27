package io.piano.android.common

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RestrictTo
import java.util.UUID

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class CommonPrefsStorage(
    context: Context,
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    public val deviceId: String = prefs.getString(KEY_DEVICE_ID, null) ?: generateDeviceId().also {
        prefs.edit().putString(KEY_DEVICE_ID, it).apply()
    }

    private fun generateDeviceId() = UUID.randomUUID().toString()

    private companion object {
        const val PREFS_NAME = "io.piano.android.common"
        const val KEY_DEVICE_ID = "deviceId"
    }
}
