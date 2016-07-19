package io.piano.android.composer.model;

import org.json.JSONObject;

public class User {

    public String uid;
    public String firstName;
    public String lastName;
    public String email;

    static User fromJson(JSONObject json) {
        User user = new User();

        user.uid = json.optString("uid");
        user.firstName = json.optString("firstName");
        user.lastName = json.optString("lastName");
        user.email = json.optString("email");

        return user;
    }
}
