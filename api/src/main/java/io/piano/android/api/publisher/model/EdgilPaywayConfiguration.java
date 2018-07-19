package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class EdgilPaywayConfiguration {
  
  private String configurationId = null;
  private App app = null;
  private String sourceName = null;
  private Integer sourceId = null;
  private String title = null;
  private Boolean isEditable = null;
  private Boolean isDisabled = null;
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
  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
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
   * Is configuration properties can be still edit
   **/
  public Boolean getIsEditable() {
    return isEditable;
  }

  public void setIsEditable(Boolean isEditable) {
    this.isEditable = isEditable;
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
   * Edgil Payway payment provider properties
   **/
  public String getProperties() {
    return properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }
  
  public static EdgilPaywayConfiguration fromJson(JSONObject json) throws JSONException {
    EdgilPaywayConfiguration edgilPaywayConfiguration = new EdgilPaywayConfiguration();

    edgilPaywayConfiguration.configurationId = json.optString("configuration_id");
    edgilPaywayConfiguration.app = App.fromJson(json.optJSONObject("app"));
    edgilPaywayConfiguration.sourceName = json.optString("source_name");
    edgilPaywayConfiguration.sourceId = json.optInt("source_id");
    edgilPaywayConfiguration.title = json.optString("title");
    edgilPaywayConfiguration.isEditable = json.optBoolean("is_editable");
    edgilPaywayConfiguration.isDisabled = json.optBoolean("is_disabled");
    edgilPaywayConfiguration.properties = json.optString("properties");
    
    return edgilPaywayConfiguration;
  }
}
