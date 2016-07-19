package io.piano.android.api.publisher.api;

import android.util.Pair;

import io.piano.android.api.publisher.model.User;
import java.util.Date;
import java.math.BigDecimal;
import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
   * @return List<User>
   */
  public List<User> list(String aid, Integer offset, Integer limit, Boolean disabled, String q) throws ApiException {
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
   * @param name Find users which contain a keyword in name
   * @param email Find users which contain a keyword in email
   * @param accessToResources Find users which have access to selected resources
   * @param convertedTerms Find users which have conversion terms for selected terms
   * @param accessFrom Find users which have user access from selected date
   * @param accessUntil Find users which have user access until selected date
   * @param convertedTermFrom Find users which have converted term from selected date
   * @param convertedTermUntil Find users which have converted term until selected date
   * @param redeemedPromotions Find users which have selected redeemed promotions
   * @param redeemedPromotionFrom Find users which have redeemed promotions from selected date
   * @param redeemedPromotionUntil Find users which have redeemed promotions until selected date
   * @param trialPeriod Find users which have trial subscription
   * @param hasAccess Find users which have user access (can be expired)
   * @param hasConversionTerm Find users which have conversion term
   * @param hasRedeemedPromotion Find users which have redeemed promotion
   * @param includeTrialRedemptions Find users which have reserved trial redemptions. It works together with redeemed promotions and works as &#39;OR&#39; conditions 
   * @param spentMoneyCurrency Currency of the payments to take into account
   * @param spentMoneyFrom Find users which spent money more than a value
   * @param spentMoneyUntil Find users which spent money less than a value
   * @param spentFromDate Find users which bougth something from a date
   * @param spentUntilDate Find users which bougth something until a date
   * @param paymentMethods Find users which use selected payment methods
   * @param billingFailureFrom Find users which have problems with autorenew of subscription from a date
   * @param billingFailureUntil Find users which have problems with autorenew of subscription until a date
   * @param hadBillingFailure Find users which had problems with billing
   * @param hasPayment Find users which have payment
   * @param activeSubscriptionToResources Find users which have active subscription to selected resources
   * @param hasActiveSubscription Find users which have active subscription
   * @param subscriptionStartFrom Find users which have starting subscription from selected date
   * @param subscriptionStartUntil Find users which have starting subscription until selected date
   * @param subscriptionRenewFrom Find users which have renewing subscription from selected date
   * @param subscriptionRenewUntil Find users which have renewing subscription until selected date
   * @param subscriptionExpireFrom Find users which have exipiring subscription from selected date
   * @param subscriptionExpireUntil Find users which have expiring subscription until selected date
   * @param trialExpireFrom Find users which have expiring trial subscription from selected date
   * @param trialExpireUntil Find users which have expiring trial subscription until selected date
   * @param hasAnySubscriptions Find users with any subscriptions
   * @param hasUnresolvedInquiry Find users which have unresolved inquiry
   * @param submittedInquiryFrom Find users which have submitted inquiry from selected date
   * @param submittedInquiryUntil Find users which have submitted inquiry until selected date
   * @param receivedResponseFrom Find users which received response from selected date
   * @param receivedResponseUntil Find users which received response until selected date
   * @param resolvedInquiryFrom Find users which have resolved inquiry from selected date
   * @param resolvedInquiryUntil Find users which have resolved inquiry from selected date
   * @param hasSubmittedInquiry Find users with submitted inquries
   * @param hasReceivedResponseInquiry Find users with inquiries with response
   * @param dataType Defines searching field
   * @param data Defines search data
   * @param hasData Find users with any data
   * @param hasResolvedInquiry Find users with resolved inquiries
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return List<User>
   */
  public List<User> search(String aid, Integer offset, Integer limit, String uid, String name, String email, List<String> accessToResources, List<String> convertedTerms, Date accessFrom, Date accessUntil, Date convertedTermFrom, Date convertedTermUntil, List<String> redeemedPromotions, Date redeemedPromotionFrom, Date redeemedPromotionUntil, Boolean trialPeriod, Boolean hasAccess, Boolean hasConversionTerm, Boolean hasRedeemedPromotion, Boolean includeTrialRedemptions, String spentMoneyCurrency, BigDecimal spentMoneyFrom, BigDecimal spentMoneyUntil, Date spentFromDate, Date spentUntilDate, List<Integer> paymentMethods, Date billingFailureFrom, Date billingFailureUntil, Boolean hadBillingFailure, Boolean hasPayment, List<String> activeSubscriptionToResources, Boolean hasActiveSubscription, Date subscriptionStartFrom, Date subscriptionStartUntil, Date subscriptionRenewFrom, Date subscriptionRenewUntil, Date subscriptionExpireFrom, Date subscriptionExpireUntil, Date trialExpireFrom, Date trialExpireUntil, Boolean hasAnySubscriptions, Boolean hasUnresolvedInquiry, Date submittedInquiryFrom, Date submittedInquiryUntil, Date receivedResponseFrom, Date receivedResponseUntil, Date resolvedInquiryFrom, Date resolvedInquiryUntil, Boolean hasSubmittedInquiry, Boolean hasReceivedResponseInquiry, List<String> dataType, String data, Boolean hasData, Boolean hasResolvedInquiry, String q, String orderBy, String orderDirection) throws ApiException {
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
      
      if (trialPeriod != null) {
        builder.addTextBody("trial_period", ApiInvoker.parameterToString(trialPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
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
      
      if (hasResolvedInquiry != null) {
        builder.addTextBody("has_resolved_inquiry", ApiInvoker.parameterToString(hasResolvedInquiry), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formParams.put("access_to_resources", ApiInvoker.parameterToString(accessToResources));
      formParams.put("converted_terms", ApiInvoker.parameterToString(convertedTerms));
      formParams.put("access_from", ApiInvoker.parameterToString(accessFrom));
      formParams.put("access_until", ApiInvoker.parameterToString(accessUntil));
      formParams.put("converted_term_from", ApiInvoker.parameterToString(convertedTermFrom));
      formParams.put("converted_term_until", ApiInvoker.parameterToString(convertedTermUntil));
      formParams.put("redeemed_promotions", ApiInvoker.parameterToString(redeemedPromotions));
      formParams.put("redeemed_promotion_from", ApiInvoker.parameterToString(redeemedPromotionFrom));
      formParams.put("redeemed_promotion_until", ApiInvoker.parameterToString(redeemedPromotionUntil));
      formParams.put("trial_period", ApiInvoker.parameterToString(trialPeriod));
      formParams.put("has_access", ApiInvoker.parameterToString(hasAccess));
      formParams.put("has_conversion_term", ApiInvoker.parameterToString(hasConversionTerm));
      formParams.put("has_redeemed_promotion", ApiInvoker.parameterToString(hasRedeemedPromotion));
      formParams.put("include_trial_redemptions", ApiInvoker.parameterToString(includeTrialRedemptions));
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
      formParams.put("has_resolved_inquiry", ApiInvoker.parameterToString(hasResolvedInquiry));
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
