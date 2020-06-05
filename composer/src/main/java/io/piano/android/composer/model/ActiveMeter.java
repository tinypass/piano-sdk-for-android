package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Keep
public class ActiveMeter {
    @NonNull
    @SerializedName("meterName")
    public String meterName = "";

    @SerializedName("views")
    public int views;

    @SerializedName("viewsLeft")
    public int viewsLeft;

    @SerializedName("maxViews")
    public int maxViews;

    @SerializedName("totalViews")
    public int totalViews;
}
