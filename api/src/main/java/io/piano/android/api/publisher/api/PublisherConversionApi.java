package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.TermConversion;

public class PublisherConversionApi {

  private ApiInvoker apiInvoker;

  public PublisherConversionApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Counts conversions
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @param orderBy Field to order by: term_name, resource_type, resource_name
   * @param orderDirection Order direction (asc/desc)
   * @param includeType Type of terms to include into the list
   * @param excludeType Type of terms to exclude from the list
   * @param termId Term id to list
   * @param resourceType Type of resource
   * @param source Type of external API source
   * @param type Type of term to list
   * @return Long
   */
  public Long count(String aid, Integer offset, Integer limit, String q, String orderBy, String orderDirection, List<String> includeType, List<String> excludeType, String termId, String resourceType, List<String> source, String type) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling count");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling count");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "include_type", includeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "exclude_type", excludeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resource_type", resourceType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "source", source));
    
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
   * Get a conversion
   * 
   * @param aid Application aid
   * @param termConversionId Term conversion id
   * @param accessId The access id
   * @return TermConversion
   */
  public TermConversion get(String aid, String termConversionId, String accessId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_conversion_id", termConversionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "access_id", accessId));
    

    

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
        return (TermConversion) ApiInvoker.deserialize(response, "", TermConversion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Last access
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param uid User&#39;s uid
   * @param subscriptionId User subscription id
   * @return TermConversion
   */
  public TermConversion getLast(String aid, String rid, String uid, String subscriptionId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getLast");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/lastAccess";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_id", subscriptionId));
    

    

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
        return (TermConversion) ApiInvoker.deserialize(response, "", TermConversion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists conversions for an app
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param uid User&#39;s uid
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<TermConversion>
   */
  public List<TermConversion> list(String aid, Integer offset, Integer limit, String uid, String q, String orderBy, String orderDirection) throws ApiException {
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
    String path = "/publisher/conversion/list";

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
   * Lists conversions. Deprecated - use &#39;list&#39;
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<TermConversion>
   */
  public List<TermConversion> listAccess(String aid, Integer offset, Integer limit, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listAccess");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listAccess");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listAccess");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/access";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
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
    String path = "/publisher/conversion/log";

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
  
}
