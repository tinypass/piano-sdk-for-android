package io.piano.android.composer.model;

import org.json.JSONObject;

public class DelayBy {

    public static final String TYPE_TIME = "time";
    public static final String TYPE_SCROLL = "scroll";

    public String type;
    public int value;

    public static DelayBy fromJson(JSONObject json) {
        DelayBy delayBy = new DelayBy();

        delayBy.type = json.optString("type");
        delayBy.value = json.optInt("value");

        return delayBy;
    }
}
