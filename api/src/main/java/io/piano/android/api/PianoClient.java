package io.piano.android.api;

import android.content.Context;

import io.piano.android.api.anon.api.*;
import io.piano.android.api.publisher.api.*;
import io.piano.android.api.user.api.*;
import io.piano.android.api.common.*;

public class PianoClient {

	private Context context;
	private String aid;
	private boolean sandbox;
	private ApiInvoker apiInvoker;

	public PianoClient(Context context, String aid, boolean sandbox) {
		this(context, aid, sandbox, null);
	}

	public PianoClient(Context context, String aid, boolean sandbox, Network client) {
		this.context = context;
		this.aid = aid;
		this.sandbox = sandbox;
		String basePath = sandbox ? "https://sandbox.tinypass.com/api/v3" : "https://api.piano.io/api/v3";
		this.apiInvoker = new ApiInvoker(basePath, client);
	}

	public String getAid() {
		return aid;
	}

	public boolean isSandbox() {
		return sandbox;
	}

	public void setAccessToken(String accessToken) {
		if (accessToken == null) {
			apiInvoker.addDefaultHeader("Authorization", null);
		} else {
			apiInvoker.addDefaultHeader("Authorization", "Bearer " + accessToken);
		}
	}

	private AccessApi apiAccess;

	public AccessApi getAccessApi() {
		if (apiAccess == null) apiAccess = new AccessApi(apiInvoker);
		return apiAccess;
	}

	private AccessTokenApi apiAccessToken;

	public AccessTokenApi getAccessTokenApi() {
		if (apiAccessToken == null) apiAccessToken = new AccessTokenApi(apiInvoker);
		return apiAccessToken;
	}

	private AnonApi apiAnon;

	public AnonApi getAnonApi() {
		if (apiAnon == null) apiAnon = new AnonApi(apiInvoker);
		return apiAnon;
	}

	private AnonCurrencyListApi apiAnonCurrencyList;

	public AnonCurrencyListApi getAnonCurrencyListApi() {
		if (apiAnonCurrencyList == null) apiAnonCurrencyList = new AnonCurrencyListApi(apiInvoker);
		return apiAnonCurrencyList;
	}

	private AnonCurrencyListWorldpayApi apiAnonCurrencyListWorldpay;

	public AnonCurrencyListWorldpayApi getAnonCurrencyListWorldpayApi() {
		if (apiAnonCurrencyListWorldpay == null) apiAnonCurrencyListWorldpay = new AnonCurrencyListWorldpayApi(apiInvoker);
		return apiAnonCurrencyListWorldpay;
	}

	private AnonMeterApi apiAnonMeter;

	public AnonMeterApi getAnonMeterApi() {
		if (apiAnonMeter == null) apiAnonMeter = new AnonMeterApi(apiInvoker);
		return apiAnonMeter;
	}

	private ConversionApi apiConversion;

	public ConversionApi getConversionApi() {
		if (apiConversion == null) apiConversion = new ConversionApi(apiInvoker);
		return apiConversion;
	}

	private ConversionExternalApi apiConversionExternal;

	public ConversionExternalApi getConversionExternalApi() {
		if (apiConversionExternal == null) apiConversionExternal = new ConversionExternalApi(apiInvoker);
		return apiConversionExternal;
	}

	private ConversionRegistrationApi apiConversionRegistration;

	public ConversionRegistrationApi getConversionRegistrationApi() {
		if (apiConversionRegistration == null) apiConversionRegistration = new ConversionRegistrationApi(apiInvoker);
		return apiConversionRegistration;
	}

	private ExperienceApi apiExperience;

	public ExperienceApi getExperienceApi() {
		if (apiExperience == null) apiExperience = new ExperienceApi(apiInvoker);
		return apiExperience;
	}

	private OauthApi apiOauth;

	public OauthApi getOauthApi() {
		if (apiOauth == null) apiOauth = new OauthApi(apiInvoker);
		return apiOauth;
	}

	private SubscriptionApi apiSubscription;

	public SubscriptionApi getSubscriptionApi() {
		if (apiSubscription == null) apiSubscription = new SubscriptionApi(apiInvoker);
		return apiSubscription;
	}

	private PublisherAppApi apiPublisherApp;

	public PublisherAppApi getPublisherAppApi() {
		if (apiPublisherApp == null) apiPublisherApp = new PublisherAppApi(apiInvoker);
		return apiPublisherApp;
	}

	private PublisherAppFeaturesApi apiPublisherAppFeatures;

