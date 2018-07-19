package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class RedemptionPage {
  
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
  
  public static RedemptionPage fromJson(JSONObject json) throws JSONException {
    RedemptionPage redemptionPage = new RedemptionPage();

    redemptionPage.enabled = json.optBoolean("enabled");
    
    return redemptionPage;
  }
}
