package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubscriptionRestrictions {
  
  private Boolean allowChangeNextBillDate = null;
  private Boolean allowEnableAutoRenew = null;
  private Boolean allowSwitchPaymentMethod = null;
  private Boolean allowSchedulerRenewals = null;
  private Boolean allowFutureRenewals = null;
  
  /**
   * Is app allowed to change the next billing date for the subscription
   **/
  public Boolean getAllowChangeNextBillDate() {
    return allowChangeNextBillDate;
  }

  public void setAllowChangeNextBillDate(Boolean allowChangeNextBillDate) {
    this.allowChangeNextBillDate = allowChangeNextBillDate;
  }
  
  /**
   * Is app CAN disable auto-renew BUT cannot enable auto renew if it is disabled
   **/
  public Boolean getAllowEnableAutoRenew() {
    return allowEnableAutoRenew;
  }

  public void setAllowEnableAutoRenew(Boolean allowEnableAutoRenew) {
    this.allowEnableAutoRenew = allowEnableAutoRenew;
  }
  
  /**
   * Is app allowed to change their user's payemnt method for subscriptions
   **/
  public Boolean getAllowSwitchPaymentMethod() {
    return allowSwitchPaymentMethod;
  }

  public void setAllowSwitchPaymentMethod(Boolean allowSwitchPaymentMethod) {
    this.allowSwitchPaymentMethod = allowSwitchPaymentMethod;
  }
  
  /**
   * Is app allowed to run manually scheduler renewal for the subscription
   **/
  public Boolean getAllowSchedulerRenewals() {
    return allowSchedulerRenewals;
  }

  public void setAllowSchedulerRenewals(Boolean allowSchedulerRenewals) {
    this.allowSchedulerRenewals = allowSchedulerRenewals;
  }
  
  /**
   * Is app allowed to manually renew subscriptions whcih has next billing date in the future
   **/
  public Boolean getAllowFutureRenewals() {
    return allowFutureRenewals;
  }

  public void setAllowFutureRenewals(Boolean allowFutureRenewals) {
    this.allowFutureRenewals = allowFutureRenewals;
  }
  
  public static SubscriptionRestrictions fromJson(JSONObject json) throws JSONException {
    SubscriptionRestrictions subscriptionRestrictions = new SubscriptionRestrictions();

    subscriptionRestrictions.allowChangeNextBillDate = json.optBoolean("allow_change_next_bill_date");
    subscriptionRestrictions.allowEnableAutoRenew = json.optBoolean("allow_enable_auto_renew");
    subscriptionRestrictions.allowSwitchPaymentMethod = json.optBoolean("allow_switch_payment_method");
    subscriptionRestrictions.allowSchedulerRenewals = json.optBoolean("allow_scheduler_renewals");
    subscriptionRestrictions.allowFutureRenewals = json.optBoolean("allow_future_renewals");
    
    return subscriptionRestrictions;
  }
}
