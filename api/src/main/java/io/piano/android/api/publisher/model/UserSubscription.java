package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Resource;
import io.piano.android.api.publisher.model.Term;
import io.piano.android.api.publisher.model.User;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserSubscription {
  
  private String subscriptionId = null;
  private Boolean autoRenew = null;
  private Date nextBillDate = null;
  private String paymentMethod = null;
  private String billingPlan = null;
  private String userPaymentInfoId = null;
  private String status = null;
  private String statusName = null;
  private Term term = null;
  private Resource resource = null;
  private User user = null;
  private Date startDate = null;
  private Boolean cancelable = null;
  private Boolean cancelableAndRefundadle = null;

  
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
   * The start date
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
    userSubscription.term = Term.fromJson(json.optJSONObject("term"));
    userSubscription.resource = Resource.fromJson(json.optJSONObject("resource"));
    userSubscription.user = User.fromJson(json.optJSONObject("user"));
    userSubscription.startDate = new Date(json.optLong("start_date") * 1000);
    userSubscription.cancelable = json.optBoolean("cancelable");
    userSubscription.cancelableAndRefundadle = json.optBoolean("cancelable_and_refundadle");
    
    return userSubscription;
  }
}
