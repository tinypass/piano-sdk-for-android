package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Keep
public class EventModuleParams {
    @NonNull
    @SerializedName("moduleId")
    public String moduleId = "";

    @NonNull
    @SerializedName("moduleName")
    public String moduleName = "";
}
