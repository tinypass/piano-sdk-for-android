package io.piano.sample;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Collection;

import io.piano.android.composer.Composer;
import io.piano.android.composer.listeners.EventTypeListener;
import io.piano.android.composer.listeners.ExperienceExecuteListener;
import io.piano.android.composer.listeners.MeterListener;
import io.piano.android.composer.listeners.NonSiteListener;
import io.piano.android.composer.listeners.ShowLoginListener;
import io.piano.android.composer.listeners.ShowTemplateListener;
import io.piano.android.composer.listeners.UserSegmentListener;
import io.piano.android.composer.model.CustomParameters;
import io.piano.android.composer.model.ExperienceRequest;
import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.showtemplate.ComposerJs;
import io.piano.android.composer.showtemplate.ShowTemplateController;
import io.piano.android.id.PianoId;
import io.piano.android.id.PianoIdException;
import io.piano.android.id.models.PianoIdToken;
import timber.log.Timber;

public class ComposerActivity extends AppCompatActivity {
    private static final int PIANO_ID_REQUEST_CODE = 1;

    private ShowTemplateController showTemplateController;
    private PrefsStorage prefsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsStorage = SimpleDependenciesProvider.getInstance(this).getPrefsStorage();
        setContentView(R.layout.activity_composer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomParameters customParameters = new CustomParameters()
                .content("contentKey", "contentValue0")
                .content("contentKey", "contentValue1")
                .user("userKey", "userValue")
                .request("requestKey", "requstValue1")
                .request("requestKey", "requstValue2")
                .request("requestKey", "requstValue3");
        ExperienceRequest request = new ExperienceRequest.Builder()
                .tag("tag")
                .debug(true)
                .customParams(customParameters)
                .build();

        Collection<EventTypeListener<? extends EventType>> listeners = Arrays.asList(
                (ExperienceExecuteListener) event -> {
                    String message = String.format("[%s] User = %s", Thread.currentThread().getName(), event.eventData.user);
                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                },
                (UserSegmentListener) event -> {
                    String message = String.format("[%s] User state = %s", Thread.currentThread().getName(), event.eventData.state);
                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                },
                (ShowLoginListener) event -> {
                    String message = String.format("[%s] %s", Thread.currentThread().getName(), event.eventData.userProvider);
                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                    signIn(event.eventData.userProvider);
                },
                (MeterListener) event -> {
                    String message = String.format("[%s] Meter state: %s\nviews = %s\nviewsLeft = %s\nmaxViews = %s\ntotalViews = %s", Thread.currentThread().getName(), event.eventData.state.name(), event.eventData.views, event.eventData.viewsLeft, event.eventData.maxViews, event.eventData.totalViews);
                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                },
                (ShowTemplateListener) event -> {
                    String message = String.format("[%s] %s", Thread.currentThread().getName(), event.eventData);
                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                    showTemplateController = ShowTemplateController.show(ComposerActivity.this, event, new ComposerJs() {
                        @JavascriptInterface
                        @Override
                        public void customEvent(@NonNull String eventData) {
                            Snackbar.make(findViewById(R.id.app_bar), eventData, Snackbar.LENGTH_LONG).show();
                        }

                        @JavascriptInterface
                        @Override
                        public void login(@NonNull String eventData) {
                            signIn(Composer.USER_PROVIDER_PIANO_ID);
                        }
                    });
                },
                (NonSiteListener) event -> {
                    String message = null;
                    if ((event.eventExecutionContext.activeMeters == null) || event.eventExecutionContext.activeMeters.isEmpty()) {
                        message = String.format("[%s] Active meters are null or empty!", Thread.currentThread().getName());
                    } else {
                        io.piano.android.composer.model.ActiveMeter activeMeter = event.eventExecutionContext.activeMeters.get(0);
                        message = String.format("[%s] Active meter:\nmeterName = %s\nviews = %s\nviewsLeft = %s\nmaxViews = %s\ntotalViews = %s", Thread.currentThread().getName(), activeMeter.meterName, activeMeter.views, activeMeter.viewsLeft, activeMeter.maxViews, activeMeter.totalViews);
                    }

                    Toast.makeText(
                            ComposerActivity.this, message, Toast.LENGTH_LONG
                    ).show();
                }
        );
        Composer.getInstance().getExperience(request, listeners, exception -> {
            String message = String.format("[%s] %s", Thread.currentThread().getName(), exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage());
            Toast.makeText(
                    ComposerActivity.this, message, Toast.LENGTH_LONG
            ).show();
        });
    }

    private void setAccessToken(PianoIdToken token) {
        prefsStorage.setPianoIdToken(token);
        String accessToken = token != null ? token.accessToken : null;

        if (showTemplateController != null && accessToken != null) {
            showTemplateController.reloadWithToken(accessToken);
        }

        Snackbar.make(findViewById(R.id.app_bar), "accessToken = " + accessToken, Snackbar.LENGTH_LONG).show();
    }

    private void signIn(String userProvider) {
        startActivityForResult(PianoId.signIn().getIntent(this), PIANO_ID_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIANO_ID_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    try {
                        setAccessToken(PianoId.getPianoIdTokenResult(data));
                    } catch (PianoIdException e) {
                        Timber.e(e);
                        Snackbar.make(findViewById(R.id.app_bar), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                    break;
                case RESULT_CANCELED:
                    Snackbar.make(findViewById(R.id.app_bar), "OAuth cancelled", Snackbar.LENGTH_SHORT).show();
                    break;
                case PianoId.RESULT_ERROR:
                    Snackbar.make(findViewById(R.id.app_bar), "Result error", Snackbar.LENGTH_SHORT).show();
                    break;
            }

        }
    }
}
