package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferTemplate {
  
  private String offerTemplateId = null;
  private Boolean isStatic = null;
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
  private String categoryId = null;
  private String thumbnailImageUrl = null;
  private String liveThumbnailImageUrl = null;
  private String status = null;
  private Boolean isPublished = null;
  private Integer countVariants = null;
  private List<OfferTemplateVariant> variantList = null;
  private Date archivedDate = null;
  private User archivedBy = null;
  private List<OfferTemplateContentField> contentFieldList = null;
  
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
   * Is static template
   **/
  public Boolean getIsStatic() {
    return isStatic;
  }

  public void setIsStatic(Boolean isStatic) {
    this.isStatic = isStatic;
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
   * The id of Category
   **/
  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
  
  /**
   * Thumbnail image URL
   **/
  public String getThumbnailImageUrl() {
    return thumbnailImageUrl;
  }

  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }
  
  /**
   * Live thumbnail image URL
   **/
  public String getLiveThumbnailImageUrl() {
    return liveThumbnailImageUrl;
  }

  public void setLiveThumbnailImageUrl(String liveThumbnailImageUrl) {
    this.liveThumbnailImageUrl = liveThumbnailImageUrl;
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
   * Published or not
   **/
  public Boolean getIsPublished() {
    return isPublished;
  }

  public void setIsPublished(Boolean isPublished) {
    this.isPublished = isPublished;
  }
  
  /**
   * The number of variants of the template
   **/
  public Integer getCountVariants() {
    return countVariants;
  }

  public void setCountVariants(Integer countVariants) {
    this.countVariants = countVariants;
  }
  
  /**
   **/
  public List<OfferTemplateVariant> getVariantList() {
    return variantList;
  }

  public void setVariantList(List<OfferTemplateVariant> variantList) {
    this.variantList = variantList;
  }
  
  /**
   * The archived date
   **/
  public Date getArchivedDate() {
    return archivedDate;
  }

  public void setArchivedDate(Date archivedDate) {
    this.archivedDate = archivedDate;
  }
  
  /**
   **/
  public User getArchivedBy() {
    return archivedBy;
  }

  public void setArchivedBy(User archivedBy) {
    this.archivedBy = archivedBy;
  }
  
  /**
   **/
  public List<OfferTemplateContentField> getContentFieldList() {
    return contentFieldList;
  }

  public void setContentFieldList(List<OfferTemplateContentField> contentFieldList) {
    this.contentFieldList = contentFieldList;
  }
  
  public static OfferTemplate fromJson(JSONObject json) throws JSONException {
    OfferTemplate offerTemplate = new OfferTemplate();

    offerTemplate.offerTemplateId = json.optString("offer_template_id");
    offerTemplate.isStatic = json.optBoolean("is_static");
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
    offerTemplate.categoryId = json.optString("category_id");
    offerTemplate.thumbnailImageUrl = json.optString("thumbnail_image_url");
    offerTemplate.liveThumbnailImageUrl = json.optString("live_thumbnail_image_url");
    offerTemplate.status = json.optString("status");
    offerTemplate.isPublished = json.optBoolean("is_published");
    offerTemplate.countVariants = json.optInt("count_variants");
    offerTemplate.variantList = new ArrayList<>();
    JSONArray variantListJsonArray = json.optJSONArray("variant_list");
    int variantListLength = variantListJsonArray.length();
    for (int ii = 0; ii < variantListLength; ii++) {
      offerTemplate.variantList.add(OfferTemplateVariant.fromJson(variantListJsonArray.optJSONObject(ii)));
    }
    offerTemplate.archivedDate = new Date(json.optLong("archived_date") * 1000);
    offerTemplate.archivedBy = User.fromJson(json.optJSONObject("archived_by"));
    offerTemplate.contentFieldList = new ArrayList<>();
    JSONArray contentFieldListJsonArray = json.optJSONArray("content_field_list");
    int contentFieldListLength = contentFieldListJsonArray.length();
    for (int ii = 0; ii < contentFieldListLength; ii++) {
      offerTemplate.contentFieldList.add(OfferTemplateContentField.fromJson(contentFieldListJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplate;
  }
}
