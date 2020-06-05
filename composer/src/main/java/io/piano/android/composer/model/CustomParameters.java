package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Keep
public class CustomParameters {
    @Nullable
    @SerializedName("content")
    Map<String, List<String>> content;
    @Nullable
    @SerializedName("user")
    Map<String, List<String>> user;
    @Nullable
    @SerializedName("request")
    Map<String, List<String>> request;

    public CustomParameters content(@NonNull String key, @Nullable String value) {
        if (content == null)
            content = new HashMap<>();
        putValueForKey(content, key, value);
        return this;
    }

    public CustomParameters user(@NonNull String key, @Nullable String value) {
        if (user == null)
            user = new HashMap<>();
        putValueForKey(user, key, value);
        return this;
    }

    public CustomParameters request(@NonNull String key, @Nullable String value) {
        if (request == null)
            request = new HashMap<>();
        putValueForKey(request, key, value);
        return this;
    }

    public boolean isEmpty() {
        return content == null && user == null && request == null;
    }

    Map<String, List<String>> putValueForKey(@NonNull Map<String, List<String>> scope, @NonNull String key, @Nullable String value) {
        List<String> values = scope.get(key);
        if (values == null) {
            values = new ArrayList<>();
            scope.put(key, values);
        }
        values.add(value);
        return scope;
    }
}
