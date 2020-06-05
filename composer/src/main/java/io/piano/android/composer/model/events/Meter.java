package io.piano.android.composer.model.events;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class Meter extends EventType {
    @SerializedName("views")
    public int views;

    @SerializedName("viewsLeft")
    public int viewsLeft;

    @SerializedName("maxViews")
    public int maxViews;

    @SerializedName("totalViews")
    public int totalViews;

    public MeterState state = MeterState.ACTIVE;

    public enum MeterState {
        ACTIVE,
        EXPIRED
    }
}
