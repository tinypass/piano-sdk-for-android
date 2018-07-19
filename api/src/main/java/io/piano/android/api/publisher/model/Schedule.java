package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
  
  private String aid = null;
  private String name = null;
  private String scheduleId = null;
  private Boolean deleted = null;
  private Date createDate = null;
  private Date updateDate = null;
  private List<Period> periods = null;
  
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
   * The schedule name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * The schedule ID
   **/
  public String getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
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
   **/
  public List<Period> getPeriods() {
    return periods;
  }

  public void setPeriods(List<Period> periods) {
    this.periods = periods;
  }
  
  public static Schedule fromJson(JSONObject json) throws JSONException {
    Schedule schedule = new Schedule();

    schedule.aid = json.optString("aid");
    schedule.name = json.optString("name");
    schedule.scheduleId = json.optString("schedule_id");
    schedule.deleted = json.optBoolean("deleted");
    schedule.createDate = new Date(json.optLong("create_date") * 1000);
    schedule.updateDate = new Date(json.optLong("update_date") * 1000);
    schedule.periods = new ArrayList<>();
    JSONArray periodsJsonArray = json.optJSONArray("periods");
    int periodsLength = periodsJsonArray.length();
    for (int ii = 0; ii < periodsLength; ii++) {
      schedule.periods.add(Period.fromJson(periodsJsonArray.optJSONObject(ii)));
    }
    
    return schedule;
  }
}
