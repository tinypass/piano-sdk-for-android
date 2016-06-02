package io.piano.android.api.user.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
  
  private String uid = null;
  private String firstName = null;
  private String lastName = null;
  private String email = null;
  private String image1 = null;

  
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
   * User's email address
   **/
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * User's profile image
   **/
  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1;
  }

  public static User fromJson(JSONObject json) throws JSONException {
    User user = new User();

    user.uid = json.optString("uid");
    user.firstName = json.optString("first_name");
    user.lastName = json.optString("last_name");
    user.email = json.optString("email");
    user.image1 = json.optString("image1");
    
    return user;
  }
}
