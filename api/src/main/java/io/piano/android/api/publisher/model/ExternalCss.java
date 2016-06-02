package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExternalCss {
  
  private String title = null;
  private String url = null;
  private String status = null;
  private Integer position = null;
  private String externalCssId = null;

  
  /**
   * The title
   **/
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
   * The status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  /**
   * The position
   **/
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }
  /**
   * External css id
   **/
  public String getExternalCssId() {
    return externalCssId;
  }

  public void setExternalCssId(String externalCssId) {
    this.externalCssId = externalCssId;
  }

  public static ExternalCss fromJson(JSONObject json) throws JSONException {
    ExternalCss externalCss = new ExternalCss();

    externalCss.title = json.optString("title");
    externalCss.url = json.optString("url");
    externalCss.status = json.optString("status");
    externalCss.position = json.optInt("position");
    externalCss.externalCssId = json.optString("external_css_id");
    
    return externalCss;
  }
}
