package io.piano.android.api.anon.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.anon.model.AccessTokenList;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

public class AccessTokenApi {

  private ApiInvoker apiInvoker;

  public AccessTokenApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Returns the list of access tokens
   * 
   * @param aid Application aid
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @return AccessTokenList
   */
  public AccessTokenList tokenList(String aid, String userToken, String userProvider, String userRef) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling tokenList");
    }
    

    // create path and map variables
    String path = "/access/token/list";

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
        return (AccessTokenList) ApiInvoker.deserialize(response, "", AccessTokenList.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
