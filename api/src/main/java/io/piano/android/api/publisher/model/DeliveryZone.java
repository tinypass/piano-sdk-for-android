package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Country;
import io.piano.android.api.publisher.model.TermBrief;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeliveryZone {
  
  private String deliveryZoneId = null;
  private String deliveryZoneName = null;
  private List<Country> countries = null;
  private List<TermBrief> terms = null;
  
  /**
   * Delivery zone id
   **/
  public String getDeliveryZoneId() {
    return deliveryZoneId;
  }

  public void setDeliveryZoneId(String deliveryZoneId) {
    this.deliveryZoneId = deliveryZoneId;
  }
  
  /**
   * Delivery zone name
   **/
  public String getDeliveryZoneName() {
    return deliveryZoneName;
  }

  public void setDeliveryZoneName(String deliveryZoneName) {
    this.deliveryZoneName = deliveryZoneName;
  }
  
  /**
   **/
  public List<Country> getCountries() {
    return countries;
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }
  
  /**
   **/
  public List<TermBrief> getTerms() {
    return terms;
  }

  public void setTerms(List<TermBrief> terms) {
    this.terms = terms;
  }
  
  public static DeliveryZone fromJson(JSONObject json) throws JSONException {
    DeliveryZone deliveryZone = new DeliveryZone();

    deliveryZone.deliveryZoneId = json.optString("delivery_zone_id");
    deliveryZone.deliveryZoneName = json.optString("delivery_zone_name");
    deliveryZone.countries = new ArrayList<>();
    JSONArray countriesJsonArray = json.optJSONArray("countries");
    int countriesLength = countriesJsonArray.length();
    for (int ii = 0; ii < countriesLength; ii++) {
      deliveryZone.countries.add(Country.fromJson(countriesJsonArray.optJSONObject(ii)));
    }
    deliveryZone.terms = new ArrayList<>();
    JSONArray termsJsonArray = json.optJSONArray("terms");
    int termsLength = termsJsonArray.length();
    for (int ii = 0; ii < termsLength; ii++) {
      deliveryZone.terms.add(TermBrief.fromJson(termsJsonArray.optJSONObject(ii)));
    }
    
    return deliveryZone;
  }
}
