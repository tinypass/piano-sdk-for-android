package io.piano.android.api.anon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
  
  private String uid = null;
  private String email = null;
  private String firstName = null;
  private String lastName = null;
  
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
  
  public static User fromJson(JSONObject json) throws JSONException {
    User user = new User();

    user.uid = json.optString("uid");
    user.email = json.optString("email");
    user.firstName = json.optString("first_name");
    user.lastName = json.optString("last_name");
    
    return user;
  }
}
