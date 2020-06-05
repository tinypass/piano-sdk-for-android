package io.piano.android.composer.model.events;

import androidx.annotation.Keep;

@Keep
public class UserSegment extends EventType {
    public final boolean state;

    public UserSegment(boolean state) {
        this.state = state;
    }
}
