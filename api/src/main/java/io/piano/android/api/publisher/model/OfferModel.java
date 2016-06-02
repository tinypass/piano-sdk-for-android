package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Term;
import io.piano.android.api.publisher.model.User;
import java.util.*;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfferModel {
  
  private String aid = null;
  private String name = null;
  private String offerId = null;
  private String status = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private User createBy = null;
  private Date updateDate = null;
  private User updateBy = null;
  private List<Term> terms = null;

  
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
   * The offer name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
   * The status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
   **/
  public List<Term> getTerms() {
    return terms;
  }

  public void setTerms(List<Term> terms) {
    this.terms = terms;
  }

  public static OfferModel fromJson(JSONObject json) throws JSONException {
    OfferModel offerModel = new OfferModel();

    offerModel.aid = json.optString("aid");
    offerModel.name = json.optString("name");
    offerModel.offerId = json.optString("offer_id");
    offerModel.status = json.optString("status");
    offerModel.deleted = json.optBoolean("deleted");
    offerModel.createDate = new Date(json.optLong("create_date") * 1000);
    offerModel.createBy = User.fromJson(json.optJSONObject("create_by"));
    offerModel.updateDate = new Date(json.optLong("update_date") * 1000);
    offerModel.updateBy = User.fromJson(json.optJSONObject("update_by"));
    offerModel.terms = new ArrayList<>();
    JSONArray termsJsonArray = json.optJSONArray("terms");
    int termsLength = termsJsonArray.length();
    for (int ii = 0; ii < termsLength; ii++) {
      offerModel.terms.add(Term.fromJson(termsJsonArray.optJSONObject(ii)));
    }
    
    return offerModel;
  }
}
