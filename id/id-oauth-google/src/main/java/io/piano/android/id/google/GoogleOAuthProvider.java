package io.piano.android.id.google;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import io.piano.android.id.PianoIdOAuthProvider;

public class GoogleOAuthProvider implements PianoIdOAuthProvider {

    static final String NAME = "google";

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @NonNull
    @Override
    public Intent buildIntent(@NonNull Context context, @NonNull Bundle extras) {
        return new Intent(context, GoogleSignInActivity.class);
    }
}
