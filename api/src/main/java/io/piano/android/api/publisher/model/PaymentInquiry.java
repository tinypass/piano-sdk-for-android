package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentInquiry {
  
  private String paymentInquiryId = null;
  private Resource resource = null;
  private App app = null;
  private Integer state = null;
  private String inquiryReason = null;
  private String createDate = null;
  private List<InquiryComment> inquiryComments = null;
  private String category = null;
  private User updateStateBy = null;
  private String updateStateDate = null;
  private String startDate = null;
  private String expireDate = null;
  private String transactionDate = null;
  private String transactionId = null;
  private Double spentMoney = null;
  private String source = null;
  private String currency = null;
  private String refundedDate = null;
  
  /**
   * Payment inquiry public id
   **/
  public String getPaymentInquiryId() {
    return paymentInquiryId;
  }

  public void setPaymentInquiryId(String paymentInquiryId) {
    this.paymentInquiryId = paymentInquiryId;
  }
  
  /**
   * The resource
   **/
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
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
   * Inquiry state
   **/
  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }
  
  /**
   * Inquiry reason
   **/
  public String getInquiryReason() {
    return inquiryReason;
  }

  public void setInquiryReason(String inquiryReason) {
    this.inquiryReason = inquiryReason;
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
   **/
  public List<InquiryComment> getInquiryComments() {
    return inquiryComments;
  }

  public void setInquiryComments(List<InquiryComment> inquiryComments) {
    this.inquiryComments = inquiryComments;
  }
  
  /**
   * Category
   **/
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
  
  /**
   **/
  public User getUpdateStateBy() {
    return updateStateBy;
  }

  public void setUpdateStateBy(User updateStateBy) {
    this.updateStateBy = updateStateBy;
  }
  
  /**
   * Date when state was updated
   **/
  public String getUpdateStateDate() {
    return updateStateDate;
  }

  public void setUpdateStateDate(String updateStateDate) {
    this.updateStateDate = updateStateDate;
  }
  
  /**
   * The start date.
   **/
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  /**
   * Expire date
   **/
  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }
  
  /**
   * Date of transaction
   **/
  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }
  
  /**
   * Transaction id
   **/
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  
  /**
   * spent money
   **/
  public Double getSpentMoney() {
    return spentMoney;
  }

  public void setSpentMoney(Double spentMoney) {
    this.spentMoney = spentMoney;
  }
  
  /**
   * Source
   **/
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
  
  /**
   * User payment currency
   **/
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
  
  /**
   * Refunded date
   **/
  public String getRefundedDate() {
    return refundedDate;
  }

  public void setRefundedDate(String refundedDate) {
    this.refundedDate = refundedDate;
  }
  
  public static PaymentInquiry fromJson(JSONObject json) throws JSONException {
    PaymentInquiry paymentInquiry = new PaymentInquiry();

    paymentInquiry.paymentInquiryId = json.optString("payment_inquiry_id");
    paymentInquiry.resource = Resource.fromJson(json.optJSONObject("resource"));
    paymentInquiry.app = App.fromJson(json.optJSONObject("app"));
    paymentInquiry.state = json.optInt("state");
    paymentInquiry.inquiryReason = json.optString("inquiry_reason");
    paymentInquiry.createDate = json.optString("create_date");
    paymentInquiry.inquiryComments = new ArrayList<>();
    JSONArray inquiryCommentsJsonArray = json.optJSONArray("inquiry_comments");
    int inquiryCommentsLength = inquiryCommentsJsonArray.length();
    for (int ii = 0; ii < inquiryCommentsLength; ii++) {
      paymentInquiry.inquiryComments.add(InquiryComment.fromJson(inquiryCommentsJsonArray.optJSONObject(ii)));
    }
    paymentInquiry.category = json.optString("category");
    paymentInquiry.updateStateBy = User.fromJson(json.optJSONObject("update_state_by"));
    paymentInquiry.updateStateDate = json.optString("update_state_date");
    paymentInquiry.startDate = json.optString("start_date");
    paymentInquiry.expireDate = json.optString("expire_date");
    paymentInquiry.transactionDate = json.optString("transaction_date");
    paymentInquiry.transactionId = json.optString("transaction_id");
    paymentInquiry.spentMoney = json.optDouble("spent_money");
    paymentInquiry.source = json.optString("source");
    paymentInquiry.currency = json.optString("currency");
    paymentInquiry.refundedDate = json.optString("refunded_date");
    
    return paymentInquiry;
  }
}
