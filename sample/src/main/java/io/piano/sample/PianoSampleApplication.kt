package io.piano.sample

import android.webkit.WebView
import androidx.multidex.MultiDexApplication
import com.google.android.gms.security.ProviderInstaller
import io.piano.android.composer.Composer
import io.piano.android.composer.Composer.Endpoint
import io.piano.android.id.PianoId
import io.piano.android.id.PianoIdJs
import io.piano.android.id.facebook.FacebookOAuthProvider
import io.piano.android.id.google.GoogleOAuthProvider
import io.piano.android.id.models.PianoIdAuthFailureResult
import io.piano.android.id.models.PianoIdAuthSuccessResult
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

        // Add code for debugging. Don't use in real release application 
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        val prefsStorage = SimpleDependenciesProvider.getInstance(this).prefsStorage
        PianoId.init(PIANO_ID_ENDPOINT, BuildConfig.PIANO_AID)
            .with { r ->
                when (r) {
                    is PianoIdAuthSuccessResult -> {
                        Timber.d("Is this a new user registered? %b", r.isNewUser)
                        prefsStorage.pianoIdToken = r.token
                        Composer.getInstance().userToken(r.token?.accessToken)
                    }
                    is PianoIdAuthFailureResult -> Timber.e(r.exception)
                }
            }
            .with(object : PianoIdJs {
                override fun customEvent(eventData: String) {
                    Timber.d("Custom event: %s", eventData)
                }
            })
            .with(GoogleOAuthProvider())
            .with(FacebookOAuthProvider())
        // Use this one if you want Piano C1X integration
        // PianoC1X.init(this, BuildConfig.CX_SITE_ID, BuildConfig.PIANO_AID, COMPOSER_ENDPOINT)
        // Use this one for Composer only
        Composer.init(this, BuildConfig.PIANO_AID, COMPOSER_ENDPOINT)
        Composer.getInstance().userToken(prefsStorage.pianoIdToken?.accessToken)
    }

    companion object {
        val PIANO_ID_ENDPOINT = BuildConfig.PIANO_ENDPOINT.takeUnless { it.isEmpty() } ?: PianoId.ENDPOINT_SANDBOX
        val COMPOSER_ENDPOINT = BuildConfig.PIANO_ENDPOINT.takeUnless {
            it.isEmpty()
        }?.let {
            Endpoint(it, it)
        } ?: Endpoint.SANDBOX
    }
}
