package io.piano.android.composer.model.events;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.piano.android.composer.model.DelayBy;

@Keep
public class ShowTemplate extends EventType {
    @Nullable
    public String url;

    @NonNull
    @SerializedName("templateId")
    public String templateId;

    @Nullable
    @SerializedName("templateVariantId")
    public String templateVariantId;

    @SerializedName("displayMode")
    public DisplayMode displayMode;

    @SerializedName("containerSelector")
    public String containerSelector;

    @SerializedName("delayBy")
    public DelayBy delayBy;

    @SerializedName("showCloseButton")
    public boolean showCloseButton;

    public enum DisplayMode {
        @SerializedName("modal")
        MODAL("modal"),
        @SerializedName("inline")
        INLINE("inline");

        private final String mode;

        DisplayMode(@NonNull String mode) {
            this.mode = mode;
        }

        @NonNull
        public String getMode() {
            return mode;
        }

        @NonNull
        @Override
        public String toString() {
            return mode;
        }
    }
}
