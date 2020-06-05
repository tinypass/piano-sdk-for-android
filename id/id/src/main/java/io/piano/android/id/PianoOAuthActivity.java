package io.piano.android.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

public abstract class PianoOAuthActivity extends AppCompatActivity {
    @VisibleForTesting
    PianoIdClient client;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = PianoId.getClient();
    }

    /**
     * Sets success data for Piano ID OAuth
     *
     * @param providerName Provider name
     * @param token        Provider token
     */
    protected void setSuccessResult(@NonNull String providerName, @Nullable String token) {
        Intent intent = new Intent()
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_NAME, providerName)
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN, token);
        setResult(Activity.RESULT_OK, intent);
    }

    /**
     * Sets failure data for Piano ID OAuth
     *
     * @param throwable Throwable, that will be processed
     */
    protected void setFailureResult(@NonNull Throwable throwable) {
        PianoIdException exception = Utils.wrapException(throwable);
        Intent intent = new Intent().putExtra(PianoId.KEY_ERROR, client.saveException(exception));
        setResult(PianoId.RESULT_ERROR, intent);
    }
}
