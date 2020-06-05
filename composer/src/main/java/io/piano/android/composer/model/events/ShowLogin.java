package io.piano.android.composer.model.events;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Keep
public class ShowLogin extends EventType {
    @NonNull
    @SerializedName("userProvider")
    public String userProvider = "";
}
