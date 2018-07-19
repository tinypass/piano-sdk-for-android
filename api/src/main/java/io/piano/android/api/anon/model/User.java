package io.piano.android.api.anon.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class User {
  
  private String uid = null;
  private String email = null;
  private String firstName = null;
  private String lastName = null;
  private Date createDate = null;
  
  /**
   * User's UID
   **/
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
  
  /**
   * User's email address
   **/
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * User's first name
   **/
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * User's last name
   **/
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * User creation date
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  public static User fromJson(JSONObject json) throws JSONException {
    User user = new User();

    user.uid = json.optString("uid");
    user.email = json.optString("email");
    user.firstName = json.optString("first_name");
    user.lastName = json.optString("last_name");
    user.createDate = new Date(json.optLong("create_date") * 1000);
    
    return user;
  }
}
