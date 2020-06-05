package io.piano.android.id;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import io.piano.android.id.models.PianoIdToken;
import timber.log.Timber;

public class PianoIdActivity extends AppCompatActivity implements PianoIdJsInterface {

    static final String KEY_WIDGET = "io.piano.android.id.PianoIdActivity.WIDGET";
    static final String KEY_DISABLE_SIGN_UP = "io.piano.android.id.PianoIdActivity.DISABLE_SIGN_UP";

    static final String JS_INTERFACE_NAME = "PianoIDMobileSDK";

    static final int OAUTH_PROVIDER_REQUEST_CODE = 1;

    @VisibleForTesting
    WebView webView;
    private ContentLoadingProgressBar progressBar;
    private String widget;
    private boolean disableSignUp;
    private PianoIdClient client;
    private PianoIdJsInterface jsInterface;

    static Intent buildIntent(Context context, boolean disableSignUp, String widget) {
        return new Intent(context, PianoIdActivity.class)
                .putExtra(KEY_DISABLE_SIGN_UP, disableSignUp)
                .putExtra(KEY_WIDGET, widget)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id);
        client = PianoId.getClient();
        jsInterface = new PianoIdJavascriptDelegate(this);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        processIntent(getIntent());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.hide();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Timber.d(url);
                progressBar.show();
                return false;
            }
        });
        client.getSignInUrl(disableSignUp, widget, new PianoIdCallback<String>() {
            @Override
            public void onSuccess(String url) {
                progressBar.setIndeterminate(false);

                webView.addJavascriptInterface(jsInterface, "PianoIDMobileSDK");
                webView.clearHistory();
                webView.loadUrl(url);
            }

            @Override
            public void onFailure(PianoIdException exception) {
                setFailureResultData(exception);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == OAUTH_PROVIDER_REQUEST_CODE) {
            if (data == null) {
                setFailureResultData(new IllegalStateException("Result intent is null"));
                return;
            }
            try {
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        String provider = data.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_NAME);
                        String token = data.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN);
                        if (provider == null || token == null) {
                            setFailureResultData(new IllegalArgumentException("Result intent should provide provider name and token"));
                            return;
                        }
                        String code = client.buildResultJsCommand(provider, token);
                        evaluateJavascript(code);
                        return;
                    case Activity.RESULT_CANCELED:
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                        return;
                    case PianoId.RESULT_ERROR:
                        int statusCode = data.getIntExtra(PianoId.KEY_ERROR, 0);
                        setFailureResultData(client.getStoredException(statusCode));
                }
            } catch (Exception e) {
                setFailureResultData(e);
            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        webView.removeJavascriptInterface(JS_INTERFACE_NAME);
        jsInterface = null;
        super.onDestroy();
    }

    void processIntent(Intent intent) {
        widget = intent.getStringExtra(KEY_WIDGET);
        disableSignUp = intent.getBooleanExtra(KEY_DISABLE_SIGN_UP, false);
    }

    void setSuccessResultData(PianoIdToken token) {
        Intent intent = new Intent();
        intent.putExtra(PianoId.KEY_TOKEN, token);
        setResult(Activity.RESULT_OK, intent);
        PianoIdCallback<PianoIdToken> tokenCallback = client.getTokenCallback();
        if (tokenCallback != null)
            tokenCallback.onSuccess(token);
        finish();
    }

    void setFailureResultData(Throwable throwable) {
        Intent intent = new Intent();
        PianoIdException exception = Utils.wrapException(throwable);
        intent.putExtra(PianoId.KEY_ERROR, client.saveException(exception));
        setResult(PianoId.RESULT_ERROR, intent);
        PianoIdCallback<PianoIdToken> tokenCallback = client.getTokenCallback();
        if (tokenCallback != null)
            tokenCallback.onFailure(exception);
        finish();
    }

    void evaluateJavascript(@NonNull String code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.evaluateJavascript(code, null);
        } else {
            webView.loadUrl("javascript:" + code);
        }
    }

    @Override
    public void socialLogin(String payload) {
        try {
            Intent intent = client.buildSocialAuthIntent(this, payload);
            startActivityForResult(intent, OAUTH_PROVIDER_REQUEST_CODE);
        } catch (Exception e) {
            setFailureResultData(e);
        }
    }

    @Override
    public void loginSuccess(String payload) {
        try {
            PianoIdToken token = client.buildToken(payload);
            setSuccessResultData(token);
        } catch (Exception e) {
            setFailureResultData(e);
        }
    }

    @Override
    public void cancel() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
