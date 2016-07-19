package io.piano.android.api.anon.api;

import android.util.Pair;

import io.piano.android.api.anon.model.TermConversion;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ConversionApi {

  private ApiInvoker apiInvoker;

  public ConversionApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Returns the list of term conversions for user
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @param q Search value
   * @return List<TermConversion>
   */
  public List<TermConversion> list(String aid, Integer offset, Integer limit, String userToken, String userProvider, String userRef, String q) throws ApiException {
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
    String path = "/conversion/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_token", userToken));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_provider", userProvider));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_ref", userRef));
    
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
        return (List<TermConversion>) ApiInvoker.deserialize(response, "array", TermConversion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Log third party event
   * 
   * @param trackingId Conversion tracking id
   * @param stepNumber Checkout step number
   * @param stepName Checkout step name
   * @param customParams Custom parameters (any key-value pairs) to save (this value should be a valid JSON object)
   * @return void
   */
  public void logEvent(String trackingId, Integer stepNumber, String stepName, String customParams) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'trackingId' is set
    if (trackingId == null) {
       throw new ApiException(400, "Missing the required parameter 'trackingId' when calling logEvent");
    }
    
    // verify the required parameter 'stepNumber' is set
    if (stepNumber == null) {
       throw new ApiException(400, "Missing the required parameter 'stepNumber' when calling logEvent");
    }
    
    // verify the required parameter 'stepName' is set
    if (stepName == null) {
       throw new ApiException(400, "Missing the required parameter 'stepName' when calling logEvent");
    }
    

    // create path and map variables
    String path = "/conversion/logEvent";

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
      
      if (trackingId != null) {
        builder.addTextBody("tracking_id", ApiInvoker.parameterToString(trackingId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (stepNumber != null) {
        builder.addTextBody("step_number", ApiInvoker.parameterToString(stepNumber), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (stepName != null) {
        builder.addTextBody("step_name", ApiInvoker.parameterToString(stepName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customParams != null) {
        builder.addTextBody("custom_params", ApiInvoker.parameterToString(customParams), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("tracking_id", ApiInvoker.parameterToString(trackingId));
      formParams.put("step_number", ApiInvoker.parameterToString(stepNumber));
      formParams.put("step_name", ApiInvoker.parameterToString(stepName));
      formParams.put("custom_params", ApiInvoker.parameterToString(customParams));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
