package io.piano.android.oauth.ui.activity;

import android.app.Activity;
import android.content.Context;
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
    public static final String EXTRA_DISABLE_SIGN_UP = "disableSignUp";
    public static final String EXTRA_ACCESS_TOKEN = "accessToken";

    public static void start(Context context, int requestCode, String aid) {
        start(context, requestCode, aid, null, false, null);
    }

    public static void start(Context context, int requestCode, String aid, boolean sandbox) {
        start(context, requestCode, aid, null, sandbox, null);
    }

    public static void start(Context context, int requestCode, String aid, boolean sandbox, boolean disableSignUp) {
        start(context, requestCode, aid, null, sandbox, disableSignUp);
    }

    public static void start(Context context, int requestCode, String aid, String endpoint) {
        start(context, requestCode, aid, endpoint, false, null);
    }

    public static void start(Context context, int requestCode, String aid, String endpoint, boolean disableSignUp) {
        start(context, requestCode, aid, endpoint, false, disableSignUp);
    }

    private static void start(Context context, int requestCode, String aid, String endpoint, boolean sandbox, Boolean disableSignUp) {
        Intent intent = new Intent(context, OAuthActivity.class);
        intent.putExtra(EXTRA_AID, aid);
        if (!TextUtils.isEmpty(endpoint)) {
            intent.putExtra(EXTRA_ENDPOINT, endpoint);
        } else if (sandbox) {
            intent.putExtra(EXTRA_SANDBOX, true);
        }
        if (disableSignUp != null) {
            intent.putExtra(EXTRA_DISABLE_SIGN_UP, disableSignUp.booleanValue());
        }

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String aid = extras.getString(EXTRA_AID);
        String endpoint = extras.getString(EXTRA_ENDPOINT);
        if (TextUtils.isEmpty(endpoint)) {
            endpoint = extras.getBoolean(EXTRA_SANDBOX) ? BASE_URL_SANDBOX : BASE_URL_PROD;
        }
        boolean disableSignUp = extras.getBoolean(EXTRA_DISABLE_SIGN_UP, true);

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
                        "%scheckout/user/loginShow?client_id=%s&redirect_uri=%s&response_type=token&disable_sign_up=%b"
                        , endpoint
                        , aid
                        , REDIRECT_URI
                        , disableSignUp
                )
        );
    }
}
