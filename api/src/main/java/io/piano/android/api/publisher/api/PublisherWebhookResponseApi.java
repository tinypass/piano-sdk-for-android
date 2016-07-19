package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.WebhookResponse;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherWebhookResponseApi {

  private ApiInvoker apiInvoker;

  public PublisherWebhookResponseApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Get responses for an event
   * 
   * @param webhookId Webhook id
   * @param limit Limit
   * @param offset Offset
   * @param orderBy Order by
   * @param orderDirection Order direction
   * @return List<WebhookResponse>
   */
  public List<WebhookResponse> getResponses(String webhookId, Integer limit, Integer offset, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'webhookId' is set
    if (webhookId == null) {
       throw new ApiException(400, "Missing the required parameter 'webhookId' when calling getResponses");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling getResponses");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling getResponses");
    }
    
    // verify the required parameter 'orderBy' is set
    if (orderBy == null) {
       throw new ApiException(400, "Missing the required parameter 'orderBy' when calling getResponses");
    }
    
    // verify the required parameter 'orderDirection' is set
    if (orderDirection == null) {
       throw new ApiException(400, "Missing the required parameter 'orderDirection' when calling getResponses");
    }
    

    // create path and map variables
    String path = "/publisher/webhook/response/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "webhook_id", webhookId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<WebhookResponse>) ApiInvoker.deserialize(response, "array", WebhookResponse.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Resend an event
   * 
   * @param webhookId Webhook id
   * @return WebhookResponse
   */
  public WebhookResponse resend(String webhookId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'webhookId' is set
    if (webhookId == null) {
       throw new ApiException(400, "Missing the required parameter 'webhookId' when calling resend");
    }
    

    // create path and map variables
    String path = "/publisher/webhook/response/resend";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "webhook_id", webhookId));
    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (WebhookResponse) ApiInvoker.deserialize(response, "", WebhookResponse.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
