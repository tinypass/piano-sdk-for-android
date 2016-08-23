package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Export;

public class PublisherExportCreateAamApi {

  private ApiInvoker apiInvoker;

  public PublisherExportCreateAamApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Create AAM daily proof of access
   * 
   * @param aid Application aid
   * @param exportName Downloadable report name
   * @param dateFrom Report begin date
   * @param dateTo Report end date
   * @return Export
   */
  public Export createAAMDailyProofOfAcess(String aid, String exportName, Date dateFrom, Date dateTo) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createAAMDailyProofOfAcess");
    }
    
    // verify the required parameter 'exportName' is set
    if (exportName == null) {
       throw new ApiException(400, "Missing the required parameter 'exportName' when calling createAAMDailyProofOfAcess");
    }
    
    // verify the required parameter 'dateFrom' is set
    if (dateFrom == null) {
       throw new ApiException(400, "Missing the required parameter 'dateFrom' when calling createAAMDailyProofOfAcess");
    }
    
    // verify the required parameter 'dateTo' is set
    if (dateTo == null) {
       throw new ApiException(400, "Missing the required parameter 'dateTo' when calling createAAMDailyProofOfAcess");
    }
    

    // create path and map variables
    String path = "/publisher/export/create/aam/daily";

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
      
      if (exportName != null) {
        builder.addTextBody("export_name", ApiInvoker.parameterToString(exportName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (dateFrom != null) {
        builder.addTextBody("date_from", ApiInvoker.parameterToString(dateFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (dateTo != null) {
        builder.addTextBody("date_to", ApiInvoker.parameterToString(dateTo), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("export_name", ApiInvoker.parameterToString(exportName));
      formParams.put("date_from", ApiInvoker.parameterToString(dateFrom));
      formParams.put("date_to", ApiInvoker.parameterToString(dateTo));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Export) ApiInvoker.deserialize(response, "", Export.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Create AAM monthly report
   * 
   * @param aid Application aid
   * @param exportName Downloadable report name
   * @param dateFrom Report begin date
   * @param dateTo Report end date
   * @return Export
   */
  public Export createAAMMonthlyReport(String aid, String exportName, Date dateFrom, Date dateTo) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createAAMMonthlyReport");
    }
    
    // verify the required parameter 'exportName' is set
    if (exportName == null) {
       throw new ApiException(400, "Missing the required parameter 'exportName' when calling createAAMMonthlyReport");
    }
    
    // verify the required parameter 'dateFrom' is set
    if (dateFrom == null) {
       throw new ApiException(400, "Missing the required parameter 'dateFrom' when calling createAAMMonthlyReport");
    }
    
    // verify the required parameter 'dateTo' is set
    if (dateTo == null) {
       throw new ApiException(400, "Missing the required parameter 'dateTo' when calling createAAMMonthlyReport");
    }
    

    // create path and map variables
    String path = "/publisher/export/create/aam/monthly";

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
      
      if (exportName != null) {
        builder.addTextBody("export_name", ApiInvoker.parameterToString(exportName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (dateFrom != null) {
        builder.addTextBody("date_from", ApiInvoker.parameterToString(dateFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (dateTo != null) {
        builder.addTextBody("date_to", ApiInvoker.parameterToString(dateTo), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("export_name", ApiInvoker.parameterToString(exportName));
      formParams.put("date_from", ApiInvoker.parameterToString(dateFrom));
      formParams.put("date_to", ApiInvoker.parameterToString(dateTo));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Export) ApiInvoker.deserialize(response, "", Export.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
