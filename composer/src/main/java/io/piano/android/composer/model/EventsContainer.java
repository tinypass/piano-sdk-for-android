package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.piano.android.composer.model.events.EventType;

@Keep
public class EventsContainer {
    @Nullable
    @SerializedName("events")
    public List<Event<? extends EventType>> events;
}
