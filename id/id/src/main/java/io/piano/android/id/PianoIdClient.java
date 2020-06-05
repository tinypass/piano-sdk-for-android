package io.piano.android.id;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.piano.android.id.models.HostResponse;
import io.piano.android.id.models.PianoIdApi;
import io.piano.android.id.models.PianoIdToken;
import io.piano.android.id.models.SocialTokenData;
import io.piano.android.id.models.SocialTokenResponse;
import io.piano.android.id.models.TokenData;
import io.piano.android.id.models.TokenResponse;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Piano ID authorization
 */
public class PianoIdClient {
    private static final String AUTH_PATH = "id/api/v1/identity/vxauth/authorize";
    private static final String SIGN_OUT_PATH = "/id/api/v1/identity/logout?response_type=code";

    static final String SOCIAL_LOGIN_CALLBACK_TEMPLATE = "(function(){window.PianoIDMobileSDK.socialLoginCallback('%s')})()";
    static final String LINK_AUTHORITY = "success";
    static final String PARAM_ACCESS_TOKEN = "access_token";
    static final String PARAM_REFRESH_TOKEN = "refresh_token";

    static final String PARAM_RESPONSE_TYPE = "response_type";
    static final String PARAM_CLIENT_ID = "client_id";
    static final String PARAM_FORCE_REDIRECT = "force_redirect";
    static final String PARAM_DISABLE_SIGN_UP = "disable_sign_up";
    static final String PARAM_SCREEN = "screen";
    static final String PARAM_OAUTH_PROVIDERS = "oauth_providers";

    static final String VALUE_RESPONSE_TYPE_TOKEN = "token";
    static final String VALUE_FORCE_REDIRECT = "1";

    final HttpUrl backendEndpoint;
    final String aid;
    private final PianoIdApi api;
    private final Gson gson;
    private final SparseArray<PianoIdException> exceptions = new SparseArray<>();
    final Map<String, PianoIdOAuthProvider> oauthProviders = new HashMap<>();
    HttpUrl authEndpoint = null;
    private PianoIdCallback<PianoIdToken> tokenCallback;

    PianoIdClient(@NonNull PianoIdApi api, @NonNull Gson gson, @NonNull HttpUrl endpoint, @NonNull String aid) {
        this.api = api;
        this.gson = gson;
        this.backendEndpoint = endpoint;
        this.aid = aid;
    }

