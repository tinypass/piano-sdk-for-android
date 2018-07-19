package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.OfferTemplateVersion;

public class PublisherOfferTemplateUpdateApi {

  private ApiInvoker apiInvoker;

  public PublisherOfferTemplateUpdateApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Updates offer template and variants content fields
   * 
   * @param aid Application aid
   * @param offerTemplateId Offer Template ID
   * @param contentFieldList Content field list
   * @param variantList Variants of the template
   * @param historyComment Offer Template History Comment
   * @return OfferTemplateVersion
   */
  public OfferTemplateVersion updateContentFields(String aid, String offerTemplateId, String contentFieldList, String variantList, String historyComment) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling updateContentFields");
    }
    
    // verify the required parameter 'offerTemplateId' is set
    if (offerTemplateId == null) {
       throw new ApiException(400, "Missing the required parameter 'offerTemplateId' when calling updateContentFields");
    }
    
    // verify the required parameter 'contentFieldList' is set
    if (contentFieldList == null) {
       throw new ApiException(400, "Missing the required parameter 'contentFieldList' when calling updateContentFields");
    }
    
    // verify the required parameter 'variantList' is set
    if (variantList == null) {
       throw new ApiException(400, "Missing the required parameter 'variantList' when calling updateContentFields");
    }
    

    // create path and map variables
    String path = "/publisher/offer/template/update/contentfields";

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
      
      if (offerTemplateId != null) {
        builder.addTextBody("offer_template_id", ApiInvoker.parameterToString(offerTemplateId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (contentFieldList != null) {
        builder.addTextBody("content_field_list", ApiInvoker.parameterToString(contentFieldList), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (variantList != null) {
        builder.addTextBody("variant_list", ApiInvoker.parameterToString(variantList), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (historyComment != null) {
        builder.addTextBody("history_comment", ApiInvoker.parameterToString(historyComment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("offer_template_id", ApiInvoker.parameterToString(offerTemplateId));
      formParams.put("content_field_list", ApiInvoker.parameterToString(contentFieldList));
      formParams.put("variant_list", ApiInvoker.parameterToString(variantList));
      formParams.put("history_comment", ApiInvoker.parameterToString(historyComment));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (OfferTemplateVersion) ApiInvoker.deserialize(response, "", OfferTemplateVersion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
