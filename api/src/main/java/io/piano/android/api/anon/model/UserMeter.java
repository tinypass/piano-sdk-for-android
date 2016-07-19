package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserMeter {
  
  private Integer paywallId = null;
  private Integer views = null;
  private Integer viewsLeft = null;
  private Integer maxViews = null;
  private String cookieName = null;
  private String cookieDomain = null;
  private String cookieValue = null;
  private Integer cookieExpires = null;
  private Boolean trackPageView = null;
  private String state = null;
  private String offerId = null;
  private String curtainTemplateId = null;
  private String reminderTemplateId = null;
  private Boolean showCloseButton = null;
  private Boolean showReminder = null;
  private Integer expires = null;
  private Boolean canRenew = null;
  private Integer renewalDaysRemaining = null;
  private String countryCode = null;
  private String region = null;
  private String reason = null;
  private String meterName = null;
  
  /**
   * The paywall id
   **/
  public Integer getPaywallId() {
    return paywallId;
  }

  public void setPaywallId(Integer paywallId) {
    this.paywallId = paywallId;
  }
  
  /**
   * Current user meter views
   **/
  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }
  
  /**
   * How many views left for this meter
   **/
  public Integer getViewsLeft() {
    return viewsLeft;
  }

  public void setViewsLeft(Integer viewsLeft) {
    this.viewsLeft = viewsLeft;
  }
  
  /**
   * How many views total
   **/
  public Integer getMaxViews() {
    return maxViews;
  }

  public void setMaxViews(Integer maxViews) {
    this.maxViews = maxViews;
  }
  
  /**
   * User meter cookie name
   **/
  public String getCookieName() {
    return cookieName;
  }

  public void setCookieName(String cookieName) {
    this.cookieName = cookieName;
  }
  
  /**
   * User meter cookie domain
   **/
  public String getCookieDomain() {
    return cookieDomain;
  }

  public void setCookieDomain(String cookieDomain) {
    this.cookieDomain = cookieDomain;
  }
  
  /**
   * User meter cookie value
   **/
  public String getCookieValue() {
    return cookieValue;
  }

  public void setCookieValue(String cookieValue) {
    this.cookieValue = cookieValue;
  }
  
  /**
   * When the meter could should expire
   **/
  public Integer getCookieExpires() {
    return cookieExpires;
  }

  public void setCookieExpires(Integer cookieExpires) {
    this.cookieExpires = cookieExpires;
  }
  
  /**
   * Track page views
   **/
  public Boolean getTrackPageView() {
    return trackPageView;
  }

  public void setTrackPageView(Boolean trackPageView) {
    this.trackPageView = trackPageView;
  }
  
  /**
   * Meter state
   **/
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  /**
   * The offer ID
   **/
  public String getOfferId() {
    return offerId;
  }

  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }
  
  /**
   * The template id of the curtain
   **/
  public String getCurtainTemplateId() {
    return curtainTemplateId;
  }

  public void setCurtainTemplateId(String curtainTemplateId) {
    this.curtainTemplateId = curtainTemplateId;
  }
  
  /**
   * The template id of the reminder
   **/
  public String getReminderTemplateId() {
    return reminderTemplateId;
  }

  public void setReminderTemplateId(String reminderTemplateId) {
    this.reminderTemplateId = reminderTemplateId;
  }
  
  /**
   * Whether to show the close button
   **/
  public Boolean getShowCloseButton() {
    return showCloseButton;
  }

  public void setShowCloseButton(Boolean showCloseButton) {
    this.showCloseButton = showCloseButton;
  }
  
  /**
   * Whether to show the reminder
   **/
  public Boolean getShowReminder() {
    return showReminder;
  }

  public void setShowReminder(Boolean showReminder) {
    this.showReminder = showReminder;
  }
  
  /**
   * Meter expiration
   **/
  public Integer getExpires() {
    return expires;
  }

  public void setExpires(Integer expires) {
    this.expires = expires;
  }
  
  /**
   * Whether renewal is supported
   **/
  public Boolean getCanRenew() {
    return canRenew;
  }

  public void setCanRenew(Boolean canRenew) {
    this.canRenew = canRenew;
  }
  
  /**
   * Renewal days remaining
   **/
  public Integer getRenewalDaysRemaining() {
    return renewalDaysRemaining;
  }

  public void setRenewalDaysRemaining(Integer renewalDaysRemaining) {
    this.renewalDaysRemaining = renewalDaysRemaining;
  }
  
  /**
   * Country code
   **/
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  
  /**
   * Region
   **/
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }
  
  /**
   * Reason
   **/
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
  
  /**
   * Current meter name
   **/
  public String getMeterName() {
    return meterName;
  }

  public void setMeterName(String meterName) {
    this.meterName = meterName;
  }
  
  public static UserMeter fromJson(JSONObject json) throws JSONException {
    UserMeter userMeter = new UserMeter();

    userMeter.paywallId = json.optInt("paywall_id");
    userMeter.views = json.optInt("views");
    userMeter.viewsLeft = json.optInt("views_left");
    userMeter.maxViews = json.optInt("max_views");
    userMeter.cookieName = json.optString("cookie_name");
    userMeter.cookieDomain = json.optString("cookie_domain");
    userMeter.cookieValue = json.optString("cookie_value");
    userMeter.cookieExpires = json.optInt("cookie_expires");
    userMeter.trackPageView = json.optBoolean("track_page_view");
    userMeter.state = json.optString("state");
    userMeter.offerId = json.optString("offer_id");
    userMeter.curtainTemplateId = json.optString("curtain_template_id");
    userMeter.reminderTemplateId = json.optString("reminder_template_id");
    userMeter.showCloseButton = json.optBoolean("show_close_button");
    userMeter.showReminder = json.optBoolean("show_reminder");
    userMeter.expires = json.optInt("expires");
    userMeter.canRenew = json.optBoolean("can_renew");
    userMeter.renewalDaysRemaining = json.optInt("renewal_days_remaining");
    userMeter.countryCode = json.optString("country_code");
    userMeter.region = json.optString("region");
    userMeter.reason = json.optString("reason");
    userMeter.meterName = json.optString("meter_name");
    
    return userMeter;
  }
}
