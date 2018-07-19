package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AppFeatures {
  
  private ContentAlgorithm contentAlgorithm = null;
  private MyAccount myAccount = null;
  private Composer composer = null;
  private SubscriptionRestrictions subscriptionRestrictions = null;
  private RedemptionPage redemptionPage = null;
  private Boolean isPaymentMockEnabled = null;
  private Boolean isPublisherDashboardLocalizationEnabled = null;
  
  /**
   * Content algorithm enabled
   **/
  public ContentAlgorithm getContentAlgorithm() {
    return contentAlgorithm;
  }

  public void setContentAlgorithm(ContentAlgorithm contentAlgorithm) {
    this.contentAlgorithm = contentAlgorithm;
  }
  
  /**
   * My account enabled
   **/
  public MyAccount getMyAccount() {
    return myAccount;
  }

  public void setMyAccount(MyAccount myAccount) {
    this.myAccount = myAccount;
  }
  
  /**
   * Composer enabled
   **/
  public Composer getComposer() {
    return composer;
  }

  public void setComposer(Composer composer) {
    this.composer = composer;
  }
  
  /**
   * What restrictions are appliable for the app
   **/
  public SubscriptionRestrictions getSubscriptionRestrictions() {
    return subscriptionRestrictions;
  }

  public void setSubscriptionRestrictions(SubscriptionRestrictions subscriptionRestrictions) {
    this.subscriptionRestrictions = subscriptionRestrictions;
  }
  
  /**
   * Is app allowed to use system redemption page
   **/
  public RedemptionPage getRedemptionPage() {
    return redemptionPage;
  }

  public void setRedemptionPage(RedemptionPage redemptionPage) {
    this.redemptionPage = redemptionPage;
  }
  
  /**
   * Is Mock instead of real payment providers enabled
   **/
  public Boolean getIsPaymentMockEnabled() {
    return isPaymentMockEnabled;
  }

  public void setIsPaymentMockEnabled(Boolean isPaymentMockEnabled) {
    this.isPaymentMockEnabled = isPaymentMockEnabled;
  }
  
  /**
   * Is publisher dashboard localization enabled
   **/
  public Boolean getIsPublisherDashboardLocalizationEnabled() {
    return isPublisherDashboardLocalizationEnabled;
  }

  public void setIsPublisherDashboardLocalizationEnabled(Boolean isPublisherDashboardLocalizationEnabled) {
    this.isPublisherDashboardLocalizationEnabled = isPublisherDashboardLocalizationEnabled;
  }
  
  public static AppFeatures fromJson(JSONObject json) throws JSONException {
    AppFeatures appFeatures = new AppFeatures();

    appFeatures.contentAlgorithm = ContentAlgorithm.fromJson(json.optJSONObject("content_algorithm"));
    appFeatures.myAccount = MyAccount.fromJson(json.optJSONObject("my_account"));
    appFeatures.composer = Composer.fromJson(json.optJSONObject("composer"));
    appFeatures.subscriptionRestrictions = SubscriptionRestrictions.fromJson(json.optJSONObject("subscription_restrictions"));
    appFeatures.redemptionPage = RedemptionPage.fromJson(json.optJSONObject("redemption_page"));
    appFeatures.isPaymentMockEnabled = json.optBoolean("is_payment_mock_enabled");
    appFeatures.isPublisherDashboardLocalizationEnabled = json.optBoolean("is_publisher_dashboard_localization_enabled");
    
    return appFeatures;
  }
}
