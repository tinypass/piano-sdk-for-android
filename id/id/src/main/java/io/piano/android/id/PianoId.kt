package io.piano.android.id

import android.net.Uri
import android.os.Build
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import com.squareup.moshi.Moshi
import io.piano.android.id.models.PianoIdToken
import io.piano.android.id.models.PianoUserInfo
import io.piano.android.id.models.PianoUserProfile
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.Locale

class PianoId {

    companion object {
        @VisibleForTesting
        @RestrictTo(RestrictTo.Scope.LIBRARY)
        @JvmStatic
        private var client: PianoIdClient? = null

        /**
         * Initialize {@link PianoIdClient} singleton instance. It doesn't re-init it at next calls.
         *
         * @param endpoint Endpoint, which will be used. For example, {@link #ENDPOINT_PRODUCTION},
         *                 {@link #ENDPOINT_SANDBOX} or your custom endpoint
         * @param aid      Your AID
         * @param useCustomHost a flag if a custom deployment host should be used ([endpoint] will be used as is)
         * @return {@link PianoIdClient} instance.
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        fun init(endpoint: String, aid: String, useCustomHost: Boolean = false): PianoIdClient = init(endpoint.toHttpUrl(), aid, useCustomHost)

        /**
         * Initialize {@link PianoIdClient} singleton instance. It doesn't re-init it at next calls.
         *
         * @param endpoint Endpoint, which will be used.
         * @param aid      Your AID
         * @param useCustomHost If true [endpoint] will be used as deployment host else (default) the host gets overridden internally.
         * @return {@link PianoIdClient} instance.
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        fun init(endpoint: HttpUrl, aid: String, useCustomHost: Boolean = false): PianoIdClient =
            client ?: run {
                val userAgent = "Piano ID SDK ${BuildConfig.SDK_VERSION} (Android ${Build.VERSION.RELEASE})"
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(UserAgentInterceptor(userAgent))
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            if (BuildConfig.DEBUG || isLogHttpSet()) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        )
                    )
                    .build()
                val moshi = Moshi.Builder()
                    .add(PianoIdJsonAdapterFactory())
                    .add(UnixTimeDateAdapter)
                    .build()
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(endpoint)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                PianoIdClient(retrofit.create(), moshi, aid, endpoint, useCustomHost).also { client = it }
            }

        /**
         * Gets preferences for authorization process
         *
         * @return {@link PianoIdClient.SignInContext} instance
         * @throws IllegalStateException If you call it before {@link #init(String, String)}
         */
        @Suppress("unused") // Public API.
        @Throws(IllegalStateException::class)
        @JvmStatic
        fun signIn(): PianoIdClient.SignInContext = getClient().signIn()

        /**
         * Sign out user by it's token
         *
         * @param accessToken User access token
         * @param callback    callback, which will receive sign-out result
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        @JvmOverloads
        fun signOut(accessToken: String, callback: PianoIdFuncCallback<Any>? = null) {
            val signOutCallback = callback ?: {}
            client?.signOut(accessToken, signOutCallback)
                ?: signOutCallback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
        }

        /**
         * Sign out user by it's token
         *
         * @param accessToken User access token
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        suspend fun signOut(accessToken: String) = suspendCancellableCoroutine { continuation ->
            signOut(accessToken) { result ->
                continuation.resumeWith(result)
            }
        }

        /**
         * Refresh user access token
         *
         * @param refreshToken User refresh token
         * @param callback callback, which will receive result
         */
        @Suppress("unused") // Public API.
        @Throws(IllegalStateException::class)
        @JvmStatic
        fun refreshToken(refreshToken: String, callback: PianoIdFuncCallback<PianoIdToken>) {
            client?.refreshToken(refreshToken, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
        }

        /**
         * Refresh user access token
         *
         * @param refreshToken User refresh token
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        suspend fun refreshToken(refreshToken: String) = suspendCancellableCoroutine { continuation ->
            refreshToken(refreshToken) { result ->
                continuation.resumeWith(result)
            }
        }

        /**
         * Gets user info
         *
         * @param accessToken User access token
         * @param formName Form name, which stores data. Use null for default
         * @param callback callback, which will receive result
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        @JvmOverloads
        fun getUserInfo(
            accessToken: String,
            formName: String? = null,
            callback: PianoIdFuncCallback<PianoUserProfile>,
        ) {
            client?.getUserInfo(accessToken, formName, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
        }

        /**
         * Gets user info
         *
         * @param accessToken User access token
         * @param formName Form name, which stores data. Use null for default
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        @JvmOverloads
        suspend fun getUserInfo(
            accessToken: String,
            formName: String? = null,
        ) = suspendCancellableCoroutine { continuation ->
            getUserInfo(accessToken, formName) { result ->
                continuation.resumeWith(result)
            }
        }

        /**
         * Stores user info
         *
         * @param accessToken User access token
         * @param newUserInfo New user info
         * @param callback callback, which will receive result
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        fun putUserInfo(
            accessToken: String,
            newUserInfo: PianoUserInfo,
            callback: PianoIdFuncCallback<PianoUserProfile>,
        ) {
            client?.putUserInfo(accessToken, newUserInfo, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
        }

        /**
         * Stores user info
         *
         * @param accessToken User access token
         * @param newUserInfo New user info
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        suspend fun putUserInfo(
            accessToken: String,
            newUserInfo: PianoUserInfo,
        ) = suspendCancellableCoroutine { continuation ->
            putUserInfo(accessToken, newUserInfo) { result ->
                continuation.resumeWith(result)
            }
        }

        @Suppress("unused") // Public API.
        @JvmStatic
        fun Uri?.parsePianoIdToken(callback: PianoIdFuncCallback<PianoIdToken>) =
            if (isPianoIdUri()) {
                getClient().parseToken(this!!, callback)
            } else {
                callback(Result.failure(PianoIdException("It's not Piano ID uri")))
            }

        @Suppress("unused") // Public API.
        @JvmStatic
        suspend fun Uri?.parsePianoIdToken() = suspendCancellableCoroutine { continuation ->
            parsePianoIdToken { result ->
                continuation.resumeWith(result)
            }
        }

        @Suppress("unused") // Public API.
        @JvmStatic
        fun Uri?.isPianoIdUri(): Boolean =
            this?.run {
                scheme?.lowercase(Locale.ENGLISH)?.startsWith(PianoIdClient.LINK_SCHEME_PREFIX) == true &&
                    PianoIdClient.LINK_AUTHORITY.equals(authority, ignoreCase = true)
            } ?: false

        @JvmStatic
        internal fun getClient() = checkNotNull(client) {
            NOT_INITIALIZED_MSG
        }

        /**
         * Default production endpoint
         */
        @Suppress("unused") // Public API.
        const val ENDPOINT_PRODUCTION = "https://buy.tinypass.com"

        /**
         * Australia production endpoint
         */
        @Suppress("unused") // Public API.
        const val ENDPOINT_PRODUCTION_AUSTRALIA = "https://buy-au.piano.io"

        /**
         * Asia/Pacific production endpoint
         */
        @Suppress("unused") // Public API.
        const val ENDPOINT_PRODUCTION_ASIA_PACIFIC = "https://buy-ap.piano.io"

        /**
         * Europe production endpoint
         */
        @Suppress("unused") // Public API.
        const val ENDPOINT_PRODUCTION_EUROPE = "https://buy-eu.piano.io"

        /**
         * Sandbox endpoint
         */
        @Suppress("unused") // Public API.
        const val ENDPOINT_SANDBOX = "https://sandbox.piano.io"

        /**
         * Widget for login screen
         */
        @Suppress("unused") // Public API.
        const val WIDGET_LOGIN = "login"

        /**
         * Widget for registration screen
         */
        @Suppress("unused") // Public API.
        const val WIDGET_REGISTER = "register"

        /**
         * Client ID key for OAuth providers
         */
        @Suppress("unused") // Public API.
        const val KEY_CLIENT_ID = "io.piano.android.id.CLIENT_ID"

        /**
         * Activity result code for error
         */
        @Suppress("unused") // Public API.
        const val RESULT_ERROR = 1

        internal const val KEY_OAUTH_PROVIDER_NAME = "io.piano.android.id.OAUTH_PROVIDER_NAME"
        internal const val KEY_OAUTH_PROVIDER_TOKEN = "io.piano.android.id.OAUTH_PROVIDER_TOKEN"
        internal const val KEY_TOKEN = "io.piano.android.id.PianoIdActivity.TOKEN"
        internal const val KEY_IS_NEW_USER = "io.piano.android.id.PianoIdActivity.IS_NEW_USER"
        internal const val KEY_ERROR = "io.piano.android.id.PianoIdActivity.ERROR"
        private const val NOT_INITIALIZED_MSG = "Piano ID SDK is not initialized! Make sure that you " +
            "initialize it via init()"
    }
}
