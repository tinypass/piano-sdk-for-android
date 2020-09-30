package io.piano.sample;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import io.piano.android.id.models.PianoIdToken;
import timber.log.Timber;

public class PrefsStorage {
    public static final String KEY_TOKEN = "piano_token";

    private final SharedPreferences sharedPreferences;
    private final JsonAdapter<PianoIdToken> pianoIdTokenJsonAdapter;

    public PrefsStorage(Context context, Moshi moshi) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        pianoIdTokenJsonAdapter = moshi.adapter(PianoIdToken.class);
    }

    @Nullable
    public PianoIdToken getPianoIdToken() {
        try {
            return pianoIdTokenJsonAdapter.fromJson(sharedPreferences.getString(KEY_TOKEN, null));
        } catch (IOException e) {
            Timber.e(e);
        }
        return null;
    }

    public void setPianoIdToken(@Nullable PianoIdToken token) {
        sharedPreferences.edit().putString(KEY_TOKEN, pianoIdTokenJsonAdapter.toJson(token)).apply();
    }
}
