package io.piano.android.api.publisher.model;

import io.piano.android.api.publisher.model.User;
import io.piano.android.api.publisher.model.WebhookResponse;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookEvent {
  
  private String webhookId = null;
  private String status = null;
  private String retried = null;
  private Date createDate = null;
  private Date updateDate = null;
  private WebhookResponse lastWebhookResponse = null;
  private User user = null;
  private String type = null;
  private String event = null;
  private String eventType = null;
  private Integer responsesCount = null;

  
  /**
   * Webhook id
   **/
  public String getWebhookId() {
    return webhookId;
  }

  public void setWebhookId(String webhookId) {
    this.webhookId = webhookId;
  }
  /**
   * The status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  /**
   * Retry count
   **/
  public String getRetried() {
    return retried;
  }

  public void setRetried(String retried) {
    this.retried = retried;
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
   * The update date
   **/
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
  /**
   **/
  public WebhookResponse getLastWebhookResponse() {
    return lastWebhookResponse;
  }

  public void setLastWebhookResponse(WebhookResponse lastWebhookResponse) {
    this.lastWebhookResponse = lastWebhookResponse;
  }
  /**
   **/
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  /**
   * Type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  /**
   * Event
   **/
  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }
  /**
   * The webhook event type
   **/
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }
  /**
   * Responses count
   **/
  public Integer getResponsesCount() {
    return responsesCount;
  }

  public void setResponsesCount(Integer responsesCount) {
    this.responsesCount = responsesCount;
  }

  public static WebhookEvent fromJson(JSONObject json) throws JSONException {
    WebhookEvent webhookEvent = new WebhookEvent();

    webhookEvent.webhookId = json.optString("webhook_id");
    webhookEvent.status = json.optString("status");
    webhookEvent.retried = json.optString("retried");
    webhookEvent.createDate = new Date(json.optLong("create_date") * 1000);
    webhookEvent.updateDate = new Date(json.optLong("update_date") * 1000);
    webhookEvent.lastWebhookResponse = WebhookResponse.fromJson(json.optJSONObject("last_webhook_response"));
    webhookEvent.user = User.fromJson(json.optJSONObject("user"));
    webhookEvent.type = json.optString("type");
    webhookEvent.event = json.optString("event");
    webhookEvent.eventType = json.optString("event_type");
    webhookEvent.responsesCount = json.optInt("responses_count");
    
    return webhookEvent;
  }
}
