package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

public class PublisherGdprApi {

  private ApiInvoker apiInvoker;

  public PublisherGdprApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Accepts GDPR request
   * 
   * @param aid Application aid
   * @param dataSubjectRequest A JSON Web token
   * @param uid User&#39;s uid
   * @param tbc The Tinypass browser cookie (tbc)
   * @return String
   */
  public String check(String aid, String dataSubjectRequest, String uid, String tbc) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling check");
    }
    
    // verify the required parameter 'dataSubjectRequest' is set
    if (dataSubjectRequest == null) {
       throw new ApiException(400, "Missing the required parameter 'dataSubjectRequest' when calling check");
    }
    

    // create path and map variables
    String path = "/publisher/gdpr/oath";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "uid", uid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tbc", tbc));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "dataSubjectRequest", dataSubjectRequest));
    

    

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
        return (String) ApiInvoker.deserialize(response, "", String.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
