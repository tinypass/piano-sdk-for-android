package io.piano.sample

import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.piano.android.composer.Composer
import io.piano.android.composer.listeners.EventsListener
import io.piano.android.composer.model.EdgeResult
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.id.PianoId
import io.piano.android.id.PianoId.Companion.isPianoIdUri
import io.piano.android.id.PianoId.Companion.parsePianoIdToken
import io.piano.android.id.PianoIdAuthResultContract
import io.piano.android.id.models.PianoIdAuthFailureResult
import io.piano.android.id.models.PianoIdAuthSuccessResult
import io.piano.android.id.models.PianoIdToken
import io.piano.android.id.models.PianoUserInfo
import io.piano.sample.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.FormBody
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefsStorage: PrefsStorage
    private lateinit var okHttpClient: OkHttpClient

    private val isDeepLink: Boolean
        get() {
            val uri = intent.data ?: return false
            if (uri.isPianoIdUri()) {
                uri.parsePianoIdToken { r ->
                    r.onFailure {
                        Timber.e(it, "Auth unsuccessful")
                    }.onSuccess {
                        Timber.d("Auth successful")
                        setAccessToken(it)
                    }
                }
            } else {
                Timber.d("App deep link")
            }
            return true
        }

    private val authResult = registerForActivityResult(PianoIdAuthResultContract()) { r ->
        when (r) {
            null -> showMessage("OAuth cancelled")
            is PianoIdAuthSuccessResult -> {
                Timber.d("Is Double opt-in enabled? %b", r.token?.emailConfirmationRequired)
                Timber.d("Is this a new user registered? %b", r.isNewUser)
                setAccessToken(r.token)
            }

            is PianoIdAuthFailureResult -> showError(r.exception)
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dependenciesProvider = SimpleDependenciesProvider.getInstance(this)
        prefsStorage = dependenciesProvider.prefsStorage
        okHttpClient = dependenciesProvider.okHttpClient
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isDeepLink) {
            Timber.d("We processed deep link")
        }
        binding.apply {
            buttonPianoIdLogin.setOnClickListener {
                authResult.launch(PianoId.signIn())
            }
            buttonPianoIdLogout.setOnClickListener { signOut() }
            buttonPianoIdRefreshToken.setOnClickListener {
                prefsStorage.pianoIdToken?.run {
                    PianoId.refreshToken(refreshToken) { r ->
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
            buttonComposerEdge.setOnClickListener {
                val edgeCookies = Composer.getInstance().edgeCookies.toMap().map { "${it.key}=${it.value}" }
                val edgeBody = FormBody.Builder()
                    .add("url", BuildConfig.SITE_URL)
                    .add("inflate", "true")
                    .add("debug", "true")
                    .add("customVariables", "{\"variable1\":\"value1\"}")
                    .add("contentAuthor", "JohnDoe")
                    .add("contentSection", "sport")
                    .add("contentCreated", "2024-01-12T08:00:00")
                    .add("tags", "premium")
                    .build()
                val edgeRequest = Request.Builder()
                    .url(BuildConfig.EDGE_URL)
                    .apply {
                        edgeCookies.forEach { addHeader("Cookie", it) }
                    }
                    .post(edgeBody)
                    .build()
                okHttpClient.newCall(edgeRequest).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        showError(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val cookies = Cookie.parseAll(BuildConfig.SITE_URL.toHttpUrl(), response.headers)
                        val request = ExperienceRequest.Builder()
                            .url(BuildConfig.SITE_URL)
                            .edgeResult(
                                EdgeResult(
                                    cookies.getCookieValue("__tbc"),
                                    cookies.getCookieValue("xbc"),
                                    cookies.getCookieValue("_pcer")
                                )
                            )
                            .debug(true)
                            .build()
                        val eventsListener: EventsListener = {
                            val eventTypes = it.joinToString(prefix = "[", postfix = "]") { event ->
                                event.eventData.toString()
                            }
                            val message = "Got events: $eventTypes"
                            Timber.d(message)
                            showMessage(message)
                        }
                        Composer.getInstance().getExperience(request, eventsListener) {
                            showError(it)
                        }
                    }
                })
            }
            buttonComposerClearStorage.setOnClickListener {
                Composer.getInstance().clearStoredData()
            }
            buttonClearAccessToken.setOnClickListener {
                signOut()
                val cookieManager = CookieManager.getInstance()
                cookieManager.removeAllCookies(null)
            }
        }
    }

    private fun List<Cookie>.getCookieValue(name: String): String = firstOrNull { it.name == name }?.value.orEmpty()

    private fun signOut() {
        val token = prefsStorage.pianoIdToken
        setAccessToken(null)
        PianoId.getInstance().signOut(token?.accessToken ?: "tmp") { r ->
            r.onSuccess {
                showMessage("Sign out success callback")
            }.onFailure {
                showError(it)
            }
        }
    }

    private fun setAccessToken(token: PianoIdToken?) {
        prefsStorage.pianoIdToken = token
        if (token == null) {
            return
        }
        val userFields = token.info.map { (key, value) -> "$key = $value" }.joinToString(prefix = "[", postfix = "]")
        Timber.d("Token has these fields: %s", userFields)
        PianoId.getInstance().getUserInfo(token.accessToken) { r ->
            val customFields = r.getOrNull()
                ?.customFields
                ?.joinToString(prefix = "[", postfix = "]") { "${it.fieldName} = ${it.value}" }
            Timber.d("User custom fields = $customFields")
            val newUserInfo = PianoUserInfo("new_form")
                .customField("test0", listOf("value"))
                .customField("test1", "test")
                .customField("test2", true)
                .customField("test3", 5)
            PianoId.getInstance().putUserInfo(token.accessToken, newUserInfo) { r2 ->
                val newCustomFields = r2.getOrNull()
                    ?.customFields
                    ?.joinToString(prefix = "[", postfix = "]") { "${it.fieldName} = ${it.value}" }
                Timber.d("Updated user custom fields = $newCustomFields")
            }
        }
        Composer.getInstance().userToken(token.accessToken)
        showMessage("accessToken = " + token.accessToken)
    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage("We've got error: " + throwable.message)
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
