package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Term;

public class PublisherTermCustomApi {

  private ApiInvoker apiInvoker;

  public PublisherTermCustomApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates a custom term
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param name Term name
   * @param customRequireUser Whether a valid user is required to complete the term
   * @param customDefaultAccessPeriod The default access period
   * @param description Term description
   * @return Term
   */
  public Term createCustomTerm(String aid, String rid, String name, Boolean customRequireUser, Integer customDefaultAccessPeriod, String description) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createCustomTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createCustomTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createCustomTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/custom/create";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customRequireUser != null) {
        builder.addTextBody("custom_require_user", ApiInvoker.parameterToString(customRequireUser), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customDefaultAccessPeriod != null) {
        builder.addTextBody("custom_default_access_period", ApiInvoker.parameterToString(customDefaultAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("custom_require_user", ApiInvoker.parameterToString(customRequireUser));
      formParams.put("custom_default_access_period", ApiInvoker.parameterToString(customDefaultAccessPeriod));
      formParams.put("description", ApiInvoker.parameterToString(description));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Term) ApiInvoker.deserialize(response, "", Term.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Updates a custom term
   * 
   * @param termId Term ID
   * @param rid Unique id for resource
   * @param name Term name
   * @param customRequireUser Whether a valid user is required to complete the term
   * @param customDefaultAccessPeriod The default access period
   * @param description Term description
   * @return Term
   */
  public Term updateCustomTerm(String termId, String rid, String name, Boolean customRequireUser, Integer customDefaultAccessPeriod, String description) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling updateCustomTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/custom/update";

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
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customRequireUser != null) {
        builder.addTextBody("custom_require_user", ApiInvoker.parameterToString(customRequireUser), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customDefaultAccessPeriod != null) {
        builder.addTextBody("custom_default_access_period", ApiInvoker.parameterToString(customDefaultAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("custom_require_user", ApiInvoker.parameterToString(customRequireUser));
      formParams.put("custom_default_access_period", ApiInvoker.parameterToString(customDefaultAccessPeriod));
      formParams.put("description", ApiInvoker.parameterToString(description));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Term) ApiInvoker.deserialize(response, "", Term.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
