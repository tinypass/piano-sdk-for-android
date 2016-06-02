package io.piano.android.sample.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.concurrent.Callable;

import io.piano.android.api.PianoClient;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.user.model.Access;
import io.piano.android.oauth.ui.activity.OAuthActivity;
import io.piano.android.sample.PianoSampleApplication;
import io.piano.android.sample.R;
import io.piano.android.sample.feature.composer.ComposerActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonLoginOAuth;

    private Button buttonClearAccessToken;
    private Button buttonListAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean runApiCall = true;

                Editable email = editTextEmail.getText();
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Required!");
                    runApiCall = false;
                }

                Editable password = editTextPassword.getText();
                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Required!");
                    runApiCall = false;
                }

                if (runApiCall) {
                    authenticate(email.toString(), password.toString());
                }
            }
        });

        buttonLoginOAuth = (Button) findViewById(R.id.button_login_oauth);
        buttonLoginOAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OAuthActivity.class);
                PianoClient pianoClient = ((PianoSampleApplication) getApplication()).getPianoClient();
                intent.putExtra(OAuthActivity.EXTRA_AID, pianoClient.getAid());
                intent.putExtra(OAuthActivity.EXTRA_SANDBOX, pianoClient.isSandbox());
                startActivityForResult(intent, 42);
            }
        });

        findViewById(R.id.button_composer_example).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ComposerActivity.class));
            }
        });

        buttonClearAccessToken = (Button) findViewById(R.id.button_clear_access_token);
        buttonClearAccessToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("oauth", MODE_PRIVATE);
                sharedPreferences.edit().remove("accessToken").apply();
                ((PianoSampleApplication) getApplication()).getPianoClient().setAccessToken(null);
                Snackbar.make(buttonClearAccessToken, "signed out!", Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonListAccess = (Button) findViewById(R.id.button_list_access);
        buttonListAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("oauth", MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("accessToken", null);
                if (TextUtils.isEmpty(accessToken)) {
                    Snackbar.make(buttonListAccess, "login with piano.io first!", Snackbar.LENGTH_SHORT).show();
                } else {
                    listAccess();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (42 == requestCode) {
            if (RESULT_OK == resultCode) {
                String accessToken = data.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
                Snackbar.make(buttonLoginOAuth, "accessToken = " + accessToken, Snackbar.LENGTH_SHORT).show();

                SharedPreferences sharedPreferencesOAuth = getSharedPreferences("oauth", MODE_PRIVATE);
                sharedPreferencesOAuth.edit().putString("accessToken", accessToken).apply();
                ((PianoSampleApplication) getApplication()).getPianoClient().setAccessToken(accessToken);
            }
        }
    }

    private void authenticate(final String email, final String password) {
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // your API call
                return email + "/" + password;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Snackbar.make(buttonLogin, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Object o) {
                Snackbar.make(buttonLogin, "authentication completed: " + o, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void listAccess() {
        Observable.fromCallable(new Callable<List<Access>>() {
            @Override
            public List<Access> call() throws Exception {
                List<Access> list;
                try {
                    PianoClient pianoClient = ((PianoSampleApplication) getApplication()).getPianoClient();
                    list = pianoClient.getUserAccessApi().list(pianoClient.getAid());
                } catch (ApiException e) {
                    throw OnErrorThrowable.from(e);
                }

                return list;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Access>>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Snackbar.make(buttonListAccess, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(List<Access> accesses) {
                if ((accesses == null) || accesses.isEmpty()) {
                    Snackbar.make(buttonListAccess, "no access", Snackbar.LENGTH_SHORT).show();
                } else {
                    StringBuilder accessBuilder = new StringBuilder("Here's what you can access:\n");
                    for (Access access : accesses) {
                        accessBuilder.append(access.getResource().getName()).append(", ");
                    }
                    accessBuilder.delete(accessBuilder.length() - 2, accessBuilder.length());

                    Snackbar.make(buttonListAccess, accessBuilder.toString(), Snackbar.LENGTH_INDEFINITE).setAction("Great!", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {}
                    }).show();
                }
            }
        });
    }
}
