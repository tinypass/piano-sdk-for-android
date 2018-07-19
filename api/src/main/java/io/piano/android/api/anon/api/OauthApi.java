package io.piano.android.api.anon.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.anon.model.OAuthToken;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

public class OauthApi {

  private ApiInvoker apiInvoker;

  public OauthApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * OAuth 2.0 authorize
   * 
   * @param clientId Client ID of OAuth authorize
   * @param clientSecret Client secret of OAuth authorize
   * @param code OAuth code of OAuth authorize
   * @param refreshToken OAuth refresh token of OAuth authorize
   * @param grantType Grant type of OAuth authorize
   * @param redirectUri Redirect URI of OAuth authorize
   * @param username Username
   * @param password Password
   * @param state State
   * @param deviceId Device ID
   * @return OAuthToken
   */
  public OAuthToken authToken(String clientId, String clientSecret, String code, String refreshToken, String grantType, String redirectUri, String username, String password, String state, String deviceId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'clientId' is set
    if (clientId == null) {
       throw new ApiException(400, "Missing the required parameter 'clientId' when calling authToken");
    }
    

    // create path and map variables
    String path = "/oauth/authToken";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      "application/x-www-form-urlencoded"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (clientId != null) {
        builder.addTextBody("client_id", ApiInvoker.parameterToString(clientId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (clientSecret != null) {
        builder.addTextBody("client_secret", ApiInvoker.parameterToString(clientSecret), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (code != null) {
        builder.addTextBody("code", ApiInvoker.parameterToString(code), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (refreshToken != null) {
        builder.addTextBody("refresh_token", ApiInvoker.parameterToString(refreshToken), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (grantType != null) {
        builder.addTextBody("grant_type", ApiInvoker.parameterToString(grantType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (redirectUri != null) {
        builder.addTextBody("redirect_uri", ApiInvoker.parameterToString(redirectUri), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (username != null) {
        builder.addTextBody("username", ApiInvoker.parameterToString(username), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (password != null) {
        builder.addTextBody("password", ApiInvoker.parameterToString(password), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (state != null) {
        builder.addTextBody("state", ApiInvoker.parameterToString(state), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (deviceId != null) {
        builder.addTextBody("device_id", ApiInvoker.parameterToString(deviceId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("client_id", ApiInvoker.parameterToString(clientId));
      formParams.put("client_secret", ApiInvoker.parameterToString(clientSecret));
      formParams.put("code", ApiInvoker.parameterToString(code));
      formParams.put("refresh_token", ApiInvoker.parameterToString(refreshToken));
      formParams.put("grant_type", ApiInvoker.parameterToString(grantType));
      formParams.put("redirect_uri", ApiInvoker.parameterToString(redirectUri));
      formParams.put("username", ApiInvoker.parameterToString(username));
      formParams.put("password", ApiInvoker.parameterToString(password));
      formParams.put("state", ApiInvoker.parameterToString(state));
      formParams.put("device_id", ApiInvoker.parameterToString(deviceId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OAuthToken) ApiInvoker.deserialize(response, "", OAuthToken.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
