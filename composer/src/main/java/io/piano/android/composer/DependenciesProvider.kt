package io.piano.android.composer

import android.content.Context
import android.os.Build
import androidx.annotation.RestrictTo
import com.squareup.moshi.Moshi
import io.piano.android.composer.model.PcusContainer
import io.piano.android.composer.model.PprvContainer
import io.piano.android.consents.ConsentJsonAdapterFactory
import io.piano.android.consents.PianoConsents
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class DependenciesProvider private constructor(
    context: Context,
    aid: String,
    endpoint: Composer.Endpoint,
    pianoConsents: PianoConsents?,
) {
    private val prefsStorage = PrefsStorage(context)
    private val userAgent = "Piano composer SDK ${BuildConfig.SDK_VERSION} (Android ${Build.VERSION.RELEASE} " +
        "(Build ${Build.ID}); ${context.deviceType()} ${Build.MANUFACTURER}/${Build.MODEL})"

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(UserAgentInterceptor(userAgent))
        .addInterceptor(AidInterceptor(aid))
        .addInterceptor(RequestPolicyInterceptor(prefsStorage))
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
    private val moshi = Moshi.Builder()
        .add(ComposerJsonAdapterFactory())
        .add(EventJsonAdapterFactory())
        .add(CustomValuesJsonAdapter)
        .add(UnixTimeDateAdapter)
        .add(ConsentJsonAdapterFactory)
        .build()

    private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    private val composerApi: ComposerApi = Retrofit.Builder()
        .baseUrl(endpoint.composerHost)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create()

    private val generalApi: GeneralApi = Retrofit.Builder()
        .baseUrl(endpoint.apiHost)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create()

    private val edgeCookiesProvider = EdgeCookiesProvider(
        prefsStorage,
        pianoConsents,
        moshi.adapter(PprvContainer::class.java),
        moshi.adapter(PcusContainer::class.java)
    )

    internal val composer: Composer = Composer(
        composerApi,
        generalApi,
        HttpHelper(ExperienceIdsProvider(prefsStorage, PageViewIdProvider), prefsStorage, moshi, userAgent),
        prefsStorage,
        aid,
        endpoint,
        edgeCookiesProvider,
        pianoConsents
    )

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Context.deviceType() = if (resources.getBoolean(R.bool.isTablet)) "Tablet" else "Mobile"

    companion object {
        @JvmStatic
        @Volatile
        private var instance: DependenciesProvider? = null

        @JvmStatic
        internal fun init(context: Context, aid: String, endpoint: Composer.Endpoint, pianoConsents: PianoConsents?) {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DependenciesProvider(context, aid, endpoint, pianoConsents)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): DependenciesProvider = checkNotNull(instance) {
            "Piano Composer SDK is not initialized! Make sure that you initialize it"
        }
    }
}
