package io.piano.android.api.anon.api;

import android.util.Pair;

import io.piano.android.api.anon.model.UserMeter;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AnonMeterApi {

  private ApiInvoker apiInvoker;

  public AnonMeterApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Loads a meter
   * 
   * @param umc The Tinypass user meter cookie (umc)
   * @param paywallId The paywall id
   * @param url The URL of the page
   * @param referer The page referer
   * @param trackPageView Track page views
   * @param transactionId Transaction id
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @param meterName Current meter name
   * @param pageviewId PageView ID
   * @param tbc The Tinypass browser cookie (tbc)
   * @return UserMeter
   */
  public UserMeter load(String umc, Long paywallId, String url, String referer, Boolean trackPageView, String transactionId, String userToken, String userProvider, String userRef, String meterName, String pageviewId, String tbc) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/anon/meter/load";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "umc", umc));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "paywall_id", paywallId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "url", url));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "referer", referer));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "track_page_view", trackPageView));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "transaction_id", transactionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_token", userToken));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_provider", userProvider));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "user_ref", userRef));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "meter_name", meterName));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "pageview_id", pageviewId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tbc", tbc));
    

    

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
        return (UserMeter) ApiInvoker.deserialize(response, "", UserMeter.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Removes a user meter cookie
   * 
   * @param paywallId The paywall id
   * @return UserMeter
   */
  public UserMeter logout(String paywallId) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/anon/meter/logout";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "paywall_id", paywallId));
    

    

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
        return (UserMeter) ApiInvoker.deserialize(response, "", UserMeter.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