	public PublisherAppFeaturesApi getPublisherAppFeaturesApi() {
		if (apiPublisherAppFeatures == null) apiPublisherAppFeatures = new PublisherAppFeaturesApi(apiInvoker);
		return apiPublisherAppFeatures;
	}

	private PublisherConversionApi apiPublisherConversion;

	public PublisherConversionApi getPublisherConversionApi() {
		if (apiPublisherConversion == null) apiPublisherConversion = new PublisherConversionApi(apiInvoker);
		return apiPublisherConversion;
	}

	private PublisherConversionCustomApi apiPublisherConversionCustom;

	public PublisherConversionCustomApi getPublisherConversionCustomApi() {
		if (apiPublisherConversionCustom == null) apiPublisherConversionCustom = new PublisherConversionCustomApi(apiInvoker);
		return apiPublisherConversionCustom;
	}

	private PublisherConversionExternalApi apiPublisherConversionExternal;

	public PublisherConversionExternalApi getPublisherConversionExternalApi() {
		if (apiPublisherConversionExternal == null) apiPublisherConversionExternal = new PublisherConversionExternalApi(apiInvoker);
		return apiPublisherConversionExternal;
	}

	private PublisherConversionRegistrationApi apiPublisherConversionRegistration;

	public PublisherConversionRegistrationApi getPublisherConversionRegistrationApi() {
		if (apiPublisherConversionRegistration == null) apiPublisherConversionRegistration = new PublisherConversionRegistrationApi(apiInvoker);
		return apiPublisherConversionRegistration;
	}

	private PublisherInquiryApi apiPublisherInquiry;

	public PublisherInquiryApi getPublisherInquiryApi() {
		if (apiPublisherInquiry == null) apiPublisherInquiry = new PublisherInquiryApi(apiInvoker);
		return apiPublisherInquiry;
	}

	private PublisherOfferApi apiPublisherOffer;

	public PublisherOfferApi getPublisherOfferApi() {
		if (apiPublisherOffer == null) apiPublisherOffer = new PublisherOfferApi(apiInvoker);
		return apiPublisherOffer;
	}

	private PublisherOfferTemplateApi apiPublisherOfferTemplate;

	public PublisherOfferTemplateApi getPublisherOfferTemplateApi() {
		if (apiPublisherOfferTemplate == null) apiPublisherOfferTemplate = new PublisherOfferTemplateApi(apiInvoker);
		return apiPublisherOfferTemplate;
	}

	private PublisherOfferTemplateCreateApi apiPublisherOfferTemplateCreate;

	public PublisherOfferTemplateCreateApi getPublisherOfferTemplateCreateApi() {
		if (apiPublisherOfferTemplateCreate == null) apiPublisherOfferTemplateCreate = new PublisherOfferTemplateCreateApi(apiInvoker);
		return apiPublisherOfferTemplateCreate;
	}

	private PublisherOfferTermApi apiPublisherOfferTerm;

	public PublisherOfferTermApi getPublisherOfferTermApi() {
		if (apiPublisherOfferTerm == null) apiPublisherOfferTerm = new PublisherOfferTermApi(apiInvoker);
		return apiPublisherOfferTerm;
	}

	private PublisherOfferTermApplicableApi apiPublisherOfferTermApplicable;

	public PublisherOfferTermApplicableApi getPublisherOfferTermApplicableApi() {
		if (apiPublisherOfferTermApplicable == null) apiPublisherOfferTermApplicable = new PublisherOfferTermApplicableApi(apiInvoker);
		return apiPublisherOfferTermApplicable;
	}

	private PublisherOfferTermOfferApi apiPublisherOfferTermOffer;

	public PublisherOfferTermOfferApi getPublisherOfferTermOfferApi() {
		if (apiPublisherOfferTermOffer == null) apiPublisherOfferTermOffer = new PublisherOfferTermOfferApi(apiInvoker);
		return apiPublisherOfferTermOffer;
	}

	private PublisherPaymentProviderConfigurationGetWorldpayApi apiPublisherPaymentProviderConfigurationGetWorldpay;

	public PublisherPaymentProviderConfigurationGetWorldpayApi getPublisherPaymentProviderConfigurationGetWorldpayApi() {
		if (apiPublisherPaymentProviderConfigurationGetWorldpay == null) apiPublisherPaymentProviderConfigurationGetWorldpay = new PublisherPaymentProviderConfigurationGetWorldpayApi(apiInvoker);
		return apiPublisherPaymentProviderConfigurationGetWorldpay;
	}

