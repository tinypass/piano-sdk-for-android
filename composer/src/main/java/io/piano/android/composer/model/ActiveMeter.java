package io.piano.android.composer.model;

import org.json.JSONObject;

public class ActiveMeter {

    public String meterName;
    public int views;
    public int viewsLeft;
    public int maxViews;
    public int totalViews;

    public static ActiveMeter fromJson(JSONObject json) {
        ActiveMeter activeMeter = new ActiveMeter();

        activeMeter.meterName = json.optString("meterName");
        activeMeter.views = json.optInt("views");
        activeMeter.viewsLeft = json.optInt("viewsLeft");
        activeMeter.maxViews = json.optInt("maxViews");
        activeMeter.totalViews = json.optInt("totalViews");

        return activeMeter;
    }
}
