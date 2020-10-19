package io.piano.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdCallback;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.models.PianoIdToken;
import io.piano.sample.databinding.ActivityMainBinding;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int PIANO_ID_REQUEST_CODE = 1;

    private ActivityMainBinding binding;
    private PrefsStorage prefsStorage;

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
            Intent intent = PianoId.signIn().getIntent(MainActivity.this);
            startActivityForResult(intent, PIANO_ID_REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIANO_ID_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    try {
                        PianoIdToken token = PianoId.getPianoIdTokenResult(data);
                        setAccessToken(token);
                    } catch (PianoIdException e) {
                        showError(e);
                    }
                    break;
                case RESULT_CANCELED:
                    showMessage("OAuth cancelled");
                    break;
                case PianoId.RESULT_ERROR:
                    try {
                        PianoId.getPianoIdTokenResult(data);
                    } catch (PianoIdException e) {
                        showError(e);
                    }
                    break;
            }

        }
    }

    private void signOut() {
        PianoIdToken token = prefsStorage.getPianoIdToken();
        prefsStorage.setPianoIdToken(null);
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
        showMessage("accessToken = " + token.accessToken);
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
