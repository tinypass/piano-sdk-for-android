package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Currency {
  
  private String currencyCode = null;
  private String currencySymbol = null;

  
  /**
   * Currency code by ISO 4217 standard
   **/
  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }
  /**
   * Currency symbol
   **/
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public static Currency fromJson(JSONObject json) throws JSONException {
    Currency currency = new Currency();

    currency.currencyCode = json.optString("currency_code");
    currency.currencySymbol = json.optString("currency_symbol");
    
    return currency;
  }
}
