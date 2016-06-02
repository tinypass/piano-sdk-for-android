package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Resource;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Term {
  
  private String termId = null;
  private String aid = null;
  private Resource resource = null;
  private String type = null;
  private String name = null;
  private String description = null;
  private Boolean verifyOnRenewal = null;
  private Date createDate = null;
  private String paymentBillingPlan = null;
  private String paymentBillingPlanDescription = null;
  private Integer paymentAllowRenewDays = null;
  private Boolean paymentForceAutoRenew = null;
  private Boolean paymentIsCustomPriceAvailable = null;
  private Boolean paymentIsSubscription = null;
  private Boolean paymentHasFreeTrial = null;
  private Boolean paymentNewCustomersOnly = null;
  private Boolean paymentTrialNewCustomersOnly = null;
  private Boolean paymentAllowPromoCodes = null;
  private Integer paymentRenewGracePeriod = null;
  private Boolean paymentAllowGift = null;
  private String paymentCurrency = null;
  private Boolean customRequireUser = null;
  private Integer customDefaultAccessPeriod = null;
  private String adviewVastUrl = null;
  private Integer adviewAccessPeriod = null;
  private Integer registrationAccessPeriod = null;
  private Integer registrationGracePeriod = null;
  private String externalApiId = null;
  private String externalApiName = null;
  private Integer evtVerificationPeriod = null;
  private Integer evtFixedTimeAccessPeriod = null;
  private Integer evtGracePeriod = null;
  private String evtItunesBundleId = null;
  private String evtItunesProductId = null;

  
  /**
   * Term ID
   **/
  public String getTermId() {
    return termId;
  }

  public void setTermId(String termId) {
    this.termId = termId;
  }
  /**
   * Application aid
   **/
  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
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
   * Term type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  /**
   * Term name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  /**
   * Term description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  /**
   * Is term should be verified before renewal or it skips this step
   **/
  public Boolean getVerifyOnRenewal() {
    return verifyOnRenewal;
  }

  public void setVerifyOnRenewal(Boolean verifyOnRenewal) {
    this.verifyOnRenewal = verifyOnRenewal;
  }
  /**
   * The creation date
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
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
   * Term billing plan description
   **/
  public String getPaymentBillingPlanDescription() {
    return paymentBillingPlanDescription;
  }

  public void setPaymentBillingPlanDescription(String paymentBillingPlanDescription) {
    this.paymentBillingPlanDescription = paymentBillingPlanDescription;
  }
  /**
   * How many days in advance a user can renew
   **/
  public Integer getPaymentAllowRenewDays() {
    return paymentAllowRenewDays;
  }

  public void setPaymentAllowRenewDays(Integer paymentAllowRenewDays) {
    this.paymentAllowRenewDays = paymentAllowRenewDays;
  }
  /**
   * Payment forces the auto renewal of subscriptions
   **/
  public Boolean getPaymentForceAutoRenew() {
    return paymentForceAutoRenew;
  }

  public void setPaymentForceAutoRenew(Boolean paymentForceAutoRenew) {
    this.paymentForceAutoRenew = paymentForceAutoRenew;
  }
  /**
   * Payment is available custom price
   **/
  public Boolean getPaymentIsCustomPriceAvailable() {
    return paymentIsCustomPriceAvailable;
  }

  public void setPaymentIsCustomPriceAvailable(Boolean paymentIsCustomPriceAvailable) {
    this.paymentIsCustomPriceAvailable = paymentIsCustomPriceAvailable;
  }
  /**
   * Payment is subscription
   **/
  public Boolean getPaymentIsSubscription() {
    return paymentIsSubscription;
  }

  public void setPaymentIsSubscription(Boolean paymentIsSubscription) {
    this.paymentIsSubscription = paymentIsSubscription;
  }
  /**
   * Payment has free trial
   **/
  public Boolean getPaymentHasFreeTrial() {
    return paymentHasFreeTrial;
  }

  public void setPaymentHasFreeTrial(Boolean paymentHasFreeTrial) {
    this.paymentHasFreeTrial = paymentHasFreeTrial;
  }
  /**
   * Whether to allow buy only first time
   **/
  public Boolean getPaymentNewCustomersOnly() {
    return paymentNewCustomersOnly;
  }

  public void setPaymentNewCustomersOnly(Boolean paymentNewCustomersOnly) {
    this.paymentNewCustomersOnly = paymentNewCustomersOnly;
  }
  /**
   * Whether to allow trial period only first time
   **/
  public Boolean getPaymentTrialNewCustomersOnly() {
    return paymentTrialNewCustomersOnly;
  }

  public void setPaymentTrialNewCustomersOnly(Boolean paymentTrialNewCustomersOnly) {
    this.paymentTrialNewCustomersOnly = paymentTrialNewCustomersOnly;
  }
  /**
   * Whether to allow promo codes to be applied
   **/
  public Boolean getPaymentAllowPromoCodes() {
    return paymentAllowPromoCodes;
  }

  public void setPaymentAllowPromoCodes(Boolean paymentAllowPromoCodes) {
    this.paymentAllowPromoCodes = paymentAllowPromoCodes;
  }
  /**
   * The number of days after expiration to still allow access to the resource
   **/
  public Integer getPaymentRenewGracePeriod() {
    return paymentRenewGracePeriod;
  }

  public void setPaymentRenewGracePeriod(Integer paymentRenewGracePeriod) {
    this.paymentRenewGracePeriod = paymentRenewGracePeriod;
  }
  /**
   * Whether the term can be gifted
   **/
  public Boolean getPaymentAllowGift() {
    return paymentAllowGift;
  }

  public void setPaymentAllowGift(Boolean paymentAllowGift) {
    this.paymentAllowGift = paymentAllowGift;
  }
  /**
   * Currency of the term
   **/
  public String getPaymentCurrency() {
    return paymentCurrency;
  }

  public void setPaymentCurrency(String paymentCurrency) {
    this.paymentCurrency = paymentCurrency;
  }
  /**
   * Whether a valid user is required to complete the term
   **/
  public Boolean getCustomRequireUser() {
    return customRequireUser;
  }

  public void setCustomRequireUser(Boolean customRequireUser) {
    this.customRequireUser = customRequireUser;
  }
  /**
   * The default access period
   **/
  public Integer getCustomDefaultAccessPeriod() {
    return customDefaultAccessPeriod;
  }

  public void setCustomDefaultAccessPeriod(Integer customDefaultAccessPeriod) {
    this.customDefaultAccessPeriod = customDefaultAccessPeriod;
  }
  /**
   * The VAST URL to use
   **/
  public String getAdviewVastUrl() {
    return adviewVastUrl;
  }

  public void setAdviewVastUrl(String adviewVastUrl) {
    this.adviewVastUrl = adviewVastUrl;
  }
  /**
   * The length of time a user gets access for
   **/
  public Integer getAdviewAccessPeriod() {
    return adviewAccessPeriod;
  }

  public void setAdviewAccessPeriod(Integer adviewAccessPeriod) {
    this.adviewAccessPeriod = adviewAccessPeriod;
  }
  /**
   * The access time period 
   **/
  public Integer getRegistrationAccessPeriod() {
    return registrationAccessPeriod;
  }

  public void setRegistrationAccessPeriod(Integer registrationAccessPeriod) {
    this.registrationAccessPeriod = registrationAccessPeriod;
  }
  /**
   * The time period after registration that will count it as a valid registration conversion
   **/
  public Integer getRegistrationGracePeriod() {
    return registrationGracePeriod;
  }

  public void setRegistrationGracePeriod(Integer registrationGracePeriod) {
    this.registrationGracePeriod = registrationGracePeriod;
  }
  /**
   * External API Configuration ID
   **/
  public String getExternalApiId() {
    return externalApiId;
  }

  public void setExternalApiId(String externalApiId) {
    this.externalApiId = externalApiId;
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
   * External verification period
   **/
  public Integer getEvtVerificationPeriod() {
    return evtVerificationPeriod;
  }

  public void setEvtVerificationPeriod(Integer evtVerificationPeriod) {
    this.evtVerificationPeriod = evtVerificationPeriod;
  }
  /**
   * Period to grant access for in days
   **/
  public Integer getEvtFixedTimeAccessPeriod() {
    return evtFixedTimeAccessPeriod;
  }

  public void setEvtFixedTimeAccessPeriod(Integer evtFixedTimeAccessPeriod) {
    this.evtFixedTimeAccessPeriod = evtFixedTimeAccessPeriod;
  }
  /**
   * External API grace period
   **/
  public Integer getEvtGracePeriod() {
    return evtGracePeriod;
  }

  public void setEvtGracePeriod(Integer evtGracePeriod) {
    this.evtGracePeriod = evtGracePeriod;
  }
  /**
   * iTunes bundle id
   **/
  public String getEvtItunesBundleId() {
    return evtItunesBundleId;
  }

  public void setEvtItunesBundleId(String evtItunesBundleId) {
    this.evtItunesBundleId = evtItunesBundleId;
  }
  /**
   * iTunes  product id
   **/
  public String getEvtItunesProductId() {
    return evtItunesProductId;
  }

  public void setEvtItunesProductId(String evtItunesProductId) {
    this.evtItunesProductId = evtItunesProductId;
  }

  public static Term fromJson(JSONObject json) throws JSONException {
    Term term = new Term();

    term.termId = json.optString("term_id");
    term.aid = json.optString("aid");
    term.resource = Resource.fromJson(json.optJSONObject("resource"));
    term.type = json.optString("type");
    term.name = json.optString("name");
    term.description = json.optString("description");
    term.verifyOnRenewal = json.optBoolean("verify_on_renewal");
    term.createDate = new Date(json.optLong("create_date") * 1000);
    term.paymentBillingPlan = json.optString("payment_billing_plan");
    term.paymentBillingPlanDescription = json.optString("payment_billing_plan_description");
    term.paymentAllowRenewDays = json.optInt("payment_allow_renew_days");
    term.paymentForceAutoRenew = json.optBoolean("payment_force_auto_renew");
    term.paymentIsCustomPriceAvailable = json.optBoolean("payment_is_custom_price_available");
    term.paymentIsSubscription = json.optBoolean("payment_is_subscription");
    term.paymentHasFreeTrial = json.optBoolean("payment_has_free_trial");
    term.paymentNewCustomersOnly = json.optBoolean("payment_new_customers_only");
    term.paymentTrialNewCustomersOnly = json.optBoolean("payment_trial_new_customers_only");
    term.paymentAllowPromoCodes = json.optBoolean("payment_allow_promo_codes");
    term.paymentRenewGracePeriod = json.optInt("payment_renew_grace_period");
    term.paymentAllowGift = json.optBoolean("payment_allow_gift");
    term.paymentCurrency = json.optString("payment_currency");
    term.customRequireUser = json.optBoolean("custom_require_user");
    term.customDefaultAccessPeriod = json.optInt("custom_default_access_period");
    term.adviewVastUrl = json.optString("adview_vast_url");
    term.adviewAccessPeriod = json.optInt("adview_access_period");
    term.registrationAccessPeriod = json.optInt("registration_access_period");
    term.registrationGracePeriod = json.optInt("registration_grace_period");
    term.externalApiId = json.optString("external_api_id");
    term.externalApiName = json.optString("external_api_name");
    term.evtVerificationPeriod = json.optInt("evt_verification_period");
    term.evtFixedTimeAccessPeriod = json.optInt("evt_fixed_time_access_period");
    term.evtGracePeriod = json.optInt("evt_grace_period");
    term.evtItunesBundleId = json.optString("evt_itunes_bundle_id");
    term.evtItunesProductId = json.optString("evt_itunes_product_id");
    
    return term;
  }
}
