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
import io.piano.android.api.publisher.model.Export;

public class PublisherExportUpdateApi {

  private ApiInvoker apiInvoker;

  public PublisherExportUpdateApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Update user search report definition
   * 
   * @param aid Application aid
   * @param exportId Downloadable report ID
   * @param exportName Downloadable report name
   * @param offset Offset from which to start returning results
   * @param limit Maximum index of returned results
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
   * @param resolvedInquiryUntil Find users which have resolved inquiry until selected date
   * @param hasSubmittedInquiry Find users with submitted inquries
   * @param hasReceivedResponseInquiry Find users with inquiries with response
   * @param dataType Defines searching field
   * @param data Defines search data
   * @param hasData Find users with any data
   * @param selectedConsentsMap Find user who accept list of consentDataType
   * @param consentChecked Find checked or unchecked consents
   * @param hasResolvedInquiry Find users with resolved inquiries
   * @param consentHasData Find users with accepted consents
   * @param q Search value
   * @param orderBy Field to order by
   * @param orderDirection Order direction (asc/desc)
   * @return Export
   */
  public Export updateUserExport(String aid, String exportId, String exportName, Integer offset, Integer limit, String name, String email, List<String> accessToResources, List<String> convertedTerms, Date accessFrom, Date accessUntil, Date convertedTermFrom, Date convertedTermUntil, List<String> redeemedPromotions, Date redeemedPromotionFrom, Date redeemedPromotionUntil, Boolean trialPeriod, Boolean hasAccess, Boolean hasConversionTerm, Boolean hasRedeemedPromotion, Boolean includeTrialRedemptions, String spentMoneyCurrency, BigDecimal spentMoneyFrom, BigDecimal spentMoneyUntil, Date spentFromDate, Date spentUntilDate, List<Integer> paymentMethods, Date billingFailureFrom, Date billingFailureUntil, Boolean hadBillingFailure, Boolean hasPayment, List<String> activeSubscriptionToResources, Boolean hasActiveSubscription, Date subscriptionStartFrom, Date subscriptionStartUntil, Date subscriptionRenewFrom, Date subscriptionRenewUntil, Date subscriptionExpireFrom, Date subscriptionExpireUntil, Date trialExpireFrom, Date trialExpireUntil, Boolean hasAnySubscriptions, Boolean hasUnresolvedInquiry, Date submittedInquiryFrom, Date submittedInquiryUntil, Date receivedResponseFrom, Date receivedResponseUntil, Date resolvedInquiryFrom, Date resolvedInquiryUntil, Boolean hasSubmittedInquiry, Boolean hasReceivedResponseInquiry, List<String> dataType, String data, Boolean hasData, List<String> selectedConsentsMap, Boolean consentChecked, Boolean hasResolvedInquiry, Boolean consentHasData, String q, String orderBy, String orderDirection) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling updateUserExport");
    }
    
    // verify the required parameter 'exportId' is set
    if (exportId == null) {
       throw new ApiException(400, "Missing the required parameter 'exportId' when calling updateUserExport");
    }
    
    // verify the required parameter 'exportName' is set
    if (exportName == null) {
       throw new ApiException(400, "Missing the required parameter 'exportName' when calling updateUserExport");
    }
    
    // verify the required parameter 'offset' is set
    if (offset == null) {
       throw new ApiException(400, "Missing the required parameter 'offset' when calling updateUserExport");
    }
    
    // verify the required parameter 'limit' is set
    if (limit == null) {
       throw new ApiException(400, "Missing the required parameter 'limit' when calling updateUserExport");
    }
    

    // create path and map variables
    String path = "/publisher/export/update/userExport";

    // query params
    List<Pair<String, String>> queryParams = new ArrayList<>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "aid", aid));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "export_id", exportId));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "export_name", exportName));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "email", email));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "access_to_resources", accessToResources));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "converted_terms", convertedTerms));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "access_from", accessFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "access_until", accessUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "converted_term_from", convertedTermFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "converted_term_until", convertedTermUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "redeemed_promotions", redeemedPromotions));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "redeemed_promotion_from", redeemedPromotionFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "redeemed_promotion_until", redeemedPromotionUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trial_period", trialPeriod));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_access", hasAccess));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_conversion_term", hasConversionTerm));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_redeemed_promotion", hasRedeemedPromotion));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_trial_redemptions", includeTrialRedemptions));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "spent_money_currency", spentMoneyCurrency));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "spent_money_from", spentMoneyFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "spent_money_until", spentMoneyUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "spent_from_date", spentFromDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "spent_until_date", spentUntilDate));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "payment_methods", paymentMethods));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "billing_failure_from", billingFailureFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "billing_failure_until", billingFailureUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "had_billing_failure", hadBillingFailure));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_payment", hasPayment));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "active_subscription_to_resources", activeSubscriptionToResources));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_active_subscription", hasActiveSubscription));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_start_from", subscriptionStartFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_start_until", subscriptionStartUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_renew_from", subscriptionRenewFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_renew_until", subscriptionRenewUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_expire_from", subscriptionExpireFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "subscription_expire_until", subscriptionExpireUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trial_expire_from", trialExpireFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trial_expire_until", trialExpireUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_any_subscriptions", hasAnySubscriptions));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_unresolved_inquiry", hasUnresolvedInquiry));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "submitted_inquiry_from", submittedInquiryFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "submitted_inquiry_until", submittedInquiryUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "received_response_from", receivedResponseFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "received_response_until", receivedResponseUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resolved_inquiry_from", resolvedInquiryFrom));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "resolved_inquiry_until", resolvedInquiryUntil));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_submitted_inquiry", hasSubmittedInquiry));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_received_response_inquiry", hasReceivedResponseInquiry));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "data_type", dataType));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "data", data));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_data", hasData));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("csv", "selected_consents_map", selectedConsentsMap));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "consent_checked", consentChecked));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "has_resolved_inquiry", hasResolvedInquiry));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "consent_has_data", consentHasData));
    
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
