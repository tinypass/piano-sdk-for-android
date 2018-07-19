package io.piano.android.api.publisher.api;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.piano.android.api.common.ApiException;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.publisher.model.Term;

public class PublisherTermPaymentApi {

  private ApiInvoker apiInvoker;

  public PublisherTermPaymentApi(ApiInvoker apiInvoker) {
    this.apiInvoker = apiInvoker;
  }
  
  /**
   * Creates a payment term
   * 
   * @param aid Application aid
   * @param rid Unique id for resource
   * @param name Term name
   * @param paymentBillingPlan Term billing plan
   * @param paymentAllowRenewDays How many days in advance a user can renew
   * @param paymentForceAutoRenew Payment forces the auto renewal of subscriptions
   * @param paymentNewCustomersOnly Whether to allow buy only first time
   * @param paymentTrialNewCustomersOnly Whether to allow trial period only first time
   * @param paymentAllowPromoCodes Whether to allow promo codes to be applied
   * @param paymentRenewGracePeriod The number of days after expiration to still allow access to the resource
   * @param paymentAllowGift Whether the term can be gifted
   * @param description Term description
   * @param verifyOnRenewal Is term should be verified before renewal or it skips this step
   * @param evtVerificationPeriod External verification period
   * @param collectAddress Collect address for this term
   * @param deliveryZone List of delivery zones for this term
   * @param defaultCountry Pre-selected country for consumers outside of delivery zones
   * @param scheduleId The schedule ID
   * @param scheduleBillingModel The schedule billing model
   * @return Term
   */
  public Term createPaymentTerm(String aid, String rid, String name, String paymentBillingPlan, Integer paymentAllowRenewDays, Boolean paymentForceAutoRenew, Boolean paymentNewCustomersOnly, Boolean paymentTrialNewCustomersOnly, Boolean paymentAllowPromoCodes, Integer paymentRenewGracePeriod, Boolean paymentAllowGift, String description, Boolean verifyOnRenewal, Integer evtVerificationPeriod, Boolean collectAddress, List<String> deliveryZone, String defaultCountry, String scheduleId, String scheduleBillingModel) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'aid' is set
    if (aid == null) {
       throw new ApiException(400, "Missing the required parameter 'aid' when calling createPaymentTerm");
    }
    
