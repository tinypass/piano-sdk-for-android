package io.piano.android.composer.model;

import org.json.JSONObject;

public class UserSegmentTrue extends UserSegment {

    public static UserSegmentTrue fromJson(JSONObject json) {
        UserSegmentTrue userSegmentTrue = new UserSegmentTrue();
        return userSegmentTrue;
    }
}
