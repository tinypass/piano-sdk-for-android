package io.piano.android.id

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import io.piano.android.id.PianoIdClient.Companion.toPianoIdException
import io.piano.android.id.databinding.ActivityPianoIdBinding
import io.piano.android.id.models.PianoIdToken
import timber.log.Timber

class PianoIdActivity : AppCompatActivity(), PianoIdJsInterface {
    private val jsInterface = PianoIdJavascriptDelegate(this)
    private var widget: String? = null
    private var disableSignUp: Boolean = false

    @VisibleForTesting
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    private lateinit var binding: ActivityPianoIdBinding
    private val client: PianoIdClient = PianoId.getClient()

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
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progressBar.progress = newProgress
                    }
                }
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        Timber.d(url)
                        progressBar.show()
                    }

                    override fun onPageFinished(view: WebView, url: String?) {
                        super.onPageFinished(view, url)
                        progressBar.hide()
                    }

                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        progressBar.show()
                        return false
                    }
                }
            }
            client.getSignInUrl(disableSignUp, widget) { r ->
                r.onSuccess {
                    progressBar.isIndeterminate = false
                    webview.apply {
                        addJavascriptInterface(jsInterface, "PianoIDMobileSDK")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == OAUTH_PROVIDER_REQUEST_CODE) {
            data?.let {
                runCatching {
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            val provider = requireNotNull(it.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_NAME)) {
                                "provider must be filled"
                            }
                            val token = requireNotNull(it.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN)) {
                                "token must be filled"
                            }
                            evaluateJavascript(client.buildResultJsCommand(provider, token))
                        }
                        Activity.RESULT_CANCELED -> {
                            setResult(Activity.RESULT_CANCELED)
                            finish()
                        }
                        else -> {
                            val exc = client.getStoredException(data.getIntExtra(PianoId.KEY_ERROR, 0))
                                ?: IllegalStateException("Unknown error")
                            setFailureResultData(exc)
                        }
                    }
                }.onFailure {
                    setFailureResultData(it)
                }
            } ?: setFailureResultData(IllegalStateException("Result intent is null"))
        } else super.onActivityResult(requestCode, resultCode, data)
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
            startActivityForResult(
                client.buildSocialAuthIntent(this, requireNotNull(payload)),
                OAUTH_PROVIDER_REQUEST_CODE
            )
        }.onFailure {
            setFailureResultData(it)
        }
    }

    override fun loginSuccess(payload: String?) {
        runCatching {
            setSuccessResultData(client.buildToken(requireNotNull(payload)))
        }.onFailure {
            setFailureResultData(it)
        }
    }

    override fun cancel() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun setSuccessResultData(token: PianoIdToken) {
        setResult(Activity.RESULT_OK, Intent().putExtra(PianoId.KEY_TOKEN, token))
        client.tokenCallback?.invoke(Result.success(token))
        finish()
    }

    private fun setFailureResultData(throwable: Throwable) {
        val exc = throwable.toPianoIdException()
        setResult(PianoId.RESULT_ERROR, Intent().putExtra(PianoId.KEY_ERROR, client.saveException(exc)))
        client.tokenCallback?.invoke(Result.failure(exc))
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
        internal const val OAUTH_PROVIDER_REQUEST_CODE = 1

        @JvmStatic
        internal fun buildIntent(context: Context, disableSignUp: Boolean, widget: String?): Intent =
            Intent(context, PianoIdActivity::class.java)
                .putExtra(KEY_DISABLE_SIGN_UP, disableSignUp)
                .putExtra(KEY_WIDGET, widget)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
}
