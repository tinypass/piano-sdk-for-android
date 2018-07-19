package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.OfferTemplate;
import io.piano.android.api.publisher.model.OfferTemplateCategories;
import io.piano.android.api.publisher.model.OfferTemplateHistories;
import io.piano.android.api.publisher.model.OfferTemplateVersion;

public class PublisherOfferTemplateApi {

  private ApiInvoker apiInvoker;

  public PublisherOfferTemplateApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Change status offer template to ARCHIVED
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param historyComment Offer Template History Comment
   * @return void
   */
  public void archive(String aid, String offerTemplateId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling archive");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling archive");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/archive";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_id", offerTemplateId));
    
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
   * Counts offer template
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
    String path = "/publisher/offer/template/count";

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
   * Creates an offer template
   * 
   * @param aid Application aid
   * @param name The name
   * @param description The description
   * @param categoryId The id of Category
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion create(String aid, String name, String description, String categoryId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling create");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/create";

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
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (categoryId != null) {
        builder.addTextBody("category_id", ApiInvoker.parameterToString(categoryId), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("category_id", ApiInvoker.parameterToString(categoryId));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete offer template
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @return void
   */
  public void delete(String aid, String offerTemplateId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling delete");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/delete";

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
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("offer_template_id", ApiInvoker.parameterToString(offerTemplateId));
      
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
   * Gets an offer template
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion get(String aid, String offerTemplateId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling get");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_id", offerTemplateId));
    

    

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
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Gets a duplicate offer template
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion getDuplicate(String aid, String offerTemplateId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getDuplicate");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling getDuplicate");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/duplicate";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_id", offerTemplateId));
    
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
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Gets a full history of the offer template
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @return OfferTemplateHistories
   */
  public OfferTemplateHistories getHistory(String aid, String offerTemplateId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getHistory");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling getHistory");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/getHistory";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_id", offerTemplateId));
    

    

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
        return (OfferTemplateHistories) ApiInvoker.deserialize(response, "", OfferTemplateHistories.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists offer templates
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param status Template status
   * @return List<OfferTemplate>
   */
  public List<OfferTemplate> list(String aid, Integer offset, Integer limit, String q, String orderBy, String orderDirection, String status) throws ApiException {
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
    String path = "/publisher/offer/template/list";

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
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "status", status));
    

    

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
        return (List<OfferTemplate>) ApiInvoker.deserialize(response, "array", OfferTemplate.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists offer templates by categories
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param categories Template categories
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param status Template status (active/archived)
   * @param templateType Template type (default_offer/zuora_offer/newscycle_offer/template)
   * @return List<OfferTemplateCategories>
   */
  public List<OfferTemplateCategories> listByCategory(String aid, Integer offset, Integer limit, List<String> categories, String q, String orderBy, String orderDirection, String status, String templateType) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listByCategory");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listByCategory");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listByCategory");
    }
    
    // verify the required parameter 'categories' is set
    if (categories == null) {
       throw new ApiException(400, "Missing the required parameter 'categories' when calling listByCategory");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/listByCategory";

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
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "status", status));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "templateType", templateType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "categories", categories));
    

    

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
        return (List<OfferTemplateCategories>) ApiInvoker.deserialize(response, "array", OfferTemplateCategories.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Change status offer template to ACTIVE
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param historyComment Offer Template History Comment
   * @return void
   */
  public void restore(String aid, String offerTemplateId, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling restore");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling restore");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/restore";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_template_id", offerTemplateId));
    
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
   * Updates an offer template
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param name The name
   * @param description The description
   * @param categoryId The id of Category
   * @param thumbnailImageUrl Thumbnail image URL
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion update(String aid, String offerTemplateId, String name, String description, String categoryId, String thumbnailImageUrl, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling update");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/update";

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
      
      if (categoryId != null) {
        builder.addTextBody("category_id", ApiInvoker.parameterToString(categoryId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (thumbnailImageUrl != null) {
        builder.addTextBody("thumbnail_image_url", ApiInvoker.parameterToString(thumbnailImageUrl), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("category_id", ApiInvoker.parameterToString(categoryId));
      formParams.put("thumbnail_image_url", ApiInvoker.parameterToString(thumbnailImageUrl));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Updates an offer template, version, external css list
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param name The name
   * @param categoryId The id of Category
   * @param description The description
   * @param thumbnailImageUrl Thumbnail image URL
   * @param action The action
   * @param versionName The version name
   * @param version Template version
   * @param content1Type The content1 type
   * @param content1Value The content1 value
   * @param content2Type The content2 type
   * @param content2Value The content2 value
   * @param externalCssList External css list
   * @param contentFieldList Content field list
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion updateFull(String aid, String offerTemplateId, String name, String categoryId, String description, String thumbnailImageUrl, String action, String versionName, Long version, String content1Type, String content1Value, String content2Type, String content2Value, String externalCssList, String contentFieldList, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling updateFull");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling updateFull");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling updateFull");
    }
    
    // verify the required parameter 'categoryId' is set
    if (categoryId == null) {
       throw new ApiException(400, "Missing the required parameter 'categoryId' when calling updateFull");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/updatefull";

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
      
      if (categoryId != null) {
        builder.addTextBody("category_id", ApiInvoker.parameterToString(categoryId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (thumbnailImageUrl != null) {
        builder.addTextBody("thumbnail_image_url", ApiInvoker.parameterToString(thumbnailImageUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (action != null) {
        builder.addTextBody("action", ApiInvoker.parameterToString(action), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (versionName != null) {
        builder.addTextBody("version_name", ApiInvoker.parameterToString(versionName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (version != null) {
        builder.addTextBody("version", ApiInvoker.parameterToString(version), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (content1Type != null) {
        builder.addTextBody("content1_type", ApiInvoker.parameterToString(content1Type), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (content1Value != null) {
        builder.addTextBody("content1_value", ApiInvoker.parameterToString(content1Value), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (content2Type != null) {
        builder.addTextBody("content2_type", ApiInvoker.parameterToString(content2Type), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (content2Value != null) {
        builder.addTextBody("content2_value", ApiInvoker.parameterToString(content2Value), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (externalCssList != null) {
        builder.addTextBody("external_css_list", ApiInvoker.parameterToString(externalCssList), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("offer_template_id", ApiInvoker.parameterToString(offerTemplateId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("category_id", ApiInvoker.parameterToString(categoryId));
      formParams.put("thumbnail_image_url", ApiInvoker.parameterToString(thumbnailImageUrl));
      formParams.put("action", ApiInvoker.parameterToString(action));
      formParams.put("version_name", ApiInvoker.parameterToString(versionName));
      formParams.put("version", ApiInvoker.parameterToString(version));
      formParams.put("content1_type", ApiInvoker.parameterToString(content1Type));
      formParams.put("content1_value", ApiInvoker.parameterToString(content1Value));
      formParams.put("content2_type", ApiInvoker.parameterToString(content2Type));
      formParams.put("content2_value", ApiInvoker.parameterToString(content2Value));
      formParams.put("external_css_list", ApiInvoker.parameterToString(externalCssList));
      formParams.put("content_field_list", ApiInvoker.parameterToString(contentFieldList));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
