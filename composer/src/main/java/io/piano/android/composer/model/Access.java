package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Keep
public class Access {
    @NonNull
    @SerializedName("rid")
    public String resourceId = "";

    @NonNull
    @SerializedName("resourceName")
    public String resourceName = "";

    @SerializedName("expireDate")
    public int expireDate;

    @SerializedName("daysUntilExpiration")
    public int daysUntilExpiration;
}
