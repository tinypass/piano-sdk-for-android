package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class UserSubscription {
  
  private String subscriptionId = null;
  private Boolean autoRenew = null;
  private Date nextBillDate = null;
  private String paymentMethod = null;
  private String billingPlan = null;
  private String userPaymentInfoId = null;
  private String status = null;
  private String statusName = null;
  private String statusNameInReports = null;
  private Term term = null;
  private Resource resource = null;
  private User user = null;
  private Date startDate = null;
  private Boolean cancelable = null;
  private Boolean cancelableAndRefundadle = null;
  private UserAddress userAddress = null;
  private String pscSubscriberNumber = null;
  private String externalApiName = null;
  private String conversionResult = null;
  private Boolean isInTrial = null;
  private Date trialPeriodEndDate = null;
  private Double trialAmount = null;
  private String trialCurrency = null;
  private Date endDate = null;
  private Integer chargeCount = null;
  private String upiExtCustomerId = null;
  private String upiExtCustomerIdLabel = null;
  
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
   * User subscription auto renew
   **/
  public Boolean getAutoRenew() {
    return autoRenew;
  }

  public void setAutoRenew(Boolean autoRenew) {
    this.autoRenew = autoRenew;
  }
  
  /**
   * User subscription next bill date
   **/
  public Date getNextBillDate() {
    return nextBillDate;
  }

  public void setNextBillDate(Date nextBillDate) {
    this.nextBillDate = nextBillDate;
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
   * User subscription billing plan
   **/
  public String getBillingPlan() {
    return billingPlan;
  }

  public void setBillingPlan(String billingPlan) {
    this.billingPlan = billingPlan;
  }
  
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
   * User subscription status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * User subscription status displayable
   **/
  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
  
  /**
   * User subscription status displayable in reports
   **/
  public String getStatusNameInReports() {
    return statusNameInReports;
  }

  public void setStatusNameInReports(String statusNameInReports) {
    this.statusNameInReports = statusNameInReports;
  }
  
  /**
   **/
  public Term getTerm() {
    return term;
  }

  public void setTerm(Term term) {
    this.term = term;
  }
  
  /**
   * The resource
   **/
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }
  
  /**
   * The user
   **/
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  /**
   * The start date.
   **/
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  
  /**
   * Whether this subscription could be cancelled. Cancel means that access no longer be prolongated and current access will be revoked
   **/
  public Boolean getCancelable() {
    return cancelable;
  }

  public void setCancelable(Boolean cancelable) {
    this.cancelable = cancelable;
  }
  
  /**
   * Whether this subscription could be cancelled and the payment for the last period could be refunded. Cancel means that access no longer be prolongated and current access will be revoked
   **/
  public Boolean getCancelableAndRefundadle() {
    return cancelableAndRefundadle;
  }

  public void setCancelableAndRefundadle(Boolean cancelableAndRefundadle) {
    this.cancelableAndRefundadle = cancelableAndRefundadle;
  }
  
  /**
   * User address entity
   **/
  public UserAddress getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(UserAddress userAddress) {
    this.userAddress = userAddress;
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
   * External API Configuration name
   **/
  public String getExternalApiName() {
    return externalApiName;
  }

  public void setExternalApiName(String externalApiName) {
    this.externalApiName = externalApiName;
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
   * Is the user currently in trial period
   **/
  public Boolean getIsInTrial() {
    return isInTrial;
  }

  public void setIsInTrial(Boolean isInTrial) {
    this.isInTrial = isInTrial;
  }
  
  /**
   * Date when trial period ends
   **/
  public Date getTrialPeriodEndDate() {
    return trialPeriodEndDate;
  }

  public void setTrialPeriodEndDate(Date trialPeriodEndDate) {
    this.trialPeriodEndDate = trialPeriodEndDate;
  }
  
  /**
   * Price of the trial period
   **/
  public Double getTrialAmount() {
    return trialAmount;
  }

  public void setTrialAmount(Double trialAmount) {
    this.trialAmount = trialAmount;
  }
  
  /**
   * Currency of the trial period
   **/
  public String getTrialCurrency() {
    return trialCurrency;
  }

  public void setTrialCurrency(String trialCurrency) {
    this.trialCurrency = trialCurrency;
  }
  
  /**
   * Subscription end date
   **/
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  /**
   * User subscription charge count
   **/
  public Integer getChargeCount() {
    return chargeCount;
  }

  public void setChargeCount(Integer chargeCount) {
    this.chargeCount = chargeCount;
  }
  
  /**
   * External customer id
   **/
  public String getUpiExtCustomerId() {
    return upiExtCustomerId;
  }

  public void setUpiExtCustomerId(String upiExtCustomerId) {
    this.upiExtCustomerId = upiExtCustomerId;
  }
  
  /**
   * Label for external customer id
   **/
  public String getUpiExtCustomerIdLabel() {
    return upiExtCustomerIdLabel;
  }

  public void setUpiExtCustomerIdLabel(String upiExtCustomerIdLabel) {
    this.upiExtCustomerIdLabel = upiExtCustomerIdLabel;
  }
  
  public static UserSubscription fromJson(JSONObject json) throws JSONException {
    UserSubscription userSubscription = new UserSubscription();

    userSubscription.subscriptionId = json.optString("subscription_id");
    userSubscription.autoRenew = json.optBoolean("auto_renew");
    userSubscription.nextBillDate = new Date(json.optLong("next_bill_date") * 1000);
    userSubscription.paymentMethod = json.optString("payment_method");
    userSubscription.billingPlan = json.optString("billing_plan");
    userSubscription.userPaymentInfoId = json.optString("user_payment_info_id");
    userSubscription.status = json.optString("status");
    userSubscription.statusName = json.optString("status_name");
    userSubscription.statusNameInReports = json.optString("status_name_in_reports");
    userSubscription.term = Term.fromJson(json.optJSONObject("term"));
    userSubscription.resource = Resource.fromJson(json.optJSONObject("resource"));
    userSubscription.user = User.fromJson(json.optJSONObject("user"));
    userSubscription.startDate = new Date(json.optLong("start_date") * 1000);
    userSubscription.cancelable = json.optBoolean("cancelable");
    userSubscription.cancelableAndRefundadle = json.optBoolean("cancelable_and_refundadle");
    userSubscription.userAddress = UserAddress.fromJson(json.optJSONObject("user_address"));
    userSubscription.pscSubscriberNumber = json.optString("psc_subscriber_number");
    userSubscription.externalApiName = json.optString("external_api_name");
    userSubscription.conversionResult = json.optString("conversion_result");
    userSubscription.isInTrial = json.optBoolean("is_in_trial");
    userSubscription.trialPeriodEndDate = new Date(json.optLong("trial_period_end_date") * 1000);
    userSubscription.trialAmount = json.optDouble("trial_amount");
    userSubscription.trialCurrency = json.optString("trial_currency");
    userSubscription.endDate = new Date(json.optLong("end_date") * 1000);
    userSubscription.chargeCount = json.optInt("charge_count");
    userSubscription.upiExtCustomerId = json.optString("upi_ext_customer_id");
    userSubscription.upiExtCustomerIdLabel = json.optString("upi_ext_customer_id_label");
    
    return userSubscription;
  }
}
