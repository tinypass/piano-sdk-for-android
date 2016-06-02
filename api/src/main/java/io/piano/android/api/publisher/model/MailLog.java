package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.App;
import io.piano.android.api.publisher.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MailLog {
  
  private String emailId = null;
  private App app = null;
  private User user = null;
  private String sender = null;
  private String recipient = null;
  private String replyTo = null;
  private String createDate = null;
  private String openDate = null;
  private String status = null;
  private String rejectReason = null;
  private String emailName = null;
  private String subject = null;
  private String body = null;

  
  /**
   * Email id
   **/
  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }
  /**
   * Application ref
   **/
  public App getApp() {
    return app;
  }

  public void setApp(App app) {
    this.app = app;
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
   * Sender
   **/
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }
  /**
   * Recipient
   **/
  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
  /**
   * Reply to
   **/
  public String getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(String replyTo) {
    this.replyTo = replyTo;
  }
  /**
   * The creation date
   **/
  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  /**
   * Date opened
   **/
  public String getOpenDate() {
    return openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }
  /**
   * Status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  /**
   * Reject reason
   **/
  public String getRejectReason() {
    return rejectReason;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }
  /**
   * Email name
   **/
  public String getEmailName() {
    return emailName;
  }

  public void setEmailName(String emailName) {
    this.emailName = emailName;
  }
  /**
   * Subject
   **/
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }
  /**
   * Body
   **/
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public static MailLog fromJson(JSONObject json) throws JSONException {
    MailLog mailLog = new MailLog();

    mailLog.emailId = json.optString("email_id");
    mailLog.app = App.fromJson(json.optJSONObject("app"));
    mailLog.user = User.fromJson(json.optJSONObject("user"));
    mailLog.sender = json.optString("sender");
    mailLog.recipient = json.optString("recipient");
    mailLog.replyTo = json.optString("reply_to");
    mailLog.createDate = json.optString("create_date");
    mailLog.openDate = json.optString("open_date");
    mailLog.status = json.optString("status");
    mailLog.rejectReason = json.optString("reject_reason");
    mailLog.emailName = json.optString("email_name");
    mailLog.subject = json.optString("subject");
    mailLog.body = json.optString("body");
    
    return mailLog;
  }
}