	private PublisherPromotionApi apiPublisherPromotion;

	public PublisherPromotionApi getPublisherPromotionApi() {
		if (apiPublisherPromotion == null) apiPublisherPromotion = new PublisherPromotionApi(apiInvoker);
		return apiPublisherPromotion;
	}

	private PublisherPromotionCodeApi apiPublisherPromotionCode;

	public PublisherPromotionCodeApi getPublisherPromotionCodeApi() {
		if (apiPublisherPromotionCode == null) apiPublisherPromotionCode = new PublisherPromotionCodeApi(apiInvoker);
		return apiPublisherPromotionCode;
	}

	private PublisherPromotionTermApi apiPublisherPromotionTerm;

	public PublisherPromotionTermApi getPublisherPromotionTermApi() {
		if (apiPublisherPromotionTerm == null) apiPublisherPromotionTerm = new PublisherPromotionTermApi(apiInvoker);
		return apiPublisherPromotionTerm;
	}

	private PublisherReportConversionApi apiPublisherReportConversion;

	public PublisherReportConversionApi getPublisherReportConversionApi() {
		if (apiPublisherReportConversion == null) apiPublisherReportConversion = new PublisherReportConversionApi(apiInvoker);
		return apiPublisherReportConversion;
	}

	private PublisherResourceApi apiPublisherResource;

	public PublisherResourceApi getPublisherResourceApi() {
		if (apiPublisherResource == null) apiPublisherResource = new PublisherResourceApi(apiInvoker);
		return apiPublisherResource;
	}

	private PublisherResourceBundleApi apiPublisherResourceBundle;

	public PublisherResourceBundleApi getPublisherResourceBundleApi() {
		if (apiPublisherResourceBundle == null) apiPublisherResourceBundle = new PublisherResourceBundleApi(apiInvoker);
		return apiPublisherResourceBundle;
	}

	private PublisherResourceStatsApi apiPublisherResourceStats;

	public PublisherResourceStatsApi getPublisherResourceStatsApi() {
		if (apiPublisherResourceStats == null) apiPublisherResourceStats = new PublisherResourceStatsApi(apiInvoker);
		return apiPublisherResourceStats;
	}

	private PublisherResourceTagApi apiPublisherResourceTag;

	public PublisherResourceTagApi getPublisherResourceTagApi() {
		if (apiPublisherResourceTag == null) apiPublisherResourceTag = new PublisherResourceTagApi(apiInvoker);
		return apiPublisherResourceTag;
	}

	private PublisherResourceUserApi apiPublisherResourceUser;

	public PublisherResourceUserApi getPublisherResourceUserApi() {
		if (apiPublisherResourceUser == null) apiPublisherResourceUser = new PublisherResourceUserApi(apiInvoker);
		return apiPublisherResourceUser;
	}

	private PublisherSubscriptionApi apiPublisherSubscription;

	public PublisherSubscriptionApi getPublisherSubscriptionApi() {
		if (apiPublisherSubscription == null) apiPublisherSubscription = new PublisherSubscriptionApi(apiInvoker);
		return apiPublisherSubscription;
	}

	private PublisherSubscriptionExportApi apiPublisherSubscriptionExport;

	public PublisherSubscriptionExportApi getPublisherSubscriptionExportApi() {
		if (apiPublisherSubscriptionExport == null) apiPublisherSubscriptionExport = new PublisherSubscriptionExportApi(apiInvoker);
		return apiPublisherSubscriptionExport;
	}

	private PublisherTermApi apiPublisherTerm;

	public PublisherTermApi getPublisherTermApi() {
		if (apiPublisherTerm == null) apiPublisherTerm = new PublisherTermApi(apiInvoker);
		return apiPublisherTerm;
	}

	private PublisherTermCustomApi apiPublisherTermCustom;

	public PublisherTermCustomApi getPublisherTermCustomApi() {
		if (apiPublisherTermCustom == null) apiPublisherTermCustom = new PublisherTermCustomApi(apiInvoker);
		return apiPublisherTermCustom;
	}

	private PublisherTermExternalApi apiPublisherTermExternal;

	public PublisherTermExternalApi getPublisherTermExternalApi() {
		if (apiPublisherTermExternal == null) apiPublisherTermExternal = new PublisherTermExternalApi(apiInvoker);
		return apiPublisherTermExternal;
	}

	private PublisherTermPaymentApi apiPublisherTermPayment;

