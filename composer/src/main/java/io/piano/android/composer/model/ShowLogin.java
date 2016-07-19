package io.piano.android.composer.model;

import org.json.JSONObject;

public class ShowLogin extends Event {

    public String userProvider;

    public static ShowLogin fromJson(JSONObject json) {
        ShowLogin showLogin = new ShowLogin();

        showLogin.userProvider = json.optJSONObject("eventParams").optString("userProvider");

        return showLogin;
    }
}
