package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookSettings {
  
  private String url = null;
  private Boolean enabled = null;
  private Integer version = null;
  
  /**
   * Webhook endpoint url
   **/
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  /**
   * Webhook endpoint enabled
   **/
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
  
  /**
   * Webhook version
   **/
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
  
  public static WebhookSettings fromJson(JSONObject json) throws JSONException {
    WebhookSettings webhookSettings = new WebhookSettings();

    webhookSettings.url = json.optString("url");
    webhookSettings.enabled = json.optBoolean("enabled");
    webhookSettings.version = json.optInt("version");
    
    return webhookSettings;
  }
}
