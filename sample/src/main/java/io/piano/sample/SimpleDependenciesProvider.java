package io.piano.sample;

import android.content.Context;

import androidx.annotation.NonNull;

import com.squareup.moshi.Moshi;

import io.piano.android.id.PianoIdTokenJsonAdapter;

public class SimpleDependenciesProvider {
    private static SimpleDependenciesProvider instance;

    private final Context appContext;
    private final Moshi moshi;
    private final PrefsStorage prefsStorage;

    private SimpleDependenciesProvider(Context context) {
        appContext = context.getApplicationContext();
        moshi = new Moshi.Builder().add(PianoIdTokenJsonAdapter.FACTORY).build();
        prefsStorage = new PrefsStorage(appContext, moshi);
    }

    public Context getAppContext() {
        return appContext;
    }

    public Moshi getMoshi() {
        return moshi;
    }

    public PrefsStorage getPrefsStorage() {
        return prefsStorage;
    }

    @NonNull
    public static SimpleDependenciesProvider getInstance(Context context) {
        if (instance == null) {
            synchronized (SimpleDependenciesProvider.class) {
                if (instance == null)
                    instance = new SimpleDependenciesProvider(context);
            }
        }
        return instance;
    }
}
