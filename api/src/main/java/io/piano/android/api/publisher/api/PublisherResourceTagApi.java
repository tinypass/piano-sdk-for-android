package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.ResourceTag;
import io.piano.android.api.publisher.model.Resource;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherResourceTagApi {

  private ApiInvoker apiInvoker;

  public PublisherResourceTagApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Attach a resource tag to a resource
   * 
   * @param resourceTagId Id for resource tag
   * @param aid Application aid
   * @param rid Unique id for resource
   * @return void
   */
  public void attachTag(String resourceTagId, String aid, String rid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'resourceTagId' is set
    if (resourceTagId == null) {
       throw new ApiException(400, "Missing the required parameter 'resourceTagId' when calling attachTag");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling attachTag");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling attachTag");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/attach";

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
      
      if (resourceTagId != null) {
        builder.addTextBody("resource_tag_id", ApiInvoker.parameterToString(resourceTagId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
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
      formParams.put("resource_tag_id", ApiInvoker.parameterToString(resourceTagId));
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
   * Creates a resource tag
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param name The name
   * @param type The type
   * @return ResourceTag
   */
  public ResourceTag createTag(String aid, String rid, String name, String type) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createTag");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createTag");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createTag");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/create";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
    

    

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
        return (ResourceTag) ApiInvoker.deserialize(response, "", ResourceTag.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete a resource tag
   * 
   * @param resourceTagId Id for resource tag
   * @param aid Application aid
   * @return void
   */
  public void deleteTag(String resourceTagId, String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'resourceTagId' is set
    if (resourceTagId == null) {
       throw new ApiException(400, "Missing the required parameter 'resourceTagId' when calling deleteTag");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling deleteTag");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/delete";

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
      
      if (resourceTagId != null) {
        builder.addTextBody("resource_tag_id", ApiInvoker.parameterToString(resourceTagId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("resource_tag_id", ApiInvoker.parameterToString(resourceTagId));
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      
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
   * Detach a resource tag from a resource
   * 
   * @param resourceTagId Id for resource tag
   * @param aid Application aid
   * @param rid Unique id for resource
   * @return void
   */
  public void detachTag(String resourceTagId, String aid, String rid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'resourceTagId' is set
    if (resourceTagId == null) {
       throw new ApiException(400, "Missing the required parameter 'resourceTagId' when calling detachTag");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling detachTag");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling detachTag");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/detach";

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
      
      if (resourceTagId != null) {
        builder.addTextBody("resource_tag_id", ApiInvoker.parameterToString(resourceTagId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
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
      formParams.put("resource_tag_id", ApiInvoker.parameterToString(resourceTagId));
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
   * Get a resource tag
   * 
   * @param resourceTagId Id for resource tag
   * @param aid Application aid
   * @return ResourceTag
   */
  public ResourceTag getTag(String resourceTagId, String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'resourceTagId' is set
    if (resourceTagId == null) {
       throw new ApiException(400, "Missing the required parameter 'resourceTagId' when calling getTag");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getTag");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resource_tag_id", resourceTagId));
    
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
        return (ResourceTag) ApiInvoker.deserialize(response, "", ResourceTag.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists bundles for tags
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param type Resource type
   * @param includedTagId Included tag IDs
   * @param q Search value
   * @param disabled Disabled flag
   * @param bundleType Bundle type
   * @return List<Resource>
   */
  public List<Resource> listBundlesForTags(String aid, Integer offset, Integer limit, String orderBy, String orderDirection, String type, List<String> includedTagId, String q, Boolean disabled, Integer bundleType) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listBundlesForTags");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listBundlesForTags");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listBundlesForTags");
    }
    
    // verify the required parameter 'orderBy' is set
    if (orderBy == null) {
       throw new ApiException(400, "Missing the required parameter 'orderBy' when calling listBundlesForTags");
    }
    
    // verify the required parameter 'orderDirection' is set
    if (orderDirection == null) {
       throw new ApiException(400, "Missing the required parameter 'orderDirection' when calling listBundlesForTags");
    }
    
    // verify the required parameter 'type' is set
    if (type == null) {
       throw new ApiException(400, "Missing the required parameter 'type' when calling listBundlesForTags");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/bundles";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "included_tag_id", includedTagId));
    
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
   * Lists resource tags for an app or a resource
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param tagType Resource tag type
   * @param rid Unique id for resource
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<ResourceTag>
   */
  public List<ResourceTag> listTags(String aid, Integer offset, Integer limit, Integer tagType, String rid, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listTags");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling listTags");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling listTags");
    }
    
    // verify the required parameter 'tagType' is set
    if (tagType == null) {
       throw new ApiException(400, "Missing the required parameter 'tagType' when calling listTags");
    }
    

    // create path and map variables
    String path = "/publisher/resource/tag/list";

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
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tag_type", tagType));
    

    

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
        return (List<ResourceTag>) ApiInvoker.deserialize(response, "array", ResourceTag.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
