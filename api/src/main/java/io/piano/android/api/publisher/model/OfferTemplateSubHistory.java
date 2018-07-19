package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class OfferTemplateSubHistory {
  
  private String historyContent = null;
  private String offerTemplateHistoryEvent = null;
  private String offerTemplateId = null;
  private String offerTemplateVariantId = null;
  private String offerTemplateVersionId = null;
  
  /**
   * History Content
   **/
  public String getHistoryContent() {
    return historyContent;
  }

  public void setHistoryContent(String historyContent) {
    this.historyContent = historyContent;
  }
  
  /**
   * Offer Template History Event
   **/
  public String getOfferTemplateHistoryEvent() {
    return offerTemplateHistoryEvent;
  }

  public void setOfferTemplateHistoryEvent(String offerTemplateHistoryEvent) {
    this.offerTemplateHistoryEvent = offerTemplateHistoryEvent;
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
   * Offer template variant ID
   **/
  public String getOfferTemplateVariantId() {
    return offerTemplateVariantId;
  }

  public void setOfferTemplateVariantId(String offerTemplateVariantId) {
    this.offerTemplateVariantId = offerTemplateVariantId;
  }
  
  /**
   * Offer template version ID
   **/
  public String getOfferTemplateVersionId() {
    return offerTemplateVersionId;
  }

  public void setOfferTemplateVersionId(String offerTemplateVersionId) {
    this.offerTemplateVersionId = offerTemplateVersionId;
  }
  
  public static OfferTemplateSubHistory fromJson(JSONObject json) throws JSONException {
    OfferTemplateSubHistory offerTemplateSubHistory = new OfferTemplateSubHistory();

    offerTemplateSubHistory.historyContent = json.optString("history_content");
    offerTemplateSubHistory.offerTemplateHistoryEvent = json.optString("offer_template_history_event");
    offerTemplateSubHistory.offerTemplateId = json.optString("offer_template_id");
    offerTemplateSubHistory.offerTemplateVariantId = json.optString("offer_template_variant_id");
    offerTemplateSubHistory.offerTemplateVersionId = json.optString("offer_template_version_id");
    
    return offerTemplateSubHistory;
  }
}
