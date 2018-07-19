package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferTemplateVersion {
  
  private String offerTemplateId = null;
  private Boolean isStatic = null;
  private String aid = null;
  private String name = null;
  private String description = null;
  private String documentation = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private User createBy = null;
  private Date updateDate = null;
  private User updateBy = null;
  private String content1Type = null;
  private String content2Type = null;
  private String content1Value = null;
  private String content2Value = null;
  private Integer version = null;
  private Date publishDate = null;
  private String type = null;
  private String typeId = null;
  private String categoryId = null;
  private String thumbnailImageUrl = null;
  private String liveThumbnailImageUrl = null;
  private Boolean published = null;
  private List<ExternalCss> externalCssList = null;
  private Boolean hasPreview = null;
  private String status = null;
  private List<OfferTemplateVariant> variantList = null;
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
   * Offer Template documentation
   **/
  public String getDocumentation() {
    return documentation;
  }

  public void setDocumentation(String documentation) {
    this.documentation = documentation;
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
   * The content1 type
   **/
  public String getContent1Type() {
    return content1Type;
  }

  public void setContent1Type(String content1Type) {
    this.content1Type = content1Type;
  }
  
  /**
   * The content2 type
   **/
  public String getContent2Type() {
    return content2Type;
  }

  public void setContent2Type(String content2Type) {
    this.content2Type = content2Type;
  }
  
  /**
   * content1_value
   **/
  public String getContent1Value() {
    return content1Value;
  }

  public void setContent1Value(String content1Value) {
    this.content1Value = content1Value;
  }
  
  /**
   * content2_value
   **/
  public String getContent2Value() {
    return content2Value;
  }

  public void setContent2Value(String content2Value) {
    this.content2Value = content2Value;
  }
  
  /**
   * Template version
   **/
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
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
   * The type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * The id of type
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
   * Whether the template is published
   **/
  public Boolean getPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
  }
  
  /**
   **/
  public List<ExternalCss> getExternalCssList() {
    return externalCssList;
  }

  public void setExternalCssList(List<ExternalCss> externalCssList) {
    this.externalCssList = externalCssList;
  }
  
  /**
   * Whether template has preview
   **/
  public Boolean getHasPreview() {
    return hasPreview;
  }

  public void setHasPreview(Boolean hasPreview) {
    this.hasPreview = hasPreview;
  }
  
  /**
   * status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
   **/
  public List<OfferTemplateContentField> getContentFieldList() {
    return contentFieldList;
  }

  public void setContentFieldList(List<OfferTemplateContentField> contentFieldList) {
    this.contentFieldList = contentFieldList;
  }
  
  public static OfferTemplateVersion fromJson(JSONObject json) throws JSONException {
    OfferTemplateVersion offerTemplateVersion = new OfferTemplateVersion();

    offerTemplateVersion.offerTemplateId = json.optString("offer_template_id");
    offerTemplateVersion.isStatic = json.optBoolean("is_static");
    offerTemplateVersion.aid = json.optString("aid");
    offerTemplateVersion.name = json.optString("name");
    offerTemplateVersion.description = json.optString("description");
    offerTemplateVersion.documentation = json.optString("documentation");
    offerTemplateVersion.deleted = json.optBoolean("deleted");
    offerTemplateVersion.createDate = new Date(json.optLong("create_date") * 1000);
    offerTemplateVersion.createBy = User.fromJson(json.optJSONObject("create_by"));
    offerTemplateVersion.updateDate = new Date(json.optLong("update_date") * 1000);
    offerTemplateVersion.updateBy = User.fromJson(json.optJSONObject("update_by"));
    offerTemplateVersion.content1Type = json.optString("content1_type");
    offerTemplateVersion.content2Type = json.optString("content2_type");
    offerTemplateVersion.content1Value = json.optString("content1_value");
    offerTemplateVersion.content2Value = json.optString("content2_value");
    offerTemplateVersion.version = json.optInt("version");
    offerTemplateVersion.publishDate = new Date(json.optLong("publish_date") * 1000);
    offerTemplateVersion.type = json.optString("type");
    offerTemplateVersion.typeId = json.optString("type_id");
    offerTemplateVersion.categoryId = json.optString("category_id");
    offerTemplateVersion.thumbnailImageUrl = json.optString("thumbnail_image_url");
    offerTemplateVersion.liveThumbnailImageUrl = json.optString("live_thumbnail_image_url");
    offerTemplateVersion.published = json.optBoolean("published");
    offerTemplateVersion.externalCssList = new ArrayList<>();
    JSONArray externalCssListJsonArray = json.optJSONArray("external_css_list");
    int externalCssListLength = externalCssListJsonArray.length();
    for (int ii = 0; ii < externalCssListLength; ii++) {
      offerTemplateVersion.externalCssList.add(ExternalCss.fromJson(externalCssListJsonArray.optJSONObject(ii)));
    }
    offerTemplateVersion.hasPreview = json.optBoolean("has_preview");
    offerTemplateVersion.status = json.optString("status");
    offerTemplateVersion.variantList = new ArrayList<>();
    JSONArray variantListJsonArray = json.optJSONArray("variant_list");
    int variantListLength = variantListJsonArray.length();
    for (int ii = 0; ii < variantListLength; ii++) {
      offerTemplateVersion.variantList.add(OfferTemplateVariant.fromJson(variantListJsonArray.optJSONObject(ii)));
    }
    offerTemplateVersion.contentFieldList = new ArrayList<>();
    JSONArray contentFieldListJsonArray = json.optJSONArray("content_field_list");
    int contentFieldListLength = contentFieldListJsonArray.length();
    for (int ii = 0; ii < contentFieldListLength; ii++) {
      offerTemplateVersion.contentFieldList.add(OfferTemplateContentField.fromJson(contentFieldListJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplateVersion;
  }
}
