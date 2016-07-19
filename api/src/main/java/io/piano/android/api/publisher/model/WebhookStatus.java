package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookStatus {
  
  private String status = null;
  private String description = null;
  
  /**
   * Webhook status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Webhook status description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public static WebhookStatus fromJson(JSONObject json) throws JSONException {
    WebhookStatus webhookStatus = new WebhookStatus();

    webhookStatus.status = json.optString("status");
    webhookStatus.description = json.optString("description");
    
    return webhookStatus;
  }
}
