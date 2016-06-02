package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.PaymentInquiry;
import io.piano.android.api.publisher.model.InquiryComment;
import io.piano.android.api.publisher.model.CommentAction;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherInquiryApi {

  private ApiInvoker apiInvoker;

  public PublisherInquiryApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Adds comment
   * 
   * @param aid Application aid
   * @param paymentInquiryId Payment inquiry public id
   * @param action The action
   * @param comment Inquiry comment
   * @param internal Internal comment
   * @return PaymentInquiry
   */
  public PaymentInquiry addComment(String aid, String paymentInquiryId, List<String> action, String comment, Boolean internal) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling addComment");
    }
    
    // verify the required parameter 'paymentInquiryId' is set
    if (paymentInquiryId == null) {
       throw new ApiException(400, "Missing the required parameter 'paymentInquiryId' when calling addComment");
    }
    
    // verify the required parameter 'action' is set
    if (action == null) {
       throw new ApiException(400, "Missing the required parameter 'action' when calling addComment");
    }
    
    // verify the required parameter 'comment' is set
    if (comment == null) {
       throw new ApiException(400, "Missing the required parameter 'comment' when calling addComment");
    }
    
    // verify the required parameter 'internal' is set
    if (internal == null) {
       throw new ApiException(400, "Missing the required parameter 'internal' when calling addComment");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/add";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "payment_inquiry_id", paymentInquiryId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "action", action));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "comment", comment));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "internal", internal));
    

    

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
        return (PaymentInquiry) ApiInvoker.deserialize(response, "", PaymentInquiry.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists inquiry comments
   * 
   * @param aid Application aid
   * @param paymentInquiryId Payment inquiry public id
   * @return List<InquiryComment>
   */
  public List<InquiryComment> comments(String aid, String paymentInquiryId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling comments");
    }
    
    // verify the required parameter 'paymentInquiryId' is set
    if (paymentInquiryId == null) {
       throw new ApiException(400, "Missing the required parameter 'paymentInquiryId' when calling comments");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/comments";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "payment_inquiry_id", paymentInquiryId));
    

    

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
        return (List<InquiryComment>) ApiInvoker.deserialize(response, "array", InquiryComment.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get inqiury
   * 
   * @param aid Application aid
   * @param paymentInquiryId Payment inquiry public id
   * @return PaymentInquiry
   */
  public PaymentInquiry details(String aid, String paymentInquiryId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling details");
    }
    
    // verify the required parameter 'paymentInquiryId' is set
    if (paymentInquiryId == null) {
       throw new ApiException(400, "Missing the required parameter 'paymentInquiryId' when calling details");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/details";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "payment_inquiry_id", paymentInquiryId));
    

    

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
        return (PaymentInquiry) ApiInvoker.deserialize(response, "", PaymentInquiry.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Available actions for an inquiry
   * 
   * @param aid Application aid
   * @param paymentInquiryId Payment inquiry public id
   * @return List<CommentAction>
   */
  public List<CommentAction> getInquiryActions(String aid, String paymentInquiryId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getInquiryActions");
    }
    
    // verify the required parameter 'paymentInquiryId' is set
    if (paymentInquiryId == null) {
       throw new ApiException(400, "Missing the required parameter 'paymentInquiryId' when calling getInquiryActions");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/inquiryActions";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "payment_inquiry_id", paymentInquiryId));
    

    

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
        return (List<CommentAction>) ApiInvoker.deserialize(response, "array", CommentAction.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get last inquiry comment for user
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @return PaymentInquiry
   */
  public PaymentInquiry lastComment(String aid, String uid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling lastComment");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling lastComment");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/lastComment";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    

    

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
        return (PaymentInquiry) ApiInvoker.deserialize(response, "", PaymentInquiry.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists inquiries
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @return List<PaymentInquiry>
   */
  public List<PaymentInquiry> list(String aid, String uid, Integer offset, Integer limit, String q) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling list");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling list");
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
    String path = "/publisher/inquiry/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    

    

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
        return (List<PaymentInquiry>) ApiInvoker.deserialize(response, "array", PaymentInquiry.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get unresolved inquiries count
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @return Integer
   */
  public Integer unresolvedCount(String aid, String uid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling unresolvedCount");
    }
    

    // create path and map variables
    String path = "/publisher/inquiry/unresolvedCount";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    

    

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
        return (Integer) ApiInvoker.deserialize(response, "", Integer.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
