package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Composer;
import io.piano.android.api.publisher.model.ContentAlgorithm;
import io.piano.android.api.publisher.model.MyAccount;
import io.piano.android.api.publisher.model.SubscriptionRestrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppFeatures {
  
  private ContentAlgorithm contentAlgorithm = null;
  private MyAccount myAccount = null;
  private Composer composer = null;
  private SubscriptionRestrictions subscriptionRestrictions = null;
  private Boolean isPaymentMockEnabled = null;

  
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
   * Is Mock instead of real payment providers enabled
   **/
  public Boolean getIsPaymentMockEnabled() {
    return isPaymentMockEnabled;
  }

  public void setIsPaymentMockEnabled(Boolean isPaymentMockEnabled) {
    this.isPaymentMockEnabled = isPaymentMockEnabled;
  }

  public static AppFeatures fromJson(JSONObject json) throws JSONException {
    AppFeatures appFeatures = new AppFeatures();

    appFeatures.contentAlgorithm = ContentAlgorithm.fromJson(json.optJSONObject("content_algorithm"));
    appFeatures.myAccount = MyAccount.fromJson(json.optJSONObject("my_account"));
    appFeatures.composer = Composer.fromJson(json.optJSONObject("composer"));
    appFeatures.subscriptionRestrictions = SubscriptionRestrictions.fromJson(json.optJSONObject("subscription_restrictions"));
    appFeatures.isPaymentMockEnabled = json.optBoolean("is_payment_mock_enabled");
    
    return appFeatures;
  }
}
