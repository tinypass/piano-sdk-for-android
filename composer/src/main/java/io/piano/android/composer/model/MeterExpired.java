package io.piano.android.composer.model;

import org.json.JSONObject;

public class MeterExpired extends Meter {

    public static MeterExpired fromJson(JSONObject json) {
        MeterExpired meterExpired = new MeterExpired();

        parseCommon(meterExpired, json);

        return meterExpired;
    }
}
