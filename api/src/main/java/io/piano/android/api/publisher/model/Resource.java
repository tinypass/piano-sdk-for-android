package io.piano.android.api.publisher.model;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Resource {
  
  private String rid = null;
  private String aid = null;
  private Boolean deleted = null;
  private Boolean disabled = null;
  private Date createDate = null;
  private Date updateDate = null;
  private Date publishDate = null;
  private String name = null;
  private String description = null;
  private String imageUrl = null;
  private String type = null;
  private String bundleType = null;
  private String purchaseUrl = null;
  private String resourceUrl = null;

  
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
   * If the object is deleted
   **/
  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
  /**
   * If the object is disabled
   **/
  public Boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
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
   * The update date
   **/
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
  /**
   * The publish date
   **/
  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
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
  /**
   * Type of resource
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  /**
   * Type of resource bundle
   **/
  public String getBundleType() {
    return bundleType;
  }

  public void setBundleType(String bundleType) {
    this.bundleType = bundleType;
  }
  /**
   * Purchase page URL
   **/
  public String getPurchaseUrl() {
    return purchaseUrl;
  }

  public void setPurchaseUrl(String purchaseUrl) {
    this.purchaseUrl = purchaseUrl;
  }
  /**
   * Resource URL
   **/
  public String getResourceUrl() {
    return resourceUrl;
  }

  public void setResourceUrl(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }

  public static Resource fromJson(JSONObject json) throws JSONException {
    Resource resource = new Resource();

    resource.rid = json.optString("rid");
    resource.aid = json.optString("aid");
    resource.deleted = json.optBoolean("deleted");
    resource.disabled = json.optBoolean("disabled");
    resource.createDate = new Date(json.optLong("create_date") * 1000);
    resource.updateDate = new Date(json.optLong("update_date") * 1000);
    resource.publishDate = new Date(json.optLong("publish_date") * 1000);
    resource.name = json.optString("name");
    resource.description = json.optString("description");
    resource.imageUrl = json.optString("image_url");
    resource.type = json.optString("type");
    resource.bundleType = json.optString("bundle_type");
    resource.purchaseUrl = json.optString("purchase_url");
    resource.resourceUrl = json.optString("resource_url");
    
    return resource;
  }
}
