package io.piano.android.oauth.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.piano.android.oauth.exception.OAuthException;
import io.piano.android.oauth.ui.activity.PianoIdOAuthRedirectActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PianoIdClient {

    public static final String WIDGET_LOGIN = "login";
    public static final String WIDGET_REGISTER = "register";

    private static final Handler HANDLER_MAIN_THREAD = new Handler(Looper.getMainLooper());

    private static final String REDIRECT_URI_PROTOCOL = "piano.id.oauth";
    private static final String REDIRECT_URI_HOST = "success";

    private static final String BASE_URL_API_PROD = "https://buy.tinypass.com/";
    private static final String BASE_URL_OAUTH_PROD = "https://id.tinypass.com/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";

    private static final String URL_AUTHORIZE = "id/api/v1/identity/vxauth/authorize";
    private static final String URL_SIGN_OUT = "id/api/v1/identity/logout";
    private static final String URL_DEPLOYMENT_HOST = "api/v3/anon/mobile/sdk/id/deployment/host";

    private static String deploymentHost = "";

    private Context context;
    private int requestCode;

    private String aid;
    private String idEndpointUrl;
    private String apiEndpointUrl;
    private String authPath;

    private PianoIdClient(Context context, int requestCode, String aid, String idEndpointUrl, String apiEndpointUrl, String authPath) {
        this.context = context;
        this.requestCode = requestCode;
        this.aid = aid;
        this.idEndpointUrl = idEndpointUrl;
        this.apiEndpointUrl = apiEndpointUrl;
        this.authPath = authPath;
    }

    public void signIn() {
        if (!deploymentHost.isEmpty()) {
            startActivity();
        } else {
            requestDeploymentHost(new HostCallback() {
                @Override
                public void onFailure(OAuthException e) {
                    HANDLER_MAIN_THREAD.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Couldn't connect to server.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onSuccess(final String host) {
                    HANDLER_MAIN_THREAD.post(new Runnable() {
                        @Override
                        public void run() {
                            deploymentHost = host;
                            startActivity();
                        }
                    });
                }
            });
        }
    }

    public void signOut(String token, @NonNull final OAuthCallback callback) {
        requestSignOut(token, callback);
    }

    public void warmup() {
        requestDeploymentHost(new HostCallback() {
            @Override
            public void onFailure(OAuthException e) {
            }

            @Override
            public void onSuccess(final String host) {
                deploymentHost = host;
            }
        });
    }

    private void startActivity() {
        final Uri url = Uri.parse(deploymentHost)
                .buildUpon()
                .encodedPath(authPath)
                .build();

        Intent intent = new Intent(context, PianoIdOAuthRedirectActivity.class);
        intent.putExtra(PianoIdOAuthRedirectActivity.EXTRA_URL, url.toString());
        intent.putExtra(PianoIdOAuthRedirectActivity.EXTRA_AID, aid);

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    private void requestSignOut(String token, @NonNull final OAuthCallback callback) {
        Uri.Builder builder = Uri.parse(idEndpointUrl).buildUpon()
                .encodedPath(URL_SIGN_OUT)
                .appendQueryParameter("client_id", aid)
                .appendQueryParameter("token", token);

        Request request = new Request.Builder()
                .url(builder.build().toString())
                .get()
                .build();

        OkHttpClientHolder.OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HANDLER_MAIN_THREAD.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(new OAuthException("Sign out failed", e));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    HANDLER_MAIN_THREAD.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess();
                        }
                    });
                } else {
                    HANDLER_MAIN_THREAD.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(new OAuthException("Sign out failed"));
                        }
                    });
                }
            }
        });
    }

    private void requestDeploymentHost(final HostCallback callback) {
        Uri.Builder builder = Uri.parse(apiEndpointUrl)
                .buildUpon()
                .encodedPath(URL_DEPLOYMENT_HOST);

        FormBody formBody = new FormBody.Builder()
                .add("aid", aid)
                .build();

        Request request = new Request.Builder()
                .url(builder.build().toString())
                .post(formBody)
                .build();

        OkHttpClientHolder.OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(new OAuthException("Deployment host request failed", e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonStr = response.body().string();
                    JSONObject json = new JSONObject(jsonStr);

                    String deploymentHost = json.getString("data");
                    if (deploymentHost != null && deploymentHost.isEmpty()) {
                        deploymentHost = BASE_URL_OAUTH_PROD;
                    }

                    callback.onSuccess(deploymentHost);
                } catch (IOException | JSONException e) {
                    callback.onFailure(new OAuthException("Cannot parse deployment host data", e));
                }
            }
        });
    }

    private static class OkHttpClientHolder {
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .readTimeout(10_000, TimeUnit.MILLISECONDS)
                .writeTimeout(10_000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static class Builder {

        private Context context;
        private int requestCode;
        private String aid;
        private boolean sandbox;
        private String endpoint;
        private Boolean disableSignUp;
        private String widget;

        private Builder() {
        }

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

        public PianoIdClient build() {
            String idEndpointUrl;
            String apiEndpointUrl;

            if (!TextUtils.isEmpty(endpoint)) {
                idEndpointUrl = endpoint;
                apiEndpointUrl = endpoint;
            } else if (sandbox) {
                idEndpointUrl = BASE_URL_SANDBOX;
                apiEndpointUrl = BASE_URL_SANDBOX;
            } else {
                idEndpointUrl = BASE_URL_OAUTH_PROD;
                apiEndpointUrl = BASE_URL_API_PROD;
            }

            Uri.Builder urlBuilder = new Uri.Builder()
                    .encodedPath(URL_AUTHORIZE)
                    .appendQueryParameter("client_id", aid)
                    .appendQueryParameter("response_type", "token")
                    .appendQueryParameter("redirect_uri", REDIRECT_URI_PROTOCOL + "." + aid.toLowerCase() + "://" + REDIRECT_URI_HOST)
                    .appendQueryParameter("force_redirect", "1");

            if (disableSignUp != null) {
                urlBuilder.appendQueryParameter("disable_sign_up", disableSignUp.toString());
            }

            if (!TextUtils.isEmpty(widget)) {
                urlBuilder.appendQueryParameter("screen", widget);
            }

            String authUrl = urlBuilder.build().toString();
            return new PianoIdClient(context, requestCode, aid, idEndpointUrl, apiEndpointUrl, authUrl);
        }
    }

    public interface OAuthCallback {

        void onFailure(OAuthException e);

        void onSuccess();
    }

    private interface HostCallback {

        void onFailure(OAuthException e);

        void onSuccess(String host);
    }
}
