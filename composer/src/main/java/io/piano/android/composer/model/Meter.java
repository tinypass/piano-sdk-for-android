package io.piano.android.composer.model;

import org.json.JSONObject;

public abstract class Meter extends Event {

    public int views;
    public int viewsLeft;
    public int maxViews;
    public int totalViews;

    static void parseCommon(Meter meter, JSONObject json) {
        JSONObject eventParams = json.optJSONObject("eventParams");

        meter.views = eventParams.optInt("views");
        meter.viewsLeft = eventParams.optInt("viewsLeft");
        meter.maxViews = eventParams.optInt("maxViews");
        meter.totalViews = eventParams.optInt("totalViews");
    }
}
