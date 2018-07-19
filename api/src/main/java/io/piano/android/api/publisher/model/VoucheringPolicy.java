package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VoucheringPolicy {
  
  private String voucheringPolicyId = null;
  private String voucheringPolicyBillingPlan = null;
  private String voucheringPolicyBillingPlanDescription = null;
  private String voucheringPolicyRedemptionUrl = null;
  
  /**
   * Vouchering policy identifier
   **/
  public String getVoucheringPolicyId() {
    return voucheringPolicyId;
  }

  public void setVoucheringPolicyId(String voucheringPolicyId) {
    this.voucheringPolicyId = voucheringPolicyId;
  }
  
  /**
   * Billing plan of vouchering policy
   **/
  public String getVoucheringPolicyBillingPlan() {
    return voucheringPolicyBillingPlan;
  }

  public void setVoucheringPolicyBillingPlan(String voucheringPolicyBillingPlan) {
    this.voucheringPolicyBillingPlan = voucheringPolicyBillingPlan;
  }
  
  /**
   * Description of vouchering policy's billing plan
   **/
  public String getVoucheringPolicyBillingPlanDescription() {
    return voucheringPolicyBillingPlanDescription;
  }

  public void setVoucheringPolicyBillingPlanDescription(String voucheringPolicyBillingPlanDescription) {
    this.voucheringPolicyBillingPlanDescription = voucheringPolicyBillingPlanDescription;
  }
  
  /**
   * Redemption URL of vouchering policy
   **/
  public String getVoucheringPolicyRedemptionUrl() {
    return voucheringPolicyRedemptionUrl;
  }

  public void setVoucheringPolicyRedemptionUrl(String voucheringPolicyRedemptionUrl) {
    this.voucheringPolicyRedemptionUrl = voucheringPolicyRedemptionUrl;
  }
  
  public static VoucheringPolicy fromJson(JSONObject json) throws JSONException {
    VoucheringPolicy voucheringPolicy = new VoucheringPolicy();

    voucheringPolicy.voucheringPolicyId = json.optString("vouchering_policy_id");
    voucheringPolicy.voucheringPolicyBillingPlan = json.optString("vouchering_policy_billing_plan");
    voucheringPolicy.voucheringPolicyBillingPlanDescription = json.optString("vouchering_policy_billing_plan_description");
    voucheringPolicy.voucheringPolicyRedemptionUrl = json.optString("vouchering_policy_redemption_url");
    
    return voucheringPolicy;
  }
}
