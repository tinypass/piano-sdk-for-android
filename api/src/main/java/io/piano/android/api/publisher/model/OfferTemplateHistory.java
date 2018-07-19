package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfferTemplateHistory {
  
  private String historyContent = null;
  private String historyComment = null;
  private String offerTemplateHistoryEvent = null;
  private String offerTemplateId = null;
  private List<OfferTemplateSubHistory> historyList = null;
  
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
   * Offer Template History Comment
   **/
  public String getHistoryComment() {
    return historyComment;
  }

  public void setHistoryComment(String historyComment) {
    this.historyComment = historyComment;
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
   **/
  public List<OfferTemplateSubHistory> getHistoryList() {
    return historyList;
  }

  public void setHistoryList(List<OfferTemplateSubHistory> historyList) {
    this.historyList = historyList;
  }
  
  public static OfferTemplateHistory fromJson(JSONObject json) throws JSONException {
    OfferTemplateHistory offerTemplateHistory = new OfferTemplateHistory();

    offerTemplateHistory.historyContent = json.optString("history_content");
    offerTemplateHistory.historyComment = json.optString("history_comment");
    offerTemplateHistory.offerTemplateHistoryEvent = json.optString("offer_template_history_event");
    offerTemplateHistory.offerTemplateId = json.optString("offer_template_id");
    offerTemplateHistory.historyList = new ArrayList<>();
    JSONArray historyListJsonArray = json.optJSONArray("history_list");
    int historyListLength = historyListJsonArray.length();
    for (int ii = 0; ii < historyListLength; ii++) {
      offerTemplateHistory.historyList.add(OfferTemplateSubHistory.fromJson(historyListJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplateHistory;
  }
}
