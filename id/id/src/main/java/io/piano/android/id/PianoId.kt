package io.piano.android.id

import android.net.Uri
import android.os.Build
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.consents.ConsentJsonAdapterFactory
import io.piano.android.consents.PianoConsents
import io.piano.android.consents.models.Consent
import io.piano.android.consents.models.Purpose
import io.piano.android.id.models.ConsentData
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
        @Volatile
        private var client: PianoIdClient? = null

        @JvmStatic
        private fun buildClient(
            endpoint: HttpUrl,
            aid: String,
            pianoConsents: PianoConsents?,
        ): PianoIdClient {
            val userAgent = "Piano ID SDK ${BuildConfig.SDK_VERSION} (Android ${Build.VERSION.RELEASE})"
            val moshi = Moshi.Builder()
                .add(PianoIdJsonAdapterFactory())
                .add(UnixTimeDateAdapter)
                .add(ConsentJsonAdapterFactory)
                .build()
            val storage = pianoConsents ?: runCatching { PianoConsents.getInstance() }.getOrNull()
            val consentsDataProvider = ConsentsDataProvider(
                storage,
                moshi.adapter(
                    Types.newParameterizedType(
                        Map::class.java,
                        Purpose::class.java,
                        Consent::class.java
                    )
                ),
                moshi.adapter(
                    Types.newParameterizedType(
                        List::class.java,
                        ConsentData::class.java
                    )
                )
            )
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ConsentsInterceptor(consentsDataProvider))
                .addInterceptor(UserAgentInterceptor(userAgent))
                .addInterceptor(AidInterceptor(aid))
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
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(endpoint)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return PianoIdClient(retrofit.create(), moshi, aid, consentsDataProvider, endpoint)
        }

        /**
         * Initialize {@link PianoIdClient} singleton instance. It doesn't re-init it at next calls.
         *
         * @param endpoint Endpoint, which will be used. For example, {@link #ENDPOINT_PRODUCTION},
         *                 {@link #ENDPOINT_SANDBOX} or your custom endpoint
         * @param aid      Your AID
         * @param pianoConsents [PianoConsents] instance for managing user consent
         * @return {@link PianoIdClient} instance.
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        fun init(
            endpoint: String,
            aid: String,
            pianoConsents: PianoConsents? = null,
        ): PianoIdClient = init(endpoint.toHttpUrl(), aid, pianoConsents)

        /**
         * Initialize {@link PianoIdClient} singleton instance. It doesn't re-init it at next calls.
         *
         * @param endpoint Endpoint, which will be used.
         * @param aid      Your AID
         * @param pianoConsents [PianoConsents] instance for managing user consent
         * @return {@link PianoIdClient} instance.
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        fun init(
            endpoint: HttpUrl,
            aid: String,
            pianoConsents: PianoConsents? = null,
        ): PianoIdClient {
            if (client == null) {
                synchronized(this) {
                    if (client == null) {
                        client = buildClient(endpoint, aid, pianoConsents)
                    }
                }
            }
            return getInstance()
        }

        @JvmStatic
        @Suppress("unused") // Public API.
        fun getInstance(): PianoIdClient = checkNotNull(client) {
            NOT_INITIALIZED_MSG
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
        @Deprecated(
            "Use method directly from PianoIdClient instance, will be removed in future versions",
            ReplaceWith("PianoId.getInstance().signIn()")
        )
        fun signIn(): PianoIdClient.SignInContext = getInstance().signIn()

        /**
         * Sign out user by it's token
         *
         * @param accessToken User access token
         * @param callback    callback, which will receive sign-out result
         */
        @Suppress("unused") // Public API.
        @JvmStatic
        @JvmOverloads
        @Deprecated(
            "Use method directly from PianoIdClient instance, will be removed in future versions",
            ReplaceWith("PianoId.getInstance().signOut(accessToken, callback)")
        )
        fun signOut(accessToken: String, callback: PianoIdFuncCallback<Any>? = null) {
            val signOutCallback = callback ?: {}
            client?.signOut(accessToken, signOutCallback)
                ?: signOutCallback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
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
        @Deprecated(
            "Use method directly from PianoIdClient instance, will be removed in future versions",
            ReplaceWith("PianoId.getInstance().refreshToken(refreshToken, callback)")
        )
        fun refreshToken(refreshToken: String, callback: PianoIdFuncCallback<PianoIdToken>) {
            client?.refreshToken(refreshToken, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
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
        @Deprecated(
            "Use method directly from PianoIdClient instance, will be removed in future versions",
            ReplaceWith("PianoId.getInstance().getUserInfo(accessToken, formName, callback)")
        )
        fun getUserInfo(
            accessToken: String,
            formName: String? = null,
            callback: PianoIdFuncCallback<PianoUserProfile>,
        ) {
            client?.getUserInfo(accessToken, formName, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
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
        @Deprecated(
            "Use method directly from PianoIdClient instance, will be removed in future versions",
            ReplaceWith("PianoId.getInstance().putUserInfo(accessToken, newUserInfo, callback)")
        )
        fun putUserInfo(
            accessToken: String,
            newUserInfo: PianoUserInfo,
            callback: PianoIdFuncCallback<PianoUserProfile>,
        ) {
            client?.putUserInfo(accessToken, newUserInfo, callback)
                ?: callback(Result.failure(IllegalStateException(NOT_INITIALIZED_MSG)))
        }

        @Suppress("unused") // Public API.
        @JvmStatic
        fun Uri?.parsePianoIdToken(callback: PianoIdFuncCallback<PianoIdToken>) =
            if (isPianoIdUri()) {
                getInstance().parseToken(this!!, callback)
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
