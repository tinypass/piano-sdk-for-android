package io.piano.android.api;

import android.content.Context;
import android.text.TextUtils;

import io.piano.android.api.anon.api.AccessApi;
import io.piano.android.api.anon.api.AccessTokenApi;
import io.piano.android.api.anon.api.AnonApi;
import io.piano.android.api.anon.api.AnonCurrencyListApi;
import io.piano.android.api.anon.api.AnonCurrencyListWorldpayApi;
import io.piano.android.api.anon.api.AnonMeterApi;
import io.piano.android.api.anon.api.ConversionApi;
import io.piano.android.api.anon.api.ConversionExternalApi;
import io.piano.android.api.anon.api.ConversionRegistrationApi;
import io.piano.android.api.anon.api.ExperienceApi;
import io.piano.android.api.anon.api.OauthApi;
import io.piano.android.api.anon.api.SubscriptionApi;
import io.piano.android.api.common.ApiInvoker;
import io.piano.android.api.common.Network;
import io.piano.android.api.publisher.api.PublisherAppApi;
import io.piano.android.api.publisher.api.PublisherAppFeaturesApi;
import io.piano.android.api.publisher.api.PublisherConsentApi;
import io.piano.android.api.publisher.api.PublisherConsentEntryApi;
import io.piano.android.api.publisher.api.PublisherConsentPageApi;
import io.piano.android.api.publisher.api.PublisherConversionApi;
import io.piano.android.api.publisher.api.PublisherConversionCustomApi;
import io.piano.android.api.publisher.api.PublisherConversionExternalApi;
import io.piano.android.api.publisher.api.PublisherConversionRegistrationApi;
import io.piano.android.api.publisher.api.PublisherExportApi;
import io.piano.android.api.publisher.api.PublisherExportCreateAamApi;
import io.piano.android.api.publisher.api.PublisherExportCreateApi;
import io.piano.android.api.publisher.api.PublisherExportUpdateApi;
import io.piano.android.api.publisher.api.PublisherInquiryApi;
import io.piano.android.api.publisher.api.PublisherOfferApi;
import io.piano.android.api.publisher.api.PublisherOfferTemplateApi;
import io.piano.android.api.publisher.api.PublisherOfferTemplateCreateApi;
import io.piano.android.api.publisher.api.PublisherOfferTermApi;
import io.piano.android.api.publisher.api.PublisherOfferTermApplicableApi;
import io.piano.android.api.publisher.api.PublisherOfferTermOfferApi;
import io.piano.android.api.publisher.api.PublisherPaymentProviderConfigurationGetWorldpayApi;
import io.piano.android.api.publisher.api.PublisherPromotionApi;
import io.piano.android.api.publisher.api.PublisherPromotionCodeApi;
import io.piano.android.api.publisher.api.PublisherPromotionTermApi;
import io.piano.android.api.publisher.api.PublisherReportConversionApi;
import io.piano.android.api.publisher.api.PublisherResourceApi;
import io.piano.android.api.publisher.api.PublisherResourceBundleApi;
import io.piano.android.api.publisher.api.PublisherResourceStatsApi;
import io.piano.android.api.publisher.api.PublisherResourceTagApi;
import io.piano.android.api.publisher.api.PublisherResourceUserApi;
import io.piano.android.api.publisher.api.PublisherSubscriptionApi;
import io.piano.android.api.publisher.api.PublisherSubscriptionCountFailedApi;
import io.piano.android.api.publisher.api.PublisherSubscriptionExportApi;
import io.piano.android.api.publisher.api.PublisherTaxApi;
import io.piano.android.api.publisher.api.PublisherTaxRateApi;
import io.piano.android.api.publisher.api.PublisherTaxTaxJarApi;
import io.piano.android.api.publisher.api.PublisherTaxTinypassApi;
import io.piano.android.api.publisher.api.PublisherTermApi;
import io.piano.android.api.publisher.api.PublisherTermCustomApi;
import io.piano.android.api.publisher.api.PublisherTermExternalApi;
import io.piano.android.api.publisher.api.PublisherTermPaymentApi;
import io.piano.android.api.publisher.api.PublisherTermRegistrationApi;
import io.piano.android.api.publisher.api.PublisherTermStatsApi;
import io.piano.android.api.publisher.api.PublisherTermUpdateExternalApiApi;
import io.piano.android.api.publisher.api.PublisherTestApi;
import io.piano.android.api.publisher.api.PublisherUserAccessActiveApi;
import io.piano.android.api.publisher.api.PublisherUserAccessApi;
import io.piano.android.api.publisher.api.PublisherUserApi;
import io.piano.android.api.publisher.api.PublisherUserAppApi;
import io.piano.android.api.publisher.api.PublisherUserEmailApi;
import io.piano.android.api.publisher.api.PublisherWebhookApi;
import io.piano.android.api.publisher.api.PublisherWebhookResponseApi;
import io.piano.android.api.publisher.api.PublisherWebhookSettingsApi;
import io.piano.android.api.user.api.UserAccessApi;
import io.piano.android.api.user.api.UserApi;
import io.piano.android.api.user.api.UserConsentApi;

public class PianoClient {

	private static final String BASE_PATH_PROD = "https://api.tinypass.com/api/v3";
	private static final String BASE_PATH_SANDBOX = "https://sandbox.tinypass.com/api/v3";

	private Context context;
	private String aid;
	private boolean sandbox;
	private String endpoint;
	private ApiInvoker apiInvoker;

