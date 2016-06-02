package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Resource {
  
  private String rid = null;
  private String aid = null;
  private String name = null;
  private String description = null;
  private String imageUrl = null;

  
  /**
   * Unique id for resource
   **/
  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
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
   * The name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  /**
   * Resource description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  /**
   * Resource image URL
   **/
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public static Resource fromJson(JSONObject json) throws JSONException {
    Resource resource = new Resource();

    resource.rid = json.optString("rid");
    resource.aid = json.optString("aid");
    resource.name = json.optString("name");
    resource.description = json.optString("description");
    resource.imageUrl = json.optString("image_url");
    
    return resource;
  }
}
