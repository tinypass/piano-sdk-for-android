package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Keep
public class User {
    @NonNull
    @SerializedName("uid")
    public String userId = "";

    @Nullable
    @SerializedName("firstName")
    public String firstName;

    @Nullable
    @SerializedName("lastName")
    public String lastName;

    @NonNull
    @SerializedName("email")
    public String email = "";
}
