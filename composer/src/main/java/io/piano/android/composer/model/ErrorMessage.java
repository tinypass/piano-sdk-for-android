package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Keep
public class ErrorMessage {
    @Nullable
    @SerializedName("msg")
    public String message;
}
