package io.piano.sample

import androidx.multidex.MultiDexApplication
import com.google.android.gms.security.ProviderInstaller
import io.piano.android.composer.Composer
import io.piano.android.id.PianoId
import io.piano.android.id.facebook.FacebookOAuthProvider
import io.piano.android.id.google.GoogleOAuthProvider
import timber.log.Timber
import timber.log.Timber.DebugTree

class PianoSampleApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        runCatching {
            ProviderInstaller.installIfNeeded(applicationContext)
        }.onFailure {
            Timber.e(it)
        }

        val prefsStorage = SimpleDependenciesProvider.getInstance(this).prefsStorage
        PianoId.init(PIANO_ENDPOINT, BuildConfig.PIANO_AID)
            .with { r ->
                r.onSuccess {
                    prefsStorage.pianoIdToken = it
                    Composer.getInstance().userToken(it.accessToken)
                }.onFailure {
                    Timber.e(it)
                }
            }
            .with(GoogleOAuthProvider())
            .with(FacebookOAuthProvider())
        Composer.init(this, BuildConfig.PIANO_AID, PIANO_ENDPOINT)
        Composer.getInstance().userToken(prefsStorage.pianoIdToken?.accessToken)
    }

    companion object {
        val PIANO_ENDPOINT = BuildConfig.PIANO_ENDPOINT.takeUnless { it.isEmpty() }
            ?: if (BuildConfig.DEBUG) PianoId.ENDPOINT_SANDBOX else PianoId.ENDPOINT_PRODUCTION
    }
}
