package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Keep
public class ExperienceResponse {
    @Nullable
    @SerializedName("tbc")
    public CookieObject tbCookie;

    @Nullable
    @SerializedName("xbc")
    public CookieObject xbCookie;

    @Nullable
    @SerializedName("tac")
    public CookieObject taCookie;

    @SerializedName("timezone_offset")
    public int timeZoneOffsetMillis;

    @Nullable
    @SerializedName("visit_timeout")
    public Integer visitTimeoutMinutes;

    @NonNull
    @SerializedName("result")
    public EventsContainer result;
}
