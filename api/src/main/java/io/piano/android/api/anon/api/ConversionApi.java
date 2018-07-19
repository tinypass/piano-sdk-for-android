package io.piano.android.api.anon.api;

import android.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.anon.model.TermConversion;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

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
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<TermConversion>
   */
  public List<TermConversion> list(String aid, Integer offset, Integer limit, String userToken, String userProvider, String userRef, String q, String orderBy, String orderDirection) throws ApiException {
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
   * Log third party conversion
   * 
   * @param trackingId Conversion tracking id
   * @param termId Term ID
   * @param termName Term name
   * @param stepNumber Checkout step number
   * @param conversionCategory Conversion category
   * @param amount Conversion amount
   * @param currency Conversion currency by ISO 4217 standard
   * @param customParams Custom parameters (any key-value pairs) to save (this value should be a valid JSON object)
   * @return void
   */
  public void logConversion(String trackingId, String termId, String termName, Integer stepNumber, String conversionCategory, BigDecimal amount, String currency, String customParams) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'trackingId' is set
    if (trackingId == null) {
       throw new ApiException(400, "Missing the required parameter 'trackingId' when calling logConversion");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling logConversion");
    }
    
    // verify the required parameter 'termName' is set
    if (termName == null) {
       throw new ApiException(400, "Missing the required parameter 'termName' when calling logConversion");
    }
    

    // create path and map variables
    String path = "/conversion/log";

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
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termName != null) {
        builder.addTextBody("term_name", ApiInvoker.parameterToString(termName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (stepNumber != null) {
        builder.addTextBody("step_number", ApiInvoker.parameterToString(stepNumber), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (conversionCategory != null) {
        builder.addTextBody("conversion_category", ApiInvoker.parameterToString(conversionCategory), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (amount != null) {
        builder.addTextBody("amount", ApiInvoker.parameterToString(amount), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (currency != null) {
        builder.addTextBody("currency", ApiInvoker.parameterToString(currency), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("term_name", ApiInvoker.parameterToString(termName));
      formParams.put("step_number", ApiInvoker.parameterToString(stepNumber));
      formParams.put("conversion_category", ApiInvoker.parameterToString(conversionCategory));
      formParams.put("amount", ApiInvoker.parameterToString(amount));
      formParams.put("currency", ApiInvoker.parameterToString(currency));
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
  
  /**
   * Log funnel step
   * 
   * @param trackingId Conversion tracking id
   * @param stepNumber Checkout step number
   * @param stepName Checkout step name
   * @param customParams Custom parameters (any key-value pairs) to save (this value should be a valid JSON object)
   * @return void
   */
  public void logFunnelStep(String trackingId, Integer stepNumber, String stepName, String customParams) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'trackingId' is set
    if (trackingId == null) {
       throw new ApiException(400, "Missing the required parameter 'trackingId' when calling logFunnelStep");
    }
    
    // verify the required parameter 'stepNumber' is set
    if (stepNumber == null) {
       throw new ApiException(400, "Missing the required parameter 'stepNumber' when calling logFunnelStep");
    }
    
    // verify the required parameter 'stepName' is set
    if (stepName == null) {
       throw new ApiException(400, "Missing the required parameter 'stepName' when calling logFunnelStep");
    }
    

    // create path and map variables
    String path = "/conversion/logFunnelStep";

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
  
  /**
   * Log micro conversion
   * 
   * @param trackingId Conversion tracking id
   * @param eventGroupId Event group
   * @param customParams Custom parameters (any key-value pairs) to save (this value should be a valid JSON object)
   * @return void
   */
  public void logMicroConversion(String trackingId, String eventGroupId, String customParams) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'trackingId' is set
    if (trackingId == null) {
       throw new ApiException(400, "Missing the required parameter 'trackingId' when calling logMicroConversion");
    }
    
    // verify the required parameter 'eventGroupId' is set
    if (eventGroupId == null) {
       throw new ApiException(400, "Missing the required parameter 'eventGroupId' when calling logMicroConversion");
    }
    

    // create path and map variables
    String path = "/conversion/logMicroConversion";

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
      
      if (eventGroupId != null) {
        builder.addTextBody("event_group_id", ApiInvoker.parameterToString(eventGroupId), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("event_group_id", ApiInvoker.parameterToString(eventGroupId));
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
