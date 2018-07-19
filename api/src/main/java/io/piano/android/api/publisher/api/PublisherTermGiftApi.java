package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Term;

public class PublisherTermGiftApi {

  private ApiInvoker apiInvoker;

  public PublisherTermGiftApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates a gift term
   * 
   * @param aid Application aid
   * @param name Term name
   * @param rid Unique id for resource
   * @param billingPlanPeriod Period of billing plan
   * @param billingPlanPrice Price of billing plan
   * @param billingPlanCurrency Currency of billing plan
   * @param description Term description
   * @param paymentAllowPromoCodes Whether to allow promo codes to be applied
   * @param voucheringPolicyRedemptionUrl Redemption URL of vouchering policy
   * @return Term
   */
  public Term createGiftTerm(String aid, String name, String rid, String billingPlanPeriod, BigDecimal billingPlanPrice, String billingPlanCurrency, String description, Boolean paymentAllowPromoCodes, String voucheringPolicyRedemptionUrl) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createGiftTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createGiftTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createGiftTerm");
    }
    
    // verify the required parameter 'billingPlanPeriod' is set
    if (billingPlanPeriod == null) {
       throw new ApiException(400, "Missing the required parameter 'billingPlanPeriod' when calling createGiftTerm");
    }
    
    // verify the required parameter 'billingPlanPrice' is set
    if (billingPlanPrice == null) {
       throw new ApiException(400, "Missing the required parameter 'billingPlanPrice' when calling createGiftTerm");
    }
    
    // verify the required parameter 'billingPlanCurrency' is set
    if (billingPlanCurrency == null) {
       throw new ApiException(400, "Missing the required parameter 'billingPlanCurrency' when calling createGiftTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/gift/create";

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
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanPeriod != null) {
        builder.addTextBody("billing_plan_period", ApiInvoker.parameterToString(billingPlanPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanPrice != null) {
        builder.addTextBody("billing_plan_price", ApiInvoker.parameterToString(billingPlanPrice), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanCurrency != null) {
        builder.addTextBody("billing_plan_currency", ApiInvoker.parameterToString(billingPlanCurrency), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowPromoCodes != null) {
        builder.addTextBody("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (voucheringPolicyRedemptionUrl != null) {
        builder.addTextBody("vouchering_policy_redemption_url", ApiInvoker.parameterToString(voucheringPolicyRedemptionUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("billing_plan_period", ApiInvoker.parameterToString(billingPlanPeriod));
      formParams.put("billing_plan_price", ApiInvoker.parameterToString(billingPlanPrice));
      formParams.put("billing_plan_currency", ApiInvoker.parameterToString(billingPlanCurrency));
      formParams.put("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes));
      formParams.put("vouchering_policy_redemption_url", ApiInvoker.parameterToString(voucheringPolicyRedemptionUrl));
      
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
   * Updates a gift term
   * 
   * @param aid Application aid
   * @param termId Term ID
   * @param name Term name
   * @param rid Unique id for resource
   * @param description Term description
   * @param billingPlanPeriod Period of billing plan
   * @param billingPlanPrice Price of billing plan
   * @param billingPlanCurrency Currency of billing plan
   * @param paymentAllowPromoCodes Whether to allow promo codes to be applied
   * @param voucheringPolicyRedemptionUrl Redemption URL of vouchering policy
   * @return Term
   */
  public Term updateGiftTerm(String aid, String termId, String name, String rid, String description, String billingPlanPeriod, BigDecimal billingPlanPrice, String billingPlanCurrency, Boolean paymentAllowPromoCodes, String voucheringPolicyRedemptionUrl) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling updateGiftTerm");
    }
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling updateGiftTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling updateGiftTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling updateGiftTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/gift/update";

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
      
      if (termId != null) {
        builder.addTextBody("term_id", ApiInvoker.parameterToString(termId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanPeriod != null) {
        builder.addTextBody("billing_plan_period", ApiInvoker.parameterToString(billingPlanPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanPrice != null) {
        builder.addTextBody("billing_plan_price", ApiInvoker.parameterToString(billingPlanPrice), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingPlanCurrency != null) {
        builder.addTextBody("billing_plan_currency", ApiInvoker.parameterToString(billingPlanCurrency), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowPromoCodes != null) {
        builder.addTextBody("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (voucheringPolicyRedemptionUrl != null) {
        builder.addTextBody("vouchering_policy_redemption_url", ApiInvoker.parameterToString(voucheringPolicyRedemptionUrl), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("billing_plan_period", ApiInvoker.parameterToString(billingPlanPeriod));
      formParams.put("billing_plan_price", ApiInvoker.parameterToString(billingPlanPrice));
      formParams.put("billing_plan_currency", ApiInvoker.parameterToString(billingPlanCurrency));
      formParams.put("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes));
      formParams.put("vouchering_policy_redemption_url", ApiInvoker.parameterToString(voucheringPolicyRedemptionUrl));
      
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
