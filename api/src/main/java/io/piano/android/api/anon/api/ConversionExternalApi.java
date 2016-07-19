package io.piano.android.api.anon.api;

import android.util.Pair;

import io.piano.android.api.anon.model.TermConversion;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ConversionExternalApi {

  private ApiInvoker apiInvoker;

  public ConversionExternalApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Records an external conversion
   * 
   * @param aid Application aid
   * @param termId Term ID
   * @param fields JSON object tht specify what fields have to be checked using external API
   * @param userToken User token
   * @param userProvider User token provider
   * @param userRef Encrypted user reference
   * @return TermConversion
   */
  public TermConversion externalVerifiedCreate(String aid, String termId, String fields, String userToken, String userProvider, String userRef) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling externalVerifiedCreate");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling externalVerifiedCreate");
    }
    
    // verify the required parameter 'fields' is set
    if (fields == null) {
       throw new ApiException(400, "Missing the required parameter 'fields' when calling externalVerifiedCreate");
    }
    

    // create path and map variables
    String path = "/conversion/external/create";

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
      
      if (fields != null) {
        builder.addTextBody("fields", ApiInvoker.parameterToString(fields), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (userToken != null) {
        builder.addTextBody("user_token", ApiInvoker.parameterToString(userToken), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (userProvider != null) {
        builder.addTextBody("user_provider", ApiInvoker.parameterToString(userProvider), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (userRef != null) {
        builder.addTextBody("user_ref", ApiInvoker.parameterToString(userRef), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("fields", ApiInvoker.parameterToString(fields));
      formParams.put("user_token", ApiInvoker.parameterToString(userToken));
      formParams.put("user_provider", ApiInvoker.parameterToString(userProvider));
      formParams.put("user_ref", ApiInvoker.parameterToString(userRef));
      
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