	public PianoClient(Context context, String aid, boolean sandbox) {
		this(context, aid, sandbox, null, null);
	}

	public PianoClient(Context context, String aid, boolean sandbox, Network client) {
		this(context, aid, sandbox, null, client);
	}

	public PianoClient(Context context, String aid, String endpoint) {
		this(context, aid, false, endpoint, null);
	}

	public PianoClient(Context context, String aid, String endpoint, Network client) {
		this(context, aid, false, endpoint, client);
	}

	private PianoClient(Context context, String aid, boolean sandbox, String endpoint, Network client) {
		this.context = context.getApplicationContext();
		this.aid = aid;
		this.sandbox = sandbox;
		this.endpoint = endpoint;
		this.apiInvoker = new ApiInvoker(TextUtils.isEmpty(endpoint) ? sandbox ? BASE_PATH_SANDBOX : BASE_PATH_PROD : endpoint, client);
	}

	public String getAid() {
		return aid;
	}

	public boolean isSandbox() {
		return sandbox;
	}

	public String getEndpoint() {
		return endpoint;
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

	private PublisherConsentApi apiPublisherConsent;

	public PublisherConsentApi getPublisherConsentApi() {
		if (apiPublisherConsent == null) apiPublisherConsent = new PublisherConsentApi(apiInvoker);
		return apiPublisherConsent;
	}

	private PublisherConsentEntryApi apiPublisherConsentEntry;

	public PublisherConsentEntryApi getPublisherConsentEntryApi() {
		if (apiPublisherConsentEntry == null) apiPublisherConsentEntry = new PublisherConsentEntryApi(apiInvoker);
		return apiPublisherConsentEntry;
	}

	private PublisherConsentPageApi apiPublisherConsentPage;

	public PublisherConsentPageApi getPublisherConsentPageApi() {
		if (apiPublisherConsentPage == null) apiPublisherConsentPage = new PublisherConsentPageApi(apiInvoker);
		return apiPublisherConsentPage;
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

	private PublisherExportApi apiPublisherExport;

	public PublisherExportApi getPublisherExportApi() {
		if (apiPublisherExport == null) apiPublisherExport = new PublisherExportApi(apiInvoker);
		return apiPublisherExport;
	}

	private PublisherExportCreateApi apiPublisherExportCreate;

	public PublisherExportCreateApi getPublisherExportCreateApi() {
		if (apiPublisherExportCreate == null) apiPublisherExportCreate = new PublisherExportCreateApi(apiInvoker);
		return apiPublisherExportCreate;
	}

	private PublisherExportCreateAamApi apiPublisherExportCreateAam;

	public PublisherExportCreateAamApi getPublisherExportCreateAamApi() {
		if (apiPublisherExportCreateAam == null) apiPublisherExportCreateAam = new PublisherExportCreateAamApi(apiInvoker);
		return apiPublisherExportCreateAam;
	}

	private PublisherExportUpdateApi apiPublisherExportUpdate;

	public PublisherExportUpdateApi getPublisherExportUpdateApi() {
		if (apiPublisherExportUpdate == null) apiPublisherExportUpdate = new PublisherExportUpdateApi(apiInvoker);
		return apiPublisherExportUpdate;
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

	private PublisherSubscriptionCountFailedApi apiPublisherSubscriptionCountFailed;

	public PublisherSubscriptionCountFailedApi getPublisherSubscriptionCountFailedApi() {
		if (apiPublisherSubscriptionCountFailed == null) apiPublisherSubscriptionCountFailed = new PublisherSubscriptionCountFailedApi(apiInvoker);
		return apiPublisherSubscriptionCountFailed;
	}

	private PublisherSubscriptionExportApi apiPublisherSubscriptionExport;

	public PublisherSubscriptionExportApi getPublisherSubscriptionExportApi() {
		if (apiPublisherSubscriptionExport == null) apiPublisherSubscriptionExport = new PublisherSubscriptionExportApi(apiInvoker);
		return apiPublisherSubscriptionExport;
	}

	private PublisherTaxApi apiPublisherTax;

	public PublisherTaxApi getPublisherTaxApi() {
		if (apiPublisherTax == null) apiPublisherTax = new PublisherTaxApi(apiInvoker);
		return apiPublisherTax;
	}

	private PublisherTaxRateApi apiPublisherTaxRate;

	public PublisherTaxRateApi getPublisherTaxRateApi() {
		if (apiPublisherTaxRate == null) apiPublisherTaxRate = new PublisherTaxRateApi(apiInvoker);
		return apiPublisherTaxRate;
	}

	private PublisherTaxTaxJarApi apiPublisherTaxTaxJar;

	public PublisherTaxTaxJarApi getPublisherTaxTaxJarApi() {
		if (apiPublisherTaxTaxJar == null) apiPublisherTaxTaxJar = new PublisherTaxTaxJarApi(apiInvoker);
		return apiPublisherTaxTaxJar;
	}

	private PublisherTaxTinypassApi apiPublisherTaxTinypass;

	public PublisherTaxTinypassApi getPublisherTaxTinypassApi() {
		if (apiPublisherTaxTinypass == null) apiPublisherTaxTinypass = new PublisherTaxTinypassApi(apiInvoker);
		return apiPublisherTaxTinypass;
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

	private UserConsentApi apiUserConsent;

	public UserConsentApi getUserConsentApi() {
		if (apiUserConsent == null) apiUserConsent = new UserConsentApi(apiInvoker);
		return apiUserConsent;
	}

}
