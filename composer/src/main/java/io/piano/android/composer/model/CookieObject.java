package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Keep
public class CookieObject {
    @Nullable
    @SerializedName("cookie_value")
    public String value;
}
