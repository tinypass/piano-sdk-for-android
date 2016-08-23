package io.piano.android.api.common;

import java.util.Map;

public class RequestBody {

    private Map<String, String> formParams;

    public RequestBody(Map<String, String> formParams) {
        this.formParams = formParams;
    }

    public Map<String, String> getFormParams() {
        return formParams;
    }
}
