package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.TermConversion;
import java.util.Date;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherConversionExternalApi {

  private ApiInvoker apiInvoker;

  public PublisherConversionExternalApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Records an external conversion
   * 
   * @param aid Application aid
   * @param termId Term ID
   * @param uid User&#39;s uid
   * @param fields JSON object tht specify what fields have to be checked using external API
   * @param checkValidity Check validity of passed values or use them forcely
   * @param accessTo access_to
   * @return TermConversion
   */
  public TermConversion externalVerificationCreate(String aid, String termId, String uid, String fields, Boolean checkValidity, Date accessTo) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling externalVerificationCreate");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling externalVerificationCreate");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling externalVerificationCreate");
    }
    
    // verify the required parameter 'fields' is set
    if (fields == null) {
       throw new ApiException(400, "Missing the required parameter 'fields' when calling externalVerificationCreate");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/external/create";

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
      
      if (fields != null) {
        builder.addTextBody("fields", ApiInvoker.parameterToString(fields), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (checkValidity != null) {
        builder.addTextBody("check_validity", ApiInvoker.parameterToString(checkValidity), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accessTo != null) {
        builder.addTextBody("access_to", ApiInvoker.parameterToString(accessTo), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("fields", ApiInvoker.parameterToString(fields));
      formParams.put("check_validity", ApiInvoker.parameterToString(checkValidity));
      formParams.put("access_to", ApiInvoker.parameterToString(accessTo));
      
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
