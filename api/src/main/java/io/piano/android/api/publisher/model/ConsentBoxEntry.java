package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class ConsentBoxEntry {
  
  private String fieldName = null;
  private String displayText = null;
  private Boolean entry = null;
  private Date createDate = null;
  private String type = null;
  
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
   * Consent box display text
   **/
  public String getDisplayText() {
    return displayText;
  }

  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }
  
  /**
   * Is consent checked by user
   **/
  public Boolean getEntry() {
    return entry;
  }

  public void setEntry(Boolean entry) {
    this.entry = entry;
  }
  
  /**
   * Date when entry was created
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
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
  
  public static ConsentBoxEntry fromJson(JSONObject json) throws JSONException {
    ConsentBoxEntry consentBoxEntry = new ConsentBoxEntry();

    consentBoxEntry.fieldName = json.optString("field_name");
    consentBoxEntry.displayText = json.optString("display_text");
    consentBoxEntry.entry = json.optBoolean("entry");
    consentBoxEntry.createDate = new Date(json.optLong("create_date") * 1000);
    consentBoxEntry.type = json.optString("type");
    
    return consentBoxEntry;
  }
}
