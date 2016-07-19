package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Region {
  
  private String regionName = null;
  private String regionCode = null;
  private String regionId = null;
  
  /**
   * Name of the regions for specific country
   **/
  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }
  
  /**
   * Code of the regions for specific country
   **/
  public String getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }
  
  /**
   * Id of special region
   **/
  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }
  
  public static Region fromJson(JSONObject json) throws JSONException {
    Region region = new Region();

    region.regionName = json.optString("region_name");
    region.regionCode = json.optString("region_code");
    region.regionId = json.optString("region_id");
    
    return region;
  }
}
