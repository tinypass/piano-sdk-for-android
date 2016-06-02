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

public class PublisherOfferTermApi {

  private ApiInvoker apiInvoker;

  public PublisherOfferTermApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Add a term to an offer
   * 
   * @param offerId The offer ID
   * @param termId Term ID
   * @return void
   */
  public void addTerms(String offerId, List<String> termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerId' when calling addTerms");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling addTerms");
    }
    

    // create path and map variables
    String path = "/publisher/offer/term/add";

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
      
      if (offerId != null) {
        builder.addTextBody("offer_id", ApiInvoker.parameterToString(offerId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("offer_id", ApiInvoker.parameterToString(offerId));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists terms in an offer
   * 
   * @param offerId The offer ID
   * @return List<Term>
   */
  public List<Term> listTerms(String offerId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerId' when calling listTerms");
    }
    

    // create path and map variables
    String path = "/publisher/offer/term/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offer_id", offerId));
    

    

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
        return (List<Term>) ApiInvoker.deserialize(response, "array", Term.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Reorder terms in offer
   * 
   * @param offerId The offer ID
   * @param termId Term ID
   * @return void
   */
  public void reorderTerms(String offerId, List<String> termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerId' when calling reorderTerms");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling reorderTerms");
    }
    

    // create path and map variables
    String path = "/publisher/offer/term/reorder";

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
      
      if (offerId != null) {
        builder.addTextBody("offer_id", ApiInvoker.parameterToString(offerId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("offer_id", ApiInvoker.parameterToString(offerId));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Remove a term from an offer
   * 
   * @param offerId The offer ID
   * @param termId Term ID
   * @return void
   */
  public void unlinkTerms(String offerId, List<String> termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerId' when calling unlinkTerms");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling unlinkTerms");
    }
    

    // create path and map variables
    String path = "/publisher/offer/term/remove";

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
      
      if (offerId != null) {
        builder.addTextBody("offer_id", ApiInvoker.parameterToString(offerId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("offer_id", ApiInvoker.parameterToString(offerId));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
