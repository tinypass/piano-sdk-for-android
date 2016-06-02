package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.User;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PromoCode {
  
  private String promoCodeId = null;
  private String promotionId = null;
  private String code = null;
  private String assignedEmail = null;
  private Date reserveDate = null;
  private String state = null;
  private User claimedUser = null;
  private Date claimedDate = null;
  private Date createDate = null;
  private String createBy = null;
  private Date updateDate = null;
  private String updateBy = null;
  private Boolean deleted = null;

  
  /**
   * Promo code id
   **/
  public String getPromoCodeId() {
    return promoCodeId;
  }

  public void setPromoCodeId(String promoCodeId) {
    this.promoCodeId = promoCodeId;
  }
  /**
   * Promotion id
   **/
  public String getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }
  /**
   * Promo code
   **/
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
  /**
   * Assigned email
   **/
  public String getAssignedEmail() {
    return assignedEmail;
  }

  public void setAssignedEmail(String assignedEmail) {
    this.assignedEmail = assignedEmail;
  }
  /**
   * Promo code reserve_date
   **/
  public Date getReserveDate() {
    return reserveDate;
  }

  public void setReserveDate(Date reserveDate) {
    this.reserveDate = reserveDate;
  }
  /**
   * Promo code state
   **/
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  /**
   * Promotion code claimed user
   **/
  public User getClaimedUser() {
    return claimedUser;
  }

  public void setClaimedUser(User claimedUser) {
    this.claimedUser = claimedUser;
  }
  /**
   * Promotion code claimed date
   **/
  public Date getClaimedDate() {
    return claimedDate;
  }

  public void setClaimedDate(Date claimedDate) {
    this.claimedDate = claimedDate;
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
   * The user who created the object
   **/
  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
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
   * The last user to update the object
   **/
  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
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

  public static PromoCode fromJson(JSONObject json) throws JSONException {
    PromoCode promoCode = new PromoCode();

    promoCode.promoCodeId = json.optString("promo_code_id");
    promoCode.promotionId = json.optString("promotion_id");
    promoCode.code = json.optString("code");
    promoCode.assignedEmail = json.optString("assigned_email");
    promoCode.reserveDate = new Date(json.optLong("reserve_date") * 1000);
    promoCode.state = json.optString("state");
    promoCode.claimedUser = User.fromJson(json.optJSONObject("claimed_user"));
    promoCode.claimedDate = new Date(json.optLong("claimed_date") * 1000);
    promoCode.createDate = new Date(json.optLong("create_date") * 1000);
    promoCode.createBy = json.optString("create_by");
    promoCode.updateDate = new Date(json.optLong("update_date") * 1000);
    promoCode.updateBy = json.optString("update_by");
    promoCode.deleted = json.optBoolean("deleted");
    
    return promoCode;
  }
}
