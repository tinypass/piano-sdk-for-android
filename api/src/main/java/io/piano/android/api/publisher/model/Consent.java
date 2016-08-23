package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Consent {
  
  private String consentId = null;
  private String fieldName = null;
  private String fieldId = null;
  private String displayText = null;
  private String errorMessage = null;
  private String type = null;
  private Boolean preChecked = null;
  private Boolean required = null;
  private Boolean enabled = null;
  private Boolean fieldIdEnabled = null;
  
  /**
   * Consent ID
   **/
  public String getConsentId() {
    return consentId;
  }

  public void setConsentId(String consentId) {
    this.consentId = consentId;
  }
  
  /**
   * Consent box field name
   **/
  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  
  /**
   * Consent box field ID
   **/
  public String getFieldId() {
    return fieldId;
  }

  public void setFieldId(String fieldId) {
    this.fieldId = fieldId;
  }
  
  /**
   * Consent box display text
   **/
  public String getDisplayText() {
    return displayText;
  }

  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }
  
  /**
   * Consent box error message
   **/
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  
  /**
   * Consent box type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * Consent box pre-checked state
   **/
  public Boolean getPreChecked() {
    return preChecked;
  }

  public void setPreChecked(Boolean preChecked) {
    this.preChecked = preChecked;
  }
  
  /**
   * Is consent box required
   **/
  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }
  
  /**
   * Is consent box enabled
   **/
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
  
  /**
   * Is field ID editing enabled
   **/
  public Boolean getFieldIdEnabled() {
    return fieldIdEnabled;
  }

  public void setFieldIdEnabled(Boolean fieldIdEnabled) {
    this.fieldIdEnabled = fieldIdEnabled;
  }
  
  public static Consent fromJson(JSONObject json) throws JSONException {
    Consent consent = new Consent();

    consent.consentId = json.optString("consent_id");
    consent.fieldName = json.optString("field_name");
    consent.fieldId = json.optString("field_id");
    consent.displayText = json.optString("display_text");
    consent.errorMessage = json.optString("error_message");
    consent.type = json.optString("type");
    consent.preChecked = json.optBoolean("pre_checked");
    consent.required = json.optBoolean("required");
    consent.enabled = json.optBoolean("enabled");
    consent.fieldIdEnabled = json.optBoolean("field_id_enabled");
    
    return consent;
  }
}
