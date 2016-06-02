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

public class PublisherTermExternalApi {

  private ApiInvoker apiInvoker;

  public PublisherTermExternalApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates an external verified term
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param externalApiId External API Configuration ID
   * @param name Term name
   * @param description Term description
   * @param evtFixedTimeAccessPeriod Period to grant access for in days
   * @param evtGracePeriod External API grace period
   * @param evtVerificationPeriod External verification period
   * @param evtItunesBundleId iTunes bundle id
   * @param evtItunesProductId iTunes  product id
   * @return Term
   */
  public Term createExternalVerificationTerm(String aid, String rid, String externalApiId, String name, String description, Integer evtFixedTimeAccessPeriod, Integer evtGracePeriod, Integer evtVerificationPeriod, String evtItunesBundleId, String evtItunesProductId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createExternalVerificationTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createExternalVerificationTerm");
    }
    
    // verify the required parameter 'externalApiId' is set
    if (externalApiId == null) {
       throw new ApiException(400, "Missing the required parameter 'externalApiId' when calling createExternalVerificationTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createExternalVerificationTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/external/create";

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
      
      if (externalApiId != null) {
        builder.addTextBody("external_api_id", ApiInvoker.parameterToString(externalApiId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtFixedTimeAccessPeriod != null) {
        builder.addTextBody("evt_fixed_time_access_period", ApiInvoker.parameterToString(evtFixedTimeAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtGracePeriod != null) {
        builder.addTextBody("evt_grace_period", ApiInvoker.parameterToString(evtGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtVerificationPeriod != null) {
        builder.addTextBody("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtItunesBundleId != null) {
        builder.addTextBody("evt_itunes_bundle_id", ApiInvoker.parameterToString(evtItunesBundleId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtItunesProductId != null) {
        builder.addTextBody("evt_itunes_product_id", ApiInvoker.parameterToString(evtItunesProductId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("external_api_id", ApiInvoker.parameterToString(externalApiId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("evt_fixed_time_access_period", ApiInvoker.parameterToString(evtFixedTimeAccessPeriod));
      formParams.put("evt_grace_period", ApiInvoker.parameterToString(evtGracePeriod));
      formParams.put("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod));
      formParams.put("evt_itunes_bundle_id", ApiInvoker.parameterToString(evtItunesBundleId));
      formParams.put("evt_itunes_product_id", ApiInvoker.parameterToString(evtItunesProductId));
      
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
   * Updates an external verified term
   * 
   * @param termId Term ID
   * @param externalApiId External API Configuration ID
   * @param name Term name
   * @param rid Unique id for resource
   * @param description Term description
   * @param evtFixedTimeAccessPeriod Period to grant access for in days
   * @param evtGracePeriod External API grace period
   * @param evtVerificationPeriod External verification period
   * @param evtItunesBundleId iTunes bundle id
   * @param evtItunesProductId iTunes  product id
   * @return Term
   */
  public Term updateExternalVerificationTerm(String termId, String externalApiId, String name, String rid, String description, Integer evtFixedTimeAccessPeriod, Integer evtGracePeriod, Integer evtVerificationPeriod, String evtItunesBundleId, String evtItunesProductId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling updateExternalVerificationTerm");
    }
    
    // verify the required parameter 'externalApiId' is set
    if (externalApiId == null) {
       throw new ApiException(400, "Missing the required parameter 'externalApiId' when calling updateExternalVerificationTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling updateExternalVerificationTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/external/update";

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
      
      if (externalApiId != null) {
        builder.addTextBody("external_api_id", ApiInvoker.parameterToString(externalApiId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtFixedTimeAccessPeriod != null) {
        builder.addTextBody("evt_fixed_time_access_period", ApiInvoker.parameterToString(evtFixedTimeAccessPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtGracePeriod != null) {
        builder.addTextBody("evt_grace_period", ApiInvoker.parameterToString(evtGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtVerificationPeriod != null) {
        builder.addTextBody("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtItunesBundleId != null) {
        builder.addTextBody("evt_itunes_bundle_id", ApiInvoker.parameterToString(evtItunesBundleId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtItunesProductId != null) {
        builder.addTextBody("evt_itunes_product_id", ApiInvoker.parameterToString(evtItunesProductId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("external_api_id", ApiInvoker.parameterToString(externalApiId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("evt_fixed_time_access_period", ApiInvoker.parameterToString(evtFixedTimeAccessPeriod));
      formParams.put("evt_grace_period", ApiInvoker.parameterToString(evtGracePeriod));
      formParams.put("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod));
      formParams.put("evt_itunes_bundle_id", ApiInvoker.parameterToString(evtItunesBundleId));
      formParams.put("evt_itunes_product_id", ApiInvoker.parameterToString(evtItunesProductId));
      
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
