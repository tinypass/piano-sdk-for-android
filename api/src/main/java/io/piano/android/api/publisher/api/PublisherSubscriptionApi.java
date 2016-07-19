package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.UserSubscription;
import java.util.Date;
import io.piano.android.api.publisher.model.UserSubscriptionDto;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherSubscriptionApi {

  private ApiInvoker apiInvoker;

  public PublisherSubscriptionApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Cancels a user&#39;s subscription
   * 
   * @param aid Application aid
   * @param subscriptionId User subscription id
   * @param refundLastPayment Refund last payment
   * @return Boolean
   */
  public Boolean cancelSubscription(String aid, String subscriptionId, Boolean refundLastPayment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling cancelSubscription");
    }
    
    // verify the required parameter 'subscriptionId' is set
    if (subscriptionId == null) {
       throw new ApiException(400, "Missing the required parameter 'subscriptionId' when calling cancelSubscription");
    }
    
    // verify the required parameter 'refundLastPayment' is set
    if (refundLastPayment == null) {
       throw new ApiException(400, "Missing the required parameter 'refundLastPayment' when calling cancelSubscription");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/cancel";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionId != null) {
        builder.addTextBody("subscription_id", ApiInvoker.parameterToString(subscriptionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (refundLastPayment != null) {
        builder.addTextBody("refund_last_payment", ApiInvoker.parameterToString(refundLastPayment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("subscription_id", ApiInvoker.parameterToString(subscriptionId));
      formParams.put("refund_last_payment", ApiInvoker.parameterToString(refundLastPayment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Boolean) ApiInvoker.deserialize(response, "", Boolean.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Count active subscriptions
   * 
   * @param aid Application aid
   * @return Integer
   */
  public Integer count(String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Integer) ApiInvoker.deserialize(response, "", Integer.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists subscriptions
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param type The type
   * @param startDate The start date
   * @param endDate The end date
   * @param q Search value
   * @param selectBy Filter subscription date field
   * @return List<UserSubscription>
   */
  public List<UserSubscription> list(String aid, Integer offset, Integer limit, String type, Date startDate, Date endDate, String q, String selectBy) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling list");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling list");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling list");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_date", startDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_date", endDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "select_by", selectBy));
    

    

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
        return (List<UserSubscription>) ApiInvoker.deserialize(response, "array", UserSubscription.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists subscriptions summary stats
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @return List<UserSubscriptionDto>
   */
  public List<UserSubscriptionDto> stats(String aid, String uid, Integer offset, Integer limit, String q) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling stats");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling stats");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling stats");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling stats");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/stats";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (q != null) {
        builder.addTextBody("q", ApiInvoker.parameterToString(q), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (offset != null) {
        builder.addTextBody("offset", ApiInvoker.parameterToString(offset), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (limit != null) {
        builder.addTextBody("limit", ApiInvoker.parameterToString(limit), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("q", ApiInvoker.parameterToString(q));
      formParams.put("offset", ApiInvoker.parameterToString(offset));
      formParams.put("limit", ApiInvoker.parameterToString(limit));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<UserSubscriptionDto>) ApiInvoker.deserialize(response, "array", UserSubscriptionDto.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Updates a user&#39;s subscription
   * 
   * @param aid Application aid
   * @param subscriptionId Subscription id
   * @param nextBillDate Date of next bill
   * @param autoRenew Subscription auto renew
   * @param paymentMethodId Payment method id
   * @param userAddressId Public id of specific user address
   * @return Boolean
   */
  public Boolean update(String aid, String subscriptionId, Date nextBillDate, Boolean autoRenew, String paymentMethodId, String userAddressId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'subscriptionId' is set
    if (subscriptionId == null) {
       throw new ApiException(400, "Missing the required parameter 'subscriptionId' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/update";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionId != null) {
        builder.addTextBody("subscription_id", ApiInvoker.parameterToString(subscriptionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (nextBillDate != null) {
        builder.addTextBody("next_bill_date", ApiInvoker.parameterToString(nextBillDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (autoRenew != null) {
        builder.addTextBody("auto_renew", ApiInvoker.parameterToString(autoRenew), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentMethodId != null) {
        builder.addTextBody("payment_method_id", ApiInvoker.parameterToString(paymentMethodId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (userAddressId != null) {
        builder.addTextBody("user_address_id", ApiInvoker.parameterToString(userAddressId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("subscription_id", ApiInvoker.parameterToString(subscriptionId));
      formParams.put("next_bill_date", ApiInvoker.parameterToString(nextBillDate));
      formParams.put("auto_renew", ApiInvoker.parameterToString(autoRenew));
      formParams.put("payment_method_id", ApiInvoker.parameterToString(paymentMethodId));
      formParams.put("user_address_id", ApiInvoker.parameterToString(userAddressId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Boolean) ApiInvoker.deserialize(response, "", Boolean.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
