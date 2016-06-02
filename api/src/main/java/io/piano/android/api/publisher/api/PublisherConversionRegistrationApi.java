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

public class PublisherConversionRegistrationApi {

  private ApiInvoker apiInvoker;

  public PublisherConversionRegistrationApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Records a registration conversion
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param termId Term ID
   * @param email User&#39;s email address
   * @param firstName User&#39;s first name
   * @param lastName User&#39;s last name
   * @param createDate The creation date
   * @param tbc The Tinypass browser cookie (tbc)
   * @return TermConversion
   */
  public TermConversion createRegistrationConversion(String aid, String uid, String termId, String email, String firstName, String lastName, Date createDate, String tbc) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createRegistrationConversion");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling createRegistrationConversion");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling createRegistrationConversion");
    }
    
    // verify the required parameter 'email' is set
    if (email == null) {
       throw new ApiException(400, "Missing the required parameter 'email' when calling createRegistrationConversion");
    }
    

    // create path and map variables
    String path = "/publisher/conversion/registration/create";

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
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (firstName != null) {
        builder.addTextBody("first_name", ApiInvoker.parameterToString(firstName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (lastName != null) {
        builder.addTextBody("last_name", ApiInvoker.parameterToString(lastName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (createDate != null) {
        builder.addTextBody("create_date", ApiInvoker.parameterToString(createDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (tbc != null) {
        builder.addTextBody("tbc", ApiInvoker.parameterToString(tbc), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("email", ApiInvoker.parameterToString(email));
      formParams.put("first_name", ApiInvoker.parameterToString(firstName));
      formParams.put("last_name", ApiInvoker.parameterToString(lastName));
      formParams.put("create_date", ApiInvoker.parameterToString(createDate));
      formParams.put("tbc", ApiInvoker.parameterToString(tbc));
      
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
