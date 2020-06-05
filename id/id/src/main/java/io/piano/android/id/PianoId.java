package io.piano.android.id;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.piano.android.id.models.PianoIdApi;
import io.piano.android.id.models.PianoIdToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Piano ID authorization
 */
public class PianoId {

    /**
     * Default production endpoint
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String ENDPOINT_PRODUCTION = "https://buy.tinypass.com";
    /**
     * Australia production endpoint
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String ENDPOINT_PRODUCTION_AUSTRALIA = "https://buy-au.piano.io";
    /**
     * Asia/Pacific production endpoint
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String ENDPOINT_PRODUCTION_ASIA_PACIFIC = "https://buy-ap.piano.io";
    /**
     * Sandbox endpoint
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String ENDPOINT_SANDBOX = "https://sandbox.tinypass.com";

    /**
     * Widget for login screen
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String WIDGET_LOGIN = "login";
    /**
     * Widget for registration screen
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String WIDGET_REGISTER = "register";

    /**
     * Client ID key for OAuth providers
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final String KEY_CLIENT_ID = "io.piano.android.id.CLIENT_ID";
    /**
     * Activity result code for error
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static final int RESULT_ERROR = 1;

    static final String KEY_OAUTH_PROVIDER_NAME = "io.piano.android.id.OAUTH_PROVIDER_NAME";
    static final String KEY_OAUTH_PROVIDER_TOKEN = "io.piano.android.id.OAUTH_PROVIDER_TOKEN";
    static final String KEY_TOKEN = "io.piano.android.id.PianoIdActivity.TOKEN";
    static final String KEY_ERROR = "io.piano.android.id.PianoIdActivity.ERROR";
    static final IllegalStateException NOT_INITIALIZED_EXC = new IllegalStateException(
            "Piano ID SDK is not initialized! Make sure that you initialize it via init()"
    );

    @VisibleForTesting
    static PianoIdClient client;

    /**
     * Initialize {@link PianoIdClient} singleton instance. It does'nt re-init it at next calls.
     *
     * @param endpoint Endpoint, which will be used. For example, {@link #ENDPOINT_PRODUCTION},
     *                 {@link #ENDPOINT_SANDBOX} or your custom endpoint
     * @param aid      Your AID
     * @return {@link PianoIdClient} instance.
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static PianoIdClient init(@NonNull String endpoint, @NonNull String aid) {
        if (client != null) {
            return client;
        }

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient httpClient = buildOkHttpClient();
        Retrofit retrofit = buildRetrofit(httpClient, gson, endpoint);
        final PianoIdApi api = retrofit.create(PianoIdApi.class);
        client = new PianoIdClient(api, gson, HttpUrl.get(endpoint), aid);
        return client;
    }

    /**
     * Gets preferences for authorization process
     *
     * @return {@link PianoIdClient.SignInContext} instance
     * @throws IllegalStateException If you call it before {@link #init(String, String)}
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static PianoIdClient.SignInContext signIn() throws IllegalStateException {
        if (client == null) {
            throw NOT_INITIALIZED_EXC;
        }

        return client.signIn();
    }

    /**
     * Sign out user by it's token
     *
     * @param accessToken User access token
     * @param callback    callback, which will receive sign-out result
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static void signOut(@NonNull final String accessToken, @Nullable final PianoIdCallback<Object> callback) {
        PianoIdCallback<Object> signOutCallback = callback != null ? callback : new PianoIdCallback<Object>() {
        };

        if (client == null) {
            signOutCallback.onFailure(Utils.wrapException(NOT_INITIALIZED_EXC));
        } else client.signOut(accessToken, signOutCallback);
    }

    /**
     * Sign out user by it's token. It's equals to {@link #signOut(String, PianoIdCallback)} with null callback
     *
     * @param accessToken User access token
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static void signOut(@NonNull final String accessToken) {
        signOut(accessToken, null);
    }

    /**
     * Extracts {@link PianoIdToken} from result {@link Intent}.
     * Use it in {@link android.app.Activity#onActivityResult(int, int, Intent)}
     *
     * @param intent Intent, which you get
     * @return {@link PianoIdToken} instance, if intent contains it
     * @throws PianoIdException if intent contains authorization error
     */
    @Nullable
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static PianoIdToken getResultFromIntent(@Nullable Intent intent) throws PianoIdException {
        return client.getResultFromIntent(intent);
    }

    @Nullable
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static PianoIdToken processUri(Uri uri) {
        return client.processUri(uri);
    }

    @NonNull
    static PianoIdClient getClient() throws IllegalStateException {
        if (client == null) {
            throw NOT_INITIALIZED_EXC;
        }
        return client;
    }

    static OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    static Retrofit buildRetrofit(OkHttpClient okHttpClient, Gson gson, String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
