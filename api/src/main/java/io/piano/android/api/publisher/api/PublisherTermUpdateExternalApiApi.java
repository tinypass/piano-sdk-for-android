package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherTermUpdateExternalApiApi {

  private ApiInvoker apiInvoker;

  public PublisherTermUpdateExternalApiApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Sets new external API configuration to term
   * 
   * @param termId Term ID
   * @param aid Application aid
   * @param externalApiId External API Configuration ID
   * @return Boolean
   */
  public Boolean setExternalApiConfiguration(String termId, String aid, String externalApiId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling setExternalApiConfiguration");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling setExternalApiConfiguration");
    }
    

    // create path and map variables
    String path = "/publisher/term/update/external/api/configuration";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "external_api_id", externalApiId));
    
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
        return (Boolean) ApiInvoker.deserialize(response, "", Boolean.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
