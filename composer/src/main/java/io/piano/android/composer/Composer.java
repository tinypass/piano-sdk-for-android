package io.piano.android.composer;

import android.content.Context;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceResponse;
import io.piano.android.composer.model.ShowLogin;
import io.piano.android.composer.model.ShowTemplate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public final class Composer {

    private static final String BASE_URL_PROD = "https://buy.piano.io/";
    private static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";

    private static final CharSequence TAGS_DELIMITER = ",";

    private static volatile PianoService pianoService;

    private Context context;
    private String aid;
    private boolean sandbox;

    private boolean trackPages;
    private boolean debug;
    private Map<String, Object> customVariables;
    private List<String> tags;

    private List<EventTypeListener> eventTypeListeners;

    private String userAgent;

    private Composer() {}

    public Composer(Context context, String aid) {
        this(context, aid, false);
    }

    public Composer(Context context, String aid, boolean sandbox) {
        this.context = context.getApplicationContext();
        this.aid = aid;
        this.sandbox = sandbox;

        this.trackPages = true;
        this.customVariables = new HashMap<>();
        this.tags = new ArrayList<>();

        this.eventTypeListeners = new ArrayList<>();

        this.userAgent = createUserAgent();
    }

    public Composer trackPages(boolean trackPages) {
        this.trackPages = trackPages;
        return this;
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

    public Composer tag(String tag) {
        tags.add(tag);
        return this;
    }

    public Composer tags(Collection<String> tags) {
        this.tags.addAll(tags);
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
        ensurePianoService();

        String customVariables = new JSONObject(this.customVariables).toString();
        pianoService.execute(aid, customVariables)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String jsonpCallback) {
                        if (eventTypeListeners != null) {
                            JSONObject json = null;
                            try { json = ExperienceResponse.fromJson(jsonpCallback); } catch (JSONException ignored) {}

                            if (json != null) {
                                ExperienceResponse experienceResponse = ExperienceResponse.fromJson(json);
                                for (Event event : experienceResponse.events) {
                                    if (event instanceof ShowLogin) {
                                        ShowLogin showLogin = (ShowLogin) event;
                                        showLogin(showLogin.userProvider, findListener(ShowLoginListener.class, eventTypeListeners));
                                    } else if (event instanceof ShowTemplate) {
                                        ShowTemplate showTemplate = (ShowTemplate) event;
                                        showTemplate(getBaseUrl(), showTemplate.templateId, showTemplate.delayByInSec, findListener(ShowTemplateListener.class, eventTypeListeners));
                                    }
                                }
                            }
                        }
                    }
                });
    }

    private void showLogin(final String userProvider, final ShowLoginListener showLoginListener) {
        if (showLoginListener != null) {
            showLoginListener.onExecuted(userProvider);
        }
    }

    private void showTemplate(final String baseUrl, final String templateId, final long delay, final ShowTemplateListener showTemplateListener) {
        ensurePianoService();
        pianoService.show(aid, templateId, new HashMap<String, String>())
                .delay(delay, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String htmlTemplate) {
                        if (showTemplateListener != null) {
                            showTemplateListener.onExecuted(baseUrl, htmlTemplate);
                        }
                    }
                });
    }

    private void ensurePianoService() {
        if (pianoService == null) {
            synchronized (this) {
                if (pianoService == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(sandbox ? BASE_URL_SANDBOX : BASE_URL_PROD)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    pianoService = retrofit.create(PianoService.class);
                }
            }
        }
    }

    private String getBaseUrl() {
        return sandbox ? BASE_URL_SANDBOX : BASE_URL_PROD;
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

    @SuppressWarnings("unchecked")
    private <T> T findListener(Class<T> classOfT, List<EventTypeListener> eventTypeListeners) {
        for (EventTypeListener eventTypeListener : eventTypeListeners) {
            if (classOfT.isInstance(eventTypeListener)) {
                return (T) eventTypeListener;
            }
        }

        return null;
    }
}
