package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class EventExecutionContext {
    @NonNull
    @SerializedName("experienceId")
    public String experienceId = "";

    @NonNull
    @SerializedName("executionId")
    public String executionId = "";

    @NonNull
    @SerializedName("trackingId")
    public String trackingId = "";

    @Nullable
    @SerializedName("splitTests")
    public List<SplitTest> splitTests;

    @NonNull
    @SerializedName("currentMeterName")
    public String currentMeterName = "";

    @Nullable
    @SerializedName("user")
    public User user;

    @NonNull
    @SerializedName("region")
    public String region = "";

    @NonNull
    @SerializedName("country")
    public String country = "";

    @Nullable
    @SerializedName("accessList")
    public List<Access> accessList;

    @Nullable
    @SerializedName("activeMeters")
    public List<ActiveMeter> activeMeters;
}
