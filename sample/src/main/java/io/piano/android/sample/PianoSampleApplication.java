package io.piano.android.sample;

import android.app.Application;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.piano.android.api.PianoClient;
import io.piano.android.composer.Composer;
import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdCallback;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.facebook.FacebookOAuthProvider;
import io.piano.android.id.google.GoogleOAuthProvider;
import io.piano.android.id.models.PianoIdToken;
import timber.log.Timber;

public class PianoSampleApplication extends Application {

    public static final String PIANO_ENDPOINT = BuildConfig.PIANO_ENDPOINT.isEmpty()
        ? BuildConfig.DEBUG
            ? PianoId.ENDPOINT_SANDBOX
            : PianoId.ENDPOINT_PRODUCTION
        : BuildConfig.PIANO_ENDPOINT;

    private PianoClient pianoClient;
    private final Gson gson = new GsonBuilder().setLenient().create();

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        pianoClient = new PianoClient(this, BuildConfig.PIANO_AID, PIANO_ENDPOINT);

        PianoId
            .init(PIANO_ENDPOINT, BuildConfig.PIANO_AID)
            .with(new PianoIdCallback<PianoIdToken>() {
                @Override
                public void onSuccess(PianoIdToken token) {
                    setPianoIdToken(token);
                }

                @Override
                public void onFailure(PianoIdException exception) {

                }
            })
            .with(new GoogleOAuthProvider())
            .with(new FacebookOAuthProvider());
        Composer.init(this, BuildConfig.PIANO_AID, PIANO_ENDPOINT);
        PianoIdToken token = getPianoIdToken();
        Composer.getInstance().userToken(token != null ? token.accessToken : null);
    }

    public PianoClient getPianoClient() {
        return pianoClient;
    }

    public PianoIdToken getPianoIdToken() {
        return gson.fromJson(
                getSharedPreferences("io.piano", MODE_PRIVATE).getString("token", null),
                PianoIdToken.class
        );
    }

    public void setPianoIdToken(PianoIdToken token) {
        getSharedPreferences("io.piano", MODE_PRIVATE)
            .edit()
            .putString("token", gson.toJson(token))
            .apply();

        pianoClient.setAccessToken(token.accessToken);
    }

    public void removePianoIdToken() {
        getSharedPreferences("io.piano", MODE_PRIVATE)
            .edit()
            .remove("token")
            .apply();
    }

    public void loadSettings() {
        PianoIdToken token = getPianoIdToken();
        if (token != null) {
            pianoClient.setAccessToken(token.accessToken);
        }
    }
}
