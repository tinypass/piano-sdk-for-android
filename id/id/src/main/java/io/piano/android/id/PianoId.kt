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
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.Locale

public class PianoId {

    public companion object {
        @VisibleForTesting
        @RestrictTo(RestrictTo.Scope.LIBRARY)
        @JvmStatic
        @Volatile
        private var client: PianoIdClient? = null

        @JvmStatic
        private fun buildClient(endpoint: HttpUrl, aid: String, pianoConsents: PianoConsents?): PianoIdClient {
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
                        Consent::class.java,
                    ),
                ),
                moshi.adapter(
                    Types.newParameterizedType(
                        List::class.java,
                        ConsentData::class.java,
                    ),
                ),
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
                        },
                    ),
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
        public fun init(
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
        public fun init(
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
        public fun getInstance(): PianoIdClient = checkNotNull(client) {
            NOT_INITIALIZED_MSG
        }

        @Suppress("unused") // Public API.
        @JvmStatic
        public fun Uri?.parsePianoIdToken(callback: PianoIdFuncCallback<PianoIdToken>): Unit =
            if (isPianoIdUri()) {
                getInstance().parseToken(this!!, callback)
            } else {
                callback(Result.failure(PianoIdException("It's not Piano ID uri")))
            }

        @Suppress("unused") // Public API.
        @JvmStatic
        public suspend fun Uri?.parsePianoIdToken(): PianoIdToken = suspendCancellableCoroutine { continuation ->
            parsePianoIdToken { result ->
                continuation.resumeWith(result)
            }
        }

        @Suppress("unused") // Public API.
        @JvmStatic
        public fun Uri?.isPianoIdUri(): Boolean =
            this?.run {
                scheme?.lowercase(Locale.ENGLISH)?.startsWith(PianoIdClient.LINK_SCHEME_PREFIX) == true &&
                    PianoIdClient.LINK_AUTHORITY.equals(authority, ignoreCase = true)
            } ?: false

        /**
         * Default production endpoint
         */
        @Suppress("unused") // Public API.
        public const val ENDPOINT_PRODUCTION: String = "https://buy.piano.io"

        /**
         * Australia production endpoint
         */
        @Suppress("unused") // Public API.
        public const val ENDPOINT_PRODUCTION_AUSTRALIA: String = "https://buy-au.piano.io"

        /**
         * Asia/Pacific production endpoint
         */
        @Suppress("unused") // Public API.
        public const val ENDPOINT_PRODUCTION_ASIA_PACIFIC: String = "https://buy-ap.piano.io"

        /**
         * Europe production endpoint
         */
        @Suppress("unused") // Public API.
        public const val ENDPOINT_PRODUCTION_EUROPE: String = "https://buy-eu.piano.io"

        /**
         * Sandbox endpoint
         */
        @Suppress("unused") // Public API.
        public const val ENDPOINT_SANDBOX: String = "https://sandbox.piano.io"

        /**
         * Widget for login screen
         */
        @Suppress("unused") // Public API.
        public const val WIDGET_LOGIN: String = "login"

        /**
         * Widget for registration screen
         */
        @Suppress("unused") // Public API.
        public const val WIDGET_REGISTER: String = "register"

        /**
         * Client ID key for OAuth providers
         */
        @Suppress("unused") // Public API.
        public const val KEY_CLIENT_ID: String = "io.piano.android.id.CLIENT_ID"

        /**
         * Activity result code for error
         */
        @Suppress("unused") // Public API.
        public const val RESULT_ERROR: Int = 1

        internal const val KEY_OAUTH_PROVIDER_NAME = "io.piano.android.id.OAUTH_PROVIDER_NAME"
        internal const val KEY_OAUTH_PROVIDER_TOKEN = "io.piano.android.id.OAUTH_PROVIDER_TOKEN"
        internal const val KEY_TOKEN = "io.piano.android.id.PianoIdActivity.TOKEN"
        internal const val KEY_IS_NEW_USER = "io.piano.android.id.PianoIdActivity.IS_NEW_USER"
        internal const val KEY_ERROR = "io.piano.android.id.PianoIdActivity.ERROR"
        private const val NOT_INITIALIZED_MSG = "Piano ID SDK is not initialized! Make sure that you " +
            "initialize it via init()"
    }
}
