package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.User;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfferTemplate {
  
  private String offerTemplateId = null;
  private String aid = null;
  private String name = null;
  private String description = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private User createBy = null;
  private Date updateDate = null;
  private User updateBy = null;
  private Date publishDate = null;
  private Integer version = null;
  private String type = null;
  private String typeId = null;
  private Boolean isPublished = null;

  
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
   * The description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
   * The creation date
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  /**
   **/
  public User getCreateBy() {
    return createBy;
  }

  public void setCreateBy(User createBy) {
    this.createBy = createBy;
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
   **/
  public User getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(User updateBy) {
    this.updateBy = updateBy;
  }
  /**
   * publish_date
   **/
  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }
  /**
   * The version
   **/
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
  /**
   * The type 
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  /**
   * The type 
   **/
  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }
  /**
   * Published or not
   **/
  public Boolean getIsPublished() {
    return isPublished;
  }

  public void setIsPublished(Boolean isPublished) {
    this.isPublished = isPublished;
  }

  public static OfferTemplate fromJson(JSONObject json) throws JSONException {
    OfferTemplate offerTemplate = new OfferTemplate();

    offerTemplate.offerTemplateId = json.optString("offer_template_id");
    offerTemplate.aid = json.optString("aid");
    offerTemplate.name = json.optString("name");
    offerTemplate.description = json.optString("description");
    offerTemplate.deleted = json.optBoolean("deleted");
    offerTemplate.createDate = new Date(json.optLong("create_date") * 1000);
    offerTemplate.createBy = User.fromJson(json.optJSONObject("create_by"));
    offerTemplate.updateDate = new Date(json.optLong("update_date") * 1000);
    offerTemplate.updateBy = User.fromJson(json.optJSONObject("update_by"));
    offerTemplate.publishDate = new Date(json.optLong("publish_date") * 1000);
    offerTemplate.version = json.optInt("version");
    offerTemplate.type = json.optString("type");
    offerTemplate.typeId = json.optString("type_id");
    offerTemplate.isPublished = json.optBoolean("is_published");
    
    return offerTemplate;
  }
}
