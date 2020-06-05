package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class Data<T> {
    @NonNull
    @SerializedName("models")
    public T data;

    @NonNull
    @SerializedName("errors")
    public List<ErrorMessage> errors;
}
