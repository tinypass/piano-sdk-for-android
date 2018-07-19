package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.OfferTemplateVariant;

public class PublisherOfferTemplateVariantApi {

  private ApiInvoker apiInvoker;

  public PublisherOfferTemplateVariantApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Change status offer template variant to ARCHIVED
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @param historyComment Offer Template History Comment
   * @return void
   */
  public void archive(String aid, String offerTemplateVariantId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling archive");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling archive");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/archive";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_variant_id", offerTemplateVariantId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "history_comment", historyComment));
    

    

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
   * Creates an offer template variant
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param name The name
   * @param description The description
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVariant
   */
  public OfferTemplateVariant create(String aid, String offerTemplateId, String name, String description, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling create");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling create");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/create";

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
      
      if (offerTemplateId != null) {
        builder.addTextBody("offer_template_id", ApiInvoker.parameterToString(offerTemplateId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (historyComment != null) {
        builder.addTextBody("history_comment", ApiInvoker.parameterToString(historyComment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("offer_template_id", ApiInvoker.parameterToString(offerTemplateId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVariant) ApiInvoker.deserialize(response, "", OfferTemplateVariant.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Deletes an offer template variant
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @param historyComment Offer Template History Comment
   * @return void
   */
  public void delete(String aid, String offerTemplateVariantId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling delete");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/delete";

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
      
      if (offerTemplateVariantId != null) {
        builder.addTextBody("offer_template_variant_id", ApiInvoker.parameterToString(offerTemplateVariantId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (historyComment != null) {
        builder.addTextBody("history_comment", ApiInvoker.parameterToString(historyComment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("offer_template_variant_id", ApiInvoker.parameterToString(offerTemplateVariantId));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
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
   * Gets an offer template variant
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @return OfferTemplateVariant
   */
  public OfferTemplateVariant get(String aid, String offerTemplateVariantId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling get");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_variant_id", offerTemplateVariantId));
    

    

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
        return (OfferTemplateVariant) ApiInvoker.deserialize(response, "", OfferTemplateVariant.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Gets a duplicate offer template variant
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVariant
   */
  public OfferTemplateVariant getDuplicate(String aid, String offerTemplateVariantId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getDuplicate");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling getDuplicate");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/duplicate";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_variant_id", offerTemplateVariantId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "history_comment", historyComment));
    

    

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
        return (OfferTemplateVariant) ApiInvoker.deserialize(response, "", OfferTemplateVariant.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Change status offer template variant to ACTIVE
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @param historyComment Offer Template History Comment
   * @return void
   */
  public void restore(String aid, String offerTemplateVariantId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling restore");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling restore");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/restore";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_variant_id", offerTemplateVariantId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "history_comment", historyComment));
    

    

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
   * Updates an offer template variant
   * 
   * @param aid Application aid
   * @param offerTemplateVariantId Offer template variant ID
   * @param name The name
   * @param description The description
   * @param contentFieldList Content field list
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVariant
   */
  public OfferTemplateVariant update(String aid, String offerTemplateVariantId, String name, String description, String contentFieldList, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'offerTemplateVariantId' is set
    if (offerTemplateVariantId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateVariantId' when calling update");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/variant/update";

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
      
      if (offerTemplateVariantId != null) {
        builder.addTextBody("offer_template_variant_id", ApiInvoker.parameterToString(offerTemplateVariantId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (contentFieldList != null) {
        builder.addTextBody("content_field_list", ApiInvoker.parameterToString(contentFieldList), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (historyComment != null) {
        builder.addTextBody("history_comment", ApiInvoker.parameterToString(historyComment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("offer_template_variant_id", ApiInvoker.parameterToString(offerTemplateVariantId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("content_field_list", ApiInvoker.parameterToString(contentFieldList));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVariant) ApiInvoker.deserialize(response, "", OfferTemplateVariant.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
