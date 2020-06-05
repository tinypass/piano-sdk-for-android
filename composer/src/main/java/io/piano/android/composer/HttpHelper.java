package io.piano.android.composer;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.piano.android.composer.model.ActiveMeter;
import io.piano.android.composer.model.CustomParameters;
import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceRequest;
import io.piano.android.composer.model.ExperienceResponse;
import io.piano.android.composer.model.events.ShowTemplate;

@RestrictTo(RestrictTo.Scope.LIBRARY)
class HttpHelper {
    // Experience request constants
    static final String PARAM_AID = "aid";
    static final String PARAM_DEBUG = "debug";
    static final String PARAM_USER_AGENT = "user_agent";
    static final String PARAM_PROTOCOL_VERSION = "protocol_version";
    static final String PARAM_TIMEZONE_OFFSET = "timezone_offset";
    static final String PARAM_PAGEVIEW_ID = "pageview_id";
    static final String PARAM_VISIT_ID = "visit_id";
    static final String PARAM_NEW_VISIT = "new_visit";
    static final String PARAM_SUBMIT_TYPE = "submit_type";
    static final String PARAM_SDK_VERSION = "sdk_version";
    static final String PARAM_XBUILDER_BROWSER_COOKIE = "xbc";
    static final String PARAM_TP_BROWSER_COOKIE = "tbc";
    static final String PARAM_TP_ACCESS_COOKIE = "tac";
    static final String PARAM_CUSTOM_VARIABLES = "custom_variables";
    static final String PARAM_USER_TOKEN = "user_token";
    static final String PARAM_URL = "url";
    static final String PARAM_REFERRER = "referer";
    static final String PARAM_TAGS = "tags";
    static final String PARAM_ZONE = "zone";
    static final String PARAM_CONTENT_CREATED = "content_created";
    static final String PARAM_CONTENT_AUTHOR = "content_author";
    static final String PARAM_CONTENT_SECTION = "content_section";
    static final String PARAM_CONTENT_NATIVE = "content_is_native";
    static final String PARAM_CUSTOM_PARAMS = "custom_params";

    static final String VALUE_MANUAL_SUBMIT_TYPE = "manual";


    // External events tracking constants
    static final String PARAM_EVENT_TRACKING_ID = "tracking_id";
    static final String PARAM_EVENT_TYPE = "event_type";
    static final String PARAM_EVENT_GROUP_ID = "event_group_id";

    static final String VALUE_EXTERNAL_EVENT_TYPE = "EXTERNAL_EVENT";
    static final String VALUE_CLOSE_GROUP_ID = "close";


    // Show template url constants
    static final String PARAM_TEMPLATE_ID = "templateId";
    static final String PARAM_TEMPLATE_VARIANT_ID = "templateVariantId";
    static final String PARAM_ACTIVE_METERS = "activeMeters";
    static final String PARAM_DISPLAY_MODE = "displayMode";
    static final String PARAM_SHOW_TEMPLATE_TRACKING_ID = "trackingId";
    static final String PARAM_GA_CLIENT_ID = "gaClientId";
    static final String PARAM_OS = "os";

    static final String VALUE_ANDROID_OS = "android";


    private final ExperienceIdsProvider experienceIdsProvider;
    private final PrefsStorage prefsStorage;
    private final Gson gson;
    private final String userAgent;

    HttpHelper(
            @NonNull ExperienceIdsProvider experienceIdsProvider,
            @NonNull PrefsStorage prefsStorage,
            @NonNull Gson gson,
            @NonNull String userAgent
    ) {
        this.experienceIdsProvider = experienceIdsProvider;
        this.prefsStorage = prefsStorage;
        this.gson = gson;
        this.userAgent = userAgent;
    }

    @NonNull
    Map<String, Object> convertExperienceRequest(
            @NonNull final ExperienceRequest request,
            @NonNull final String aid,
            @Nullable String userToken
    ) {
        Calendar calendar = Calendar.getInstance();
        long offset = TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeZone().getOffset(calendar.getTimeInMillis()));
        Date date = calendar.getTime();

        Map<String, Object> result = new HashMap<>();
        result.put(PARAM_AID, aid);
        result.put(PARAM_DEBUG, request.isDebug());
        result.put(PARAM_USER_AGENT, userAgent);
        result.put(PARAM_PROTOCOL_VERSION, Constants.PROTOCOL_VERSION);
        result.put(PARAM_TIMEZONE_OFFSET, offset);
        result.put(PARAM_PAGEVIEW_ID, experienceIdsProvider.getPageViewId(date));
        result.put(PARAM_VISIT_ID, experienceIdsProvider.getVisitId(date));
        result.put(PARAM_NEW_VISIT, experienceIdsProvider.isVisitIdRegenerated());
        result.put(PARAM_SUBMIT_TYPE, VALUE_MANUAL_SUBMIT_TYPE);
        result.put(PARAM_SDK_VERSION, BuildConfig.VERSION_NAME);
        result.put(PARAM_XBUILDER_BROWSER_COOKIE, prefsStorage.getXbuilderBrowserCookie());
        result.put(PARAM_TP_BROWSER_COOKIE, prefsStorage.getTpBrowserCookie());
        result.put(PARAM_TP_ACCESS_COOKIE, prefsStorage.getTpAccessCookie());
        result.put(PARAM_USER_TOKEN, userToken);
        result.put(PARAM_URL, request.getUrl());
        result.put(PARAM_REFERRER, request.getReferer());
        result.put(PARAM_ZONE, request.getZone());
        result.put(PARAM_CONTENT_CREATED, request.getContentCreated());
        result.put(PARAM_CONTENT_AUTHOR, request.getContentAuthor());
        result.put(PARAM_CONTENT_SECTION, request.getContentSection());
        result.put(PARAM_CONTENT_NATIVE, request.getContentIsNative());

