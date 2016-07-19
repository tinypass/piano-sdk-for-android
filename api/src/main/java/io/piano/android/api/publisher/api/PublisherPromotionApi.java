package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.Promotion;
import java.util.Date;
import java.math.BigDecimal;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PublisherPromotionApi {

  private ApiInvoker apiInvoker;

  public PublisherPromotionApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Check whether promo code for given email exists
   * 
   * @param promotionId Promotion id
   * @param email User&#39;s email address
   * @return Boolean
   */
  public Boolean codeExists(String promotionId, String email) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling codeExists");
    }
    
    // verify the required parameter 'email' is set
    if (email == null) {
       throw new ApiException(400, "Missing the required parameter 'email' when calling codeExists");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/exists";

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
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      formParams.put("email", ApiInvoker.parameterToString(email));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Boolean) ApiInvoker.deserialize(response, "", Boolean.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists total number of promotions
   * 
   * @param aid Application aid
   * @param expired Promotion is expired
   * @return Long
   */
  public Long count(String aid, String expired) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling count");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/count";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expired", expired));
    

    

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
   * Create promotion
   * 
   * @param aid Application aid
   * @param name Promotion name
   * @param newCustomersOnly whether promotion allows new customers only
   * @param startDate The start date
   * @param endDate The end date
   * @param discountType Type of promotion discount 
   * @param percentageDiscount Promotion percentage discount
   * @param unlimitedUses Whether to allow unlimited uses
   * @param usesAllowed Number of uses allowed by promotion
   * @param neverAllowZero Never allow the value of checkout to be zero
   * @param fixedPromotionCode Fixed value for all promotion codes
   * @param promotionCodePrefix Prefix for all codes
   * @param termDependencyType Type of dependency to terms
   * @param applyToAllBillingPeriods Promotion discount applies to first billing period or all billing periods
   * @param canBeAppliedOnRenewal Promotion can be applied on renewal
   * @return Promotion
   */
  public Promotion create(String aid, String name, Boolean newCustomersOnly, Date startDate, Date endDate, String discountType, BigDecimal percentageDiscount, Boolean unlimitedUses, Integer usesAllowed, Boolean neverAllowZero, String fixedPromotionCode, String promotionCodePrefix, String termDependencyType, Boolean applyToAllBillingPeriods, Boolean canBeAppliedOnRenewal) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling create");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling create");
    }
    
    // verify the required parameter 'newCustomersOnly' is set
    if (newCustomersOnly == null) {
       throw new ApiException(400, "Missing the required parameter 'newCustomersOnly' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/create";

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
      
      if (startDate != null) {
        builder.addTextBody("start_date", ApiInvoker.parameterToString(startDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (endDate != null) {
        builder.addTextBody("end_date", ApiInvoker.parameterToString(endDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (newCustomersOnly != null) {
        builder.addTextBody("new_customers_only", ApiInvoker.parameterToString(newCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (discountType != null) {
        builder.addTextBody("discount_type", ApiInvoker.parameterToString(discountType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (percentageDiscount != null) {
        builder.addTextBody("percentage_discount", ApiInvoker.parameterToString(percentageDiscount), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (unlimitedUses != null) {
        builder.addTextBody("unlimited_uses", ApiInvoker.parameterToString(unlimitedUses), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (usesAllowed != null) {
        builder.addTextBody("uses_allowed", ApiInvoker.parameterToString(usesAllowed), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (neverAllowZero != null) {
        builder.addTextBody("never_allow_zero", ApiInvoker.parameterToString(neverAllowZero), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (fixedPromotionCode != null) {
        builder.addTextBody("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (promotionCodePrefix != null) {
        builder.addTextBody("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termDependencyType != null) {
        builder.addTextBody("term_dependency_type", ApiInvoker.parameterToString(termDependencyType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (applyToAllBillingPeriods != null) {
        builder.addTextBody("apply_to_all_billing_periods", ApiInvoker.parameterToString(applyToAllBillingPeriods), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (canBeAppliedOnRenewal != null) {
        builder.addTextBody("can_be_applied_on_renewal", ApiInvoker.parameterToString(canBeAppliedOnRenewal), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("start_date", ApiInvoker.parameterToString(startDate));
      formParams.put("end_date", ApiInvoker.parameterToString(endDate));
      formParams.put("new_customers_only", ApiInvoker.parameterToString(newCustomersOnly));
      formParams.put("discount_type", ApiInvoker.parameterToString(discountType));
      formParams.put("percentage_discount", ApiInvoker.parameterToString(percentageDiscount));
      formParams.put("unlimited_uses", ApiInvoker.parameterToString(unlimitedUses));
      formParams.put("uses_allowed", ApiInvoker.parameterToString(usesAllowed));
      formParams.put("never_allow_zero", ApiInvoker.parameterToString(neverAllowZero));
      formParams.put("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode));
      formParams.put("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix));
      formParams.put("term_dependency_type", ApiInvoker.parameterToString(termDependencyType));
      formParams.put("apply_to_all_billing_periods", ApiInvoker.parameterToString(applyToAllBillingPeriods));
      formParams.put("can_be_applied_on_renewal", ApiInvoker.parameterToString(canBeAppliedOnRenewal));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Promotion) ApiInvoker.deserialize(response, "", Promotion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Deletes a promotion
   * 
   * @param promotionId Promotion id
   * @return void
   */
  public void delete(String promotionId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling delete");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/delete";

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
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      
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
   * Generate promo codes
   * 
   * @param promotionId Promotion id
   * @param fixedPromotionCode Fixed value for all promotion codes
   * @param promotionCodePrefix Prefix for all codes
   * @param amount Amount of promotion codes to generate
   * @param assetId asset id
   * @return Promotion
   */
  public Promotion generateCodes(String promotionId, String fixedPromotionCode, String promotionCodePrefix, Long amount, String assetId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling generateCodes");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/generate";

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
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (fixedPromotionCode != null) {
        builder.addTextBody("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (promotionCodePrefix != null) {
        builder.addTextBody("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (amount != null) {
        builder.addTextBody("amount", ApiInvoker.parameterToString(amount), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (assetId != null) {
        builder.addTextBody("asset_id", ApiInvoker.parameterToString(assetId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      formParams.put("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode));
      formParams.put("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix));
      formParams.put("amount", ApiInvoker.parameterToString(amount));
      formParams.put("asset_id", ApiInvoker.parameterToString(assetId));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Promotion) ApiInvoker.deserialize(response, "", Promotion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Gets a prommotion
   * 
   * @param promotionId Promotion id
   * @return Promotion
   */
  public Promotion get(String promotionId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/get";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    

    

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
        return (Promotion) ApiInvoker.deserialize(response, "", Promotion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists promotions
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param expired Promotion is expired
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<Promotion>
   */
  public List<Promotion> list(String aid, Integer offset, Integer limit, String expired, String q, String orderBy, String orderDirection) throws ApiException {
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
    String path = "/publisher/promotion/list";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expired", expired));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "q", q));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_by", orderBy));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_direction", orderDirection));
    

    

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
        return (List<Promotion>) ApiInvoker.deserialize(response, "array", Promotion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get total sales for promotion
   * 
   * @param promotionId Promotion id
   * @param currencyCode Currency code by ISO 4217 standard
   * @return String
   */
  public String totalSale(String promotionId, String currencyCode) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling totalSale");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/total";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "promotion_id", promotionId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency_code", currencyCode));
    

    

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
        return (String) ApiInvoker.deserialize(response, "", String.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update a promotion
   * 
   * @param promotionId Promotion id
   * @param aid Application aid
   * @param name Promotion name
   * @param discountType Type of promotion discount 
   * @param startDate The start date
   * @param endDate The end date
   * @param newCustomersOnly whether promotion allows new customers only
   * @param percentageDiscount Promotion percentage discount
   * @param unlimitedUses Whether to allow unlimited uses
   * @param usesAllowed Number of uses allowed by promotion
   * @param neverAllowZero Never allow the value of checkout to be zero
   * @param fixedPromotionCode Fixed value for all promotion codes
   * @param promotionCodePrefix Prefix for all codes
   * @param termDependencyType Type of dependency to terms
   * @param applyToAllBillingPeriods Promotion discount applies to first billing period or all billing periods
   * @param canBeAppliedOnRenewal Promotion can be applied on renewal
   * @return Promotion
   */
  public Promotion update(String promotionId, String aid, String name, String discountType, Date startDate, Date endDate, Boolean newCustomersOnly, BigDecimal percentageDiscount, Boolean unlimitedUses, Integer usesAllowed, Boolean neverAllowZero, String fixedPromotionCode, String promotionCodePrefix, String termDependencyType, Boolean applyToAllBillingPeriods, Boolean canBeAppliedOnRenewal) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'promotionId' is set
    if (promotionId == null) {
       throw new ApiException(400, "Missing the required parameter 'promotionId' when calling update");
    }
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling update");
    }
    
    // verify the required parameter 'discountType' is set
    if (discountType == null) {
       throw new ApiException(400, "Missing the required parameter 'discountType' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/promotion/update";

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
      
      if (promotionId != null) {
        builder.addTextBody("promotion_id", ApiInvoker.parameterToString(promotionId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (startDate != null) {
        builder.addTextBody("start_date", ApiInvoker.parameterToString(startDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (endDate != null) {
        builder.addTextBody("end_date", ApiInvoker.parameterToString(endDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (newCustomersOnly != null) {
        builder.addTextBody("new_customers_only", ApiInvoker.parameterToString(newCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (discountType != null) {
        builder.addTextBody("discount_type", ApiInvoker.parameterToString(discountType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (percentageDiscount != null) {
        builder.addTextBody("percentage_discount", ApiInvoker.parameterToString(percentageDiscount), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (unlimitedUses != null) {
        builder.addTextBody("unlimited_uses", ApiInvoker.parameterToString(unlimitedUses), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (usesAllowed != null) {
        builder.addTextBody("uses_allowed", ApiInvoker.parameterToString(usesAllowed), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (neverAllowZero != null) {
        builder.addTextBody("never_allow_zero", ApiInvoker.parameterToString(neverAllowZero), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (fixedPromotionCode != null) {
        builder.addTextBody("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (promotionCodePrefix != null) {
        builder.addTextBody("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (termDependencyType != null) {
        builder.addTextBody("term_dependency_type", ApiInvoker.parameterToString(termDependencyType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (applyToAllBillingPeriods != null) {
        builder.addTextBody("apply_to_all_billing_periods", ApiInvoker.parameterToString(applyToAllBillingPeriods), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (canBeAppliedOnRenewal != null) {
        builder.addTextBody("can_be_applied_on_renewal", ApiInvoker.parameterToString(canBeAppliedOnRenewal), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("promotion_id", ApiInvoker.parameterToString(promotionId));
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("start_date", ApiInvoker.parameterToString(startDate));
      formParams.put("end_date", ApiInvoker.parameterToString(endDate));
      formParams.put("new_customers_only", ApiInvoker.parameterToString(newCustomersOnly));
      formParams.put("discount_type", ApiInvoker.parameterToString(discountType));
      formParams.put("percentage_discount", ApiInvoker.parameterToString(percentageDiscount));
      formParams.put("unlimited_uses", ApiInvoker.parameterToString(unlimitedUses));
      formParams.put("uses_allowed", ApiInvoker.parameterToString(usesAllowed));
      formParams.put("never_allow_zero", ApiInvoker.parameterToString(neverAllowZero));
      formParams.put("fixed_promotion_code", ApiInvoker.parameterToString(fixedPromotionCode));
      formParams.put("promotion_code_prefix", ApiInvoker.parameterToString(promotionCodePrefix));
      formParams.put("term_dependency_type", ApiInvoker.parameterToString(termDependencyType));
      formParams.put("apply_to_all_billing_periods", ApiInvoker.parameterToString(applyToAllBillingPeriods));
      formParams.put("can_be_applied_on_renewal", ApiInvoker.parameterToString(canBeAppliedOnRenewal));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Promotion) ApiInvoker.deserialize(response, "", Promotion.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
