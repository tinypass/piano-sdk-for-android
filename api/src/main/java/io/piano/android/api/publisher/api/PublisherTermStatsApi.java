package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.TermStats;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherTermStatsApi {

  private ApiInvoker apiInvoker;

  public PublisherTermStatsApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Lists term stats
   * 
   * @param aid Application aid
   * @param termId Term ID
   * @return List<TermStats>
   */
  public List<TermStats> listTermStats(String aid, List<String> termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling listTermStats");
    }
    

    // create path and map variables
    String path = "/publisher/term/stats/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "term_id", termId));
    

    

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
        return (List<TermStats>) ApiInvoker.deserialize(response, "array", TermStats.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
