package io.piano.sample

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import io.piano.android.composer.Composer
import io.piano.android.composer.ComposerException
import io.piano.android.composer.c1x.ShowRecommendationsController
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.ExperienceExecuteListener
import io.piano.android.composer.listeners.MeterListener
import io.piano.android.composer.listeners.NonSiteListener
import io.piano.android.composer.listeners.SetResponseVariableListener
import io.piano.android.composer.listeners.ShowFormListener
import io.piano.android.composer.listeners.ShowLoginListener
import io.piano.android.composer.listeners.ShowRecommendationsListener
import io.piano.android.composer.listeners.ShowTemplateListener
import io.piano.android.composer.listeners.UserSegmentListener
import io.piano.android.composer.model.CustomParameters
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ShowForm
import io.piano.android.composer.model.events.ShowRecommendations
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.composer.showtemplate.ComposerJs
import io.piano.android.composer.showtemplate.ShowTemplateController
import io.piano.android.id.PianoId
import io.piano.android.id.PianoIdAuthResultContract
import io.piano.android.id.models.PianoIdAuthFailureResult
import io.piano.android.id.models.PianoIdAuthSuccessResult
import io.piano.android.id.models.PianoIdToken
import io.piano.android.showform.ShowFormController
import timber.log.Timber

class ComposerActivity : AppCompatActivity() {
    private var showTemplateController: ShowTemplateController? = null
    private val showFormControllers = mutableListOf<ShowFormController>()
    private lateinit var prefsStorage: PrefsStorage

    private val authResult = registerForActivityResult(PianoIdAuthResultContract()) { r ->
        when (r) {
            null -> Snackbar.make(
                findViewById(R.id.app_bar),
                "OAuth cancelled",
                Snackbar.LENGTH_SHORT
            ).show()
            is PianoIdAuthSuccessResult -> setAccessToken(r.token)
            is PianoIdAuthFailureResult -> Snackbar.make(
                findViewById(R.id.app_bar),
                r.exception.message ?: "Unknown error",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsStorage = SimpleDependenciesProvider.getInstance(this).prefsStorage
        setContentView(R.layout.activity_composer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val customParameters = CustomParameters()
            .content("contentKey", "contentValue0")
            .content("contentKey", "contentValue1")
            .user("userKey", "userValue")
            .request("requestKey", "requstValue1")
            .request("requestKey", "requstValue2")
            .request("requestKey", "requstValue3")

        val request = ExperienceRequest.Builder()
            .url("https://example.com")
            .referer("https://google.com")
            .tag("tag")
            .debug(true)
            .customParams(customParameters)
            .build()

        val listeners: Collection<EventTypeListener<out EventType>> = listOf(
            ExperienceExecuteListener { (_, _, eventData) ->
                Timber.d("Composer's user access token for Edge CDN ${Composer.getInstance().accessToken}")
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] User = ${eventData.user}",
                    Toast.LENGTH_LONG
                ).show()
            },
            UserSegmentListener { (_, _, eventData) ->
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] User state = ${eventData.state}",
                    Toast.LENGTH_LONG
                ).show()
            },
            ShowLoginListener { (_, _, eventData) ->
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] ${eventData.userProvider}",
                    Toast.LENGTH_LONG
                ).show()
                signIn()
            },
            MeterListener { (_, _, eventData) ->
                val message = eventData.run {
                    """
                        [${Thread.currentThread().name}] Meter state: ${state.name}
                        views = $views,
                        viewsLeft = $viewsLeft,
                        maxViews = $maxViews,
                        totalViews = $totalViews,
                        incremented = $incremented
                    """.trimIndent()
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            },
            ShowTemplateListener { event: Event<ShowTemplate> ->
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] ${event.eventData}",
                    Toast.LENGTH_LONG
                ).show()
                showTemplateController = ShowTemplateController(
                    event,
                    object : ComposerJs() {
                        @JavascriptInterface
                        override fun customEvent(eventData: String) {
                            Snackbar.make(findViewById(R.id.app_bar), eventData, Snackbar.LENGTH_LONG).show()
                            showTemplateController?.close("test")
                        }

                        @JavascriptInterface
                        override fun login(eventData: String) {
                            signIn()
                        }
                    }
                )
                showTemplateController?.show(this)
            },
            ShowRecommendationsListener { event: Event<ShowRecommendations> ->
                event.eventData.apply {
                    Toast.makeText(
                        this@ComposerActivity,
                        """
                        [${Thread.currentThread().name}] Data:
                        widgetId = $widgetId,
                        siteId = $siteId
                        """.trimIndent(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                ShowRecommendationsController(event).show(this)
            },
            ShowFormListener { event: Event<ShowForm> ->
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] ${event.eventData}",
                    Toast.LENGTH_LONG
                ).show()
                showFormControllers.add(
                    ShowFormController(event, prefsStorage.pianoIdToken?.accessToken ?: "") {
                        signIn()
                    }.also {
                        it.show(this)
                    }
                )
            },
            SetResponseVariableListener { (_, _, eventData) ->
                val message = eventData.responseVariables.entries.joinToString {
                    "${it.key}=${it.value}"
                }
                Toast.makeText(
                    this,
                    "[${Thread.currentThread().name}] $message",
                    Toast.LENGTH_LONG
                ).show()
            },
            NonSiteListener { (_, eventExecutionContext) ->
                val message = eventExecutionContext.activeMeters?.firstOrNull()?.run {
                    """
                        [${Thread.currentThread().name}] Active meter:
                        meterName = $meterName,
                        views = $views,
                        viewsLeft = $viewsLeft,
                        maxViews = $maxViews,
                        totalViews = $totalViews
                    """.trimIndent()
                } ?: "[${Thread.currentThread().name}] Active meters are null or empty!"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        )
        Composer.getInstance().getExperience(request, listeners) { exception: ComposerException ->
            Toast.makeText(
                this,
                "[${Thread.currentThread().name}] ${exception.cause?.message ?: exception.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setAccessToken(token: PianoIdToken?) {
        prefsStorage.pianoIdToken = token
        showTemplateController?.reloadWithToken(token?.accessToken ?: "")
        showFormControllers.forEach {
            it.updateToken(token?.accessToken ?: "")
        }
        Composer.getInstance().userToken(token?.accessToken)

        Snackbar.make(findViewById(R.id.app_bar), "accessToken = " + token?.accessToken, Snackbar.LENGTH_LONG).show()
    }

    private fun signIn() {
        authResult.launch(PianoId.signIn())
    }
}
