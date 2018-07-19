package io.piano.android.api.anon.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.anon.model.Access;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

public class AccessApi {

  private ApiInvoker apiInvoker;

  public AccessApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Returns the access details for user and rid
   * 
   * @param rid Unique id for resource
   * @param aid Application aid
   * @param tpAccessTokenV2 The Tinypass access token (v2)
   * @param umc The Tinypass user meter cookie (umc)
   * @param crossApp Provide cross application access for resource
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @return Access
   */
  public Access check(String rid, String aid, String tpAccessTokenV2, String umc, Boolean crossApp, String userToken, String userProvider, String userRef) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling check");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling check");
    }
    

    // create path and map variables
    String path = "/access/check";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tp_access_token_v2", tpAccessTokenV2));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "umc", umc));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "cross_app", crossApp));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_token", userToken));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_provider", userProvider));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_ref", userRef));
    

    

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
        return (Access) ApiInvoker.deserialize(response, "", Access.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Returns access list for user
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param active whether the object is active
   * @param expandBundled Expand bundled accesses
   * @param crossApp Provide cross application access for resource
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @return List<Access>
   */
  public List<Access> list(String aid, Integer offset, Integer limit, Boolean active, Boolean expandBundled, Boolean crossApp, String q, String orderBy, String orderDirection, String userToken, String userProvider, String userRef) throws ApiException {
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
    String path = "/access/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "active", active));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expand_bundled", expandBundled));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "cross_app", crossApp));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_token", userToken));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_provider", userProvider));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_ref", userRef));
    

    

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
        return (List<Access>) ApiInvoker.deserialize(response, "array", Access.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
