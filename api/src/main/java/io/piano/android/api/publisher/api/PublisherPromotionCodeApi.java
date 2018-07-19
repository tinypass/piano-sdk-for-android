package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Export;
import io.piano.android.api.publisher.model.PromoCode;

public class PublisherPromotionCodeApi {

  private ApiInvoker apiInvoker;

  public PublisherPromotionCodeApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Get total number of promo codes
   * 
   * @param promotionId Promotion id
   * @param q Search value
   * @param state Promo code state
   * @return Long
   */
  public Long count(String promotionId, String q, String state) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));
    

    

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
   * Create promo code
   * 
   * @param promotionId Promotion id
   * @param code Promo code
   * @return PromoCode
   */
  public PromoCode create(String promotionId, String code) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling create");
    }
    
    // verify the required parameter 'code' is set
    if (code == null) {
       throw new ApiException(400, "Missing the required parameter 'code' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/create";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "code", code));
    

    

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
        return (PromoCode) ApiInvoker.deserialize(response, "", PromoCode.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete a promo code 
   * 
   * @param promoCodeId Promo code id
   * @return void
   */
  public void delete(List<String> promoCodeId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promoCodeId' is set
    if (promoCodeId == null) {
       throw new ApiException(400, "Missing the required parameter 'promoCodeId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/delete";

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
      
      if (promoCodeId != null) {
        builder.addTextBody("promo_code_id", ApiInvoker.parameterToString(promoCodeId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promo_code_id", ApiInvoker.parameterToString(promoCodeId));
      
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
   * Create new downloadable promo codes report
   * 
   * @param promotionId Promotion id
   * @param exportName Downloadable report name
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param state Promo code state
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return Export
   */
  public Export export(String promotionId, String exportName, Integer offset, Integer limit, List<String> state, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling export");
    }
    
    // verify the required parameter 'exportName' is set
    if (exportName == null) {
       throw new ApiException(400, "Missing the required parameter 'exportName' when calling export");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling export");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling export");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/export";

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
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (exportName != null) {
        builder.addTextBody("export_name", ApiInvoker.parameterToString(exportName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (state != null) {
        builder.addTextBody("state", ApiInvoker.parameterToString(state), ApiInvoker.TEXT_PLAIN_UTF8);
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
      
      if (orderBy != null) {
        builder.addTextBody("order_by", ApiInvoker.parameterToString(orderBy), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (orderDirection != null) {
        builder.addTextBody("order_direction", ApiInvoker.parameterToString(orderDirection), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      formParams.put("export_name", ApiInvoker.parameterToString(exportName));
      formParams.put("state", ApiInvoker.parameterToString(state));
      formParams.put("q", ApiInvoker.parameterToString(q));
      formParams.put("offset", ApiInvoker.parameterToString(offset));
      formParams.put("limit", ApiInvoker.parameterToString(limit));
      formParams.put("order_by", ApiInvoker.parameterToString(orderBy));
      formParams.put("order_direction", ApiInvoker.parameterToString(orderDirection));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Export) ApiInvoker.deserialize(response, "", Export.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get a promo code
   * 
   * @param promoCodeId Promo code id
   * @return PromoCode
   */
  public PromoCode get(String promoCodeId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promoCodeId' is set
    if (promoCodeId == null) {
       throw new ApiException(400, "Missing the required parameter 'promoCodeId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promo_code_id", promoCodeId));
    

    

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
        return (PromoCode) ApiInvoker.deserialize(response, "", PromoCode.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * List promo codes
   * 
   * @param promotionId Promotion id
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param state Promo code state
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<PromoCode>
   */
  public List<PromoCode> list(String promotionId, Integer offset, Integer limit, List<String> state, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling list");
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
    String path = "/publisher/promotion/code/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "state", state));
    
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
        return (List<PromoCode>) ApiInvoker.deserialize(response, "array", PromoCode.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update a promo code
   * 
   * @param promoCodeId Promo code id
   * @param promotionId Promotion id
   * @param code Promo code
   * @return PromoCode
   */
  public PromoCode update(String promoCodeId, String promotionId, String code) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promoCodeId' is set
    if (promoCodeId == null) {
       throw new ApiException(400, "Missing the required parameter 'promoCodeId' when calling update");
    }
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling update");
    }
    
    // verify the required parameter 'code' is set
    if (code == null) {
       throw new ApiException(400, "Missing the required parameter 'code' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/code/update";

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
      
      if (promoCodeId != null) {
        builder.addTextBody("promo_code_id", ApiInvoker.parameterToString(promoCodeId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (code != null) {
        builder.addTextBody("code", ApiInvoker.parameterToString(code), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promo_code_id", ApiInvoker.parameterToString(promoCodeId));
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      formParams.put("code", ApiInvoker.parameterToString(code));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (PromoCode) ApiInvoker.deserialize(response, "", PromoCode.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
