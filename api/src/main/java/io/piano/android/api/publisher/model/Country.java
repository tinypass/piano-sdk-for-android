package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Region;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Country {
  
  private String countryName = null;
  private String countryCode = null;
  private String countryId = null;
  private List<Region> regions = null;
  
  /**
   * Name of the country
   **/
  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }
  
  /**
   * Code of the country
   **/
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  
  /**
   * Id of special country
   **/
  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }
  
  /**
   **/
  public List<Region> getRegions() {
    return regions;
  }

  public void setRegions(List<Region> regions) {
    this.regions = regions;
  }
  
  public static Country fromJson(JSONObject json) throws JSONException {
    Country country = new Country();

    country.countryName = json.optString("country_name");
    country.countryCode = json.optString("country_code");
    country.countryId = json.optString("country_id");
    country.regions = new ArrayList<>();
    JSONArray regionsJsonArray = json.optJSONArray("regions");
    int regionsLength = regionsJsonArray.length();
    for (int ii = 0; ii < regionsLength; ii++) {
      country.regions.add(Region.fromJson(regionsJsonArray.optJSONObject(ii)));
    }
    
    return country;
  }
}
