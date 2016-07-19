package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TermStats {
  
  private String pubId = null;
  private String totalSale = null;
  private String totalSaleStr = null;
  private String conversion = null;
  
  /**
   * Term public id
   **/
  public String getPubId() {
    return pubId;
  }

  public void setPubId(String pubId) {
    this.pubId = pubId;
  }
  
  /**
   * Term total sale
   **/
  public String getTotalSale() {
    return totalSale;
  }

  public void setTotalSale(String totalSale) {
    this.totalSale = totalSale;
  }
  
  /**
   * Term total sale formatd value
   **/
  public String getTotalSaleStr() {
    return totalSaleStr;
  }

  public void setTotalSaleStr(String totalSaleStr) {
    this.totalSaleStr = totalSaleStr;
  }
  
  /**
   * Term conversion
   **/
  public String getConversion() {
    return conversion;
  }

  public void setConversion(String conversion) {
    this.conversion = conversion;
  }
  
  public static TermStats fromJson(JSONObject json) throws JSONException {
    TermStats termStats = new TermStats();

    termStats.pubId = json.optString("pub_id");
    termStats.totalSale = json.optString("total_sale");
    termStats.totalSaleStr = json.optString("total_sale_str");
    termStats.conversion = json.optString("conversion");
    
    return termStats;
  }
}
