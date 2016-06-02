package io.piano.android.composer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExperienceResponse {

    public String tbc;
    public List<Event> events;
    public String xbc;

    public static JSONObject fromJson(String jsonp) throws JSONException {
        int firstBracket = jsonp.indexOf('(');
        int lastBracket = jsonp.lastIndexOf(')');
        String json = jsonp.substring(firstBracket + 1, lastBracket);
        return new JSONObject(json);
    }

    public static ExperienceResponse fromJson(JSONObject json) {
        ExperienceResponse experienceResponse = new ExperienceResponse();

        JSONObject models = json.optJSONObject("models");
        experienceResponse.tbc = models.optJSONObject("browser").optString("tbc");

        experienceResponse.events = new ArrayList<>();
        JSONObject result = models.optJSONObject("result");
        JSONArray eventsJsonArray = result.optJSONArray("events");
        int eventsLength = eventsJsonArray.length();
        for (int ii = 0; ii < eventsLength; ii++) {
            experienceResponse.events.add(Event.fromJson(eventsJsonArray.optJSONObject(ii)));
        }

        experienceResponse.xbc = models.optString("cookie_value");

        return experienceResponse;
    }
}
