package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.App;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WorldpayConfiguration {
  
  private String configurationId = null;
  private App app = null;
  private String source = null;
  private Integer sourceId = null;
  private String title = null;
  private Boolean isDisabled = null;
  private Boolean isEditable = null;
  private String properties = null;
  
  /**
   * Payment provider configuration identifier
   **/
  public String getConfigurationId() {
    return configurationId;
  }

  public void setConfigurationId(String configurationId) {
    this.configurationId = configurationId;
  }
  
  /**
   * Application ref
   **/
  public App getApp() {
    return app;
  }

  public void setApp(App app) {
    this.app = app;
  }
  
  /**
   * Payment provider name
   **/
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
  
  /**
   * Payment provider source id
   **/
  public Integer getSourceId() {
    return sourceId;
  }

  public void setSourceId(Integer sourceId) {
    this.sourceId = sourceId;
  }
  
  /**
   * The title
   **/
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  /**
   * Is configuration disabled for further using
   **/
  public Boolean getIsDisabled() {
    return isDisabled;
  }

  public void setIsDisabled(Boolean isDisabled) {
    this.isDisabled = isDisabled;
  }
  
  /**
   * Is configuration properties can be still edit
   **/
  public Boolean getIsEditable() {
    return isEditable;
  }

  public void setIsEditable(Boolean isEditable) {
    this.isEditable = isEditable;
  }
  
  /**
   * Worldpay properties
   **/
  public String getProperties() {
    return properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }
  
  public static WorldpayConfiguration fromJson(JSONObject json) throws JSONException {
    WorldpayConfiguration worldpayConfiguration = new WorldpayConfiguration();

    worldpayConfiguration.configurationId = json.optString("configuration_id");
    worldpayConfiguration.app = App.fromJson(json.optJSONObject("app"));
    worldpayConfiguration.source = json.optString("source");
    worldpayConfiguration.sourceId = json.optInt("source_id");
    worldpayConfiguration.title = json.optString("title");
    worldpayConfiguration.isDisabled = json.optBoolean("is_disabled");
    worldpayConfiguration.isEditable = json.optBoolean("is_editable");
    worldpayConfiguration.properties = json.optString("properties");
    
    return worldpayConfiguration;
  }
}
