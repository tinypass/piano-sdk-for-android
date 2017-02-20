package io.piano.android.composer.model;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.piano.android.composer.exception.ComposerException;

public class ExperienceResponse {

    public String tbc;
    public String xbc;
    public String tac;

    public List<Event> events;

    public static ExperienceResponse fromJson(JSONObject json) throws ComposerException {
        checkErrors(json.optJSONArray("errors"));

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

    private static void checkErrors(JSONArray errors) throws ComposerException {
        int length = errors.length();
        if (length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean firstTime = true;
            for (int ii = 0; ii < length; ii++) {
                JSONObject jsonError = errors.optJSONObject(ii);
                String msg = jsonError.optString("msg");
                if (!TextUtils.isEmpty(msg)) {
                    if (firstTime) {
                        firstTime = false;
                    } else {
                        stringBuilder.append('\n');
                    }
                    stringBuilder.append(msg);
                }
            }

            throw new ComposerException(stringBuilder.toString());
        }
    }
}
