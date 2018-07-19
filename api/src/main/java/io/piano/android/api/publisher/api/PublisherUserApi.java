package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.User;

public class PublisherUserApi {

  private ApiInvoker apiInvoker;

  public PublisherUserApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates a user
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param email User&#39;s email address
   * @param firstName User&#39;s first name
   * @param lastName User&#39;s last name
   * @return User
   */
  public User create(String aid, String uid, String email, String firstName, String lastName) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling create");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling create");
    }
    
    // verify the required parameter 'email' is set
    if (email == null) {
       throw new ApiException(400, "Missing the required parameter 'email' when calling create");
    }
    

    // create path and map variables
    String path = "/publisher/user/create";

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
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (firstName != null) {
        builder.addTextBody("first_name", ApiInvoker.parameterToString(firstName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (lastName != null) {
        builder.addTextBody("last_name", ApiInvoker.parameterToString(lastName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("email", ApiInvoker.parameterToString(email));
      formParams.put("first_name", ApiInvoker.parameterToString(firstName));
      formParams.put("last_name", ApiInvoker.parameterToString(lastName));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (User) ApiInvoker.deserialize(response, "", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Disables a user
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @return void
   */
  public void disable(String aid, String uid) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling disable");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling disable");
    }
    

    // create path and map variables
    String path = "/publisher/user/disable";

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
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      
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
   * Gets a user
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param disabled If the object is disabled
   * @return User
   */
  public User get(String aid, String uid, Boolean disabled) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling get");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling get");
    }
    

    // create path and map variables
    String path = "/publisher/user/get";

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
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (disabled != null) {
        builder.addTextBody("disabled", ApiInvoker.parameterToString(disabled), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("disabled", ApiInvoker.parameterToString(disabled));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (User) ApiInvoker.deserialize(response, "", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Lists an app&#39;s users
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param disabled If the object is disabled
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<User>
   */
  public List<User> list(String aid, Integer offset, Integer limit, Boolean disabled, String q, String orderBy, String orderDirection) throws ApiException {
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
    String path = "/publisher/user/list";

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
      
      if (disabled != null) {
        builder.addTextBody("disabled", ApiInvoker.parameterToString(disabled), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (q != null) {
        builder.addTextBody("q", ApiInvoker.parameterToString(q), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (offset != null) {
        builder.addTextBody("offset", ApiInvoker.parameterToString(offset), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (limit != null) {
        builder.addTextBody("limit", ApiInvoker.parameterToString(limit), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (orderBy != null) {
        builder.addTextBody("order_by", ApiInvoker.parameterToString(orderBy), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (orderDirection != null) {
        builder.addTextBody("order_direction", ApiInvoker.parameterToString(orderDirection), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("disabled", ApiInvoker.parameterToString(disabled));
      formParams.put("q", ApiInvoker.parameterToString(q));
      formParams.put("offset", ApiInvoker.parameterToString(offset));
      formParams.put("limit", ApiInvoker.parameterToString(limit));
      formParams.put("order_by", ApiInvoker.parameterToString(orderBy));
      formParams.put("order_direction", ApiInvoker.parameterToString(orderDirection));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<User>) ApiInvoker.deserialize(response, "array", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Searches an app&#39;s users
   * 
   * @param aid Application aid
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
   * @param uid User&#39;s uid
   * @param name Finds users who contain this keyword in their names.
   * @param email Finds users who contain this keyword in their emails.
   * @param registeredFrom Find users which was registered from selected date
   * @param registeredUntil Find users which was registered until selected date
   * @param accessToResources Find users who have access to select resources. Resource IDs (RIDs) are accepted values.
   * @param convertedTerms Find users who have converted on select terms. Term IDs are accepted values.
   * @param accessFrom Find users who have any ACTIVE access from this date. The date format is a unix timestamp.
   * @param accessUntil Find users who have any access until this date. The date format is a unix timestamp.
   * @param convertedTermFrom Find users who have converted on any term from this date. The date format is a unix timestamp.
   * @param convertedTermUntil Find users who have converted on any term until this date. The date format is a unix timestamp.
   * @param redeemedPromotions Find users who have redeemed select promotions. Promotion public IDs are accepted values. Promotion public IDs can be obtained by visiting Manage\u2192Promotions from the Piano Dashboard.
   * @param redeemedPromotionFrom Find users who have redeemed on any promotion on or after this date. The date format is a unix timestamp.
   * @param redeemedPromotionUntil Find users who have redeemed on any promotion on or before this date. The date format is a unix timestamp.
   * @param trialPeriodIsActive Find users who have any trial subscription at the present time.
   * @param hasTrialPeriod Find users who have any trial subscription at the any time.
   * @param hasAccess Find users who have any type of access (access that is not expired or will never expire).
   * @param hasConversionTerm Find users who have converted on any term.
   * @param hasRedeemedPromotion Find users who have redeemed any promotion and begun their subscription.
   * @param includeTrialRedemptions Find users who redeemed a promotion, including those redeemed when signing up for a free trial. In these cases, the promotion had not been applied during the period of your search but were applied as soon as the trial period ended.
   * @param convertedTermTypes Find users who have converted on particular types of terms. The accepted value of each type of term is a number: 0 (N/A), 1 (payment), 2 (ad view), 3 (registration), 4 (newsletter), 5 (external), 6 (custom), 7 (access granted), and 8 (gift).
   * @param hasConversionTermType Find users which have conversion terms for selected term types
   * @param spentMoneyCurrency Select the currency of the payments to take into account. Format is ISO 4217 (Ex: USD).
   * @param spentMoneyFrom Find users who spent above a specified monetary value across all of their purchases and conversions. This value is formatted as a decimal. (Example: 10.03. to represent $10.03 or \u00A310.03 or \u20AC10.03).
   * @param spentMoneyUntil Find users who spent below a specified monetary value across all of their purchases and conversions. This value is formatted as a decimal. (Ex: 10.03. to represent $10.03 or \u00A310.03 or \u20AC10.03).
   * @param spentFromDate Find users who bought something on or after this date. The date format is a unix timestamp.
   * @param spentUntilDate Find users who bought something on or before this date. The date format is a unix timestamp.
   * @param paymentMethods Find users who have used specific payment methods.The accepted values for each type of payment method: 1 (PayPal), 4 (BrainTree), 6 (TinyPass), 7 (Dwolla), 8 (AmazonMWS), 9 (Coinbase), 11 (PayPalBT), 12 (WorldPay_HPP), 13 (WorldPay_PayPal), 14 (WorldPay_Ideal), 15 (WorldPay_ELV), 16 (Spreedly_CC), 17 (Spreedly_Stripe_CC), 18 (Spreedly_Beanstream), 19 (EdgilPayway), 20 (WorldPay_CC_Token), 21 (Spreedly_PayU_Latam).
   * @param billingFailureFrom Find users who had problems with auto-renewal of any subscription on or after this date. The date format is a unix timestamp.
   * @param billingFailureUntil Find users who had problems with auto-renewal of any subscription on or before this date. The date format is a unix timestamp.
   * @param hadBillingFailure Finds users who had any problems with billing.
   * @param hasPayment Finds users who have made any payment. Refunded payments are not taken into account. So if user had a payment and refunded it, he will not presented in the result list.
   * @param upiExtCustomerId Find users which have given external customer id
   * @param creditCardWillExpire Find users whose cards will expire in selected dates
   * @param activeSubscriptionToResources Find users who have active subscriptions to specified resources. Resource IDs (RIDs) are accepted values.
   * @param hasActiveSubscription Finds users who have any active subscription.
   * @param subscriptionStartFrom Finds users who have any subscription starting on or after this date. The date format is a unix timestamp.
   * @param subscriptionStartUntil Finds users who have any subscription that started on or before this date. The date format is a unix timestamp.
   * @param subscriptionRenewFrom Finds users who have any subscription renewing on or after this date. The date format is a unix timestamp.
   * @param subscriptionRenewUntil Finds users who have any subscription renewing on or before this date. The date format is a unix timestamp.
   * @param subscriptionExpireFrom Finds users who have any subscription expiring on or after this date. The date format is a unix timestamp.
   * @param subscriptionExpireUntil Finds users who have any subscription expiring on or before this date. The date format is a unix timestamp.
   * @param trialExpireFrom Finds users who have any trial subscription expiring on or after this date. The date format is a unix timestamp.
   * @param trialExpireUntil Finds users who have any trial subscription expiring on or after this date. The date format is a unix timestamp.
   * @param hasAnySubscriptions Finds users with any subscriptions, including expired and canceled subscriptions.
   * @param hasUnresolvedInquiry Finds users who have any unresolved inquiry.
   * @param submittedInquiryFrom Finds users who have any submitted inquiry on or after this date. The date format is a unix timestamp.
   * @param submittedInquiryUntil Finds users who have any submitted inquiry on or before this date. The date format is a unix timestamp.
   * @param receivedResponseFrom Finds users who received any inquiry response on or after this date. The date format is a unix timestamp.
   * @param receivedResponseUntil Finds users who received any inquiry response on or before this date. The date format is a unix timestamp.
   * @param resolvedInquiryFrom Finds users who have any resolved inquiry on or after this date. The date format is a unix timestamp.
   * @param resolvedInquiryUntil Finds users who have any resolved inquiry on or before this date. The date format is a unix timestamp.
   * @param hasSubmittedInquiry Finds users with submitted inquiries.
   * @param hasReceivedResponseInquiry Finds users with any inquiries that have been responded to.
   * @param dataType Defines searching field
   * @param data Defines search data
   * @param hasData Find users with any data
   * @param selectedConsentsMap Consent public IDs are accepted values. Specified values will be used along with consent_checked parameter.
   * @param consentChecked Finds users who have checked consents. Accepted values: true/false.
   * @param customFields Finds user with following custom fields
   * @param source Data source for user searching: VX or CF (id custom fields)
   * @param hasResolvedInquiry Finds users with any resolved inquiry.
   * @param consentHasData Finds users who accepted any consent. Accepted values: true/false. If this parameter is false, selected_consents_map and consent_has_data are ignored.
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<User>
   */
  public List<User> search(String aid, Integer offset, Integer limit, String uid, String name, String email, Date registeredFrom, Date registeredUntil, List<String> accessToResources, List<String> convertedTerms, Date accessFrom, Date accessUntil, Date convertedTermFrom, Date convertedTermUntil, List<String> redeemedPromotions, Date redeemedPromotionFrom, Date redeemedPromotionUntil, Boolean trialPeriodIsActive, Boolean hasTrialPeriod, Boolean hasAccess, Boolean hasConversionTerm, Boolean hasRedeemedPromotion, Boolean includeTrialRedemptions, List<String> convertedTermTypes, Boolean hasConversionTermType, String spentMoneyCurrency, BigDecimal spentMoneyFrom, BigDecimal spentMoneyUntil, Date spentFromDate, Date spentUntilDate, List<Integer> paymentMethods, Date billingFailureFrom, Date billingFailureUntil, Boolean hadBillingFailure, Boolean hasPayment, String upiExtCustomerId, String creditCardWillExpire, List<String> activeSubscriptionToResources, Boolean hasActiveSubscription, Date subscriptionStartFrom, Date subscriptionStartUntil, Date subscriptionRenewFrom, Date subscriptionRenewUntil, Date subscriptionExpireFrom, Date subscriptionExpireUntil, Date trialExpireFrom, Date trialExpireUntil, Boolean hasAnySubscriptions, Boolean hasUnresolvedInquiry, Date submittedInquiryFrom, Date submittedInquiryUntil, Date receivedResponseFrom, Date receivedResponseUntil, Date resolvedInquiryFrom, Date resolvedInquiryUntil, Boolean hasSubmittedInquiry, Boolean hasReceivedResponseInquiry, List<String> dataType, String data, Boolean hasData, List<String> selectedConsentsMap, Boolean consentChecked, String customFields, String source, Boolean hasResolvedInquiry, Boolean consentHasData, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling search");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling search");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling search");
    }
    

    // create path and map variables
    String path = "/publisher/user/search";

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
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registeredFrom != null) {
        builder.addTextBody("registered_from", ApiInvoker.parameterToString(registeredFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (registeredUntil != null) {
        builder.addTextBody("registered_until", ApiInvoker.parameterToString(registeredUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accessToResources != null) {
        builder.addTextBody("access_to_resources", ApiInvoker.parameterToString(accessToResources), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (convertedTerms != null) {
        builder.addTextBody("converted_terms", ApiInvoker.parameterToString(convertedTerms), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accessFrom != null) {
        builder.addTextBody("access_from", ApiInvoker.parameterToString(accessFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accessUntil != null) {
        builder.addTextBody("access_until", ApiInvoker.parameterToString(accessUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (convertedTermFrom != null) {
        builder.addTextBody("converted_term_from", ApiInvoker.parameterToString(convertedTermFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (convertedTermUntil != null) {
        builder.addTextBody("converted_term_until", ApiInvoker.parameterToString(convertedTermUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (redeemedPromotions != null) {
        builder.addTextBody("redeemed_promotions", ApiInvoker.parameterToString(redeemedPromotions), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (redeemedPromotionFrom != null) {
        builder.addTextBody("redeemed_promotion_from", ApiInvoker.parameterToString(redeemedPromotionFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (redeemedPromotionUntil != null) {
        builder.addTextBody("redeemed_promotion_until", ApiInvoker.parameterToString(redeemedPromotionUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (trialPeriodIsActive != null) {
        builder.addTextBody("trial_period_is_active", ApiInvoker.parameterToString(trialPeriodIsActive), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasTrialPeriod != null) {
        builder.addTextBody("has_trial_period", ApiInvoker.parameterToString(hasTrialPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasAccess != null) {
        builder.addTextBody("has_access", ApiInvoker.parameterToString(hasAccess), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasConversionTerm != null) {
        builder.addTextBody("has_conversion_term", ApiInvoker.parameterToString(hasConversionTerm), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasRedeemedPromotion != null) {
        builder.addTextBody("has_redeemed_promotion", ApiInvoker.parameterToString(hasRedeemedPromotion), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (includeTrialRedemptions != null) {
        builder.addTextBody("include_trial_redemptions", ApiInvoker.parameterToString(includeTrialRedemptions), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (convertedTermTypes != null) {
        builder.addTextBody("converted_term_types", ApiInvoker.parameterToString(convertedTermTypes), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasConversionTermType != null) {
        builder.addTextBody("has_conversion_term_type", ApiInvoker.parameterToString(hasConversionTermType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (spentMoneyCurrency != null) {
        builder.addTextBody("spent_money_currency", ApiInvoker.parameterToString(spentMoneyCurrency), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (spentMoneyFrom != null) {
        builder.addTextBody("spent_money_from", ApiInvoker.parameterToString(spentMoneyFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (spentMoneyUntil != null) {
        builder.addTextBody("spent_money_until", ApiInvoker.parameterToString(spentMoneyUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (spentFromDate != null) {
        builder.addTextBody("spent_from_date", ApiInvoker.parameterToString(spentFromDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (spentUntilDate != null) {
        builder.addTextBody("spent_until_date", ApiInvoker.parameterToString(spentUntilDate), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentMethods != null) {
        builder.addTextBody("payment_methods", ApiInvoker.parameterToString(paymentMethods), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingFailureFrom != null) {
        builder.addTextBody("billing_failure_from", ApiInvoker.parameterToString(billingFailureFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (billingFailureUntil != null) {
        builder.addTextBody("billing_failure_until", ApiInvoker.parameterToString(billingFailureUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hadBillingFailure != null) {
        builder.addTextBody("had_billing_failure", ApiInvoker.parameterToString(hadBillingFailure), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasPayment != null) {
        builder.addTextBody("has_payment", ApiInvoker.parameterToString(hasPayment), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (upiExtCustomerId != null) {
        builder.addTextBody("upi_ext_customer_id", ApiInvoker.parameterToString(upiExtCustomerId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (creditCardWillExpire != null) {
        builder.addTextBody("credit_card_will_expire", ApiInvoker.parameterToString(creditCardWillExpire), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (activeSubscriptionToResources != null) {
        builder.addTextBody("active_subscription_to_resources", ApiInvoker.parameterToString(activeSubscriptionToResources), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasActiveSubscription != null) {
        builder.addTextBody("has_active_subscription", ApiInvoker.parameterToString(hasActiveSubscription), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionStartFrom != null) {
        builder.addTextBody("subscription_start_from", ApiInvoker.parameterToString(subscriptionStartFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionStartUntil != null) {
        builder.addTextBody("subscription_start_until", ApiInvoker.parameterToString(subscriptionStartUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionRenewFrom != null) {
        builder.addTextBody("subscription_renew_from", ApiInvoker.parameterToString(subscriptionRenewFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionRenewUntil != null) {
        builder.addTextBody("subscription_renew_until", ApiInvoker.parameterToString(subscriptionRenewUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionExpireFrom != null) {
        builder.addTextBody("subscription_expire_from", ApiInvoker.parameterToString(subscriptionExpireFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (subscriptionExpireUntil != null) {
        builder.addTextBody("subscription_expire_until", ApiInvoker.parameterToString(subscriptionExpireUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (trialExpireFrom != null) {
        builder.addTextBody("trial_expire_from", ApiInvoker.parameterToString(trialExpireFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (trialExpireUntil != null) {
        builder.addTextBody("trial_expire_until", ApiInvoker.parameterToString(trialExpireUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasAnySubscriptions != null) {
        builder.addTextBody("has_any_subscriptions", ApiInvoker.parameterToString(hasAnySubscriptions), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasUnresolvedInquiry != null) {
        builder.addTextBody("has_unresolved_inquiry", ApiInvoker.parameterToString(hasUnresolvedInquiry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (submittedInquiryFrom != null) {
        builder.addTextBody("submitted_inquiry_from", ApiInvoker.parameterToString(submittedInquiryFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (submittedInquiryUntil != null) {
        builder.addTextBody("submitted_inquiry_until", ApiInvoker.parameterToString(submittedInquiryUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (receivedResponseFrom != null) {
        builder.addTextBody("received_response_from", ApiInvoker.parameterToString(receivedResponseFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (receivedResponseUntil != null) {
        builder.addTextBody("received_response_until", ApiInvoker.parameterToString(receivedResponseUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (resolvedInquiryFrom != null) {
        builder.addTextBody("resolved_inquiry_from", ApiInvoker.parameterToString(resolvedInquiryFrom), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (resolvedInquiryUntil != null) {
        builder.addTextBody("resolved_inquiry_until", ApiInvoker.parameterToString(resolvedInquiryUntil), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasSubmittedInquiry != null) {
        builder.addTextBody("has_submitted_inquiry", ApiInvoker.parameterToString(hasSubmittedInquiry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasReceivedResponseInquiry != null) {
        builder.addTextBody("has_received_response_inquiry", ApiInvoker.parameterToString(hasReceivedResponseInquiry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (dataType != null) {
        builder.addTextBody("data_type", ApiInvoker.parameterToString(dataType), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (data != null) {
        builder.addTextBody("data", ApiInvoker.parameterToString(data), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasData != null) {
        builder.addTextBody("has_data", ApiInvoker.parameterToString(hasData), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (selectedConsentsMap != null) {
        builder.addTextBody("selected_consents_map", ApiInvoker.parameterToString(selectedConsentsMap), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (consentChecked != null) {
        builder.addTextBody("consent_checked", ApiInvoker.parameterToString(consentChecked), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (customFields != null) {
        builder.addTextBody("custom_fields", ApiInvoker.parameterToString(customFields), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (source != null) {
        builder.addTextBody("source", ApiInvoker.parameterToString(source), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (hasResolvedInquiry != null) {
        builder.addTextBody("has_resolved_inquiry", ApiInvoker.parameterToString(hasResolvedInquiry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (consentHasData != null) {
        builder.addTextBody("consent_has_data", ApiInvoker.parameterToString(consentHasData), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (q != null) {
        builder.addTextBody("q", ApiInvoker.parameterToString(q), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (offset != null) {
        builder.addTextBody("offset", ApiInvoker.parameterToString(offset), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (limit != null) {
        builder.addTextBody("limit", ApiInvoker.parameterToString(limit), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (orderBy != null) {
        builder.addTextBody("order_by", ApiInvoker.parameterToString(orderBy), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (orderDirection != null) {
        builder.addTextBody("order_direction", ApiInvoker.parameterToString(orderDirection), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("email", ApiInvoker.parameterToString(email));
      formParams.put("registered_from", ApiInvoker.parameterToString(registeredFrom));
      formParams.put("registered_until", ApiInvoker.parameterToString(registeredUntil));
      formParams.put("access_to_resources", ApiInvoker.parameterToString(accessToResources));
      formParams.put("converted_terms", ApiInvoker.parameterToString(convertedTerms));
      formParams.put("access_from", ApiInvoker.parameterToString(accessFrom));
      formParams.put("access_until", ApiInvoker.parameterToString(accessUntil));
      formParams.put("converted_term_from", ApiInvoker.parameterToString(convertedTermFrom));
      formParams.put("converted_term_until", ApiInvoker.parameterToString(convertedTermUntil));
      formParams.put("redeemed_promotions", ApiInvoker.parameterToString(redeemedPromotions));
      formParams.put("redeemed_promotion_from", ApiInvoker.parameterToString(redeemedPromotionFrom));
      formParams.put("redeemed_promotion_until", ApiInvoker.parameterToString(redeemedPromotionUntil));
      formParams.put("trial_period_is_active", ApiInvoker.parameterToString(trialPeriodIsActive));
      formParams.put("has_trial_period", ApiInvoker.parameterToString(hasTrialPeriod));
      formParams.put("has_access", ApiInvoker.parameterToString(hasAccess));
      formParams.put("has_conversion_term", ApiInvoker.parameterToString(hasConversionTerm));
      formParams.put("has_redeemed_promotion", ApiInvoker.parameterToString(hasRedeemedPromotion));
      formParams.put("include_trial_redemptions", ApiInvoker.parameterToString(includeTrialRedemptions));
      formParams.put("converted_term_types", ApiInvoker.parameterToString(convertedTermTypes));
      formParams.put("has_conversion_term_type", ApiInvoker.parameterToString(hasConversionTermType));
      formParams.put("spent_money_currency", ApiInvoker.parameterToString(spentMoneyCurrency));
      formParams.put("spent_money_from", ApiInvoker.parameterToString(spentMoneyFrom));
      formParams.put("spent_money_until", ApiInvoker.parameterToString(spentMoneyUntil));
      formParams.put("spent_from_date", ApiInvoker.parameterToString(spentFromDate));
      formParams.put("spent_until_date", ApiInvoker.parameterToString(spentUntilDate));
      formParams.put("payment_methods", ApiInvoker.parameterToString(paymentMethods));
      formParams.put("billing_failure_from", ApiInvoker.parameterToString(billingFailureFrom));
      formParams.put("billing_failure_until", ApiInvoker.parameterToString(billingFailureUntil));
      formParams.put("had_billing_failure", ApiInvoker.parameterToString(hadBillingFailure));
      formParams.put("has_payment", ApiInvoker.parameterToString(hasPayment));
      formParams.put("upi_ext_customer_id", ApiInvoker.parameterToString(upiExtCustomerId));
      formParams.put("credit_card_will_expire", ApiInvoker.parameterToString(creditCardWillExpire));
      formParams.put("active_subscription_to_resources", ApiInvoker.parameterToString(activeSubscriptionToResources));
      formParams.put("has_active_subscription", ApiInvoker.parameterToString(hasActiveSubscription));
      formParams.put("subscription_start_from", ApiInvoker.parameterToString(subscriptionStartFrom));
      formParams.put("subscription_start_until", ApiInvoker.parameterToString(subscriptionStartUntil));
      formParams.put("subscription_renew_from", ApiInvoker.parameterToString(subscriptionRenewFrom));
      formParams.put("subscription_renew_until", ApiInvoker.parameterToString(subscriptionRenewUntil));
      formParams.put("subscription_expire_from", ApiInvoker.parameterToString(subscriptionExpireFrom));
      formParams.put("subscription_expire_until", ApiInvoker.parameterToString(subscriptionExpireUntil));
      formParams.put("trial_expire_from", ApiInvoker.parameterToString(trialExpireFrom));
      formParams.put("trial_expire_until", ApiInvoker.parameterToString(trialExpireUntil));
      formParams.put("has_any_subscriptions", ApiInvoker.parameterToString(hasAnySubscriptions));
      formParams.put("has_unresolved_inquiry", ApiInvoker.parameterToString(hasUnresolvedInquiry));
      formParams.put("submitted_inquiry_from", ApiInvoker.parameterToString(submittedInquiryFrom));
      formParams.put("submitted_inquiry_until", ApiInvoker.parameterToString(submittedInquiryUntil));
      formParams.put("received_response_from", ApiInvoker.parameterToString(receivedResponseFrom));
      formParams.put("received_response_until", ApiInvoker.parameterToString(receivedResponseUntil));
      formParams.put("resolved_inquiry_from", ApiInvoker.parameterToString(resolvedInquiryFrom));
      formParams.put("resolved_inquiry_until", ApiInvoker.parameterToString(resolvedInquiryUntil));
      formParams.put("has_submitted_inquiry", ApiInvoker.parameterToString(hasSubmittedInquiry));
      formParams.put("has_received_response_inquiry", ApiInvoker.parameterToString(hasReceivedResponseInquiry));
      formParams.put("data_type", ApiInvoker.parameterToString(dataType));
      formParams.put("data", ApiInvoker.parameterToString(data));
      formParams.put("has_data", ApiInvoker.parameterToString(hasData));
      formParams.put("selected_consents_map", ApiInvoker.parameterToString(selectedConsentsMap));
      formParams.put("consent_checked", ApiInvoker.parameterToString(consentChecked));
      formParams.put("custom_fields", ApiInvoker.parameterToString(customFields));
      formParams.put("source", ApiInvoker.parameterToString(source));
      formParams.put("has_resolved_inquiry", ApiInvoker.parameterToString(hasResolvedInquiry));
      formParams.put("consent_has_data", ApiInvoker.parameterToString(consentHasData));
      formParams.put("q", ApiInvoker.parameterToString(q));
      formParams.put("offset", ApiInvoker.parameterToString(offset));
      formParams.put("limit", ApiInvoker.parameterToString(limit));
      formParams.put("order_by", ApiInvoker.parameterToString(orderBy));
      formParams.put("order_direction", ApiInvoker.parameterToString(orderDirection));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<User>) ApiInvoker.deserialize(response, "array", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Updates a user
   * 
   * @param aid Application aid
   * @param uid User&#39;s uid
   * @param email User&#39;s email address
   * @param firstName User&#39;s first name
   * @param lastName User&#39;s last name
   * @return User
   */
  public User update(String aid, String uid, String email, String firstName, String lastName) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling update");
    }
    
    // verify the required parameter 'uid' is set
    if (uid == null) {
       throw new ApiException(400, "Missing the required parameter 'uid' when calling update");
    }
    

    // create path and map variables
    String path = "/publisher/user/update";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      "application/x-www-form-urlencoded"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      
      if (aid != null) {
        builder.addTextBody("aid", ApiInvoker.parameterToString(aid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (uid != null) {
        builder.addTextBody("uid", ApiInvoker.parameterToString(uid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (firstName != null) {
        builder.addTextBody("first_name", ApiInvoker.parameterToString(firstName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (lastName != null) {
        builder.addTextBody("last_name", ApiInvoker.parameterToString(lastName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("uid", ApiInvoker.parameterToString(uid));
      formParams.put("email", ApiInvoker.parameterToString(email));
      formParams.put("first_name", ApiInvoker.parameterToString(firstName));
      formParams.put("last_name", ApiInvoker.parameterToString(lastName));
      
    }

    try {
      String response = apiInvoker.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (User) ApiInvoker.deserialize(response, "", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
