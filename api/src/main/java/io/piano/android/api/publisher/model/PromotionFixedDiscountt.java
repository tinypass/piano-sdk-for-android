package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PromotionFixedDiscountt {
  
  private String fixedDiscountId = null;
  private String currency = null;
  private String amount = null;

  
  /**
   * Fixed discount id
   **/
  public String getFixedDiscountId() {
    return fixedDiscountId;
  }

  public void setFixedDiscountId(String fixedDiscountId) {
    this.fixedDiscountId = fixedDiscountId;
  }
  /**
   * Fixed discount currency
   **/
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
  /**
   * Fixed discount amount
   **/
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public static PromotionFixedDiscountt fromJson(JSONObject json) throws JSONException {
    PromotionFixedDiscountt promotionFixedDiscountt = new PromotionFixedDiscountt();

    promotionFixedDiscountt.fixedDiscountId = json.optString("fixed_discount_id");
    promotionFixedDiscountt.currency = json.optString("currency");
    promotionFixedDiscountt.amount = json.optString("amount");
    
    return promotionFixedDiscountt;
  }
}
