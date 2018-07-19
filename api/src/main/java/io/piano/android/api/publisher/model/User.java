package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class User {
  
  private String firstName = null;
  private String lastName = null;
  private String email = null;
  private String uid = null;
  private String image1 = null;
  private Date createDate = null;
  private Boolean resetPasswordEmailSent = null;
  private List<Map> customFields = null;
  
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
  
  /**
   * User's profile image
   **/
  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1;
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
  
  /**
   * Is reset password email sent
   **/
  public Boolean getResetPasswordEmailSent() {
    return resetPasswordEmailSent;
  }

  public void setResetPasswordEmailSent(Boolean resetPasswordEmailSent) {
    this.resetPasswordEmailSent = resetPasswordEmailSent;
  }
  
  /**
   **/
  public List<Map> getCustomFields() {
    return customFields;
  }

  public void setCustomFields(List<Map> customFields) {
    this.customFields = customFields;
  }
  
  public static User fromJson(JSONObject json) throws JSONException {
    User user = new User();

    user.firstName = json.optString("first_name");
    user.lastName = json.optString("last_name");
    user.email = json.optString("email");
    user.uid = json.optString("uid");
    user.image1 = json.optString("image1");
    user.createDate = new Date(json.optLong("create_date") * 1000);
    user.resetPasswordEmailSent = json.optBoolean("reset_password_email_sent");
    user.customFields = new ArrayList<>();
    JSONArray customFieldsJsonArray = json.optJSONArray("custom_fields");
    int customFieldsLength = customFieldsJsonArray.length();
    for (int ii = 0; ii < customFieldsLength; ii++) {
      Map map = new HashMap();
      JSONObject jsonObject = customFieldsJsonArray.optJSONObject(ii);
      Iterator<String> keys = jsonObject.keys();
      while (keys.hasNext()) {
          String key = keys.next();
          map.put(key, jsonObject.get(key));
      }
      user.customFields.add(map);
    }
    
    return user;
  }
}
