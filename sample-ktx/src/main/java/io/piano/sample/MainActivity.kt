package io.piano.sample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.piano.android.id.PianoId
import io.piano.android.id.PianoId.Companion.getPianoIdTokenResult
import io.piano.android.id.PianoId.Companion.parsePianoIdToken
import io.piano.android.id.PianoId.Companion.refreshToken
import io.piano.android.id.PianoId.Companion.signIn
import io.piano.android.id.PianoId.Companion.signOut
import io.piano.android.id.models.PianoIdToken
import io.piano.sample.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefsStorage: PrefsStorage

    private val isDeepLink: Boolean
        get() {
            val uri = intent.data ?: return false
            runCatching {
                uri.parsePianoIdToken()
            }.onFailure {
                Timber.e(it, "Auth unsuccessful")
            }.getOrNull()?.let {
                Timber.d("Auth successful")
                setAccessToken(it)
            } ?: Timber.d("App deep link")
            return true
        }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsStorage = SimpleDependenciesProvider.getInstance(this).prefsStorage
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isDeepLink) {
            Timber.d("We processed deep link")
        }
        binding.apply {
            buttonPianoIdLogin.setOnClickListener {
                val intent = signIn().getIntent(this@MainActivity)
                startActivityForResult(intent, PIANO_ID_REQUEST_CODE)
            }
            buttonPianoIdLogout.setOnClickListener { signOut() }
            buttonPianoIdRefreshToken.setOnClickListener {
                prefsStorage.pianoIdToken?.run {
                    refreshToken(refreshToken) { r ->
                        r.onSuccess {
                            setAccessToken(it)
                        }.onFailure {
                            showError(it)
                        }
                    }
                } ?: showMessage("Can't refresh token, we aren't authorized yet")
            }
            buttonComposerExample.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        ComposerActivity::class.java
                    )
                )
            }
            buttonComposerScrollDepth.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        ComposerScrollDepthActivity::class.java
                    )
                )
            }
            buttonClearAccessToken.setOnClickListener {
                signOut()
                val cookieManager = CookieManager.getInstance()
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    val cookieSyncManager = CookieSyncManager.createInstance(this@MainActivity)
                    cookieSyncManager.startSync()
                    cookieManager.removeAllCookie()
                    cookieSyncManager.stopSync()
                } else {
                    cookieManager.removeAllCookies(null)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PIANO_ID_REQUEST_CODE) {
            when (resultCode) {
                RESULT_OK ->
                    runCatching {
                        setAccessToken(data.getPianoIdTokenResult())
                    }.onFailure {
                        showError(it)
                    }
                RESULT_CANCELED -> showMessage("OAuth cancelled")
                PianoId.RESULT_ERROR ->
                    runCatching {
                        setAccessToken(data.getPianoIdTokenResult())
                    }.onFailure {
                        showError(it)
                    }
            }
        }
    }

    private fun signOut() {
        val token = prefsStorage.pianoIdToken
        prefsStorage.pianoIdToken = null
        signOut(token?.accessToken ?: "tmp") { r ->
            r.onSuccess {
                showMessage("Sign out success callback")
            }.onFailure {
                showError(it)
            }
        }
    }

    private fun setAccessToken(token: PianoIdToken?) {
        prefsStorage.pianoIdToken = token
        showMessage("accessToken = " + token?.accessToken)
    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage("We've got error: " + throwable.message)
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val PIANO_ID_REQUEST_CODE = 1
    }
}
