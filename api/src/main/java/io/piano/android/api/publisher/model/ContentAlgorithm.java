package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentAlgorithm {
  
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
  
  public static ContentAlgorithm fromJson(JSONObject json) throws JSONException {
    ContentAlgorithm contentAlgorithm = new ContentAlgorithm();

    contentAlgorithm.enabled = json.optBoolean("enabled");
    
    return contentAlgorithm;
  }
}
