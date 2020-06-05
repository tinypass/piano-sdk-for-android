package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Keep
public class DelayBy {
    @Nullable
    @SerializedName("type")
    public DelayType type;

    @SerializedName("value")
    public int value;

    public static enum DelayType {
        @SerializedName("time")
        TIME,
        @SerializedName("scroll")
        SCROLL
    }

    public boolean isDelayedByTime() {
        return type == DelayType.TIME && value > 0;
    }

    public boolean isDelayedByScroll() {
        return type == DelayType.SCROLL && value > 0;
    }
}
