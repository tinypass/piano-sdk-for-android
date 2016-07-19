package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.TaxProviderConfiguration;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherTaxTaxJarApi {

  private ApiInvoker apiInvoker;

  public PublisherTaxTaxJarApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates a new tax provider configuration for TaxJar
   * 
   * @param aid Application aid
   * @param taxJarProviderToken TaxJar configuration auth token
   * @return TaxProviderConfiguration
   */
  public TaxProviderConfiguration createTaxJar(String aid, String taxJarProviderToken) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createTaxJar");
    }
    
    // verify the required parameter 'taxJarProviderToken' is set
    if (taxJarProviderToken == null) {
       throw new ApiException(400, "Missing the required parameter 'taxJarProviderToken' when calling createTaxJar");
    }
    

    // create path and map variables
    String path = "/publisher/tax/taxJar/create";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tax_jar_provider_token", taxJarProviderToken));
    

    

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
        return (TaxProviderConfiguration) ApiInvoker.deserialize(response, "", TaxProviderConfiguration.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
