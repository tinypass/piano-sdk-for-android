package io.piano.android.id.google;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;

import io.piano.android.id.PianoId;
import io.piano.android.id.PianoOAuthActivity;

public class GoogleSignInActivity extends PianoOAuthActivity {

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .requestIdToken(getIntent().getStringExtra(PianoId.KEY_CLIENT_ID))
                    .build();

            GoogleSignInClient signInClient = GoogleSignIn.getClient(this, options);
            signInClient.signOut();

            Intent intent = signInClient.getSignInIntent();
            startActivityForResult(intent, RC_SIGN_IN);
        } catch (Exception e) {
            setFailureResult(e);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn
                        .getSignedInAccountFromIntent(data)
                        .getResult(ApiException.class);

                setSuccessResult(GoogleOAuthProvider.NAME, account.getIdToken());
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case GoogleSignInStatusCodes.CANCELED:
                    case GoogleSignInStatusCodes.SIGN_IN_CANCELLED:
                        setResult(Activity.RESULT_CANCELED);
                        break;
                    default:
                        setFailureResult(e);
                }
            } catch (Exception e) {
                setFailureResult(e);
            }
        } else super.onActivityResult(requestCode, resultCode, data);

        finish();
    }
}
