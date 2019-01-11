package io.piano.android.oauth.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Use {@link io.piano.android.oauth.client.PianoIdClient} instead
 * @deprecated
 */
@Deprecated
public class PianoIdOAuthActivity extends BaseOAuthActivity {

    private static final String BASE_URL_PROD = "https://id.piano.io/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";
    private static final String URL_AUTHORIZE = "id/api/v1/identity/vxauth/authorize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URI)) {
                    Uri uri = Uri.parse(url);
                    String accessToken = uri.getQueryParameter("token");
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    try {
                        startActivity(intent);
                    } catch (Exception ignored) {}
                }
                return true;
            }
        });

        webView.loadUrl(url);
    }

    public static class Builder extends BaseOAuthActivity.Builder {

        public Builder(Context context, String aid) {
            super(context, aid);
        }

        @Override
        protected String getBaseUrlProd() {
            return BASE_URL_PROD;
        }

        @Override
        protected String getBaseUrlSandbox() {
            return BASE_URL_SANDBOX;
        }

        @Override
        protected String getUrlAction() {
            return URL_AUTHORIZE;
        }

        @Override
        protected String getWidgetParamName() {
            return "screen";
        }

        @Override
        protected Class getImplementation() {
            return PianoIdOAuthActivity.class;
        }
    }
}
