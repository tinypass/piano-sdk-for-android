package io.piano.android.id.facebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import io.piano.android.id.PianoIdOAuthProvider;

public class FacebookOAuthProvider implements PianoIdOAuthProvider {

    static final String NAME = "facebook";

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @NonNull
    @Override
    public Intent buildIntent(@NonNull Context context, @NonNull Bundle extras) {
        return new Intent(context, FacebookSignInActivity.class);
    }
}