        Map<String, String> customVariables = request.getCustomVariables();
        if (!customVariables.isEmpty())
            result.put(PARAM_CUSTOM_VARIABLES, gson.toJson(customVariables));
        List<String> tags = request.getTags();
        if (!tags.isEmpty())
            result.put(PARAM_TAGS, joinToString(tags));
        CustomParameters customParameters = request.getCustomParameters();
        if (!customParameters.isEmpty())
            result.put(PARAM_CUSTOM_PARAMS, gson.toJson(customParameters));

        filterNullValues(result);
        return result;
    }

    void processExperienceResponse(ExperienceResponse response) {
        if (response.xbCookie != null)
            prefsStorage.setXbuilderBrowserCookie(response.xbCookie.value);
        if (response.tbCookie != null)
            prefsStorage.setTpBrowserCookie(response.tbCookie.value);
        if (response.taCookie != null)
            prefsStorage.setTpAccessCookie(response.taCookie.value);
        if (response.visitTimeoutMinutes != null)
            prefsStorage.setVisitTimeout(TimeUnit.MILLISECONDS.convert(response.visitTimeoutMinutes, TimeUnit.MINUTES));
        prefsStorage.setServerTimezoneOffset(response.timeZoneOffsetMillis);
    }

    @NonNull
    Map<String, Object> buildEventTracking(@NonNull String trackingId) {
        Map<String, Object> result = new HashMap<>();
        result.put(PARAM_EVENT_TRACKING_ID, trackingId);
        result.put(PARAM_EVENT_TYPE, VALUE_EXTERNAL_EVENT_TYPE);
        result.put(PARAM_EVENT_GROUP_ID, VALUE_CLOSE_GROUP_ID);

        filterNullValues(result);
        return result;
    }

    @NonNull
    Map<String, Object> buildShowTemplateParameters(
            @NonNull Event<ShowTemplate> showTemplateEvent,
            @NonNull final ExperienceRequest experienceRequest,
            @NonNull final String aid,
            @Nullable String userToken,
            @Nullable String gaClientId
    ) {
        Map<String, Object> result = new HashMap<>();
        result.put(PARAM_AID, aid);
        result.put(PARAM_USER_TOKEN, userToken);
        result.put(PARAM_GA_CLIENT_ID, gaClientId);
        result.put(PARAM_OS, VALUE_ANDROID_OS);
        result.put(PARAM_DISPLAY_MODE, ShowTemplate.DisplayMode.INLINE);

        result.put(PARAM_SHOW_TEMPLATE_TRACKING_ID, showTemplateEvent.eventExecutionContext.trackingId);
        result.put(PARAM_TEMPLATE_ID, showTemplateEvent.eventData.templateId);
        result.put(PARAM_TEMPLATE_VARIANT_ID, showTemplateEvent.eventData.templateVariantId);

        List<ActiveMeter> activeMeters = showTemplateEvent.eventExecutionContext.activeMeters;
        if (activeMeters != null && !activeMeters.isEmpty())
            result.put(PARAM_ACTIVE_METERS, gson.toJson(activeMeters));

        result.put(PARAM_DEBUG, experienceRequest.isDebug());
        result.put(PARAM_URL, experienceRequest.getUrl());
        result.put(PARAM_CONTENT_AUTHOR, experienceRequest.getContentAuthor());
        result.put(PARAM_CONTENT_SECTION, experienceRequest.getContentSection());
        result.put(PARAM_ZONE, experienceRequest.getZone());

        Map<String, String> customVariables = experienceRequest.getCustomVariables();
        if (!customVariables.isEmpty())
            result.put(PARAM_CUSTOM_VARIABLES, gson.toJson(customVariables));
        List<String> tags = experienceRequest.getTags();
        if (!tags.isEmpty())
            result.put(PARAM_TAGS, joinToString(tags));


        filterNullValues(result);
        return result;
    }

    void filterNullValues(@NonNull Map<String, Object> map) {
        map.values().removeAll(Collections.singleton(null));
    }

    //just for mocking in tests
    @NonNull
    String joinToString(Collection<?> collection) {
        return TextUtils.join(",", collection);
    }
}
