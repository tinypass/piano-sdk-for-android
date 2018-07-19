package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPaymentInfo {
  
  private String userPaymentInfoId = null;
  private String description = null;
  private String upiNickname = null;
  private String upiNumber = null;
  private Integer upiExpirationMonth = null;
  private Integer upiExpirationYear = null;
  private String upiPostalCode = null;
  private String upiIdentifier = null;
  
  /**
   * User payment info id
   **/
  public String getUserPaymentInfoId() {
    return userPaymentInfoId;
  }

  public void setUserPaymentInfoId(String userPaymentInfoId) {
    this.userPaymentInfoId = userPaymentInfoId;
  }
  
  /**
   * User payment description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * Card nickname
   **/
  public String getUpiNickname() {
    return upiNickname;
  }

  public void setUpiNickname(String upiNickname) {
    this.upiNickname = upiNickname;
  }
  
  /**
   * Card number
   **/
  public String getUpiNumber() {
    return upiNumber;
  }

  public void setUpiNumber(String upiNumber) {
    this.upiNumber = upiNumber;
  }
  
  /**
   * Card expiration month
   **/
  public Integer getUpiExpirationMonth() {
    return upiExpirationMonth;
  }

  public void setUpiExpirationMonth(Integer upiExpirationMonth) {
    this.upiExpirationMonth = upiExpirationMonth;
  }
  
  /**
   * Card expiration year
   **/
  public Integer getUpiExpirationYear() {
    return upiExpirationYear;
  }

  public void setUpiExpirationYear(Integer upiExpirationYear) {
    this.upiExpirationYear = upiExpirationYear;
  }
  
  /**
   * Card postal code
   **/
  public String getUpiPostalCode() {
    return upiPostalCode;
  }

  public void setUpiPostalCode(String upiPostalCode) {
    this.upiPostalCode = upiPostalCode;
  }
  
  /**
   * Identifier of the payment method
   **/
  public String getUpiIdentifier() {
    return upiIdentifier;
  }

  public void setUpiIdentifier(String upiIdentifier) {
    this.upiIdentifier = upiIdentifier;
  }
  
  public static UserPaymentInfo fromJson(JSONObject json) throws JSONException {
    UserPaymentInfo userPaymentInfo = new UserPaymentInfo();

    userPaymentInfo.userPaymentInfoId = json.optString("user_payment_info_id");
    userPaymentInfo.description = json.optString("description");
    userPaymentInfo.upiNickname = json.optString("upi_nickname");
    userPaymentInfo.upiNumber = json.optString("upi_number");
    userPaymentInfo.upiExpirationMonth = json.optInt("upi_expiration_month");
    userPaymentInfo.upiExpirationYear = json.optInt("upi_expiration_year");
    userPaymentInfo.upiPostalCode = json.optString("upi_postal_code");
    userPaymentInfo.upiIdentifier = json.optString("upi_identifier");
    
    return userPaymentInfo;
  }
}
