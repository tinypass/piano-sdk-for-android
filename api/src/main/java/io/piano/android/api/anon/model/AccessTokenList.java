package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessTokenList {
  
  private String value = null;
  private String cookieDomain = null;
  
  /**
   * The encoded access token list value
   **/
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  /**
   * The domain to set the cookie on
   **/
  public String getCookieDomain() {
    return cookieDomain;
  }

  public void setCookieDomain(String cookieDomain) {
    this.cookieDomain = cookieDomain;
  }
  
  public static AccessTokenList fromJson(JSONObject json) throws JSONException {
    AccessTokenList accessTokenList = new AccessTokenList();

    accessTokenList.value = json.optString("value");
    accessTokenList.cookieDomain = json.optString("cookie_domain");
    
    return accessTokenList;
  }
}
