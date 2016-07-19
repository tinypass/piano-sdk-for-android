package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAccount {
  
  private Boolean enabled = null;
  
  /**
   * Is property enabled
   **/
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
  
  public static MyAccount fromJson(JSONObject json) throws JSONException {
    MyAccount myAccount = new MyAccount();

    myAccount.enabled = json.optBoolean("enabled");
    
    return myAccount;
  }
}
