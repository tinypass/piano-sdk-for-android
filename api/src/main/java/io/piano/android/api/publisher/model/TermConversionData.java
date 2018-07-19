package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TermConversionData {
  
  private String aid = null;
  private String offerId = null;
  private String termId = null;
  private String offerTemplateId = null;
  private String templateId = null;
  private String uid = null;
  private String userCountry = null;
  private String userRegion = null;
  private String userCity = null;
  private String zip = null;
  private String latitude = null;
  private String longitude = null;
  private String userAgent = null;
  private String locale = null;
  private String hour = null;
  private String url = null;
  private String browser = null;
  private String platform = null;
  private String operatingSystem = null;
  private String tags = null;
  private String contentCreated = null;
  private String contentAuthor = null;
  private String contentSection = null;
  private List<String> campaigns = null;
  
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
   * The offer ID
   **/
  public String getOfferId() {
    return offerId;
  }

  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }
  
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
   * Offer Template ID
   **/
  public String getOfferTemplateId() {
    return offerTemplateId;
  }

  public void setOfferTemplateId(String offerTemplateId) {
    this.offerTemplateId = offerTemplateId;
  }
  
  /**
   * Template ID
   **/
  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }
  
  /**
   * User's uid
   **/
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
  
  /**
   * The country of user who has converted the term
   **/
  public String getUserCountry() {
    return userCountry;
  }

  public void setUserCountry(String userCountry) {
    this.userCountry = userCountry;
  }
  
  /**
   * The region of user who has converted the term
   **/
  public String getUserRegion() {
    return userRegion;
  }

  public void setUserRegion(String userRegion) {
    this.userRegion = userRegion;
  }
  
  /**
   * The city of user who has converted the term
   **/
  public String getUserCity() {
    return userCity;
  }

  public void setUserCity(String userCity) {
    this.userCity = userCity;
  }
  
  /**
   * The zip of user who has converted the term
   **/
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
  
  /**
   * The latitude of user position
   **/
  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }
  
  /**
   * The longitude of user position
   **/
  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }
  
  /**
   * The user agent of user who has converted the term
   **/
  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
  
  /**
   * The locale of user
   **/
  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
  
  /**
   * The hour of the term conversion
   **/
  public String getHour() {
    return hour;
  }

  public void setHour(String hour) {
    this.hour = hour;
  }
  
  /**
   * The URL of the page
   **/
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  /**
   * The browser with which the conversion was made
   **/
  public String getBrowser() {
    return browser;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }
  
  /**
   * The platform with which the conversion was made
   **/
  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }
  
  /**
   * The operating system with which the conversion was made
   **/
  public String getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }
  
  /**
   * The tags of the page
   **/
  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }
  
  /**
   * When the content was published
   **/
  public String getContentCreated() {
    return contentCreated;
  }

  public void setContentCreated(String contentCreated) {
    this.contentCreated = contentCreated;
  }
  
  /**
   * The content author
   **/
  public String getContentAuthor() {
    return contentAuthor;
  }

  public void setContentAuthor(String contentAuthor) {
    this.contentAuthor = contentAuthor;
  }
  
  /**
   * The content section
   **/
  public String getContentSection() {
    return contentSection;
  }

  public void setContentSection(String contentSection) {
    this.contentSection = contentSection;
  }
  
  /**
   **/
  public List<String> getCampaigns() {
    return campaigns;
  }

  public void setCampaigns(List<String> campaigns) {
    this.campaigns = campaigns;
  }
  
  public static TermConversionData fromJson(JSONObject json) throws JSONException {
    TermConversionData termConversionData = new TermConversionData();

    termConversionData.aid = json.optString("aid");
    termConversionData.offerId = json.optString("offer_id");
    termConversionData.termId = json.optString("term_id");
    termConversionData.offerTemplateId = json.optString("offer_template_id");
    termConversionData.templateId = json.optString("template_id");
    termConversionData.uid = json.optString("uid");
    termConversionData.userCountry = json.optString("user_country");
    termConversionData.userRegion = json.optString("user_region");
    termConversionData.userCity = json.optString("user_city");
    termConversionData.zip = json.optString("zip");
    termConversionData.latitude = json.optString("latitude");
    termConversionData.longitude = json.optString("longitude");
    termConversionData.userAgent = json.optString("user_agent");
    termConversionData.locale = json.optString("locale");
    termConversionData.hour = json.optString("hour");
    termConversionData.url = json.optString("url");
    termConversionData.browser = json.optString("browser");
    termConversionData.platform = json.optString("platform");
    termConversionData.operatingSystem = json.optString("operating_system");
    termConversionData.tags = json.optString("tags");
    termConversionData.contentCreated = json.optString("content_created");
    termConversionData.contentAuthor = json.optString("content_author");
    termConversionData.contentSection = json.optString("content_section");
    termConversionData.campaigns = new ArrayList<>();
    JSONArray campaignsJsonArray = json.optJSONArray("campaigns");
    int campaignsLength = campaignsJsonArray.length();
    for (int ii = 0; ii < campaignsLength; ii++) {
      termConversionData.campaigns.add(campaignsJsonArray.optString(ii));
    }
    
    return termConversionData;
  }
}
