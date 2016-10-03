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

    public static final String EXTRA_URL = "url";

    public static final String EXTRA_ACCESS_TOKEN = "accessToken";

    public static final String WIDGET_LOGIN = "login";
    public static final String WIDGET_REGISTER = "register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        String url = getIntent().getStringExtra(EXTRA_URL);
        webView.loadUrl(url);
    }

    public static class Builder {

        private Context context;
        private int requestCode;
        private String aid;
        private boolean sandbox;
        private String endpoint;
        private Boolean disableSignUp;
        private String widget;

        private Builder() {}

        public Builder(Context context, String aid) {
            this.context = context;
            this.aid = aid;
        }

        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public Builder sandbox(boolean sandbox) {
            this.sandbox = sandbox;
            return this;
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder disableSignUp(boolean disableSignUp) {
            this.disableSignUp = disableSignUp;
            return this;
        }

        /**
         * @param widget {@link #WIDGET_LOGIN} or {@link #WIDGET_REGISTER}
         */
        public Builder widget(String widget) {
            this.widget = widget;
            return this;
        }

        public void start() {
            StringBuilder urlBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(endpoint)) {
                urlBuilder.append(endpoint);
            } else if (sandbox) {
                urlBuilder.append(BASE_URL_SANDBOX);
            } else {
                urlBuilder.append(BASE_URL_PROD);
            }

            urlBuilder.append("checkout/user/loginShow");
            urlBuilder.append("?client_id=").append(aid);
            urlBuilder.append("&redirect_uri=").append(REDIRECT_URI);
            urlBuilder.append("&response_type=token");

            if (disableSignUp != null) {
                urlBuilder.append("&disable_sign_up=").append(disableSignUp.booleanValue());
            }

            if (!TextUtils.isEmpty(widget)) {
                urlBuilder.append("&widget=").append(widget);
            }

            Intent intent = new Intent(context, OAuthActivity.class);
            String url = urlBuilder.toString();
            intent.putExtra(EXTRA_URL, url);

            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                activity.startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        }
    }
}
