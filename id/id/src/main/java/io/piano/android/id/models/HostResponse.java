package io.piano.android.id.models;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class HostResponse extends BaseResponse {
    @SerializedName("data")
    public String host;
}
