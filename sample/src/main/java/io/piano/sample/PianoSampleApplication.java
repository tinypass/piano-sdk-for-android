package io.piano.sample;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import io.piano.android.composer.Composer;
import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdCallback;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.facebook.FacebookOAuthProvider;
import io.piano.android.id.google.GoogleOAuthProvider;
import io.piano.android.id.models.PianoIdToken;
import timber.log.Timber;

public class PianoSampleApplication extends MultiDexApplication {

    public static final String PIANO_ENDPOINT = BuildConfig.PIANO_ENDPOINT.isEmpty()
            ? BuildConfig.DEBUG
            ? PianoId.ENDPOINT_SANDBOX
            : PianoId.ENDPOINT_PRODUCTION
            : BuildConfig.PIANO_ENDPOINT;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
        } catch (Exception e) {
            Timber.e(e);
        }

        PrefsStorage prefsStorage = SimpleDependenciesProvider.getInstance(this).getPrefsStorage();
        PianoId
                .init(PIANO_ENDPOINT, BuildConfig.PIANO_AID)
                .with(new PianoIdCallback<PianoIdToken>() {
                    @Override
                    public void onSuccess(PianoIdToken token) {
                        prefsStorage.setPianoIdToken(token);
                        Composer.getInstance().userToken(token.accessToken);
                    }

                    @Override
                    public void onFailure(@NonNull PianoIdException exception) {
                        Timber.e(exception);
                    }
                })
                .with(new GoogleOAuthProvider())
                .with(new FacebookOAuthProvider());
        Composer.init(this, BuildConfig.PIANO_AID, PIANO_ENDPOINT);
        PianoIdToken token = prefsStorage.getPianoIdToken();
        Composer.getInstance().userToken(token != null ? token.accessToken : null);
    }
}