	public PublisherTermPaymentApi getPublisherTermPaymentApi() {
		if (apiPublisherTermPayment == null) apiPublisherTermPayment = new PublisherTermPaymentApi(apiInvoker);
		return apiPublisherTermPayment;
	}

	private PublisherTermRegistrationApi apiPublisherTermRegistration;

	public PublisherTermRegistrationApi getPublisherTermRegistrationApi() {
		if (apiPublisherTermRegistration == null) apiPublisherTermRegistration = new PublisherTermRegistrationApi(apiInvoker);
		return apiPublisherTermRegistration;
	}

	private PublisherTermStatsApi apiPublisherTermStats;

	public PublisherTermStatsApi getPublisherTermStatsApi() {
		if (apiPublisherTermStats == null) apiPublisherTermStats = new PublisherTermStatsApi(apiInvoker);
		return apiPublisherTermStats;
	}

	private PublisherTermUpdateExternalApiApi apiPublisherTermUpdateExternalApi;

	public PublisherTermUpdateExternalApiApi getPublisherTermUpdateExternalApiApi() {
		if (apiPublisherTermUpdateExternalApi == null) apiPublisherTermUpdateExternalApi = new PublisherTermUpdateExternalApiApi(apiInvoker);
		return apiPublisherTermUpdateExternalApi;
	}

	private PublisherTestApi apiPublisherTest;

	public PublisherTestApi getPublisherTestApi() {
		if (apiPublisherTest == null) apiPublisherTest = new PublisherTestApi(apiInvoker);
		return apiPublisherTest;
	}

	private PublisherUserApi apiPublisherUser;

	public PublisherUserApi getPublisherUserApi() {
		if (apiPublisherUser == null) apiPublisherUser = new PublisherUserApi(apiInvoker);
		return apiPublisherUser;
	}

	private PublisherUserAccessApi apiPublisherUserAccess;

	public PublisherUserAccessApi getPublisherUserAccessApi() {
		if (apiPublisherUserAccess == null) apiPublisherUserAccess = new PublisherUserAccessApi(apiInvoker);
		return apiPublisherUserAccess;
	}

	private PublisherUserAccessActiveApi apiPublisherUserAccessActive;

	public PublisherUserAccessActiveApi getPublisherUserAccessActiveApi() {
		if (apiPublisherUserAccessActive == null) apiPublisherUserAccessActive = new PublisherUserAccessActiveApi(apiInvoker);
		return apiPublisherUserAccessActive;
	}

	private PublisherUserAppApi apiPublisherUserApp;

	public PublisherUserAppApi getPublisherUserAppApi() {
		if (apiPublisherUserApp == null) apiPublisherUserApp = new PublisherUserAppApi(apiInvoker);
		return apiPublisherUserApp;
	}

	private PublisherUserEmailApi apiPublisherUserEmail;

	public PublisherUserEmailApi getPublisherUserEmailApi() {
		if (apiPublisherUserEmail == null) apiPublisherUserEmail = new PublisherUserEmailApi(apiInvoker);
		return apiPublisherUserEmail;
	}

	private PublisherWebhookApi apiPublisherWebhook;

	public PublisherWebhookApi getPublisherWebhookApi() {
		if (apiPublisherWebhook == null) apiPublisherWebhook = new PublisherWebhookApi(apiInvoker);
		return apiPublisherWebhook;
	}

	private PublisherWebhookResponseApi apiPublisherWebhookResponse;

	public PublisherWebhookResponseApi getPublisherWebhookResponseApi() {
		if (apiPublisherWebhookResponse == null) apiPublisherWebhookResponse = new PublisherWebhookResponseApi(apiInvoker);
		return apiPublisherWebhookResponse;
	}

	private PublisherWebhookSettingsApi apiPublisherWebhookSettings;

	public PublisherWebhookSettingsApi getPublisherWebhookSettingsApi() {
		if (apiPublisherWebhookSettings == null) apiPublisherWebhookSettings = new PublisherWebhookSettingsApi(apiInvoker);
		return apiPublisherWebhookSettings;
	}

	private UserApi apiUser;

	public UserApi getUserApi() {
		if (apiUser == null) apiUser = new UserApi(apiInvoker);
		return apiUser;
	}

	private UserAccessApi apiUserAccess;

	public UserAccessApi getUserAccessApi() {
		if (apiUserAccess == null) apiUserAccess = new UserAccessApi(apiInvoker);
		return apiUserAccess;
	}

}
