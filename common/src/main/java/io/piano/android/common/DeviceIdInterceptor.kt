package io.piano.android.common

import androidx.annotation.RestrictTo
import okhttp3.Interceptor
import okhttp3.Response

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class DeviceIdInterceptor(
    private val deviceIdProvider: DeviceIdProvider,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request()
            .newBuilder()
            .header(DeviceIdProvider.DEVICE_ID_HEADER, deviceIdProvider.deviceId)
            .build(),
    )
}