    /**
     * Sets callback for {@link PianoIdToken} changes
     *
     * @param callback {@link PianoIdCallback} for listening changes
     * @return {@link PianoIdClient} instance
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public PianoIdClient with(@Nullable PianoIdCallback<PianoIdToken> callback) {
        tokenCallback = callback;
        return this;
    }

    /**
     * Adds OAuth provider
     *
     * @param provider {@link PianoIdOAuthProvider} instance
     * @return {@link PianoIdClient} instance
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public PianoIdClient with(@NonNull PianoIdOAuthProvider provider) {
        oauthProviders.put(provider.getName().toLowerCase(), provider);
        return this;
    }

    @Nullable
    PianoIdCallback<PianoIdToken> getTokenCallback() {
        return tokenCallback;
    }

    @NonNull
    SignInContext signIn() {
        return new SignInContext(this);
    }

    void getAuthEndpoint(@NonNull final PianoIdCallback<HttpUrl> callback) {
        if (authEndpoint != null)
            callback.onSuccess(authEndpoint);
        else api.getDeploymentHost(aid)
                .enqueue(new Callback<HostResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<HostResponse> call, @NonNull Response<HostResponse> response) {
                        try {
                            HostResponse hostResponse = Utils.validateResponse(response);
                            if (!hostResponse.hasError()) {
                                authEndpoint = HttpUrl.get(hostResponse.host);
                                callback.onSuccess(authEndpoint);
                            } else callback.onFailure(new PianoIdException(hostResponse.getError()));
                        } catch (Exception e) {
                            callback.onFailure(Utils.wrapException(e));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<HostResponse> call, @NonNull Throwable t) {
                        callback.onFailure(Utils.wrapException(t));
                    }
                });
    }

    void signOut(@NonNull final String accessToken, @NonNull final PianoIdCallback<Object> callback) {
        getAuthEndpoint(new PianoIdCallback<HttpUrl>() {
            @Override
            public void onSuccess(HttpUrl data) {
                api.signOut(data.newBuilder().encodedPath(SIGN_OUT_PATH).build().toString(), aid, accessToken)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                try {
                                    callback.onSuccess(Utils.validateResponse(response));
                                } catch (Exception e) {
                                    callback.onFailure(Utils.wrapException(e));
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                callback.onFailure(Utils.wrapException(t));
                            }
                        });
            }

            @Override
            public void onFailure(PianoIdException exception) {
                callback.onFailure(exception);
            }
        });
    }

    void getSignInUrl(final boolean disableSignUp, @Nullable final String widget, @NonNull final PianoIdCallback<String> callback) {
        getAuthEndpoint(new PianoIdCallback<HttpUrl>() {
            @Override
            public void onSuccess(HttpUrl data) {
                final HttpUrl.Builder builder = data.newBuilder()
                        .addEncodedPathSegments(AUTH_PATH)
                        .addQueryParameter(PARAM_RESPONSE_TYPE, VALUE_RESPONSE_TYPE_TOKEN)
                        .addQueryParameter(PARAM_CLIENT_ID, aid)
                        .addQueryParameter(PARAM_FORCE_REDIRECT, VALUE_FORCE_REDIRECT)
                        .addQueryParameter(PARAM_DISABLE_SIGN_UP, Boolean.toString(disableSignUp));
                if (widget != null && !widget.isEmpty()) {
                    builder.addQueryParameter(PARAM_SCREEN, widget);
                }
                if (!oauthProviders.isEmpty()) {
                    builder.addQueryParameter(PARAM_OAUTH_PROVIDERS, join(",", oauthProviders.keySet()));
                }
                callback.onSuccess(builder.build().toString());
            }

            @Override
            public void onFailure(PianoIdException exception) {
                callback.onFailure(exception);
            }
        });
    }

    int saveException(@NonNull PianoIdException exc) {
        int code = exc.hashCode();
        exceptions.append(code, exc);
        return code;
    }

    @Nullable
    PianoIdException getStoredException(int code) {
        return exceptions.get(code);
    }

    @Nullable
    PianoIdToken getResultFromIntent(@Nullable Intent intent) {
        if (intent == null)
            return null;
        int statusCode = intent.getIntExtra(PianoId.KEY_ERROR, 0);
        if (statusCode == 0)
            return intent.getParcelableExtra(PianoId.KEY_TOKEN);
        PianoIdException exc = exceptions.get(statusCode, new PianoIdException("Unknown error"));
        exceptions.delete(statusCode);
        throw exc;
    }

    @Nullable
    PianoIdToken processUri(Uri uri) {
        if (uri == null || !LINK_AUTHORITY.equalsIgnoreCase(uri.getAuthority()))
            return null;
        try {
            String accessToken = uri.getQueryParameter(PARAM_ACCESS_TOKEN);
            String refreshToken = uri.getQueryParameter(PARAM_REFRESH_TOKEN);
            if (accessToken == null || refreshToken == null)
                throw new IllegalArgumentException("accessToken and refreshToken must be filled");
            PianoIdToken token = buildToken(accessToken, refreshToken);
            if (tokenCallback != null)
                tokenCallback.onSuccess(token);
            return token;
        } catch (Exception e) {
            PianoIdException exc = Utils.wrapException(e);
            if (tokenCallback != null) {
                tokenCallback.onFailure(exc);
            }
            throw exc;
        }
    }

    @NonNull
    PianoIdToken buildToken(@NonNull String accessToken, @NonNull String refreshToken) {
        TokenData data = gson.fromJson(
                new String(Base64.decode(accessToken.split("\\.")[1], Base64.URL_SAFE)),
                TokenData.class
        );
        // may be add some logic for token data here
        return new PianoIdToken(accessToken, new Date(data.exp * 1000), refreshToken);
    }

    @NonNull
    PianoIdToken buildToken(@NonNull String jsPayload) {
        TokenResponse response = gson.fromJson(jsPayload, TokenResponse.class);
        return buildToken(response.accessToken, response.refreshToken);
    }

    @NonNull
    Intent buildSocialAuthIntent(@NonNull Context context, @NonNull String jsPayload) {
        SocialTokenResponse response = gson.fromJson(jsPayload, SocialTokenResponse.class);
        String name = response.oauthProvider.toLowerCase();
        PianoIdOAuthProvider provider = oauthProviders.get(name);
        if (provider == null)
            throw new PianoIdException("OAuth provider '" + name + "' is not registered");

        Bundle bundle = buildBundle(response);
        return provider.buildIntent(context, bundle).putExtras(bundle);
    }

    @NonNull
    String buildResultJsCommand(@NonNull String provider, @NonNull String token) {
        SocialTokenData tokenData = new SocialTokenData(provider.toUpperCase(), token, aid);
        String json = gson.toJson(tokenData);
        return String.format(Locale.US, SOCIAL_LOGIN_CALLBACK_TEMPLATE, json);
    }

    // mock in tests
    @NonNull
    Bundle buildBundle(SocialTokenResponse response) {
        Bundle bundle = new Bundle();
        bundle.putString(PianoId.KEY_CLIENT_ID, response.clientId);
        return bundle;
    }

    // mock in tests
    @SuppressWarnings("SameParameterValue")
    @NonNull
    String join(CharSequence delimiter, Collection<String> collection) {
        return TextUtils.join(delimiter, collection);
    }

    public static class SignInContext {

        final PianoIdClient client;
        boolean disableSignUp = false;
        String widget = null;

        SignInContext(@NonNull PianoIdClient client) {
            this.client = client;
        }

        /**
         * Turns off the registration screen
         *
         * @return {@link SignInContext} instance
         */
        @NonNull
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public SignInContext disableSignUp() {
            disableSignUp = true;
            return this;
        }

        /**
         * Sets the screen when opening Piano ID. Use {@link PianoId#WIDGET_LOGIN} to open the login screen
         * or {@link PianoId#WIDGET_REGISTER} to open the registration screen.
         *
         * @param widget {@link PianoId#WIDGET_LOGIN}, {@link PianoId#WIDGET_REGISTER} or null
         * @return {@link SignInContext} instance
         */
        @NonNull
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public SignInContext widget(@Nullable String widget) {
            this.widget = widget;
            return this;
        }

        /**
         * Gets {@link Intent} for starting authorization process
         *
         * @param context {@link Context} for building {@link Intent}
         * @return {@link Intent}, which should be passed to {@link android.app.Activity#startActivityForResult(Intent, int)}
         */
        @NonNull
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Intent getIntent(Context context) {
            return PianoIdActivity.buildIntent(context, disableSignUp, widget);
        }
    }
}
