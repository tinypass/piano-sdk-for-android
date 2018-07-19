package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Access;

public class PublisherUserAccessApi {

  private ApiInvoker apiInvoker;

  public PublisherUserAccessApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Check if user has access to a specific resource
   * Returns an Access object if the user has access to specific resource
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param rid Unique id for resource
   * @param crossApp Provide cross application access for resource
   * @return Access
   */
  public Access checkAccess(String aid, String uid, String rid, Boolean crossApp) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling checkAccess");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling checkAccess");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling checkAccess");
    }
    

    // create path and map variables
    String path = "/publisher/user/access/check";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "cross_app", crossApp));
    

    

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
   * Grants access to a user
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param sendEmail Flag that email should be sent
   * @param uid User&#39;s uid
   * @param emails User&#39;s email addresses
   * @param expireDate The access item expire date; null means unlimited
   * @param url The URL of the page
   * @param message Message
   * @return List<Access>
   */
  public List<Access> grantAccess(String aid, String rid, Boolean sendEmail, String uid, String emails, Integer expireDate, String url, String message) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling grantAccess");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling grantAccess");
    }
    
    // verify the required parameter 'sendEmail' is set
    if (sendEmail == null) {
       throw new ApiException(400, "Missing the required parameter 'sendEmail' when calling grantAccess");
    }
    

    // create path and map variables
    String path = "/publisher/user/access/grant";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "emails", emails));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expire_date", expireDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "send_email", sendEmail));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "url", url));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "message", message));
    

    

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
  
  /**
   * Grant access to users
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param sendEmail Flag that email should be sent
   * @param emails User&#39;s email addresses
   * @param fileId The file id
   * @param expireDate The access item expire date; null means unlimited
   * @param message Message
   * @return Integer
   */
  public Integer grantAccessToUsers(String aid, List<String> rid, Boolean sendEmail, List<String> emails, String fileId, Date expireDate, String message) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling grantAccessToUsers");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling grantAccessToUsers");
    }
    
    // verify the required parameter 'sendEmail' is set
    if (sendEmail == null) {
       throw new ApiException(400, "Missing the required parameter 'sendEmail' when calling grantAccessToUsers");
    }
    

    // create path and map variables
    String path = "/publisher/user/access/grantToUsers";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "emails", emails));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "file_id", fileId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expire_date", expireDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "send_email", sendEmail));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "message", message));
    

    

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
        return (Integer) ApiInvoker.deserialize(response, "", Integer.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists a user&#39;s access
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param expandBundled Expand bundled accesses
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @param crossApp Provide cross application access for resource
   * @return List<Access>
   */
  public List<Access> listAccess(String aid, String uid, Integer offset, Integer limit, Boolean expandBundled, String q, String orderBy, String orderDirection, Boolean crossApp) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listAccess");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling listAccess");
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
    String path = "/publisher/user/access/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expand_bundled", expandBundled));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "cross_app", crossApp));
    

    

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
  
  /**
   * Revokes a user&#39;s access
   * 
   * @param accessId The access id
   * @return Access
   */
  public Access revokeAccess(String accessId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'accessId' is set
    if (accessId == null) {
       throw new ApiException(400, "Missing the required parameter 'accessId' when calling revokeAccess");
    }
    

    // create path and map variables
    String path = "/publisher/user/access/revoke";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
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
   * Update user access
   * 
   * @param accessId The access id
   * @param expireDate Expire date
   * @return Access
   */
  public Access update(String accessId, Date expireDate) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'accessId' is set
    if (accessId == null) {
       throw new ApiException(400, "Missing the required parameter 'accessId' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/user/access/update";

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
      
      if (accessId != null) {
        builder.addTextBody("access_id", ApiInvoker.parameterToString(accessId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (expireDate != null) {
        builder.addTextBody("expire_date", ApiInvoker.parameterToString(expireDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("access_id", ApiInvoker.parameterToString(accessId));
      formParams.put("expire_date", ApiInvoker.parameterToString(expireDate));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
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
  
}
