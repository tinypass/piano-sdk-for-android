package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.ResultBoolean;

public class PublisherConsentPageApi {

  private ApiInvoker apiInvoker;

  public PublisherConsentPageApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Save or discard consent changes
   * 
   * @param aid Application aid
   * @param consentBoxIdList Consent box config ID list
   * @return ResultBoolean
   */
  public ResultBoolean savePage(String aid, List<String> consentBoxIdList) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling savePage");
    }
    
    // verify the required parameter 'consentBoxIdList' is set
    if (consentBoxIdList == null) {
       throw new ApiException(400, "Missing the required parameter 'consentBoxIdList' when calling savePage");
    }
    

    // create path and map variables
    String path = "/publisher/consent/page/save";

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
      
      if (consentBoxIdList != null) {
        builder.addTextBody("consent_box_id_list", ApiInvoker.parameterToString(consentBoxIdList), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("consent_box_id_list", ApiInvoker.parameterToString(consentBoxIdList));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (ResultBoolean) ApiInvoker.deserialize(response, "", ResultBoolean.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
