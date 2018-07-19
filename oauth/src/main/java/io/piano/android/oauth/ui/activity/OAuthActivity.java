package io.piano.android.oauth.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OAuthActivity extends BaseOAuthActivity {

    private static final String BASE_URL_PROD = "https://buy.tinypass.com/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";
    private static final String URL_LOGIN_SHOW = "checkout/user/loginShow";

    private static final String ACCESS_TOKEN_PREFIX = "{oauth}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URI)) {
                    String[] pairs = Uri.parse(url).getFragment().split("&");
                    for (String pair : pairs) {
                        String[] nameValue = pair.split("=");
                        if ("access_token".equals(nameValue[0])) {
                            String accessToken = nameValue[1];
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_ACCESS_TOKEN, ACCESS_TOKEN_PREFIX + accessToken);
                            setResult(RESULT_OK, intent);
                            finish();
                            break;
                        }
                    }
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
            return URL_LOGIN_SHOW;
        }

        @Override
        protected String getWidgetParamName() {
            return "widget";
        }

        @Override
        protected Class getImplementation() {
            return OAuthActivity.class;
        }
    }
}
