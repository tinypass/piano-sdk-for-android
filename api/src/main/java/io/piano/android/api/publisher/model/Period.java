package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Period {
  
  private String name = null;
  private String periodId = null;
  private Date sellDate = null;
  private Date beginDate = null;
  private Date endDate = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private Date updateDate = null;
  private Boolean isSaleStarted = null;
  private Boolean isActive = null;
  
  /**
   * The period name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * The period ID
   **/
  public String getPeriodId() {
    return periodId;
  }

  public void setPeriodId(String periodId) {
    this.periodId = periodId;
  }
  
  /**
   * The period sell date
   **/
  public Date getSellDate() {
    return sellDate;
  }

  public void setSellDate(Date sellDate) {
    this.sellDate = sellDate;
  }
  
  /**
   * The period begin date
   **/
  public Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }
  
  /**
   * The period end date
   **/
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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
   * The update date
   **/
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
  
  /**
   * The sale is started for the period
   **/
  public Boolean getIsSaleStarted() {
    return isSaleStarted;
  }

  public void setIsSaleStarted(Boolean isSaleStarted) {
    this.isSaleStarted = isSaleStarted;
  }
  
  /**
   * Is period active. Period is in active state if the sell date is passed but end date is not reached
   **/
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }
  
  public static Period fromJson(JSONObject json) throws JSONException {
    Period period = new Period();

    period.name = json.optString("name");
    period.periodId = json.optString("period_id");
    period.sellDate = new Date(json.optLong("sell_date") * 1000);
    period.beginDate = new Date(json.optLong("begin_date") * 1000);
    period.endDate = new Date(json.optLong("end_date") * 1000);
    period.deleted = json.optBoolean("deleted");
    period.createDate = new Date(json.optLong("create_date") * 1000);
    period.updateDate = new Date(json.optLong("update_date") * 1000);
    period.isSaleStarted = json.optBoolean("is_sale_started");
    period.isActive = json.optBoolean("is_active");
    
    return period;
  }
}
