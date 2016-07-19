package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserSubscriptionDto {
  
  private String termName = null;
  private String paymentBillingPlan = null;
  private String imageUrl = null;
  private String resourceName = null;
  private String rid = null;
  private String nextBillDate = null;
  private String subscriptionLastPayment = null;
  private String status = null;
  private String creaditCardExpire = null;
  private Boolean creaditCardExpireSoon = null;
  private String subscriptionId = null;
  private String paymentMethod = null;
  private Boolean accessExpired = null;
  private Boolean inAppPayment = null;
  private String pscSubscriberNumber = null;
  private String conversionResult = null;
  private String externalApiName = null;
  
  /**
   * User subscription term name
   **/
  public String getTermName() {
    return termName;
  }

  public void setTermName(String termName) {
    this.termName = termName;
  }
  
  /**
   * Term billing plan
   **/
  public String getPaymentBillingPlan() {
    return paymentBillingPlan;
  }

  public void setPaymentBillingPlan(String paymentBillingPlan) {
    this.paymentBillingPlan = paymentBillingPlan;
  }
  
  /**
   * Resource image URL
   **/
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  
  /**
   * User subscription resource name
   **/
  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }
  
  /**
   * Unique id for resource
   **/
  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }
  
  /**
   * User subscription next bill date
   **/
  public String getNextBillDate() {
    return nextBillDate;
  }

  public void setNextBillDate(String nextBillDate) {
    this.nextBillDate = nextBillDate;
  }
  
  /**
   * Subscription last payment
   **/
  public String getSubscriptionLastPayment() {
    return subscriptionLastPayment;
  }

  public void setSubscriptionLastPayment(String subscriptionLastPayment) {
    this.subscriptionLastPayment = subscriptionLastPayment;
  }
  
  /**
   * User subscription status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Credit card expire
   **/
  public String getCreaditCardExpire() {
    return creaditCardExpire;
  }

  public void setCreaditCardExpire(String creaditCardExpire) {
    this.creaditCardExpire = creaditCardExpire;
  }
  
  /**
   * Credit card expires soon
   **/
  public Boolean getCreaditCardExpireSoon() {
    return creaditCardExpireSoon;
  }

  public void setCreaditCardExpireSoon(Boolean creaditCardExpireSoon) {
    this.creaditCardExpireSoon = creaditCardExpireSoon;
  }
  
  /**
   * User subscription id
   **/
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }
  
  /**
   * User subscription payment method
   **/
  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }
  
  /**
   * The access item expired 
   **/
  public Boolean getAccessExpired() {
    return accessExpired;
  }

  public void setAccessExpired(Boolean accessExpired) {
    this.accessExpired = accessExpired;
  }
  
  /**
   * In app payment method
   **/
  public Boolean getInAppPayment() {
    return inAppPayment;
  }

  public void setInAppPayment(Boolean inAppPayment) {
    this.inAppPayment = inAppPayment;
  }
  
  /**
   * PSC subscriber number
   **/
  public String getPscSubscriberNumber() {
    return pscSubscriberNumber;
  }

  public void setPscSubscriberNumber(String pscSubscriberNumber) {
    this.pscSubscriberNumber = pscSubscriberNumber;
  }
  
  /**
   * Conversion result
   **/
  public String getConversionResult() {
    return conversionResult;
  }

  public void setConversionResult(String conversionResult) {
    this.conversionResult = conversionResult;
  }
  
  /**
   * External API Configuration name
   **/
  public String getExternalApiName() {
    return externalApiName;
  }

  public void setExternalApiName(String externalApiName) {
    this.externalApiName = externalApiName;
  }
  
  public static UserSubscriptionDto fromJson(JSONObject json) throws JSONException {
    UserSubscriptionDto userSubscriptionDto = new UserSubscriptionDto();

    userSubscriptionDto.termName = json.optString("term_name");
    userSubscriptionDto.paymentBillingPlan = json.optString("payment_billing_plan");
    userSubscriptionDto.imageUrl = json.optString("image_url");
    userSubscriptionDto.resourceName = json.optString("resource_name");
    userSubscriptionDto.rid = json.optString("rid");
    userSubscriptionDto.nextBillDate = json.optString("next_bill_date");
    userSubscriptionDto.subscriptionLastPayment = json.optString("subscription_last_payment");
    userSubscriptionDto.status = json.optString("status");
    userSubscriptionDto.creaditCardExpire = json.optString("creadit_card_expire");
    userSubscriptionDto.creaditCardExpireSoon = json.optBoolean("creadit_card_expire_soon");
    userSubscriptionDto.subscriptionId = json.optString("subscription_id");
    userSubscriptionDto.paymentMethod = json.optString("payment_method");
    userSubscriptionDto.accessExpired = json.optBoolean("access_expired");
    userSubscriptionDto.inAppPayment = json.optBoolean("in_app_payment");
    userSubscriptionDto.pscSubscriberNumber = json.optString("psc_subscriber_number");
    userSubscriptionDto.conversionResult = json.optString("conversion_result");
    userSubscriptionDto.externalApiName = json.optString("external_api_name");
    
    return userSubscriptionDto;
  }
}
