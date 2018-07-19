package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferTemplateVariant {
  
  private String offerTemplateVariantId = null;
  private String offerTemplateId = null;
  private String name = null;
  private String description = null;
  private String liveThumbnailImageUrl = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private TemplateUserModel createBy = null;
  private Date updateDate = null;
  private TemplateUserModel updateBy = null;
  private Date archivedDate = null;
  private TemplateUserModel archivedBy = null;
  private String status = null;
  private List<OfferTemplateContentField> contentFieldList = null;
  
  /**
   * Offer template variant ID
   **/
  public String getOfferTemplateVariantId() {
    return offerTemplateVariantId;
  }

  public void setOfferTemplateVariantId(String offerTemplateVariantId) {
    this.offerTemplateVariantId = offerTemplateVariantId;
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
   * Live thumbnail image URL
   **/
  public String getLiveThumbnailImageUrl() {
    return liveThumbnailImageUrl;
  }

  public void setLiveThumbnailImageUrl(String liveThumbnailImageUrl) {
    this.liveThumbnailImageUrl = liveThumbnailImageUrl;
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
  public TemplateUserModel getCreateBy() {
    return createBy;
  }

  public void setCreateBy(TemplateUserModel createBy) {
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
  public TemplateUserModel getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(TemplateUserModel updateBy) {
    this.updateBy = updateBy;
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
  public TemplateUserModel getArchivedBy() {
    return archivedBy;
  }

  public void setArchivedBy(TemplateUserModel archivedBy) {
    this.archivedBy = archivedBy;
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
   **/
  public List<OfferTemplateContentField> getContentFieldList() {
    return contentFieldList;
  }

  public void setContentFieldList(List<OfferTemplateContentField> contentFieldList) {
    this.contentFieldList = contentFieldList;
  }
  
  public static OfferTemplateVariant fromJson(JSONObject json) throws JSONException {
    OfferTemplateVariant offerTemplateVariant = new OfferTemplateVariant();

    offerTemplateVariant.offerTemplateVariantId = json.optString("offer_template_variant_id");
    offerTemplateVariant.offerTemplateId = json.optString("offer_template_id");
    offerTemplateVariant.name = json.optString("name");
    offerTemplateVariant.description = json.optString("description");
    offerTemplateVariant.liveThumbnailImageUrl = json.optString("live_thumbnail_image_url");
    offerTemplateVariant.deleted = json.optBoolean("deleted");
    offerTemplateVariant.createDate = new Date(json.optLong("create_date") * 1000);
    offerTemplateVariant.createBy = TemplateUserModel.fromJson(json.optJSONObject("create_by"));
    offerTemplateVariant.updateDate = new Date(json.optLong("update_date") * 1000);
    offerTemplateVariant.updateBy = TemplateUserModel.fromJson(json.optJSONObject("update_by"));
    offerTemplateVariant.archivedDate = new Date(json.optLong("archived_date") * 1000);
    offerTemplateVariant.archivedBy = TemplateUserModel.fromJson(json.optJSONObject("archived_by"));
    offerTemplateVariant.status = json.optString("status");
    offerTemplateVariant.contentFieldList = new ArrayList<>();
    JSONArray contentFieldListJsonArray = json.optJSONArray("content_field_list");
    int contentFieldListLength = contentFieldListJsonArray.length();
    for (int ii = 0; ii < contentFieldListLength; ii++) {
      offerTemplateVariant.contentFieldList.add(OfferTemplateContentField.fromJson(contentFieldListJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplateVariant;
  }
}
