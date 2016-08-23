package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorCode {
  
  private Integer code = null;
  private String message = null;
  
  /**
   **/
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
  
  /**
   **/
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public static ErrorCode fromJson(JSONObject json) throws JSONException {
    ErrorCode errorCode = new ErrorCode();

    errorCode.code = json.optInt("code");
    errorCode.message = json.optString("message");
    
    return errorCode;
  }
}
