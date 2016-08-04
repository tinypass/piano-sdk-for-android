package io.piano.android.composer.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExperienceResponse {

    public String tbc;
    public String xbc;
    public String tac;

    public List<Event> events;

    public static ExperienceResponse fromJson(JSONObject json) {
        JSONArray errors = json.optJSONArray("errors");
        if ((errors != null) && (errors.length() > 0)) {
            return null;
        }

        ExperienceResponse experienceResponse = new ExperienceResponse();

        JSONObject models = json.optJSONObject("models");
        JSONObject result = models.optJSONObject("result");

        experienceResponse.events = new ArrayList<>();
        JSONArray eventsJsonArray = result.optJSONArray("events");
        for (int ii = 0; ii < eventsJsonArray.length(); ii++) {
            experienceResponse.events.add(Event.fromJson(eventsJsonArray.optJSONObject(ii)));
        }

        experienceResponse.tbc = models.optJSONObject("tbc").optString("cookie_value");

        experienceResponse.xbc = models.optJSONObject("xbc").optString("cookie_value");

        experienceResponse.tac = models.optJSONObject("tac").optString("cookie_value");

        return experienceResponse;
    }
}
