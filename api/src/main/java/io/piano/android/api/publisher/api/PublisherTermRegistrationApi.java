package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.Term;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherTermRegistrationApi {

  private ApiInvoker apiInvoker;

  public PublisherTermRegistrationApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates an registration term
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param name Term name
   * @param registrationAccessPeriod The access time period 
   * @param description Term description
   * @param registrationGracePeriod The time period after registration that will count it as a valid registration conversion
   * @return Term
   */
  public Term createRegistrationTerm(String aid, String rid, String name, Long registrationAccessPeriod, String description, Long registrationGracePeriod) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createRegistrationTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createRegistrationTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createRegistrationTerm");
    }
    
    // verify the required parameter 'registrationAccessPeriod' is set
    if (registrationAccessPeriod == null) {
       throw new ApiException(400, "Missing the required parameter 'registrationAccessPeriod' when calling createRegistrationTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/registration/create";

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
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registrationAccessPeriod != null) {
        builder.addTextBody("registration_access_period", ApiInvoker.parameterToString(registrationAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registrationGracePeriod != null) {
        builder.addTextBody("registration_grace_period", ApiInvoker.parameterToString(registrationGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("registration_access_period", ApiInvoker.parameterToString(registrationAccessPeriod));
      formParams.put("registration_grace_period", ApiInvoker.parameterToString(registrationGracePeriod));
      
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
   * Updates a registration term
   * 
   * @param termId Term ID
   * @param rid Unique id for resource
   * @param name Term name
   * @param description Term description
   * @param registrationAccessPeriod The access time period 
   * @param registrationGracePeriod The time period after registration that will count it as a valid registration conversion
   * @return Term
   */
  public Term updateRegistrationTerm(String termId, String rid, String name, String description, Long registrationAccessPeriod, Long registrationGracePeriod) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling updateRegistrationTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/registration/update";

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
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registrationAccessPeriod != null) {
        builder.addTextBody("registration_access_period", ApiInvoker.parameterToString(registrationAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registrationGracePeriod != null) {
        builder.addTextBody("registration_grace_period", ApiInvoker.parameterToString(registrationGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("registration_access_period", ApiInvoker.parameterToString(registrationAccessPeriod));
      formParams.put("registration_grace_period", ApiInvoker.parameterToString(registrationGracePeriod));
      
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
