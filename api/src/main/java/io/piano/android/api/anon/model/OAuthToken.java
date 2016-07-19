package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthToken {
  
  private String accessToken = null;
  private Integer expiresIn = null;
  private String tokenType = null;
  
  /**
   * access_token
   **/
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  
  /**
   * expires_in
   **/
  public Integer getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }
  
  /**
   * token_type
   **/
  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
  
  public static OAuthToken fromJson(JSONObject json) throws JSONException {
    OAuthToken oAuthToken = new OAuthToken();

    oAuthToken.accessToken = json.optString("access_token");
    oAuthToken.expiresIn = json.optInt("expires_in");
    oAuthToken.tokenType = json.optString("token_type");
    
    return oAuthToken;
  }
}
