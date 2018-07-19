package io.piano.android.oauth.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import io.piano.android.oauth.R;

public abstract class BaseOAuthActivity extends Activity {

    protected static final String REDIRECT_URI = "piano-sdk-for-android://oauth";

    protected static final String EXTRA_URL = "url";

    public static final String EXTRA_ACCESS_TOKEN = "accessToken";

    public static final String WIDGET_LOGIN = "login";
    public static final String WIDGET_REGISTER = "register";

    protected String url;

    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getIntent().getStringExtra(EXTRA_URL);

        setContentView(R.layout.activity_oauth);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public abstract static class Builder {

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
                urlBuilder.append(getBaseUrlSandbox());
            } else {
                urlBuilder.append(getBaseUrlProd());
            }

            urlBuilder.append(getUrlAction());
            urlBuilder.append("?client_id=").append(aid);
            urlBuilder.append("&redirect_uri=").append(REDIRECT_URI);
            urlBuilder.append("&response_type=token");

            if (disableSignUp != null) {
                urlBuilder.append("&disable_sign_up=").append(disableSignUp.booleanValue());
            }

            if (!TextUtils.isEmpty(widget)) {
                urlBuilder.append("&").append(getWidgetParamName()).append("=").append(widget);
            }

            Intent intent = new Intent(context, getImplementation());
            String url = urlBuilder.toString();
            intent.putExtra(EXTRA_URL, url);

            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                activity.startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        }

        protected abstract String getBaseUrlProd();
        protected abstract String getBaseUrlSandbox();
        protected abstract String getUrlAction();
        protected abstract String getWidgetParamName();
        protected abstract Class getImplementation();
    }
}
