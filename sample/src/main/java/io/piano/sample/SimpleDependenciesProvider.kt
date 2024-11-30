package io.piano.sample

import android.content.Context
import com.squareup.moshi.Moshi
import io.piano.android.id.PianoIdJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class SimpleDependenciesProvider private constructor(
    context: Context,
) {
    val appContext: Context = context.applicationContext
    val moshi: Moshi = Moshi.Builder().add(PianoIdJsonAdapterFactory()).build()
    val prefsStorage: PrefsStorage = PrefsStorage(appContext, moshi)
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY),
        )
        .build()

    companion object {
        @JvmStatic
        private var instance: SimpleDependenciesProvider? = null

        @JvmStatic
        fun getInstance(context: Context): SimpleDependenciesProvider {
            if (instance == null) {
                synchronized(SimpleDependenciesProvider::class.java) {
                    if (instance == null) {
                        instance = SimpleDependenciesProvider(context)
                    }
                }
            }
            return instance!!
        }
    }
}
