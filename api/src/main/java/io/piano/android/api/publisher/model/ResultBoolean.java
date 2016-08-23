package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultBoolean {
  
  private List<ErrorCode> errors = null;
  private ErrorCode error = null;
  private Boolean ok = null;
  private ErrorCodes errorCodes = null;
  
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
  
  /**
   **/
  public Boolean getOk() {
    return ok;
  }

  public void setOk(Boolean ok) {
    this.ok = ok;
  }
  
  /**
   **/
  public ErrorCodes getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(ErrorCodes errorCodes) {
    this.errorCodes = errorCodes;
  }
  
  public static ResultBoolean fromJson(JSONObject json) throws JSONException {
    ResultBoolean resultBoolean = new ResultBoolean();

    resultBoolean.errors = new ArrayList<>();
    JSONArray errorsJsonArray = json.optJSONArray("errors");
    int errorsLength = errorsJsonArray.length();
    for (int ii = 0; ii < errorsLength; ii++) {
      resultBoolean.errors.add(ErrorCode.fromJson(errorsJsonArray.optJSONObject(ii)));
    }
    resultBoolean.error = ErrorCode.fromJson(json.optJSONObject("error"));
    resultBoolean.ok = json.optBoolean("ok");
    resultBoolean.errorCodes = ErrorCodes.fromJson(json.optJSONObject("errorCodes"));
    
    return resultBoolean;
  }
}
