package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class OfferTemplateContentField {
  
  private String contentFieldId = null;
  private String offerTemplateVariantId = null;
  private String offerTemplateId = null;
  private String name = null;
  private String description = null;
  private Boolean deleted = null;
  private String value = null;
  
  /**
   * Content field id
   **/
  public String getContentFieldId() {
    return contentFieldId;
  }

  public void setContentFieldId(String contentFieldId) {
    this.contentFieldId = contentFieldId;
  }
  
  /**
   * Offer template variant ID
   **/
  public String getOfferTemplateVariantId() {
    return offerTemplateVariantId;
  }

  public void setOfferTemplateVariantId(String offerTemplateVariantId) {
    this.offerTemplateVariantId = offerTemplateVariantId;
  }
  
  /**
   * Offer Template ID
   **/
  public String getOfferTemplateId() {
    return offerTemplateId;
  }

  public void setOfferTemplateId(String offerTemplateId) {
    this.offerTemplateId = offerTemplateId;
  }
  
  /**
   * The name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * The description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * If the object is deleted
   **/
  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
  
  /**
   * Content field value
   **/
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  public static OfferTemplateContentField fromJson(JSONObject json) throws JSONException {
    OfferTemplateContentField offerTemplateContentField = new OfferTemplateContentField();

    offerTemplateContentField.contentFieldId = json.optString("content_field_id");
    offerTemplateContentField.offerTemplateVariantId = json.optString("offer_template_variant_id");
    offerTemplateContentField.offerTemplateId = json.optString("offer_template_id");
    offerTemplateContentField.name = json.optString("name");
    offerTemplateContentField.description = json.optString("description");
    offerTemplateContentField.deleted = json.optBoolean("deleted");
    offerTemplateContentField.value = json.optString("value");
    
    return offerTemplateContentField;
  }
}
