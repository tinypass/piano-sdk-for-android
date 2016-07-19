package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SalesTaxRateModel {
  
  private Boolean charged = null;
  private Double chargeRate = null;
  private String stateAbbr = null;
  private String stateId = null;
  private String stateName = null;
  
  /**
   * Charge for selected state
   **/
  public Boolean getCharged() {
    return charged;
  }

  public void setCharged(Boolean charged) {
    this.charged = charged;
  }
  
  /**
   * Charge rate for selected state
   **/
  public Double getChargeRate() {
    return chargeRate;
  }

  public void setChargeRate(Double chargeRate) {
    this.chargeRate = chargeRate;
  }
  
  /**
   * State short name
   **/
  public String getStateAbbr() {
    return stateAbbr;
  }

  public void setStateAbbr(String stateAbbr) {
    this.stateAbbr = stateAbbr;
  }
  
  /**
   * State
   **/
  public String getStateId() {
    return stateId;
  }

  public void setStateId(String stateId) {
    this.stateId = stateId;
  }
  
  /**
   * State name
   **/
  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
  
  public static SalesTaxRateModel fromJson(JSONObject json) throws JSONException {
    SalesTaxRateModel salesTaxRateModel = new SalesTaxRateModel();

    salesTaxRateModel.charged = json.optBoolean("charged");
    salesTaxRateModel.chargeRate = json.optDouble("charge_rate");
    salesTaxRateModel.stateAbbr = json.optString("state_abbr");
    salesTaxRateModel.stateId = json.optString("state_id");
    salesTaxRateModel.stateName = json.optString("state_name");
    
    return salesTaxRateModel;
  }
}
