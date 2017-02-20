package io.piano.android.composer.model;

import org.json.JSONObject;

public class SplitTest {

    public String variantId;
    public String variantName;

    static SplitTest fromJson(JSONObject json) {
        SplitTest splitTest = new SplitTest();

        splitTest.variantId = json.optString("variantId");
        splitTest.variantName = json.optString("variantName");

        return splitTest;
    }
}