    // verify the required parameter 'rid' is set
    if (rid == null) {
       throw new ApiException(400, "Missing the required parameter 'rid' when calling createPaymentTerm");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling createPaymentTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/payment/create";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentBillingPlan != null) {
        builder.addTextBody("payment_billing_plan", ApiInvoker.parameterToString(paymentBillingPlan), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowRenewDays != null) {
        builder.addTextBody("payment_allow_renew_days", ApiInvoker.parameterToString(paymentAllowRenewDays), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentForceAutoRenew != null) {
        builder.addTextBody("payment_force_auto_renew", ApiInvoker.parameterToString(paymentForceAutoRenew), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentNewCustomersOnly != null) {
        builder.addTextBody("payment_new_customers_only", ApiInvoker.parameterToString(paymentNewCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentTrialNewCustomersOnly != null) {
        builder.addTextBody("payment_trial_new_customers_only", ApiInvoker.parameterToString(paymentTrialNewCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowPromoCodes != null) {
        builder.addTextBody("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentRenewGracePeriod != null) {
        builder.addTextBody("payment_renew_grace_period", ApiInvoker.parameterToString(paymentRenewGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowGift != null) {
        builder.addTextBody("payment_allow_gift", ApiInvoker.parameterToString(paymentAllowGift), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (verifyOnRenewal != null) {
        builder.addTextBody("verify_on_renewal", ApiInvoker.parameterToString(verifyOnRenewal), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtVerificationPeriod != null) {
        builder.addTextBody("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (collectAddress != null) {
        builder.addTextBody("collect_address", ApiInvoker.parameterToString(collectAddress), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (deliveryZone != null) {
        builder.addTextBody("delivery_zone", ApiInvoker.parameterToString(deliveryZone), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (defaultCountry != null) {
        builder.addTextBody("default_country", ApiInvoker.parameterToString(defaultCountry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (scheduleId != null) {
        builder.addTextBody("schedule_id", ApiInvoker.parameterToString(scheduleId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (scheduleBillingModel != null) {
        builder.addTextBody("schedule_billing_model", ApiInvoker.parameterToString(scheduleBillingModel), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("aid", ApiInvoker.parameterToString(aid));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("payment_billing_plan", ApiInvoker.parameterToString(paymentBillingPlan));
      formParams.put("payment_allow_renew_days", ApiInvoker.parameterToString(paymentAllowRenewDays));
      formParams.put("payment_force_auto_renew", ApiInvoker.parameterToString(paymentForceAutoRenew));
      formParams.put("payment_new_customers_only", ApiInvoker.parameterToString(paymentNewCustomersOnly));
      formParams.put("payment_trial_new_customers_only", ApiInvoker.parameterToString(paymentTrialNewCustomersOnly));
      formParams.put("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes));
      formParams.put("payment_renew_grace_period", ApiInvoker.parameterToString(paymentRenewGracePeriod));
      formParams.put("payment_allow_gift", ApiInvoker.parameterToString(paymentAllowGift));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("verify_on_renewal", ApiInvoker.parameterToString(verifyOnRenewal));
      formParams.put("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod));
      formParams.put("collect_address", ApiInvoker.parameterToString(collectAddress));
      formParams.put("delivery_zone", ApiInvoker.parameterToString(deliveryZone));
      formParams.put("default_country", ApiInvoker.parameterToString(defaultCountry));
      formParams.put("schedule_id", ApiInvoker.parameterToString(scheduleId));
      formParams.put("schedule_billing_model", ApiInvoker.parameterToString(scheduleBillingModel));
      
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
   * Update a payment term
   * 
   * @param termId Term ID
   * @param rid Unique id for resource
   * @param name Term name
   * @param paymentBillingPlan Term billing plan
   * @param paymentAllowRenewDays How many days in advance a user can renew
   * @param paymentForceAutoRenew Payment forces the auto renewal of subscriptions
   * @param paymentNewCustomersOnly Whether to allow buy only first time
   * @param paymentTrialNewCustomersOnly Whether to allow trial period only first time
   * @param paymentAllowPromoCodes Whether to allow promo codes to be applied
   * @param paymentRenewGracePeriod The number of days after expiration to still allow access to the resource
   * @param paymentAllowGift Whether the term can be gifted
   * @param description Term description
   * @param verifyOnRenewal Is term should be verified before renewal or it skips this step
   * @param evtVerificationPeriod External verification period
   * @param collectAddress Collect address for this term
   * @param deliveryZone List of delivery zones for this term
   * @param defaultCountry Pre-selected country for consumers outside of delivery zones
   * @param scheduleId The schedule ID
   * @param scheduleBillingModel The schedule billing model
   * @return Term
   */
  public Term updatePaymentTerm(String termId, String rid, String name, String paymentBillingPlan, Integer paymentAllowRenewDays, Boolean paymentForceAutoRenew, Boolean paymentNewCustomersOnly, Boolean paymentTrialNewCustomersOnly, Boolean paymentAllowPromoCodes, Integer paymentRenewGracePeriod, Boolean paymentAllowGift, String description, Boolean verifyOnRenewal, Integer evtVerificationPeriod, Boolean collectAddress, List<String> deliveryZone, String defaultCountry, String scheduleId, String scheduleBillingModel) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'termId' is set
    if (termId == null) {
       throw new ApiException(400, "Missing the required parameter 'termId' when calling updatePaymentTerm");
    }
    

    // create path and map variables
    String path = "/publisher/term/payment/update";

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
      
      if (rid != null) {
        builder.addTextBody("rid", ApiInvoker.parameterToString(rid), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentBillingPlan != null) {
        builder.addTextBody("payment_billing_plan", ApiInvoker.parameterToString(paymentBillingPlan), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowRenewDays != null) {
        builder.addTextBody("payment_allow_renew_days", ApiInvoker.parameterToString(paymentAllowRenewDays), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentForceAutoRenew != null) {
        builder.addTextBody("payment_force_auto_renew", ApiInvoker.parameterToString(paymentForceAutoRenew), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentNewCustomersOnly != null) {
        builder.addTextBody("payment_new_customers_only", ApiInvoker.parameterToString(paymentNewCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentTrialNewCustomersOnly != null) {
        builder.addTextBody("payment_trial_new_customers_only", ApiInvoker.parameterToString(paymentTrialNewCustomersOnly), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowPromoCodes != null) {
        builder.addTextBody("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentRenewGracePeriod != null) {
        builder.addTextBody("payment_renew_grace_period", ApiInvoker.parameterToString(paymentRenewGracePeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (paymentAllowGift != null) {
        builder.addTextBody("payment_allow_gift", ApiInvoker.parameterToString(paymentAllowGift), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (verifyOnRenewal != null) {
        builder.addTextBody("verify_on_renewal", ApiInvoker.parameterToString(verifyOnRenewal), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (evtVerificationPeriod != null) {
        builder.addTextBody("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (collectAddress != null) {
        builder.addTextBody("collect_address", ApiInvoker.parameterToString(collectAddress), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (deliveryZone != null) {
        builder.addTextBody("delivery_zone", ApiInvoker.parameterToString(deliveryZone), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (defaultCountry != null) {
        builder.addTextBody("default_country", ApiInvoker.parameterToString(defaultCountry), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (scheduleId != null) {
        builder.addTextBody("schedule_id", ApiInvoker.parameterToString(scheduleId), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (scheduleBillingModel != null) {
        builder.addTextBody("schedule_billing_model", ApiInvoker.parameterToString(scheduleBillingModel), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
      */
    } else {
      // normal form params
      formParams.put("term_id", ApiInvoker.parameterToString(termId));
      formParams.put("rid", ApiInvoker.parameterToString(rid));
      formParams.put("name", ApiInvoker.parameterToString(name));
      formParams.put("payment_billing_plan", ApiInvoker.parameterToString(paymentBillingPlan));
      formParams.put("payment_allow_renew_days", ApiInvoker.parameterToString(paymentAllowRenewDays));
      formParams.put("payment_force_auto_renew", ApiInvoker.parameterToString(paymentForceAutoRenew));
      formParams.put("payment_new_customers_only", ApiInvoker.parameterToString(paymentNewCustomersOnly));
      formParams.put("payment_trial_new_customers_only", ApiInvoker.parameterToString(paymentTrialNewCustomersOnly));
      formParams.put("payment_allow_promo_codes", ApiInvoker.parameterToString(paymentAllowPromoCodes));
      formParams.put("payment_renew_grace_period", ApiInvoker.parameterToString(paymentRenewGracePeriod));
      formParams.put("payment_allow_gift", ApiInvoker.parameterToString(paymentAllowGift));
      formParams.put("description", ApiInvoker.parameterToString(description));
      formParams.put("verify_on_renewal", ApiInvoker.parameterToString(verifyOnRenewal));
      formParams.put("evt_verification_period", ApiInvoker.parameterToString(evtVerificationPeriod));
      formParams.put("collect_address", ApiInvoker.parameterToString(collectAddress));
      formParams.put("delivery_zone", ApiInvoker.parameterToString(deliveryZone));
      formParams.put("default_country", ApiInvoker.parameterToString(defaultCountry));
      formParams.put("schedule_id", ApiInvoker.parameterToString(scheduleId));
      formParams.put("schedule_billing_model", ApiInvoker.parameterToString(scheduleBillingModel));
      
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
