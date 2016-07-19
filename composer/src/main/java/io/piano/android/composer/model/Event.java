package io.piano.android.composer.model;

import org.json.JSONObject;

public class Event {

    static Event fromJson(JSONObject json) {
        Event event = null;

        String eventType = json.optString("eventType");
        if ("showLogin".equals(eventType)) {
            event = ShowLogin.fromJson(json);
        } else if ("meterActive".equals(eventType)) {
            event = MeterActive.fromJson(json);
        } else if ("meterExpired".equals(eventType)) {
            event = MeterExpired.fromJson(json);
        } else if ("userSegmentTrue".equals(eventType)) {
            event = UserSegmentTrue.fromJson(json);
        } else if ("userSegmentFalse".equals(eventType)) {
            event = UserSegmentFalse.fromJson(json);
        } else if ("experienceExecute".equals(eventType)) {
            event = ExperienceExecute.fromJson(json);
        } else if ("nonSite".equals(eventType)) {
            event = NonSite.fromJson(json);
        }

        return event;
    }
}
