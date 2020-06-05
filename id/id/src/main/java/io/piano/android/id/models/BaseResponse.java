package io.piano.android.id.models;

import android.text.TextUtils;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Map;

@Keep
public class BaseResponse {
    static final String KEY_MESSAGE = "message";
    static final String ERROR_TEMPLATE = "Error ";

    int code;
    String message;

    @SerializedName("validation_errors")
    Map<String, String> validationErrors;

    public boolean hasError() {
        return code != 0;
    }

    public String getError() {
        if (validationErrors != null && validationErrors.size() > 0) {
            String errorMessage = validationErrors.get(KEY_MESSAGE);
            return errorMessage != null ? errorMessage : joinValidationErrors(validationErrors.values());
        }

        return message != null && !message.isEmpty() ? message : ERROR_TEMPLATE + code;
    }

    // mock in tests
    String joinValidationErrors(Collection<String> errors) {
        return TextUtils.join("; ", errors);
    }
}
