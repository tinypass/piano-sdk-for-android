package io.piano.android.api.publisher.model;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRef {
  
  private String email = null;
  private String firstName = null;
  private String lastName = null;
  private String uid = null;
  private Date createDate = null;

  
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
   * User's UID
   **/
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
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

  public static UserRef fromJson(JSONObject json) throws JSONException {
    UserRef userRef = new UserRef();

    userRef.email = json.optString("email");
    userRef.firstName = json.optString("first_name");
    userRef.lastName = json.optString("last_name");
    userRef.uid = json.optString("uid");
    userRef.createDate = new Date(json.optLong("create_date") * 1000);
    
    return userRef;
  }
}
