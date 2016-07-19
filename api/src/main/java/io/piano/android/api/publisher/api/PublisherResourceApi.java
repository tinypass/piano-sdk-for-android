package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.Resource;
import java.util.Date;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherResourceApi {

  private ApiInvoker apiInvoker;

  public PublisherResourceApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Attach resource(s) to a fixed bundle
   * 
   * @param aid Application aid
   * @param includedRid Included RIDs
   * @param bundleRid Unique id for resource bundle
   * @return void
   */
  public void attachResourceToFixedBundle(String aid, List<String> includedRid, String bundleRid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling attachResourceToFixedBundle");
    }
    
    // verify the required parameter 'includedRid' is set
    if (includedRid == null) {
       throw new ApiException(400, "Missing the required parameter 'includedRid' when calling attachResourceToFixedBundle");
    }
    
    // verify the required parameter 'bundleRid' is set
    if (bundleRid == null) {
       throw new ApiException(400, "Missing the required parameter 'bundleRid' when calling attachResourceToFixedBundle");
    }
    

    // create path and map variables
    String path = "/publisher/resource/attach";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "included_rid", includedRid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "bundle_rid", bundleRid));
    

    

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
   * Lists total number of resources
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
    String path = "/publisher/resource/count";

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
   * Creates a resource
   * 
   * @param aid Application aid
   * @param name The name
   * @param rid Unique id for resource
   * @param description Resource description
   * @param type Type of resource
   * @param imageUrl Resource image URL
   * @param bundleType Type of resource bundle
   * @param resourceTagId Id for resource tag
   * @param publishDate The publish date
   * @param resourceUrl Resource URL
   * @return Resource
   */
  public Resource createResource(String aid, String name, String rid, String description, String type, String imageUrl, String bundleType, List<String> resourceTagId, Date publishDate, String resourceUrl) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createResource");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/create";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (type != null) {
        builder.addTextBody("type", ApiInvoker.parameterToString(type), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (imageUrl != null) {
        builder.addTextBody("image_url", ApiInvoker.parameterToString(imageUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (bundleType != null) {
        builder.addTextBody("bundle_type", ApiInvoker.parameterToString(bundleType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (resourceTagId != null) {
        builder.addTextBody("resource_tag_id", ApiInvoker.parameterToString(resourceTagId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (publishDate != null) {
        builder.addTextBody("publish_date", ApiInvoker.parameterToString(publishDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (resourceUrl != null) {
        builder.addTextBody("resource_url", ApiInvoker.parameterToString(resourceUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("type", ApiInvoker.parameterToString(type));
      formParams.put("image_url", ApiInvoker.parameterToString(imageUrl));
      formParams.put("bundle_type", ApiInvoker.parameterToString(bundleType));
      formParams.put("resource_tag_id", ApiInvoker.parameterToString(resourceTagId));
      formParams.put("publish_date", ApiInvoker.parameterToString(publishDate));
      formParams.put("resource_url", ApiInvoker.parameterToString(resourceUrl));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Resource) ApiInvoker.deserialize(response, "", Resource.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete a resource
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @return void
   */
  public void deleteResource(String aid, String rid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling deleteResource");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling deleteResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/delete";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      
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
   * Detach a resource from a fixed bundle
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param bundleRid Unique id for resource bundle
   * @return void
   */
  public void detachResourceFromFixedBundle(String aid, String rid, String bundleRid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling detachResourceFromFixedBundle");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling detachResourceFromFixedBundle");
    }
    
    // verify the required parameter 'bundleRid' is set
    if (bundleRid == null) {
       throw new ApiException(400, "Missing the required parameter 'bundleRid' when calling detachResourceFromFixedBundle");
    }
    

    // create path and map variables
    String path = "/publisher/resource/detach";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "bundle_rid", bundleRid));
    

    

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
   * Disable one or more resources
   * 
   * @param aid Application aid
   * @param includedRid Included RIDs
   * @return void
   */
  public void disableResource(String aid, List<String> includedRid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling disableResource");
    }
    
    // verify the required parameter 'includedRid' is set
    if (includedRid == null) {
       throw new ApiException(400, "Missing the required parameter 'includedRid' when calling disableResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/disable";

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
      
      if (includedRid != null) {
        builder.addTextBody("included_rid", ApiInvoker.parameterToString(includedRid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("included_rid", ApiInvoker.parameterToString(includedRid));
      
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
   * Enable one or more resources
   * 
   * @param aid Application aid
   * @param includedRid Included RIDs
   * @return void
   */
  public void enableResource(String aid, List<String> includedRid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling enableResource");
    }
    
    // verify the required parameter 'includedRid' is set
    if (includedRid == null) {
       throw new ApiException(400, "Missing the required parameter 'includedRid' when calling enableResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/enable";

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
      
      if (includedRid != null) {
        builder.addTextBody("included_rid", ApiInvoker.parameterToString(includedRid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("included_rid", ApiInvoker.parameterToString(includedRid));
      
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
   * Get a resource
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @return Resource
   */
  public Resource getResource(String aid, String rid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getResource");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling getResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    

    

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
        return (Resource) ApiInvoker.deserialize(response, "", Resource.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists bundles that a resource belongs to
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param type Resource type
   * @param q Search value
   * @param disabled Disabled flag
   * @param bundleType Bundle type
   * @return List<Resource>
   */
  public List<Resource> listBundles(String aid, String rid, Integer offset, Integer limit, String orderBy, String orderDirection, String type, String q, Boolean disabled, Integer bundleType) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listBundles");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling listBundles");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listBundles");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listBundles");
    }
    
    // verify the required parameter 'orderBy' is set
    if (orderBy == null) {
       throw new ApiException(400, "Missing the required parameter 'orderBy' when calling listBundles");
    }
    
    // verify the required parameter 'orderDirection' is set
    if (orderDirection == null) {
       throw new ApiException(400, "Missing the required parameter 'orderDirection' when calling listBundles");
    }
    
    // verify the required parameter 'type' is set
    if (type == null) {
       throw new ApiException(400, "Missing the required parameter 'type' when calling listBundles");
    }
    

    // create path and map variables
    String path = "/publisher/resource/bundles";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "disabled", disabled));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "bundle_type", bundleType));
    

    

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
        return (List<Resource>) ApiInvoker.deserialize(response, "array", Resource.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists resources
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param type Resource type
   * @param includedTagId Included tag IDs
   * @param excludedRid Excluded RIDs
   * @param includedRid Included RIDs
   * @param q Search value
   * @param disabled Disabled flag
   * @param bundleType Bundle type
   * @return List<Resource>
   */
  public List<Resource> listResources(String aid, Integer offset, Integer limit, String orderBy, String orderDirection, String type, List<String> includedTagId, List<String> excludedRid, List<String> includedRid, String q, Boolean disabled, Integer bundleType) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listResources");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listResources");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listResources");
    }
    
    // verify the required parameter 'orderBy' is set
    if (orderBy == null) {
       throw new ApiException(400, "Missing the required parameter 'orderBy' when calling listResources");
    }
    
    // verify the required parameter 'orderDirection' is set
    if (orderDirection == null) {
       throw new ApiException(400, "Missing the required parameter 'orderDirection' when calling listResources");
    }
    
    // verify the required parameter 'type' is set
    if (type == null) {
       throw new ApiException(400, "Missing the required parameter 'type' when calling listResources");
    }
    

    // create path and map variables
    String path = "/publisher/resource/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "included_tag_id", includedTagId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "excluded_rid", excludedRid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "included_rid", includedRid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "disabled", disabled));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "bundle_type", bundleType));
    

    

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
        return (List<Resource>) ApiInvoker.deserialize(response, "array", Resource.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update a resource
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param name The name
   * @param newRid New rid
   * @param imageUrl Resource image URL
   * @param description Resource description
   * @param disabled If the object is disabled
   * @param publishDate The publish date
   * @param includedRid Included RIDs
   * @param fixedBundleRid Fixed bundle RIDs
   * @param addTermId Term IDs to add
   * @param delTermId Term IDs to delete
   * @param includedTagId Included tag IDs
   * @param includedTagName Included tag names
   * @param resourceUrl Resource URL
   * @return Resource
   */
  public Resource updateResource(String aid, String rid, String name, String newRid, String imageUrl, String description, Boolean disabled, Date publishDate, List<String> includedRid, List<String> fixedBundleRid, List<String> addTermId, List<String> delTermId, List<String> includedTagId, List<String> includedTagName, String resourceUrl) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling updateResource");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling updateResource");
    }
    

    // create path and map variables
    String path = "/publisher/resource/update";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (newRid != null) {
        builder.addTextBody("new_rid", ApiInvoker.parameterToString(newRid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (imageUrl != null) {
        builder.addTextBody("image_url", ApiInvoker.parameterToString(imageUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (disabled != null) {
        builder.addTextBody("disabled", ApiInvoker.parameterToString(disabled), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (publishDate != null) {
        builder.addTextBody("publish_date", ApiInvoker.parameterToString(publishDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (includedRid != null) {
        builder.addTextBody("included_rid", ApiInvoker.parameterToString(includedRid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (fixedBundleRid != null) {
        builder.addTextBody("fixed_bundle_rid", ApiInvoker.parameterToString(fixedBundleRid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (addTermId != null) {
        builder.addTextBody("add_term_id", ApiInvoker.parameterToString(addTermId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (delTermId != null) {
        builder.addTextBody("del_term_id", ApiInvoker.parameterToString(delTermId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (includedTagId != null) {
        builder.addTextBody("included_tag_id", ApiInvoker.parameterToString(includedTagId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (includedTagName != null) {
        builder.addTextBody("included_tag_name", ApiInvoker.parameterToString(includedTagName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (resourceUrl != null) {
        builder.addTextBody("resource_url", ApiInvoker.parameterToString(resourceUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("new_rid", ApiInvoker.parameterToString(newRid));
      formParams.put("image_url", ApiInvoker.parameterToString(imageUrl));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("disabled", ApiInvoker.parameterToString(disabled));
      formParams.put("publish_date", ApiInvoker.parameterToString(publishDate));
      formParams.put("included_rid", ApiInvoker.parameterToString(includedRid));
      formParams.put("fixed_bundle_rid", ApiInvoker.parameterToString(fixedBundleRid));
      formParams.put("add_term_id", ApiInvoker.parameterToString(addTermId));
      formParams.put("del_term_id", ApiInvoker.parameterToString(delTermId));
      formParams.put("included_tag_id", ApiInvoker.parameterToString(includedTagId));
      formParams.put("included_tag_name", ApiInvoker.parameterToString(includedTagName));
      formParams.put("resource_url", ApiInvoker.parameterToString(resourceUrl));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Resource) ApiInvoker.deserialize(response, "", Resource.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
