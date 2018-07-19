package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.EdgilPaywayConfiguration;

public class PublisherPaymentProviderConfigurationCreateEdgilApi {

  private ApiInvoker apiInvoker;

  public PublisherPaymentProviderConfigurationCreateEdgilApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates new payment provider configuration for Edgil Payway
   * 
   * @param aid Application aid
   * @param title The title
   * @param properties Edgil Payway payment provider properties
   * @return EdgilPaywayConfiguration
   */
  public EdgilPaywayConfiguration createEdgilPaywayConfiguration(String aid, String title, String properties) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createEdgilPaywayConfiguration");
    }
    
    // verify the required parameter 'title' is set
    if (title == null) {
       throw new ApiException(400, "Missing the required parameter 'title' when calling createEdgilPaywayConfiguration");
    }
    
    // verify the required parameter 'properties' is set
    if (properties == null) {
       throw new ApiException(400, "Missing the required parameter 'properties' when calling createEdgilPaywayConfiguration");
    }
    

    // create path and map variables
    String path = "/publisher/payment/provider/configuration/create/edgil/payway";

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
      
      if (title != null) {
        builder.addTextBody("title", ApiInvoker.parameterToString(title), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (properties != null) {
        builder.addTextBody("properties", ApiInvoker.parameterToString(properties), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("title", ApiInvoker.parameterToString(title));
      formParams.put("properties", ApiInvoker.parameterToString(properties));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (EdgilPaywayConfiguration) ApiInvoker.deserialize(response, "", EdgilPaywayConfiguration.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
