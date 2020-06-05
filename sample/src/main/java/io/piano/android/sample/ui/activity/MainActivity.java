package io.piano.android.sample.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.piano.android.api.PianoClient;
import io.piano.android.api.user.model.Access;
import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.models.PianoIdToken;
import io.piano.android.sample.PianoSampleApplication;
import io.piano.android.sample.R;
import io.piano.android.sample.feature.composer.ComposerActivity;
import io.piano.android.sample.feature.composer.ComposerScrollDepthActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static int PIANO_ID_REQUEST_CODE = 1;

    private Button buttonListAccess;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((PianoSampleApplication) getApplication()).loadSettings();

        setContentView(R.layout.activity_main);
        buttonListAccess = findViewById(R.id.button_list_access);

        if (isDeepLink()) {
            Timber.d("We processed deep link");
        }

        final Button pianoIdSignInButtonActivity = findViewById(R.id.button_piano_id_activity);
        pianoIdSignInButtonActivity.setOnClickListener(v -> {
            Intent intent = PianoId.signIn().getIntent(MainActivity.this);
            startActivityForResult(intent, PIANO_ID_REQUEST_CODE);
        });

        findViewById(R.id.button_composer_example).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ComposerActivity.class))
        );

        findViewById(R.id.button_composer_scroll_depth).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ComposerScrollDepthActivity.class))
        );

        findViewById(R.id.button_clear_access_token).setOnClickListener(v -> {
            PianoSampleApplication application = (PianoSampleApplication) getApplication();

            PianoIdToken pianoIdToken = application.getPianoIdToken();
            if (pianoIdToken != null) {
                PianoId.signOut(pianoIdToken.accessToken);
                application.removePianoIdToken();
            } else {
                PianoId.signOut("tmp");
            }

            CookieManager cookieManager = CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(MainActivity.this);
                cookieSyncManager.startSync();

                cookieManager.removeAllCookie();

                cookieSyncManager.stopSync();
            } else {
                cookieManager.removeAllCookies(null);
            }

            ((PianoSampleApplication) getApplication()).getPianoClient().setAccessToken(null);

            Snackbar.make(v, "signed out!", Snackbar.LENGTH_SHORT).show();
        });

        buttonListAccess.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("oauth", MODE_PRIVATE);
            String accessToken = sharedPreferences.getString("accessToken", null);
            if (TextUtils.isEmpty(accessToken)) {
                Snackbar.make(v, "login with piano.io first!", Snackbar.LENGTH_SHORT).show();
            } else {
                listAccess();
            }
        });

        PianoIdToken token = ((PianoSampleApplication) getApplication()).getPianoIdToken();
        if (token != null) {
            Snackbar.make(buttonListAccess, token.accessToken, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIANO_ID_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    try {
                        PianoIdToken token = PianoId.getResultFromIntent(data);
                        setAccessToken(token);
                    } catch (PianoIdException e) {
                        Timber.e(e);
                        Snackbar.make(buttonListAccess, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                    break;
                case RESULT_CANCELED:
                    Snackbar.make(buttonListAccess, "OAuth cancelled", Snackbar.LENGTH_SHORT).show();
                    break;
                case PianoId.RESULT_ERROR:
                    Snackbar.make(buttonListAccess, "Result error", Snackbar.LENGTH_SHORT).show();
                    break;
            }

        }
    }

    @Override
    protected void onStop() {
        subscriptions.dispose();
        super.onStop();
    }

    private void setAccessToken(PianoIdToken token) {
        ((PianoSampleApplication) getApplication()).setPianoIdToken(token);

        Snackbar.make(buttonListAccess, "accessToken = " + token.accessToken, Snackbar.LENGTH_SHORT).show();
    }

    private void listAccess() {
        subscriptions.add(
                Observable
                        .fromCallable(() -> {
                            List<Access> list;
                            PianoClient pianoClient = ((PianoSampleApplication) getApplication()).getPianoClient();
                            list = pianoClient.getUserAccessApi().list(pianoClient.getAid(), null, null);
                            return list != null ? list : new ArrayList<Access>();
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                accesses -> {
                                    if (accesses.isEmpty()) {
                                        Snackbar.make(buttonListAccess, "no access", Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        StringBuilder accessBuilder = new StringBuilder("Here's what you can access:\n");
                                        for (Access access : accesses) {
                                            accessBuilder.append(access.getResource().getName()).append(", ");
                                        }
                                        accessBuilder.delete(accessBuilder.length() - 2, accessBuilder.length());

                                        Snackbar.make(buttonListAccess, accessBuilder.toString(), Snackbar.LENGTH_INDEFINITE).setAction("Great!", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        }).show();
                                    }
                                },
                                e -> Snackbar.make(buttonListAccess, e.getMessage(), Snackbar.LENGTH_SHORT).show()
                        )
        );
    }

    boolean isDeepLink() {
        Uri uri = getIntent().getData();
        if (uri == null)
            return false;
        PianoIdToken token = null;
        try {
            token = PianoId.processUri(uri);
        } catch (PianoIdException exc) {
            Timber.e(exc, "Auth unsuccessful");
        }
        if (token != null) {
            Timber.d("Auth successful");
            setAccessToken(token);
        } else {
            Timber.d("App deep link");
        }
        return true;
    }
}
