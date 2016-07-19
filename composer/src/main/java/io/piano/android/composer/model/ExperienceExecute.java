package io.piano.android.composer.model;

import org.json.JSONObject;

public class ExperienceExecute extends Event {

    public User user;

    public static ExperienceExecute fromJson(JSONObject json) {
        ExperienceExecute experienceExecute = new ExperienceExecute();

        JSONObject eventParams = json.optJSONObject("eventParams");
        experienceExecute.user = User.fromJson(eventParams.optJSONObject("user"));

        return experienceExecute;
    }
}
