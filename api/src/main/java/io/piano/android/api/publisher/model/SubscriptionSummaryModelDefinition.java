package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubscriptionSummaryModelDefinition {
  
  private String date = null;
  private String newSubscriptionAmount = null;
  private String newSubscriptionTpFeeAmount = null;
  private Integer newSubscriptionCount = null;
  private Integer newTrialSubscriptionCount = null;
  private Integer subscriptionCancelledCount = null;
  private Integer subscriptionFailedOrExpiredCount = null;
  private String subscriptionRefundedAmount = null;
  private String subscriptionRefundedTpFeeAmount = null;
  private Integer subscriptionRefundedCount = null;
  private String subscriptionRenewedAmount = null;
  private String subscriptionRenewedTpFeeAmount = null;
  private Integer subscriptionRenewedCount = null;

  
  /**
   * Date
   **/
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
  /**
   * New subscription amount
   **/
  public String getNewSubscriptionAmount() {
    return newSubscriptionAmount;
  }

  public void setNewSubscriptionAmount(String newSubscriptionAmount) {
    this.newSubscriptionAmount = newSubscriptionAmount;
  }
  /**
   * New subscription TP fee amount
   **/
  public String getNewSubscriptionTpFeeAmount() {
    return newSubscriptionTpFeeAmount;
  }

  public void setNewSubscriptionTpFeeAmount(String newSubscriptionTpFeeAmount) {
    this.newSubscriptionTpFeeAmount = newSubscriptionTpFeeAmount;
  }
  /**
   * New subscription count
   **/
  public Integer getNewSubscriptionCount() {
    return newSubscriptionCount;
  }

  public void setNewSubscriptionCount(Integer newSubscriptionCount) {
    this.newSubscriptionCount = newSubscriptionCount;
  }
  /**
   * New trial subscription count
   **/
  public Integer getNewTrialSubscriptionCount() {
    return newTrialSubscriptionCount;
  }

  public void setNewTrialSubscriptionCount(Integer newTrialSubscriptionCount) {
    this.newTrialSubscriptionCount = newTrialSubscriptionCount;
  }
  /**
   * Subscription cancelled count
   **/
  public Integer getSubscriptionCancelledCount() {
    return subscriptionCancelledCount;
  }

  public void setSubscriptionCancelledCount(Integer subscriptionCancelledCount) {
    this.subscriptionCancelledCount = subscriptionCancelledCount;
  }
  /**
   * Subscription failed or expired count
   **/
  public Integer getSubscriptionFailedOrExpiredCount() {
    return subscriptionFailedOrExpiredCount;
  }

  public void setSubscriptionFailedOrExpiredCount(Integer subscriptionFailedOrExpiredCount) {
    this.subscriptionFailedOrExpiredCount = subscriptionFailedOrExpiredCount;
  }
  /**
   * Subscription refunded amount
   **/
  public String getSubscriptionRefundedAmount() {
    return subscriptionRefundedAmount;
  }

  public void setSubscriptionRefundedAmount(String subscriptionRefundedAmount) {
    this.subscriptionRefundedAmount = subscriptionRefundedAmount;
  }
  /**
   * Subscription refunded tp fee amount
   **/
  public String getSubscriptionRefundedTpFeeAmount() {
    return subscriptionRefundedTpFeeAmount;
  }

  public void setSubscriptionRefundedTpFeeAmount(String subscriptionRefundedTpFeeAmount) {
    this.subscriptionRefundedTpFeeAmount = subscriptionRefundedTpFeeAmount;
  }
  /**
   * Subscription refunded count
   **/
  public Integer getSubscriptionRefundedCount() {
    return subscriptionRefundedCount;
  }

  public void setSubscriptionRefundedCount(Integer subscriptionRefundedCount) {
    this.subscriptionRefundedCount = subscriptionRefundedCount;
  }
  /**
   * Subscription renewed amount
   **/
  public String getSubscriptionRenewedAmount() {
    return subscriptionRenewedAmount;
  }

  public void setSubscriptionRenewedAmount(String subscriptionRenewedAmount) {
    this.subscriptionRenewedAmount = subscriptionRenewedAmount;
  }
  /**
   * Subscription renewed tp fee amount
   **/
  public String getSubscriptionRenewedTpFeeAmount() {
    return subscriptionRenewedTpFeeAmount;
  }

  public void setSubscriptionRenewedTpFeeAmount(String subscriptionRenewedTpFeeAmount) {
    this.subscriptionRenewedTpFeeAmount = subscriptionRenewedTpFeeAmount;
  }
  /**
   * Subscription renewed count
   **/
  public Integer getSubscriptionRenewedCount() {
    return subscriptionRenewedCount;
  }

  public void setSubscriptionRenewedCount(Integer subscriptionRenewedCount) {
    this.subscriptionRenewedCount = subscriptionRenewedCount;
  }

  public static SubscriptionSummaryModelDefinition fromJson(JSONObject json) throws JSONException {
    SubscriptionSummaryModelDefinition subscriptionSummaryModelDefinition = new SubscriptionSummaryModelDefinition();

    subscriptionSummaryModelDefinition.date = json.optString("date");
    subscriptionSummaryModelDefinition.newSubscriptionAmount = json.optString("new_subscription_amount");
    subscriptionSummaryModelDefinition.newSubscriptionTpFeeAmount = json.optString("new_subscription_tp_fee_amount");
    subscriptionSummaryModelDefinition.newSubscriptionCount = json.optInt("new_subscription_count");
    subscriptionSummaryModelDefinition.newTrialSubscriptionCount = json.optInt("new_trial_subscription_count");
    subscriptionSummaryModelDefinition.subscriptionCancelledCount = json.optInt("subscription_cancelled_count");
    subscriptionSummaryModelDefinition.subscriptionFailedOrExpiredCount = json.optInt("subscription_failed_or_expired_count");
    subscriptionSummaryModelDefinition.subscriptionRefundedAmount = json.optString("subscription_refunded_amount");
    subscriptionSummaryModelDefinition.subscriptionRefundedTpFeeAmount = json.optString("subscription_refunded_tp_fee_amount");
    subscriptionSummaryModelDefinition.subscriptionRefundedCount = json.optInt("subscription_refunded_count");
    subscriptionSummaryModelDefinition.subscriptionRenewedAmount = json.optString("subscription_renewed_amount");
    subscriptionSummaryModelDefinition.subscriptionRenewedTpFeeAmount = json.optString("subscription_renewed_tp_fee_amount");
    subscriptionSummaryModelDefinition.subscriptionRenewedCount = json.optInt("subscription_renewed_count");
    
    return subscriptionSummaryModelDefinition;
  }
}
