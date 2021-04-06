package io.piano.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import io.piano.android.composer.Composer;
import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdAuthResultContract;
import io.piano.android.id.PianoIdCallback;
import io.piano.android.id.PianoIdClient;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.models.PianoIdAuthFailureResult;
import io.piano.android.id.models.PianoIdAuthSuccessResult;
import io.piano.android.id.models.PianoIdToken;
import io.piano.sample.databinding.ActivityMainBinding;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefsStorage prefsStorage;

    private final ActivityResultLauncher<PianoIdClient.SignInContext> authResult = registerForActivityResult(
            new PianoIdAuthResultContract(),
            r -> {
                if (r == null) {
                    showMessage("OAuth cancelled");
                } else if (r.isSuccess()) {
                    PianoIdAuthSuccessResult data = (PianoIdAuthSuccessResult) r;
                    Timber.d("Is this a new user registered? %b", data.isNewUser());
                    setAccessToken(data.getToken());
                } else {
                    showError(((PianoIdAuthFailureResult) r).getException());
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsStorage = SimpleDependenciesProvider.getInstance(this).getPrefsStorage();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (isDeepLink()) {
            Timber.d("We processed deep link");
        }

        binding.buttonPianoIdLogin.setOnClickListener(v -> {
            authResult.launch(PianoId.signIn());
        });

        binding.buttonPianoIdLogout.setOnClickListener(v -> signOut());

        binding.buttonPianoIdRefreshToken.setOnClickListener(v -> {
            PianoIdToken token = prefsStorage.getPianoIdToken();
            if (token == null) {
                showMessage("Can't refresh token, we aren't authorized yet");
                return;
            }
            PianoId.refreshToken(token.refreshToken, PianoIdCallback.asResultCallback(new PianoIdCallback<PianoIdToken>() {
                @Override
                public void onSuccess(PianoIdToken data) {
                    setAccessToken(data);
                }

                @Override
                public void onFailure(@NotNull PianoIdException exception) {
                    showError(exception);
                }
            }));
        });

        binding.buttonComposerExample.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ComposerActivity.class))
        );

        binding.buttonComposerScrollDepth.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ComposerScrollDepthActivity.class))
        );

        binding.buttonComposerClearStorage.setOnClickListener(v ->
                Composer.getInstance().clearStoredData()
        );

        binding.buttonClearAccessToken.setOnClickListener(v -> {
            signOut();

            CookieManager cookieManager = CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(MainActivity.this);
                cookieSyncManager.startSync();

                cookieManager.removeAllCookie();

                cookieSyncManager.stopSync();
            } else {
                cookieManager.removeAllCookies(null);
            }
        });
    }

    private void signOut() {
        PianoIdToken token = prefsStorage.getPianoIdToken();
        setAccessToken(null);
        PianoIdCallback<Object> callback = new PianoIdCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                showMessage("Sign out success callback");
            }

            @Override
            public void onFailure(@NotNull PianoIdException exception) {
                showError(exception);
            }
        };
        PianoId.signOut(token != null ? token.accessToken : "tmp", PianoIdCallback.asResultCallback(callback));
    }

    private void setAccessToken(PianoIdToken token) {
        prefsStorage.setPianoIdToken(token);
        String accessToken = token != null ? token.accessToken : null;
        Composer.getInstance().userToken(accessToken);
        showMessage("accessToken = " + accessToken);
    }

    private void showError(Throwable throwable) {
        Timber.e(throwable);
        showMessage("We've got error: " + throwable.getMessage());
    }

    private void showMessage(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    boolean isDeepLink() {
        Uri uri = getIntent().getData();
        if (uri == null)
            return false;
        if (PianoId.isPianoIdUri(uri)) {
            PianoId.parsePianoIdToken(uri, PianoIdCallback.asResultCallback(new PianoIdCallback<PianoIdToken>() {
                @Override
                public void onSuccess(@NonNull PianoIdToken token) {
                    Timber.d("Auth successful");
                    setAccessToken(token);
                }

                @Override
                public void onFailure(@NonNull PianoIdException exception) {
                    Timber.e(exception, "Auth unsuccessful");
                }
            }));
        } else {
            Timber.d("App deep link");
        }
        return true;
    }
}
