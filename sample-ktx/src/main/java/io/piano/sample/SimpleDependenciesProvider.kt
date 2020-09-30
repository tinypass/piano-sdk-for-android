package io.piano.sample

import android.content.Context
import com.squareup.moshi.Moshi

class SimpleDependenciesProvider private constructor(context: Context) {
    val appContext: Context = context.applicationContext
    val moshi: Moshi = Moshi.Builder().build()
    val prefsStorage: PrefsStorage = PrefsStorage(appContext, moshi)

    companion object {
        @JvmStatic
        private var instance: SimpleDependenciesProvider? = null

        @JvmStatic
        fun getInstance(context: Context): SimpleDependenciesProvider {
            if (instance == null) {
                synchronized(SimpleDependenciesProvider::class.java) {
                    if (instance == null)
                        instance = SimpleDependenciesProvider(context)
                }
            }
            return instance!!
        }
    }
}
