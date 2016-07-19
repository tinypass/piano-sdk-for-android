package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Composer {
  
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
  
  public static Composer fromJson(JSONObject json) throws JSONException {
    Composer composer = new Composer();

    composer.enabled = json.optBoolean("enabled");
    
    return composer;
  }
}
