package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Consent;

public class PublisherConsentApi {

  private ApiInvoker apiInvoker;

  public PublisherConsentApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Count consent box configs
   * 
   * @param aid Application aid
   * @return Long
   */
  public Long count(String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/consent/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    

    

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
        return (Long) ApiInvoker.deserialize(response, "", Long.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Create consent box config
   * 
   * @param aid Application aid
   * @param fieldName Consent box field name
   * @param fieldId Consent box field ID
   * @param displayText Consent box display text
   * @param preChecked Consent box pre-checked state
   * @param required Is consent box required
   * @param enabled Is consent box enabled
   * @param type Consent box type
   * @param errorMessage Consent box error message
   * @return Consent
   */
  public Consent create(String aid, String fieldName, String fieldId, String displayText, Boolean preChecked, Boolean required, Boolean enabled, String type, String errorMessage) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling create");
    }
    
    // verify the required parameter 'fieldName' is set
    if (fieldName == null) {
       throw new ApiException(400, "Missing the required parameter 'fieldName' when calling create");
    }
    
    // verify the required parameter 'fieldId' is set
    if (fieldId == null) {
       throw new ApiException(400, "Missing the required parameter 'fieldId' when calling create");
    }
    
    // verify the required parameter 'displayText' is set
    if (displayText == null) {
       throw new ApiException(400, "Missing the required parameter 'displayText' when calling create");
    }
    
    // verify the required parameter 'preChecked' is set
    if (preChecked == null) {
       throw new ApiException(400, "Missing the required parameter 'preChecked' when calling create");
    }
    
    // verify the required parameter 'required' is set
    if (required == null) {
       throw new ApiException(400, "Missing the required parameter 'required' when calling create");
    }
    
    // verify the required parameter 'enabled' is set
    if (enabled == null) {
       throw new ApiException(400, "Missing the required parameter 'enabled' when calling create");
    }
    
    // verify the required parameter 'type' is set
    if (type == null) {
       throw new ApiException(400, "Missing the required parameter 'type' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/consent/create";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "field_name", fieldName));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "field_id", fieldId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "display_text", displayText));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "pre_checked", preChecked));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "required", required));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "enabled", enabled));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "error_message", errorMessage));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    

    

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
        return (Consent) ApiInvoker.deserialize(response, "", Consent.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete consent box config
   * 
   * @param aid Application aid
   * @param consentId Consent ID
   * @return Boolean
   */
  public Boolean delete(String aid, String consentId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling delete");
    }
    
    // verify the required parameter 'consentId' is set
    if (consentId == null) {
       throw new ApiException(400, "Missing the required parameter 'consentId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/consent/delete";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "consent_id", consentId));
    

    

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
   * Find consent box config
   * 
   * @param aid Application aid
   * @param consentId Consent ID
   * @return Consent
   */
  public Consent get(String aid, String consentId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling get");
    }
    
    // verify the required parameter 'consentId' is set
    if (consentId == null) {
       throw new ApiException(400, "Missing the required parameter 'consentId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/consent/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "consent_id", consentId));
    

    

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
        return (Consent) ApiInvoker.deserialize(response, "", Consent.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Find consent box configs
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param type Consent box type
   * @param enabled Is consent checked by user
   * @param q Search value
   * @return List<Consent>
   */
  public List<Consent> list(String aid, Integer offset, Integer limit, String type, Boolean enabled, String q) throws ApiException {
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
    String path = "/publisher/consent/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "enabled", enabled));
    
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
        return (List<Consent>) ApiInvoker.deserialize(response, "array", Consent.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update consent box config
   * 
   * @param aid Application aid
   * @param consentId Consent ID
   * @param fieldId Consent box field ID
   * @param fieldName Consent box field name
   * @param displayText Consent box display text
   * @param preChecked Consent box pre-checked state
   * @param required Is consent box required
   * @param type Consent box type
   * @param errorMessage Consent box error message
   * @param enabled Is consent box enabled
   * @return Consent
   */
  public Consent update(String aid, String consentId, String fieldId, String fieldName, String displayText, Boolean preChecked, Boolean required, String type, String errorMessage, Boolean enabled) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'consentId' is set
    if (consentId == null) {
       throw new ApiException(400, "Missing the required parameter 'consentId' when calling update");
    }
    
    // verify the required parameter 'fieldId' is set
    if (fieldId == null) {
       throw new ApiException(400, "Missing the required parameter 'fieldId' when calling update");
    }
    
    // verify the required parameter 'fieldName' is set
    if (fieldName == null) {
       throw new ApiException(400, "Missing the required parameter 'fieldName' when calling update");
    }
    
    // verify the required parameter 'displayText' is set
    if (displayText == null) {
       throw new ApiException(400, "Missing the required parameter 'displayText' when calling update");
    }
    
    // verify the required parameter 'preChecked' is set
    if (preChecked == null) {
       throw new ApiException(400, "Missing the required parameter 'preChecked' when calling update");
    }
    
    // verify the required parameter 'required' is set
    if (required == null) {
       throw new ApiException(400, "Missing the required parameter 'required' when calling update");
    }
    
    // verify the required parameter 'type' is set
    if (type == null) {
       throw new ApiException(400, "Missing the required parameter 'type' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/consent/update";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "consent_id", consentId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "field_id", fieldId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "field_name", fieldName));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "display_text", displayText));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "pre_checked", preChecked));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "required", required));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "error_message", errorMessage));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "enabled", enabled));
    

    

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
        return (Consent) ApiInvoker.deserialize(response, "", Consent.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
