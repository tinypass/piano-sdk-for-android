package io.piano.android.id.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Collections;

import io.piano.android.id.PianoOAuthActivity;

public class FacebookSignInActivity extends PianoOAuthActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            LoginManager.getInstance().logOut();
            LoginManager.getInstance().logIn(this, Collections.singletonList("email"));
        } catch (Exception ex) {
            setFailureResult(ex);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case Activity.RESULT_OK:
                try {
                    final CallbackManager callbackManager = CallbackManager.Factory.create();
                    LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            if (!loginResult.getRecentlyGrantedPermissions().contains("email")) {
                                onCancel();
                                return;
                            }
                            LoginManager.getInstance().unregisterCallback(callbackManager);

                            setSuccessResult(FacebookOAuthProvider.NAME, loginResult.getAccessToken().getToken());
                        }

                        @Override
                        public void onCancel() {
                            LoginManager.getInstance().unregisterCallback(callbackManager);
                            setResult(Activity.RESULT_CANCELED);
                        }

                        @Override
                        public void onError(FacebookException e) {
                            LoginManager.getInstance().unregisterCallback(callbackManager);
                            setFailureResult(e);
                        }
                    });

                    callbackManager.onActivityResult(requestCode, resultCode, data);
                } catch (Exception ex) {
                    setFailureResult(ex);
                }
                break;
            case Activity.RESULT_CANCELED:
                setResult(Activity.RESULT_CANCELED);
                break;
        }

        finish();
    }
}
