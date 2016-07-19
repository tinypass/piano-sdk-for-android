package io.piano.android.composer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomParams {

    private static final String SCOPE_CONTENT = "content";
    private static final String SCOPE_USER = "user";
    private static final String SCOPE_REQUEST = "request";

    private Map<String, Object> content;
    private Map<String, Object> user;
    private Map<String, Object> request;

    public CustomParams() {
        this.content = new HashMap<>();
        this.user = new HashMap<>();
        this.request = new HashMap<>();
    }

    public CustomParams content(String key, Object value) {
        content.put(key, value);
        return this;
    }

    public CustomParams content(Map<String, Object> contentParams) {
        content.putAll(contentParams);
        return this;
    }

    public CustomParams user(String key, Object value) {
        user.put(key, value);
        return this;
    }

    public CustomParams user(Map<String, Object> userParams) {
        user.putAll(userParams);
        return this;
    }

    public CustomParams request(String key, Object value) {
        request.put(key, value);
        return this;
    }

    public CustomParams request(Map<String, Object> requestParams) {
        request.putAll(requestParams);
        return this;
    }

    public boolean isEmpty() {
        return content.isEmpty() && user.isEmpty() && request.isEmpty();
    }

    public String toJson() throws JSONException {
        JSONObject customParams = new JSONObject();

        if (!content.isEmpty()) {
            customParams.put(SCOPE_CONTENT, new JSONObject(content));
        }

        if (!user.isEmpty()) {
            customParams.put(SCOPE_USER, new JSONObject(user));
        }

        if (!request.isEmpty()) {
            customParams.put(SCOPE_REQUEST, new JSONObject(request));
        }

        return customParams.toString();
    }
}
