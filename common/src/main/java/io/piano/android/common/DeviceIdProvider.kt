package io.piano.android.common

public class DeviceIdProvider(
    commonPrefsStorage: CommonPrefsStorage,
) {
    public val deviceId: String = commonPrefsStorage.deviceId

    public companion object {
        public const val DEVICE_ID_HEADER: String = "Device-Id"
    }
}
