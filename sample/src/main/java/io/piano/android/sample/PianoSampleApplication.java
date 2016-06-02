package io.piano.android.sample;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import io.piano.android.api.PianoClient;

public class PianoSampleApplication extends Application {

    private PianoClient pianoClient;

    @Override
    public void onCreate() {
        super.onCreate();

        pianoClient = new PianoClient(this, BuildConfig.PIANO_AID, BuildConfig.DEBUG);

        SharedPreferences sharedPreferences = getSharedPreferences("oauth", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        if (!TextUtils.isEmpty(accessToken)) {
            pianoClient.setAccessToken(accessToken);
        }
    }

    public PianoClient getPianoClient() {
        return pianoClient;
    }
}
