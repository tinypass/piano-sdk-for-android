package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.SubscriptionSummaryModelDefinition;
import java.util.Date;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherSubscriptionExportApi {

  private ApiInvoker apiInvoker;

  public PublisherSubscriptionExportApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Export summary subscription
   * 
   * @param aid Application aid
   * @param startDate The start date
   * @param endDate The end date
   * @param selectBy Filter subscription date field
   * @return List<SubscriptionSummaryModelDefinition>
   */
  public List<SubscriptionSummaryModelDefinition> exportSummary(String aid, Date startDate, Date endDate, String selectBy) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling exportSummary");
    }
    
    // verify the required parameter 'startDate' is set
    if (startDate == null) {
       throw new ApiException(400, "Missing the required parameter 'startDate' when calling exportSummary");
    }
    
    // verify the required parameter 'endDate' is set
    if (endDate == null) {
       throw new ApiException(400, "Missing the required parameter 'endDate' when calling exportSummary");
    }
    

    // create path and map variables
    String path = "/publisher/subscription/export/summary";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_date", startDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_date", endDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "select_by", selectBy));
    

    

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
        return (List<SubscriptionSummaryModelDefinition>) ApiInvoker.deserialize(response, "array", SubscriptionSummaryModelDefinition.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
