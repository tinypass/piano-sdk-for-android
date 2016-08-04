package io.piano.android.oauth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.piano.android.oauth.R;

public class OAuthActivity extends Activity {

    private static final String BASE_URL_PROD = "https://buy.tinypass.com/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";

    private static final String REDIRECT_URI = "piano-sdk-for-android://oauth";

    private static final String ACCESS_TOKEN_PREFIX = "{oauth}";

    public static final String EXTRA_ENDPOINT = "endpoint";
    public static final String EXTRA_SANDBOX = "sandbox";
    public static final String EXTRA_AID = "aid";
    public static final String EXTRA_ACCESS_TOKEN = "accessToken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String aid = extras.getString(EXTRA_AID);
        String endpoint = extras.getString(EXTRA_ENDPOINT);
        if (TextUtils.isEmpty(endpoint)) {
            endpoint = extras.getBoolean(EXTRA_SANDBOX) ? BASE_URL_SANDBOX : BASE_URL_PROD;
        }

        setContentView(R.layout.activity_oauth);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
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
        webView.loadUrl(
                String.format(
                        "%scheckout/user/loginShow?client_id=%s&redirect_uri=%s&response_type=token"
                        , endpoint
                        , aid
                        , REDIRECT_URI
                )
        );
    }
}
