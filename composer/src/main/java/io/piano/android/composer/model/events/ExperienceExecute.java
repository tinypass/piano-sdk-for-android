package io.piano.android.composer.model.events;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.piano.android.composer.model.User;

@Keep
public class ExperienceExecute extends EventType {
    @Nullable
    @SerializedName("user")
    public User user;
}
