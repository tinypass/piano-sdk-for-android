package io.piano.android.composer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomParams {

    private static final String SCOPE_CONTENT = "content";
    private static final String SCOPE_USER = "user";
    private static final String SCOPE_REQUEST = "request";

    private Map<String, JSONArray> content;
    private Map<String, JSONArray> user;
    private Map<String, JSONArray> request;

    public CustomParams() {
        this.content = new HashMap<>();
        this.user = new HashMap<>();
        this.request = new HashMap<>();
    }

    public CustomParams content(String key, String value) {
        putKeyValue(content, key, value);
        return this;
    }

    public CustomParams user(String key, String value) {
        putKeyValue(user, key, value);
        return this;
    }

    public CustomParams request(String key, String value) {
        putKeyValue(request, key, value);
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

    private void putKeyValue(Map<String, JSONArray> scope, String key, String value) {
        JSONArray jsonArray = scope.get(key);
        if (jsonArray == null) {
            jsonArray = new JSONArray();
            scope.put(key, jsonArray);
        }
        jsonArray.put(value);
    }
}
