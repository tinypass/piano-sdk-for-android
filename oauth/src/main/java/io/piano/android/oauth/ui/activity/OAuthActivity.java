package io.piano.android.oauth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.piano.android.oauth.R;

public class OAuthActivity extends Activity {

    public static final String EXTRA_SANDBOX = "sandbox";
    public static final String EXTRA_AID = "aid";
    public static final String EXTRA_ACCESS_TOKEN = "accessToken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String aid = extras.getString(EXTRA_AID);
        String endpoint = extras.getBoolean(EXTRA_SANDBOX) ? "https://sandbox.tinypass.com" : "https://buy.piano.io";

        setContentView(R.layout.activity_oauth);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("piano-sdk-for-android://oauth")) {
                    String[] pairs = Uri.parse(url).getFragment().split("&");
                    for (String pair : pairs) {
                        String[] nameValue = pair.split("=");
                        if ("access_token".equals(nameValue[0])) {
                            String accessToken = nameValue[1];
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
                            setResult(RESULT_OK, intent);
                            finish();
                            break;
                        }
                    }
                }
                return false;
            }
        });
        webView.loadUrl(
                String.format(
                        "%s/checkout/user/loginShow?client_id=%s&redirect_uri=piano-sdk-for-android://oauth&response_type=token"
                        , endpoint
                        , aid
                )
        );
    }
}
