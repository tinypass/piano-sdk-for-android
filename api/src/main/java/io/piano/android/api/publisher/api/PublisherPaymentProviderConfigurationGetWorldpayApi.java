package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.WorldpayConfiguration;
import io.piano.android.api.publisher.model.WorldpayPaymentMethod;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherPaymentProviderConfigurationGetWorldpayApi {

  private ApiInvoker apiInvoker;

  public PublisherPaymentProviderConfigurationGetWorldpayApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Lists available payment provider configurations for Worldpay credit cards
   * 
   * @param aid Application aid
   * @return WorldpayConfiguration
   */
  public WorldpayConfiguration getWorldpayCC(String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getWorldpayCC");
    }
    

    // create path and map variables
    String path = "/publisher/payment/provider/configuration/get/worldpay/cc";

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
        return (WorldpayConfiguration) ApiInvoker.deserialize(response, "", WorldpayConfiguration.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists available payment provider configurations for Worldpay iDeal
   * 
   * @param aid Application aid
   * @return WorldpayConfiguration
   */
  public WorldpayConfiguration getWorldpayIdeal(String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getWorldpayIdeal");
    }
    

    // create path and map variables
    String path = "/publisher/payment/provider/configuration/get/worldpay/ideal";

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
        return (WorldpayConfiguration) ApiInvoker.deserialize(response, "", WorldpayConfiguration.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists available payment provider configurations for Worldpay PayPal
   * 
   * @param aid Application aid
   * @return WorldpayConfiguration
   */
  public WorldpayConfiguration getWorldpayPaypal(String aid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling getWorldpayPaypal");
    }
    

    // create path and map variables
    String path = "/publisher/payment/provider/configuration/get/worldpay/paypal";

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
        return (WorldpayConfiguration) ApiInvoker.deserialize(response, "", WorldpayConfiguration.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists available worldpay payment methods
   * 
   * @param username Worldpay username
   * @param password Worldpay password
   * @param merchantCode Worldpay merchant code
   * @param installationId Worldpay installation id
   * @param currencyCode Currency code by ISO 4217 standard
   * @return WorldpayPaymentMethod
   */
  public WorldpayPaymentMethod listWorldpayPaymentMethod(String username, String password, String merchantCode, String installationId, String currencyCode) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'username' is set
    if (username == null) {
       throw new ApiException(400, "Missing the required parameter 'username' when calling listWorldpayPaymentMethod");
    }
    
    // verify the required parameter 'password' is set
    if (password == null) {
       throw new ApiException(400, "Missing the required parameter 'password' when calling listWorldpayPaymentMethod");
    }
    
    // verify the required parameter 'merchantCode' is set
    if (merchantCode == null) {
       throw new ApiException(400, "Missing the required parameter 'merchantCode' when calling listWorldpayPaymentMethod");
    }
    
    // verify the required parameter 'installationId' is set
    if (installationId == null) {
       throw new ApiException(400, "Missing the required parameter 'installationId' when calling listWorldpayPaymentMethod");
    }
    
    // verify the required parameter 'currencyCode' is set
    if (currencyCode == null) {
       throw new ApiException(400, "Missing the required parameter 'currencyCode' when calling listWorldpayPaymentMethod");
    }
    

    // create path and map variables
    String path = "/publisher/payment/provider/configuration/get/worldpay/methods";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "username", username));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "merchant_code", merchantCode));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "installation_id", installationId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency_code", currencyCode));
    

    

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
        return (WorldpayPaymentMethod) ApiInvoker.deserialize(response, "", WorldpayPaymentMethod.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
