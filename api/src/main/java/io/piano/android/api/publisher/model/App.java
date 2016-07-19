package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App {
  
  private String aid = null;
  private String defaultLang = null;
  private String details = null;
  private String email = null;
  private String name = null;
  private String userProvider = null;
  private String url = null;
  private String logo1 = null;
  private String logo2 = null;
  private String state = null;
  private String privateKey = null;
  private String apiToken = null;
  
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
   * Default language
   **/
  public String getDefaultLang() {
    return defaultLang;
  }

  public void setDefaultLang(String defaultLang) {
    this.defaultLang = defaultLang;
  }
  
  /**
   * Application details
   **/
  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }
  
  /**
   * Email address associated with this app
   **/
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * Application name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * User token provider
   **/
  public String getUserProvider() {
    return userProvider;
  }

  public void setUserProvider(String userProvider) {
    this.userProvider = userProvider;
  }
  
  /**
   * Application website
   **/
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  /**
   * Primary image displayed within the dashboard
   **/
  public String getLogo1() {
    return logo1;
  }

  public void setLogo1(String logo1) {
    this.logo1 = logo1;
  }
  
  /**
   * Secondary image dispalyed within the ticket
   **/
  public String getLogo2() {
    return logo2;
  }

  public void setLogo2(String logo2) {
    this.logo2 = logo2;
  }
  
  /**
   * Current state of the app
   **/
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  /**
   * The app's private key
   **/
  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }
  
  /**
   * API token
   **/
  public String getApiToken() {
    return apiToken;
  }

  public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
  }
  
  public static App fromJson(JSONObject json) throws JSONException {
    App app = new App();

    app.aid = json.optString("aid");
    app.defaultLang = json.optString("default_lang");
    app.details = json.optString("details");
    app.email = json.optString("email");
    app.name = json.optString("name");
    app.userProvider = json.optString("user_provider");
    app.url = json.optString("url");
    app.logo1 = json.optString("logo1");
    app.logo2 = json.optString("logo2");
    app.state = json.optString("state");
    app.privateKey = json.optString("private_key");
    app.apiToken = json.optString("api_token");
    
    return app;
  }
}
