package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.SalesTaxRateModel;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherTaxRateApi {

  private ApiInvoker apiInvoker;

  public PublisherTaxRateApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Sets Tinypass to charge sales tax rate for selected states
   * 
   * @param aid Application aid
   * @param stateChargeIds States to charge IDs
   * @param stateDontChargeIds States to don&#39;t charge IDs
   * @return List<SalesTaxRateModel>
   */
  public List<SalesTaxRateModel> charge(String aid, List<String> stateChargeIds, List<String> stateDontChargeIds) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling charge");
    }
    
    // verify the required parameter 'stateChargeIds' is set
    if (stateChargeIds == null) {
       throw new ApiException(400, "Missing the required parameter 'stateChargeIds' when calling charge");
    }
    
    // verify the required parameter 'stateDontChargeIds' is set
    if (stateDontChargeIds == null) {
       throw new ApiException(400, "Missing the required parameter 'stateDontChargeIds' when calling charge");
    }
    

    // create path and map variables
    String path = "/publisher/tax/rate/charge";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "state_charge_ids", stateChargeIds));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "state_dont_charge_ids", stateDontChargeIds));
    

    

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
        return (List<SalesTaxRateModel>) ApiInvoker.deserialize(response, "array", SalesTaxRateModel.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists sales tax rates for states and charge attribute
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param q Search value
   * @return List<SalesTaxRateModel>
   */
  public List<SalesTaxRateModel> list(String aid, Integer offset, Integer limit, String q) throws ApiException {
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
    String path = "/publisher/tax/rate/list";

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
        return (List<SalesTaxRateModel>) ApiInvoker.deserialize(response, "array", SalesTaxRateModel.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
