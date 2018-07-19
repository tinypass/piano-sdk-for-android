package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TemplateUserModel {
  
  private String firstName = null;
  private String lastName = null;
  private String email = null;
  private String uid = null;
  
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
   * User's UID
   **/
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
  
  public static TemplateUserModel fromJson(JSONObject json) throws JSONException {
    TemplateUserModel templateUserModel = new TemplateUserModel();

    templateUserModel.firstName = json.optString("first_name");
    templateUserModel.lastName = json.optString("last_name");
    templateUserModel.email = json.optString("email");
    templateUserModel.uid = json.optString("uid");
    
    return templateUserModel;
  }
}
