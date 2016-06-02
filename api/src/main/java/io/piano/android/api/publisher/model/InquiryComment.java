package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InquiryComment {
  
  private String commentId = null;
  private Integer submitterType = null;
  private String createDate = null;
  private String message = null;
  private User user = null;
  private String email = null;
  private String name = null;
  private String internal = null;

  
  /**
   * Comment
   **/
  public String getCommentId() {
    return commentId;
  }

  public void setCommentId(String commentId) {
    this.commentId = commentId;
  }
  /**
   * Submitter type
   **/
  public Integer getSubmitterType() {
    return submitterType;
  }

  public void setSubmitterType(Integer submitterType) {
    this.submitterType = submitterType;
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
   * Message
   **/
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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
   * User's email address
   **/
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * The name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  /**
   * Internal comment
   **/
  public String getInternal() {
    return internal;
  }

  public void setInternal(String internal) {
    this.internal = internal;
  }

  public static InquiryComment fromJson(JSONObject json) throws JSONException {
    InquiryComment inquiryComment = new InquiryComment();

    inquiryComment.commentId = json.optString("comment_id");
    inquiryComment.submitterType = json.optInt("submitter_type");
    inquiryComment.createDate = json.optString("create_date");
    inquiryComment.message = json.optString("message");
    inquiryComment.user = User.fromJson(json.optJSONObject("user"));
    inquiryComment.email = json.optString("email");
    inquiryComment.name = json.optString("name");
    inquiryComment.internal = json.optString("internal");
    
    return inquiryComment;
  }
}
