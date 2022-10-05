package io.piano.android.composer

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Date
import java.util.concurrent.TimeUnit

internal class RequestPolicyInterceptor(private val prefsStorage: PrefsStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentPolicyTime = prefsStorage.requestPolicyTime
        if (currentPolicyTime > System.currentTimeMillis())
            throw ComposerPolicyException(Date(currentPolicyTime))
        return chain.proceed(chain.request()).apply {
            if (header("Composer-Request-Control-Policy") in DENIED_VALUES) {
                val newPolicyTime = System.currentTimeMillis() + timeout
                prefsStorage.requestPolicyTime = newPolicyTime
                throw ComposerPolicyException(Date(newPolicyTime))
            }
        }
    }

    companion object {
        private val timeout = TimeUnit.MINUTES.toMillis(30)
        private val DENIED_VALUES = listOf("deny-mobile", "deny-all")
    }
}
