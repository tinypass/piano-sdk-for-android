package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAddress {
  
  private String userAddressId = null;
  private Region region = null;
  private Country country = null;
  private String city = null;
  private String postalCode = null;
  private String companyName = null;
  private String firstName = null;
  private String lastName = null;
  private String address1 = null;
  private String address2 = null;
  private String phone = null;
  private String additionalFields = null;
  
  /**
   * Public id of specific user address
   **/
  public String getUserAddressId() {
    return userAddressId;
  }

  public void setUserAddressId(String userAddressId) {
    this.userAddressId = userAddressId;
  }
  
  /**
   **/
  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }
  
  /**
   **/
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
  
  /**
   * Name of the city
   **/
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
  
  /**
   * Postal code
   **/
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  /**
   * company_name
   **/
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
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
   * First address of the user
   **/
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  
  /**
   * Second address of the user
   **/
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  
  /**
   * User's phone
   **/
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  /**
   * Additional address fields (json)
   **/
  public String getAdditionalFields() {
    return additionalFields;
  }

  public void setAdditionalFields(String additionalFields) {
    this.additionalFields = additionalFields;
  }
  
  public static UserAddress fromJson(JSONObject json) throws JSONException {
    UserAddress userAddress = new UserAddress();

    userAddress.userAddressId = json.optString("user_address_id");
    userAddress.region = Region.fromJson(json.optJSONObject("region"));
    userAddress.country = Country.fromJson(json.optJSONObject("country"));
    userAddress.city = json.optString("city");
    userAddress.postalCode = json.optString("postal_code");
    userAddress.companyName = json.optString("company_name");
    userAddress.firstName = json.optString("first_name");
    userAddress.lastName = json.optString("last_name");
    userAddress.address1 = json.optString("address1");
    userAddress.address2 = json.optString("address2");
    userAddress.phone = json.optString("phone");
    userAddress.additionalFields = json.optString("additional_fields");
    
    return userAddress;
  }
}
