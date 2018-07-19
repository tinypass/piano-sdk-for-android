package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfferTemplateHistories {
  
  private List<OfferTemplateHistory> historyList = null;
  
  /**
   **/
  public List<OfferTemplateHistory> getHistoryList() {
    return historyList;
  }

  public void setHistoryList(List<OfferTemplateHistory> historyList) {
    this.historyList = historyList;
  }
  
  public static OfferTemplateHistories fromJson(JSONObject json) throws JSONException {
    OfferTemplateHistories offerTemplateHistories = new OfferTemplateHistories();

    offerTemplateHistories.historyList = new ArrayList<>();
    JSONArray historyListJsonArray = json.optJSONArray("history_list");
    int historyListLength = historyListJsonArray.length();
    for (int ii = 0; ii < historyListLength; ii++) {
      offerTemplateHistories.historyList.add(OfferTemplateHistory.fromJson(historyListJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplateHistories;
  }
}
