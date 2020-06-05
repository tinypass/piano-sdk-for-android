package io.piano.android.id.models;

import androidx.annotation.Keep;

@Keep
public class SocialTokenData {
    public String oauthProvider;
    public String socialToken;
    public String clientId;

    public SocialTokenData(String oauthProvider, String socialToken, String clientId) {
        this.oauthProvider = oauthProvider;
        this.socialToken = socialToken;
        this.clientId = clientId;
    }
}
