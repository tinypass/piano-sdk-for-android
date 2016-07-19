package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Resource;
import io.piano.android.api.publisher.model.User;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Access {
  
  private String accessId = null;
  private Boolean granted = null;
  private Resource resource = null;
  private User user = null;
  private Date expireDate = null;
  
  /**
   * The access id
   **/
  public String getAccessId() {
    return accessId;
  }

  public void setAccessId(String accessId) {
    this.accessId = accessId;
  }
  
  /**
   * Granted == true if the user has access
   **/
  public Boolean getGranted() {
    return granted;
  }

  public void setGranted(Boolean granted) {
    this.granted = granted;
  }
  
  /**
   * The resource
   **/
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }
  
  /**
   * The user
   **/
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  /**
   * The access item expire date; null means unlimited
   **/
  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }
  
  public static Access fromJson(JSONObject json) throws JSONException {
    Access access = new Access();

    access.accessId = json.optString("access_id");
    access.granted = json.optBoolean("granted");
    access.resource = Resource.fromJson(json.optJSONObject("resource"));
    access.user = User.fromJson(json.optJSONObject("user"));
    access.expireDate = new Date(json.optLong("expire_date") * 1000);
    
    return access;
  }
}
