package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Keep
public class SplitTest {
    @NonNull
    @SerializedName("variantId")
    public String variantId = "";

    @NonNull
    @SerializedName("variantName")
    public String variantName = "";
}
