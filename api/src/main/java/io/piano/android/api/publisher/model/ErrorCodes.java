package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ErrorCodes {
  
  private List<ErrorCode> errors = null;
  private ErrorCode error = null;
  
  /**
   **/
  public List<ErrorCode> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorCode> errors) {
    this.errors = errors;
  }
  
  /**
   **/
  public ErrorCode getError() {
    return error;
  }

  public void setError(ErrorCode error) {
    this.error = error;
  }
  
  public static ErrorCodes fromJson(JSONObject json) throws JSONException {
    ErrorCodes errorCodes = new ErrorCodes();

    errorCodes.errors = new ArrayList<>();
    JSONArray errorsJsonArray = json.optJSONArray("errors");
    int errorsLength = errorsJsonArray.length();
    for (int ii = 0; ii < errorsLength; ii++) {
      errorCodes.errors.add(ErrorCode.fromJson(errorsJsonArray.optJSONObject(ii)));
    }
    errorCodes.error = ErrorCode.fromJson(json.optJSONObject("error"));
    
    return errorCodes;
  }
}
