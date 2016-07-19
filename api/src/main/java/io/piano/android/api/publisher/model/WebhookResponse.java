package io.piano.android.api.publisher.model;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookResponse {
  
  private String status = null;
  private String responseHeaders = null;
  private String responseBody = null;
  private Date createDate = null;
  private String requestUrl = null;
  private String requestData = null;
  
  /**
   * Webhook response status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Webhook response headers
   **/
  public String getResponseHeaders() {
    return responseHeaders;
  }

  public void setResponseHeaders(String responseHeaders) {
    this.responseHeaders = responseHeaders;
  }
  
  /**
   * Webhook response body
   **/
  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }
  
  /**
   * Webhook response create date
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  /**
   * Webhook request URL
   **/
  public String getRequestUrl() {
    return requestUrl;
  }

  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }
  
  /**
   * Webhook request data
   **/
  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
  }
  
  public static WebhookResponse fromJson(JSONObject json) throws JSONException {
    WebhookResponse webhookResponse = new WebhookResponse();

    webhookResponse.status = json.optString("status");
    webhookResponse.responseHeaders = json.optString("response_headers");
    webhookResponse.responseBody = json.optString("response_body");
    webhookResponse.createDate = new Date(json.optLong("create_date") * 1000);
    webhookResponse.requestUrl = json.optString("request_url");
    webhookResponse.requestData = json.optString("request_data");
    
    return webhookResponse;
  }
}
