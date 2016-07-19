package io.piano.android.api.anon.model;

import io.piano.android.api.anon.model.Term;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserSubscription {
  
  private String subscriptionId = null;
  private Term term = null;
  private Boolean autoRenew = null;
  private Date gracePeriodStartDate = null;
  private Date nextBillDate = null;
  private Date startDate = null;
  private String status = null;
  private Boolean cancelable = null;
  private Boolean cancelableAndRefundadle = null;
  private String paymentBillingPlanDescription = null;
  
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
   **/
  public Term getTerm() {
    return term;
  }

  public void setTerm(Term term) {
    this.term = term;
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
   * Grace period start date
   **/
  public Date getGracePeriodStartDate() {
    return gracePeriodStartDate;
  }

  public void setGracePeriodStartDate(Date gracePeriodStartDate) {
    this.gracePeriodStartDate = gracePeriodStartDate;
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
   * The start date
   **/
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
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
   * Term billing plan description
   **/
  public String getPaymentBillingPlanDescription() {
    return paymentBillingPlanDescription;
  }

  public void setPaymentBillingPlanDescription(String paymentBillingPlanDescription) {
    this.paymentBillingPlanDescription = paymentBillingPlanDescription;
  }
  
  public static UserSubscription fromJson(JSONObject json) throws JSONException {
    UserSubscription userSubscription = new UserSubscription();

    userSubscription.subscriptionId = json.optString("subscription_id");
    userSubscription.term = Term.fromJson(json.optJSONObject("term"));
    userSubscription.autoRenew = json.optBoolean("auto_renew");
    userSubscription.gracePeriodStartDate = new Date(json.optLong("grace_period_start_date") * 1000);
    userSubscription.nextBillDate = new Date(json.optLong("next_bill_date") * 1000);
    userSubscription.startDate = new Date(json.optLong("start_date") * 1000);
    userSubscription.status = json.optString("status");
    userSubscription.cancelable = json.optBoolean("cancelable");
    userSubscription.cancelableAndRefundadle = json.optBoolean("cancelable_and_refundadle");
    userSubscription.paymentBillingPlanDescription = json.optString("payment_billing_plan_description");
    
    return userSubscription;
  }
}
