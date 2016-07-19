package io.piano.android.api.publisher.model;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WorldpayPaymentMethod {
  
  private List<String> card = null;
  private List<String> apm = null;
  
  /**
   **/
  public List<String> getCard() {
    return card;
  }

  public void setCard(List<String> card) {
    this.card = card;
  }
  
  /**
   **/
  public List<String> getApm() {
    return apm;
  }

  public void setApm(List<String> apm) {
    this.apm = apm;
  }
  
  public static WorldpayPaymentMethod fromJson(JSONObject json) throws JSONException {
    WorldpayPaymentMethod worldpayPaymentMethod = new WorldpayPaymentMethod();

    worldpayPaymentMethod.card = new ArrayList<>();
    JSONArray cardJsonArray = json.optJSONArray("card");
    int cardLength = cardJsonArray.length();
    for (int ii = 0; ii < cardLength; ii++) {
      worldpayPaymentMethod.card.add(cardJsonArray.optString(ii));
    }
    worldpayPaymentMethod.apm = new ArrayList<>();
    JSONArray apmJsonArray = json.optJSONArray("apm");
    int apmLength = apmJsonArray.length();
    for (int ii = 0; ii < apmLength; ii++) {
      worldpayPaymentMethod.apm.add(apmJsonArray.optString(ii));
    }
    
    return worldpayPaymentMethod;
  }
}
