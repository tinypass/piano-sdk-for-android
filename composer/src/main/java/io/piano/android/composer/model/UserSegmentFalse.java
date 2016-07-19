package io.piano.android.composer.model;

import org.json.JSONObject;

public class UserSegmentFalse extends UserSegment {

    public static UserSegmentFalse fromJson(JSONObject json) {
        UserSegmentFalse userSegmentFalse = new UserSegmentFalse();
        return userSegmentFalse;
    }
}
