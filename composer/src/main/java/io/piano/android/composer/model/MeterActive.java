package io.piano.android.composer.model;

import org.json.JSONObject;

public class MeterActive extends Meter {

    public static MeterActive fromJson(JSONObject json) {
        MeterActive meterActive = new MeterActive();

        parseCommon(meterActive, json);

        return meterActive;
    }
}
