package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.TermConversion;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherConversionCustomApi {

  private ApiInvoker apiInvoker;

  public PublisherConversionCustomApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Records a custom conversion
   * 
   * @param aid Application aid
   * @param termId Term ID
   * @param uid User&#39;s uid
   * @param accessPeriod The length of time to grant access for
   * @return TermConversion
   */
  public TermConversion customCreate(String aid, String termId, String uid, Integer accessPeriod) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling customCreate");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling customCreate");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling customCreate");
    }
    
    // verify the required parameter 'accessPeriod' is set
    if (accessPeriod == null) {
       throw new ApiException(400, "Missing the required parameter 'accessPeriod' when calling customCreate");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/custom/create";

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
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accessPeriod != null) {
        builder.addTextBody("access_period", ApiInvoker.parameterToString(accessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("access_period", ApiInvoker.parameterToString(accessPeriod));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (TermConversion) ApiInvoker.deserialize(response, "", TermConversion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
