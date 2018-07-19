package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Term;

public class PublisherTermApi {

  private ApiInvoker apiInvoker;

  public PublisherTermApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Lists terms applicable to promotion
   * 
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param promotionId Promotion id
   * @param q Search value
   * @param orderBy Field to order by: term_name, resource_type, resource_name
   * @param orderDirection Order direction (asc/desc)
   * @param includeType Type of terms to include into the list
   * @param excludeType Type of terms to exclude from the list
   * @param termId Term id to list
   * @param resourceType Type of resource
   * @param source Type of external API source
   * @param type Type of term to list
   * @return List<Term>
   */
  public List<Term> applicable(Integer offset, Integer limit, String promotionId, String q, String orderBy, String orderDirection, List<String> includeType, List<String> excludeType, String termId, String resourceType, List<String> source, String type) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling applicable");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling applicable");
    }
    

    // create path and map variables
    String path = "/publisher/term/applicable";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "include_type", includeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "exclude_type", excludeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resource_type", resourceType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "source", source));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    

    

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
   * Returns a count of terms
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @param orderBy Field to order by: term_name, resource_type, resource_name
   * @param orderDirection Order direction (asc/desc)
   * @param includeType Type of terms to include into the list
   * @param excludeType Type of terms to exclude from the list
   * @param termId Term id to list
   * @param resourceType Type of resource
   * @param source Type of external API source
   * @param type Type of term to list
   * @return Long
   */
  public Long count(String aid, Integer offset, Integer limit, String q, String orderBy, String orderDirection, List<String> includeType, List<String> excludeType, String termId, String resourceType, List<String> source, String type) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling count");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling count");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/term/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "include_type", includeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "exclude_type", excludeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resource_type", resourceType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "source", source));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    

    

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
        return (Long) ApiInvoker.deserialize(response, "", Long.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Delete a term
   * 
   * @param termId Term ID
   * @return void
   */
  public void delete(String termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/term/delete";

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
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
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
   * Get a term
   * 
   * @param termId Term ID
   * @return Term
   */
  public Term get(String termId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/term/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    

    

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
   * Lists terms
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param rid Unique id for resource
   * @param q Search value
   * @param orderBy Field to order by: term_name, resource_type, resource_name
   * @param orderDirection Order direction (asc/desc)
   * @param includeType Type of terms to include into the list
   * @param excludeType Type of terms to exclude from the list
   * @param termId Term id to list
   * @param resourceType Type of resource
   * @param source Type of external API source
   * @param type Type of term to list
   * @return List<Term>
   */
  public List<Term> list(String aid, Integer offset, Integer limit, String rid, String q, String orderBy, String orderDirection, List<String> includeType, List<String> excludeType, String termId, String resourceType, List<String> source, String type) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling list");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling list");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling list");
    }
    

    // create path and map variables
    String path = "/publisher/term/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "rid", rid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "include_type", includeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "exclude_type", excludeType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "term_id", termId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resource_type", resourceType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "source", source));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    

    

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
  
}
