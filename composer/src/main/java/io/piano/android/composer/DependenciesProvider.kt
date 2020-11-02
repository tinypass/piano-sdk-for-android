package io.piano.android.composer

import android.content.Context
import android.os.Build
import androidx.annotation.RestrictTo
import com.squareup.moshi.Moshi
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
    endpoint: String?
) {
    private val prefsStorage = PrefsStorage(context)
    private val userAgent = "Piano composer SDK (Android ${Build.VERSION.RELEASE} (Build ${Build.ID}); " +
        "${context.applicationContext.deviceType()} ${Build.MANUFACTURER}/${Build.MODEL})"

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(UserAgentInterceptor(userAgent))
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        )
        .build()
    private val moshi = Moshi.Builder()
        .add(CustomParametersJsonAdapter.FACTORY)
        .add(EventJsonAdapterFactory())
        .build()

    private val api: Api = Retrofit.Builder()
        .baseUrl(Composer.BASE_URL_SANDBOX)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build().create()

    internal val composer: Composer = Composer(
        api,
        HttpHelper(ExperienceIdsProvider(prefsStorage), prefsStorage, moshi, userAgent),
        prefsStorage,
        aid,
        endpoint
    )

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Context.deviceType() = if (resources.getBoolean(R.bool.isTablet)) "Tablet" else "Mobile"

    companion object {
        @JvmStatic
        @Volatile
        private var instance: DependenciesProvider? = null

        @JvmStatic
        internal fun init(context: Context, aid: String, endpoint: String?) {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DependenciesProvider(context, aid, endpoint)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): DependenciesProvider {
            checkNotNull(instance) {
                "Piano Composer SDK is not initialized! Make sure that you initialize it"
            }
            return instance as DependenciesProvider
        }
    }
}
