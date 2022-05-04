package io.piano.android.id

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import io.piano.android.id.PianoId.Companion.isPianoIdUri
import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.databinding.ActivityPianoIdBinding
import io.piano.android.id.models.OAuthFailureResult
import io.piano.android.id.models.OAuthSuccessResult
import io.piano.android.id.models.PianoIdAuthResult
import io.piano.android.id.models.PianoIdToken

class PianoIdActivity : AppCompatActivity(), PianoIdJsInterface {
    @VisibleForTesting
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    private lateinit var binding: ActivityPianoIdBinding
    private val client: PianoIdClient = PianoId.getClient()

    private val jsInterface = PianoIdJavascriptDelegate(this, client.javascriptInterface)
    private var widget: String? = null
    private var disableSignUp: Boolean = false

    private val oauthResult = registerForActivityResult(OAuthResultContract()) {
        when (it) {
            null -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
            is OAuthSuccessResult -> evaluateJavascript(it.jsCommand)
            is OAuthFailureResult -> setFailureResultData(it.exception)
        }
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPianoIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.process()
        with(binding) {
            webview.apply {
                savedInstanceState?.let { restoreState(it) }
                settings.javaScriptEnabled = true
                settings.setSupportMultipleWindows(true)
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progressBar.progress = newProgress
                    }

                    override fun onCreateWindow(
                        view: WebView,
                        isDialog: Boolean,
                        isUserGesture: Boolean,
                        resultMsg: Message
                    ): Boolean {
                        if (!isUserGesture)
                            return false
                        with(resultMsg) {
                            (obj as WebView.WebViewTransport).webView = WebView(view.context)
                            sendToTarget()
                        }
                        return true
                    }
                }
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        progressBar.show()
                    }

                    override fun onPageFinished(view: WebView, url: String?) {
                        super.onPageFinished(view, url)
                        progressBar.hide()
                    }

                    @Deprecated("Deprecated in Java")
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        val isPianoIdRedirectUrl = Uri.parse(url).isPianoIdUri()
                        if (isPianoIdRedirectUrl) {
                            setFailureResultData(
                                IllegalStateException("User already authorized, call signOut before login")
                            )
                        }
                        progressBar.show()
                        return isPianoIdRedirectUrl
                    }
                }
            }
            client.getSignInUrl(disableSignUp, widget) { r ->
                r.onSuccess {
                    CookieManager.getInstance().setCookie(it, "${client.aid}__ut=")
                    progressBar.isIndeterminate = false
                    webview.apply {
                        addJavascriptInterface(jsInterface, JS_INTERFACE_NAME)
                        clearCache(true)
                        clearHistory()
                        loadUrl(it)
                    }
                }.onFailure {
                    setFailureResultData(it)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.process()
    }

    override fun onBackPressed() {
        with(binding.webview) {
            if (canGoBack())
                goBack()
            else super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webview.saveState(outState)
    }

    override fun onDestroy() {
        binding.webview.removeJavascriptInterface(JS_INTERFACE_NAME)
        super.onDestroy()
    }

    override fun socialLogin(payload: String?) {
        runCatching {
            oauthResult.launch(requireNotNull(payload))
        }.onFailure {
            setFailureResultData(it)
        }
    }

    override fun registerSuccess(payload: String?) = parsePayload(payload, true)

    override fun loginSuccess(payload: String?) = parsePayload(payload, false)

    override fun cancel() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun parsePayload(payload: String?, isNewUser: Boolean) {
        runCatching {
            setSuccessResultData(client.buildToken(requireNotNull(payload)), isNewUser)
        }.onFailure {
            setFailureResultData(it)
        }
    }

    private fun setSuccessResultData(token: PianoIdToken, isNewUser: Boolean) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(PianoId.KEY_TOKEN, token).putExtra(PianoId.KEY_IS_NEW_USER, isNewUser)
        )
        client.authCallback?.invoke(PianoIdAuthResult.success(token, isNewUser))
        finish()
    }

    private fun setFailureResultData(throwable: Throwable) {
        val exc = throwable.toPianoIdException()
        setResult(PianoId.RESULT_ERROR, Intent().putExtra(PianoId.KEY_ERROR, client.saveException(exc)))
        client.authCallback?.invoke(PianoIdAuthResult.failure(exc))
        finish()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun evaluateJavascript(code: String) =
        binding.webview.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                evaluateJavascript(code, null)
            } else loadUrl("javascript:$code")
        }

    internal fun Intent.process() {
        widget = getStringExtra(KEY_WIDGET)
        disableSignUp = getBooleanExtra(KEY_DISABLE_SIGN_UP, false)
    }

    companion object {
        internal const val KEY_WIDGET = "io.piano.android.id.PianoIdActivity.WIDGET"
        internal const val KEY_DISABLE_SIGN_UP = "io.piano.android.id.PianoIdActivity.DISABLE_SIGN_UP"
        internal const val JS_INTERFACE_NAME = "PianoIDMobileSDK"

        @JvmStatic
        internal fun buildIntent(context: Context, disableSignUp: Boolean, widget: String?): Intent =
            Intent(context, PianoIdActivity::class.java)
                .putExtra(KEY_DISABLE_SIGN_UP, disableSignUp)
                .putExtra(KEY_WIDGET, widget)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
}
