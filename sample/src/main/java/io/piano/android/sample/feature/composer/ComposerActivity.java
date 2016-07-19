package io.piano.android.sample.feature.composer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import io.piano.android.composer.Composer;
import io.piano.android.composer.MeterActiveListener;
import io.piano.android.composer.MeterExpiredListener;
import io.piano.android.composer.ShowLoginListener;
import io.piano.android.composer.model.MeterActive;
import io.piano.android.composer.model.MeterExpired;
import io.piano.android.composer.model.ShowLogin;
import io.piano.android.oauth.ui.activity.OAuthActivity;
import io.piano.android.sample.BuildConfig;
import io.piano.android.sample.R;

public class ComposerActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_OAUTH = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String userToken = getSharedPreferences("oauth", MODE_PRIVATE).getString("accessToken", null);

        new Composer(this, BuildConfig.PIANO_AID, true)
                .user(userToken, Composer.USER_PROVIDER_TINYPASS_ACCOUNTS)
                .tag("tag")
                .debug(true)
                .addListener(new ShowLoginListener() {
                    @Override
                    public void onExecuted(ShowLogin showLogin) {
                        if (Composer.USER_PROVIDER_TINYPASS_ACCOUNTS.equals(showLogin.userProvider)) {
                            Intent intent = new Intent(ComposerActivity.this, OAuthActivity.class);
                            intent.putExtra(OAuthActivity.EXTRA_AID, BuildConfig.PIANO_AID);
                            intent.putExtra(OAuthActivity.EXTRA_SANDBOX, BuildConfig.DEBUG);
                            startActivityForResult(intent, REQUEST_CODE_OAUTH);
                        }
                    }
                })
                .addListener(new MeterActiveListener() {
                    @Override
                    public void onExecuted(MeterActive meterActive) {
                        Toast.makeText(
                                ComposerActivity.this, String.format("Meter ACTIVE!\nviews = %s\nviewsLeft = %s\nmaxViews = %s\ntotalViews = %s", meterActive.views, meterActive.viewsLeft, meterActive.maxViews, meterActive.totalViews), Toast.LENGTH_LONG
                        ).show();
                    }
                })
                .addListener(new MeterExpiredListener() {
                    @Override
                    public void onExecuted(MeterExpired meterExpired) {
                        Toast.makeText(
                                ComposerActivity.this, String.format("Meter EXPIRED!\nviews = %s\nviewsLeft = %s\nmaxViews = %s\ntotalViews = %s", meterExpired.views, meterExpired.viewsLeft, meterExpired.maxViews, meterExpired.totalViews), Toast.LENGTH_LONG
                        ).show();
                    }
                })
                .execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_OAUTH == requestCode) {
            if (RESULT_OK == resultCode) {
                String accessToken = data.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
                SharedPreferences sharedPreferences = getSharedPreferences("oauth", MODE_PRIVATE);
                sharedPreferences.edit().putString("accessToken", accessToken).apply();

                Snackbar.make(findViewById(R.id.app_bar), "accessToken = " + accessToken, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(findViewById(R.id.app_bar), "User cancelled logging in!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
