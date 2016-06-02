package io.piano.android.composer.model;

import org.json.JSONObject;

public class Event {

    public static Event fromJson(JSONObject json) {
        Event event = null;

        String eventType = json.optString("eventType");
        if ("showLogin".equals(eventType)) {
            event = ShowLogin.fromJson(json);
        } else if ("showTemplate".equals(eventType)) {
            event = ShowTemplate.fromJson(json);
        }

        return event;
    }
}
