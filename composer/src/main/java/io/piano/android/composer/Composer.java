package io.piano.android.composer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.piano.android.composer.exception.ComposerException;
import io.piano.android.composer.exception.ComposerExceptionListener;
import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceExecute;
import io.piano.android.composer.model.ExperienceResponse;
import io.piano.android.composer.model.MeterActive;
import io.piano.android.composer.model.MeterExpired;
import io.piano.android.composer.model.NonSite;
import io.piano.android.composer.model.ShowLogin;
import io.piano.android.composer.model.ShowTemplate;
import io.piano.android.composer.model.UserSegmentFalse;
import io.piano.android.composer.model.UserSegmentTrue;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class Composer {

    private static final String BASE_URL_EXPERIENCE = "https://experience.tinypass.com/";
    private static final String BASE_URL_BUY = "https://buy.tinypass.com/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";

    private static final String URL_EXECUTE = "xbuilder/experience/executeMobile";
    private static final String URL_TEMPLATE = "checkout/template/show";
    private static final String URL_TRACK_EXTERNAL_EVENT = "api/v3/conversion/logAutoMicroConversion";

    private static final String PREF = "io.piano.android.composer";

    private static final int VISIT_TIMEOUT_MINUTES_FALLBACK = 30;

    private static final CharSequence TAGS_DELIMITER = ",";
    private static final int PROTOCOL_VERSION = 1;

    private static final String RANDOM_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RANDOM_STRING_LENGTH = RANDOM_STRING.length();

    public static final String USER_PROVIDER_TINYPASS_ACCOUNTS = "tinypass_accounts";
    public static final String USER_PROVIDER_JANRAIN = "janrain";

    private static final Handler HANDLER_MAIN_THREAD = new Handler(Looper.getMainLooper());

    private Context context;
    private String aid;
    private String endpoint;
    private boolean sandbox;

    private boolean debug;
    private String userAgent;
    private Map<String, String> customVariables;
    private String userToken;
    private String url;
    private String referer;
    private List<String> tags;
    private String zone;
    private String contentCreated;
    private String contentAuthor;
    private String contentSection;
    private Boolean contentIsNative;
    private CustomParams customParams;

    private String gaClientId;

    private List<EventTypeListener> eventTypeListeners;

    private List<ComposerExceptionListener> composerExceptionListeners;

    private Composer() {}

    public Composer(Context context, String aid) {
        this(context, aid, null, false);
    }

    public Composer(Context context, String aid, String endpoint) {
        this(context, aid, endpoint, false);
    }

    public Composer(Context context, String aid, boolean sandbox) {
        this(context, aid, null, sandbox);
    }

    private Composer(Context context, String aid, String endpoint, boolean sandbox) {
        this.context = context.getApplicationContext();
        this.aid = aid;
        this.endpoint = endpoint;
        this.sandbox = sandbox;

        this.userAgent = createUserAgent();
        this.customVariables = new HashMap<>();
        this.tags = new ArrayList<>();

        this.eventTypeListeners = new ArrayList<>();

        this.composerExceptionListeners = new ArrayList<>();
    }

    public Composer debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public Composer customVariable(String key, String value) {
        customVariables.put(key, value);
        return this;
    }

    public Composer customVariables(Map<String, String> customVariables) {
        this.customVariables.putAll(customVariables);
        return this;
    }

    public Composer clearCustomVariables() {
        this.customVariables.clear();
        return this;
    }

    public Composer userToken(String userToken) {
        this.userToken = userToken;
        return this;
    }

    public Composer url(String url) {
        this.url = url;
        return this;
    }

    public Composer referer(String referer) {
        this.referer = referer;
        return this;
    }

    public Composer tag(String tag) {
        tags.add(tag);
        return this;
    }

    public Composer tags(Collection<String> tags) {
        this.tags.addAll(tags);
        return this;
    }

    public Composer zone(String zone) {
        this.zone = zone;
        return this;
    }

    /**
     * @param contentCreated ISO 8601-formatted string that includes the published date and time of the content
     */
    public Composer contentCreated(String contentCreated) {
        this.contentCreated = contentCreated;
        return this;
    }

    public Composer contentAuthor(String contentAuthor) {
        this.contentAuthor = contentAuthor;
        return this;
    }

    public Composer contentSection(String contentSection) {
        this.contentSection = contentSection;
        return this;
    }

    public Composer contentIsNative(Boolean contentIsNative) {
        this.contentIsNative = contentIsNative;
        return this;
    }

    public Composer customParams(CustomParams customParams) {
        this.customParams = customParams;
        return this;
    }

    public Composer gaClientId(String gaClientId) {
        this.gaClientId = gaClientId;
        return this;
    }

    public Composer addListener(EventTypeListener eventTypeListener) {
        eventTypeListeners.add(eventTypeListener);
        return this;
    }

    public Composer removeListener(EventTypeListener eventTypeListener) {
        eventTypeListeners.remove(eventTypeListener);
        return this;
    }

    public Composer clearListeners() {
        eventTypeListeners.clear();
        return this;
    }

    public Composer addExceptionListener(ComposerExceptionListener composerExceptionListener) {
        this.composerExceptionListeners.add(composerExceptionListener);
        return this;
    }

    public Composer removeExceptionListener(ComposerExceptionListener composerExceptionListener) {
        this.composerExceptionListeners.remove(composerExceptionListener);
        return this;
    }

    public Composer clearExceptionListeners() {
        this.composerExceptionListeners.clear();
        return this;
    }

    public void execute() {
        Request request = new Request.Builder()
                .url(getBaseUrl(true) + URL_EXECUTE)
                .post(createRequestBody())
                .build();

        OkHttpClientHolder.OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handleException(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr;
                try {
                    jsonStr = response.body().string();
                } catch (IOException e) {
                    handleException(e);
                    return;
                }

                JSONObject json;
                try {
                    json = new JSONObject(jsonStr);
                } catch (JSONException e) {
                    handleException(e);
                    return;
                }

                ExperienceResponse experienceResponse;
                try {
                    experienceResponse = ExperienceResponse.fromJson(json);
                } catch (Exception e) {
                    handleException(e);
                    return;
                }

                SharedPreferences.Editor editor = context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit();
                editor.putString("tbc", experienceResponse.tbc);
                editor.putString("xbc", experienceResponse.xbc);
                editor.putString("tac", experienceResponse.tac);
                editor.putInt("timeZoneOffsetMillis", experienceResponse.timeZoneOffsetMillis);
                if (experienceResponse.visitTimeoutMinutes != null) {
                    editor.putInt("visitTimeoutMinutes", experienceResponse.visitTimeoutMinutes);
                }
                editor.apply();

                for (Event event : experienceResponse.events) {
                    Class<? extends EventTypeListener> eventTypeListenerClass = null;

                    if (event instanceof ShowLogin) {
                        eventTypeListenerClass = ShowLoginListener.class;
                    } else if (event instanceof MeterActive) {
                        eventTypeListenerClass = MeterActiveListener.class;
                    } else if (event instanceof MeterExpired) {
                        eventTypeListenerClass = MeterExpiredListener.class;
                    } else if (event instanceof UserSegmentTrue) {
                        eventTypeListenerClass = UserSegmentTrueListener.class;
                    } else if (event instanceof UserSegmentFalse) {
                        eventTypeListenerClass = UserSegmentFalseListener.class;
                    } else if (event instanceof ExperienceExecute) {
                        eventTypeListenerClass = ExperienceExecuteListener.class;
                    } else if (event instanceof NonSite) {
                        eventTypeListenerClass = NonSiteListener.class;
                    } else if (event instanceof ShowTemplate) {
                        ShowTemplate showTemplate = (ShowTemplate) event;
                        showTemplate.url = createShowTemplateUrl(showTemplate);
                        showTemplate.endpointUrl = getBaseUrl(false);

                        eventTypeListenerClass = ShowTemplateListener.class;
                    }

                    if (eventTypeListenerClass != null) {
                        fireListeners(event, findListeners(eventTypeListenerClass, eventTypeListeners));
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void fireListeners(final Event event, List<? extends EventTypeListener> classifiedEventTypeListeners) {
        if (!classifiedEventTypeListeners.isEmpty()) {
            for (final EventTypeListener eventTypeListener : classifiedEventTypeListeners) {
                HANDLER_MAIN_THREAD.post(new Runnable() {
                    @Override
                    public void run() {
                        eventTypeListener.onExecuted(event);
                    }
                });
            }
        }
    }

    private void handleException(final Exception e) {
        if (!composerExceptionListeners.isEmpty()) {
            Iterator<ComposerExceptionListener> iterator = composerExceptionListeners.iterator();
            //noinspection WhileLoopReplaceableByForEach
            while (iterator.hasNext()) {
                final ComposerExceptionListener composerExceptionListener = iterator.next();
                HANDLER_MAIN_THREAD.post(new Runnable() {
                    @Override
                    public void run() {
                        composerExceptionListener.onComposerException(
                                e instanceof ComposerException ? (ComposerException) e : new ComposerException(e)
                        );
                    }
                });
            }
        }
    }

    private String getBaseUrl(boolean isExecute) {
        if (TextUtils.isEmpty(endpoint)) {
            return sandbox ? BASE_URL_SANDBOX : isExecute ? BASE_URL_EXPERIENCE : BASE_URL_BUY;
        } else {
            return endpoint;
        }
    }

    private RequestBody createRequestBody() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        String xbc = sharedPreferences.getString("xbc", null);
        String tbc = sharedPreferences.getString("tbc", null);
        String tac = sharedPreferences.getString("tac", null);
        int timeZoneOffset = Calendar.getInstance().getTimeZone().getRawOffset() / 1000 / 60;
        String pageViewId = generatePageViewId();
        String oldVisitId = getVisitId(sharedPreferences);
        String visitId = getOrCreateVisitId(sharedPreferences);
        boolean isNewVisit = !visitId.equals(oldVisitId);

        FormBody.Builder requestBuilder = new FormBody.Builder()
                .add("aid", aid)
                .add("debug", String.valueOf(debug))
                .add("user_agent", userAgent)
                .add("protocol_version", String.valueOf(PROTOCOL_VERSION))
                .add("timezone_offset", String.valueOf(timeZoneOffset))
                .add("pageview_id", pageViewId)
                .add("visit_id", visitId)
                .add("new_visit", String.valueOf(isNewVisit))
                .add("submit_type", "manual")
                .add("sdk_version", BuildConfig.VERSION_NAME);

        if (!TextUtils.isEmpty(xbc)) {
            requestBuilder.add("xbc", xbc);
        }

        if (!customVariables.isEmpty()) {
            requestBuilder.add("custom_variables", new JSONObject(customVariables).toString());
        }

        if (!TextUtils.isEmpty(userToken)) {
            requestBuilder.add("user_token", userToken);
        }

        if (!TextUtils.isEmpty(url)) {
            requestBuilder.add("url", url);
        }

        if (!TextUtils.isEmpty(referer)) {
            requestBuilder.add("referer", referer);
        }

        if (!tags.isEmpty()) {
            requestBuilder.add("tags", TextUtils.join(TAGS_DELIMITER, tags));
        }

        if (!TextUtils.isEmpty(zone)) {
            requestBuilder.add("zone", zone);
        }

        if (!TextUtils.isEmpty(contentCreated)) {
            requestBuilder.add("content_created", contentCreated);
        }

        if (!TextUtils.isEmpty(contentAuthor)) {
            requestBuilder.add("content_author", contentAuthor);
        }

        if (!TextUtils.isEmpty(contentSection)) {
            requestBuilder.add("content_section", contentSection);
        }

        if (contentIsNative != null) {
            requestBuilder.add("content_is_native", contentIsNative.toString());
        }

        if (!TextUtils.isEmpty(tbc)) {
            requestBuilder.add("tbc", tbc);
        }

        if (!TextUtils.isEmpty(tac)) {
            requestBuilder.add("tac", tac);
        }

        if ((customParams != null) && !customParams.isEmpty()) {
            try { requestBuilder.add("custom_params", customParams.toJson()); } catch (JSONException ignored) {}
        }

        return requestBuilder.build();
    }

    private String createUserAgent() {
        return String.format("Piano composer SDK (Android %s (Build %s); %s %s/%s)"
                , Build.VERSION.RELEASE
                , Build.ID
                , context.getResources().getBoolean(R.bool.isTablet) ? "Tablet" : "Mobile"
                , Build.MANUFACTURER
                , Build.MODEL
        );
    }

    private String generatePageViewId() {
        String random = generateRandomString(16);
        String hash = generateRandomString(32);
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US);
        String date = dateFormat.format(now);
        return date + "-" + random + "-" + hash;
    }

    private String generateRandomString(int length) {
        StringBuilder randomStringBuilder = new StringBuilder(length);

        Random random = new Random();
        for (int ii = 0; ii < length; ii++) {
            randomStringBuilder.append(RANDOM_STRING.charAt(random.nextInt(RANDOM_STRING_LENGTH)));
        }

        return randomStringBuilder.toString();
    }

    private String getVisitId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("visitId", null);
    }

    private String getOrCreateVisitId(SharedPreferences sharedPreferences) {
        long currentTimeMillis = System.currentTimeMillis();

        long visitIdTimestampMillis = sharedPreferences.getLong("visitIdTimestampMillis", -1);
        if (visitIdTimestampMillis == -1) {
            return createVisitId(sharedPreferences, currentTimeMillis);
        }

        int visitTimeoutMinutes = sharedPreferences.getInt("visitTimeoutMinutes", VISIT_TIMEOUT_MINUTES_FALLBACK);
        if (visitIdTimestampMillis < currentTimeMillis - visitTimeoutMinutes * 60_000) {
            return createVisitId(sharedPreferences, currentTimeMillis);
        }

        int timeZoneOffsetMillis = sharedPreferences.getInt("timeZoneOffsetMillis", 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.ZONE_OFFSET, timeZoneOffsetMillis);
        if (visitIdTimestampMillis < calendar.getTimeInMillis()) {
            return createVisitId(sharedPreferences, currentTimeMillis);
        }

        String visitId = getVisitId(sharedPreferences);
        sharedPreferences.edit()
                .putLong("visitIdTimestampMillis", currentTimeMillis)
                .apply();

        return visitId;
    }

    private String createVisitId(SharedPreferences sharedPreferences, long currentTimeMillis) {
        String visitId = generateVisitId();
        sharedPreferences.edit()
                .putString("visitId", visitId)
                .putLong("visitIdTimestampMillis", currentTimeMillis)
                .apply();
        return visitId;
    }

    private String generateVisitId() {
        return "v-" + generatePageViewId();
    }

    @SuppressWarnings("unchecked")
    private <T extends EventTypeListener> List<T> findListeners(Class<T> classOfT, List<? extends EventTypeListener> eventTypeListeners) {
        List<T> classifiedEventTypeListeners = new ArrayList<>();

        if (!eventTypeListeners.isEmpty()) {
            Iterator<? extends EventTypeListener> iterator = eventTypeListeners.iterator();
            //noinspection WhileLoopReplaceableByForEach
            while (iterator.hasNext()) {
                EventTypeListener eventTypeListener = iterator.next();
                if (classOfT.isInstance(eventTypeListener)) {
                    classifiedEventTypeListeners.add((T) eventTypeListener);
                }
            }
        }

        return classifiedEventTypeListeners;
    }

    private String createShowTemplateUrl(ShowTemplate showTemplate) {
        StringBuilder urlBuilder = new StringBuilder(getBaseUrl(false));

        urlBuilder.append(URL_TEMPLATE);

        urlBuilder.append("?aid=").append(aid);

        urlBuilder.append("&templateId=").append(showTemplate.templateId);

        if (!TextUtils.isEmpty(userToken)) {
            urlBuilder.append("&userToken=").append(userToken);
        }

        if (!customVariables.isEmpty()) {
            urlBuilder.append("&customVariables=").append(new JSONObject(customVariables).toString());
        }

        if (showTemplate.eventExecutionContext.activeMetersJson != null) {
            urlBuilder.append("&activeMeters=").append(showTemplate.eventExecutionContext.activeMetersJson.toString());
        }

        if (debug) {
            urlBuilder.append("&debug=").append(debug);
        }

        urlBuilder.append("&displayMode=").append(ShowTemplate.DISPLAY_MODE_INLINE);

        if (!tags.isEmpty()) {
            urlBuilder.append("&tags=").append(TextUtils.join(TAGS_DELIMITER, tags));
        }

        if (!TextUtils.isEmpty(url)) {
            urlBuilder.append("&url=").append(url);
        }

        urlBuilder.append("&trackingId=").append(showTemplate.eventExecutionContext.trackingId);

        if (!TextUtils.isEmpty(contentAuthor)) {
            urlBuilder.append("&contentAuthor=").append(contentAuthor);
        }

        if (!TextUtils.isEmpty(contentSection)) {
            urlBuilder.append("&contentSection=").append(contentSection);
        }

        if (!TextUtils.isEmpty(zone)) {
            urlBuilder.append("&zone=").append(zone);
        }

        if (!TextUtils.isEmpty(gaClientId)) {
            urlBuilder.append("&gaClientId=").append(gaClientId);
        }

        urlBuilder.append("&os=android");

        return urlBuilder.toString();
    }

    public static void trackExternalEvent(String endpointUrl, String trackingId) {
        FormBody formBody = new FormBody.Builder()
                .add("tracking_id", trackingId)
                .add("event_type", "EXTERNAL_EVENT")
                .add("event_group_id", "close")
                .build();

        Request request = new Request.Builder()
                .url(endpointUrl + URL_TRACK_EXTERNAL_EVENT)
                .post(formBody)
                .build();

        OkHttpClientHolder.OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {}
        });
    }

    private static class OkHttpClientHolder {

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .readTimeout(30_000, TimeUnit.MILLISECONDS)
                .writeTimeout(30_000, TimeUnit.MILLISECONDS)
                .build();
    }
}
