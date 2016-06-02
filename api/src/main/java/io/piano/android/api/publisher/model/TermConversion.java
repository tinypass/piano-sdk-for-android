package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.Access;
import io.piano.android.api.publisher.model.Term;
import io.piano.android.api.publisher.model.UserPayment;
import io.piano.android.api.publisher.model.UserSubscription;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TermConversion {
  
  private String termConversionId = null;
  private Term term = null;
  private String type = null;
  private String aid = null;
  private Access userAccess = null;
  private UserPayment userPayment = null;
  private Date createDate = null;
  private String browserId = null;
  private UserSubscription subscription = null;

  
  /**
   * Term conversion id
   **/
  public String getTermConversionId() {
    return termConversionId;
  }

  public void setTermConversionId(String termConversionId) {
    this.termConversionId = termConversionId;
  }
  /**
   * The term that was converted
   **/
  public Term getTerm() {
    return term;
  }

  public void setTerm(Term term) {
    this.term = term;
  }
  /**
   * The term conversion type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
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
   * The access that was created as a result of the term conversion
   **/
  public Access getUserAccess() {
    return userAccess;
  }

  public void setUserAccess(Access userAccess) {
    this.userAccess = userAccess;
  }
  /**
   * The payment that was created during term conversion
   **/
  public UserPayment getUserPayment() {
    return userPayment;
  }

  public void setUserPayment(UserPayment userPayment) {
    this.userPayment = userPayment;
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
   * Unique browser identifier
   **/
  public String getBrowserId() {
    return browserId;
  }

  public void setBrowserId(String browserId) {
    this.browserId = browserId;
  }
  /**
   * User subscription
   **/
  public UserSubscription getSubscription() {
    return subscription;
  }

  public void setSubscription(UserSubscription subscription) {
    this.subscription = subscription;
  }

  public static TermConversion fromJson(JSONObject json) throws JSONException {
    TermConversion termConversion = new TermConversion();

    termConversion.termConversionId = json.optString("term_conversion_id");
    termConversion.term = Term.fromJson(json.optJSONObject("term"));
    termConversion.type = json.optString("type");
    termConversion.aid = json.optString("aid");
    termConversion.userAccess = Access.fromJson(json.optJSONObject("user_access"));
    termConversion.userPayment = UserPayment.fromJson(json.optJSONObject("user_payment"));
    termConversion.createDate = new Date(json.optLong("create_date") * 1000);
    termConversion.browserId = json.optString("browser_id");
    termConversion.subscription = UserSubscription.fromJson(json.optJSONObject("subscription"));
    
    return termConversion;
  }
}
