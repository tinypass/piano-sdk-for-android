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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceExecute;
import io.piano.android.composer.model.ExperienceResponse;
import io.piano.android.composer.model.MeterActive;
import io.piano.android.composer.model.MeterExpired;
import io.piano.android.composer.model.NonSite;
import io.piano.android.composer.model.ShowLogin;
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

    private static final String BASE_URL_PROD = "https://buy.tinypass.com/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";
    private static final String URL_EXECUTE = "xbuilder/experience/executeMobile";

    private static final String PREF = "io.piano.android.composer";

    private static final CharSequence TAGS_DELIMITER = ",";
    private static final int PROTOCOL_VERSION = 1;

    private static final String RANDOM_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RANDOM_STRING_LENGTH = RANDOM_STRING.length();

    public static final String USER_PROVIDER_TINYPASS_ACCOUNTS = "tinypass_accounts";

    private static volatile OkHttpClient okHttpClient;
    private static final Object LOCK = new Object();
    private static final Handler HANDLER_MAIN_THREAD = new Handler(Looper.getMainLooper());

    private Context context;
    private String aid;
    private String endpoint;
    private boolean sandbox;

    private boolean debug;
    private String userAgent;
    private Map<String, Object> customVariables;
    private String userToken;
    private String userProvider;
    private String url;
    private String referer;
    private List<String> tags;
    private String zone;
    private CustomParams customParams;

    private List<EventTypeListener> eventTypeListeners;

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

        ensureOkHttpClient();
    }

    public Composer debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public Composer customVariable(String key, Object value) {
        customVariables.put(key, value);
        return this;
    }

    public Composer customVariables(Map<String, Object> customVariables) {
        this.customVariables.putAll(customVariables);
        return this;
    }

    public Composer userToken(String userToken) {
        this.userToken = userToken;
        return this;
    }

    public Composer userProvider(String userProvider) {
        this.userProvider = userProvider;
        return this;
    }

    public Composer user(String userToken, String userProvider) {
        userToken(userToken);
        userProvider(userProvider);
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

    public Composer customParams(CustomParams customParams) {
        this.customParams = customParams;
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

    public void execute() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + URL_EXECUTE)
                .post(createRequestBody())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();

                JSONObject json = null;
                try { json = new JSONObject(jsonStr); } catch (JSONException ignored) {}

                if (json != null) {
                    ExperienceResponse experienceResponse = ExperienceResponse.fromJson(json);

                    SharedPreferences.Editor editor = context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit();
                    editor.putString("tbc", experienceResponse.tbc);
                    editor.putString("xbc", experienceResponse.xbc);
                    editor.putString("tac", experienceResponse.tac);
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
                        }

                        if (eventTypeListenerClass != null) {
                            fireListeners(event, findListeners(eventTypeListenerClass, eventTypeListeners));
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void fireListeners(final Event event, List<? extends EventTypeListener> classifiedEventTypeListeners) {
        for (final EventTypeListener eventTypeListener : classifiedEventTypeListeners) {
            HANDLER_MAIN_THREAD.post(new Runnable() {
                @Override
                public void run() {
                    eventTypeListener.onExecuted(event);
                }
            });
        }
    }

    private void ensureOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (LOCK) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder().build();
                }
            }
        }
    }

    private String getBaseUrl() {
        if (TextUtils.isEmpty(endpoint)) {
            return sandbox ? BASE_URL_SANDBOX : BASE_URL_PROD;
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

        FormBody.Builder requestBuilder = new FormBody.Builder()
                .add("aid", aid)
                .add("debug", String.valueOf(debug))
                .add("user_agent", userAgent)
                .add("protocol_version", String.valueOf(PROTOCOL_VERSION))
                .add("timezone_offset", String.valueOf(timeZoneOffset))
                .add("pageview_id", pageViewId)
                .add("submit_type", "manual");

        if (!TextUtils.isEmpty(xbc)) {
            requestBuilder.add("xbc", xbc);
        }

        if (!customVariables.isEmpty()) {
            requestBuilder.add("custom_variables", new JSONObject(customVariables).toString());
        }

        if (!TextUtils.isEmpty(userToken) && !TextUtils.isEmpty(userProvider)) {
            requestBuilder.add("user_token", userToken);
            requestBuilder.add("user_provider", userProvider);
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

    @SuppressWarnings("unchecked")
    private <T extends EventTypeListener> List<T> findListeners(Class<T> classOfT, List<? extends EventTypeListener> eventTypeListeners) {
        List<T> classifiedEventTypeListeners = new ArrayList<>();

        for (EventTypeListener eventTypeListener : eventTypeListeners) {
            if (classOfT.isInstance(eventTypeListener)) {
                classifiedEventTypeListeners.add((T) eventTypeListener);
            }
        }

        return classifiedEventTypeListeners;
    }
}
