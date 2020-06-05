package io.piano.android.composer;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.piano.android.composer.listeners.EventTypeListener;
import io.piano.android.composer.listeners.ExceptionListener;
import io.piano.android.composer.model.Data;
import io.piano.android.composer.model.ErrorMessage;
import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceRequest;
import io.piano.android.composer.model.ExperienceResponse;
import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.events.ShowTemplate;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Composer {
    private final String aid;
    private final String endpoint;
    private final Api api;
    private final HttpHelper httpHelper;

    private String userToken;
    private String gaClientId;

    public static final String USER_PROVIDER_TINYPASS_ACCOUNTS = "tinypass_accounts";
    public static final String USER_PROVIDER_PIANO_ID = "piano_id";
    public static final String USER_PROVIDER_JANRAIN = "janrain";

    /**
     * Initialize Composer
     *
     * @param context App/Activity/Service context, application context will be requested from it
     * @param aid     Piano advertiser id
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static void init(@NonNull Context context, @NonNull String aid) {
        init(context, aid, null);
    }

    /**
     * Initialize Composer
     *
     * @param context App/Activity/Service context, application context will be requested from it
     * @param aid     Piano advertiser id
     * @param sandbox If true, sandbox server will be used, else - production server
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static void init(@NonNull Context context, @NonNull String aid, boolean sandbox) {
        init(context, aid, sandbox ? Constants.BASE_URL_SANDBOX : null);
    }

    /**
     * Initialize Composer
     *
     * @param context  App/Activity/Service context, application context will be requested from it
     * @param aid      Piano advertiser id
     * @param endpoint Custom endpoint, that will be used.
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static void init(@NonNull Context context, @NonNull String aid, @Nullable String endpoint) {
        DependenciesProvider.init(context, aid, endpoint);
    }

    /**
     * Get Composer instance
     *
     * @return Composer instance
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public static Composer getInstance() {
        return DependenciesProvider.getInstance().getComposer();
    }

    Composer(
            @NonNull Api api,
            @NonNull HttpHelper httpHelper,
            @NonNull String aid,
            @Nullable String endpoint
    ) {
        this.api = api;
        this.httpHelper = httpHelper;
        this.aid = aid;
        this.endpoint = endpoint;
    }

    /**
     * Sets user token, which will be sent at each experience request
     *
     * @param userToken User tokeb
     * @return Composer instance
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public Composer userToken(@Nullable String userToken) {
        this.userToken = userToken;
        return this;
    }

    /**
     * Sets Google Analytics client id
     *
     * @param gaClientId Client id
     * @return Composer instance
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public Composer gaClientId(@Nullable String gaClientId) {
        this.gaClientId = gaClientId;
        return this;
    }

    /**
     * Gets experience from server
     *
     * @param request            Prepared experience request
     * @param eventTypeListeners Collection of event listeners
     * @param exceptionListener  Listener for exceptions
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public void getExperience(
            @NonNull final ExperienceRequest request,
            @NonNull Collection<EventTypeListener<? extends EventType>> eventTypeListeners,
            @NonNull ExceptionListener exceptionListener
    ) {
        String url = (endpoint != null ? endpoint : Constants.BASE_URL_EXPERIENCE) + Constants.URL_EXPERIENCE_EXECUTE;
        api.getExperience(url, httpHelper.convertExperienceRequest(request, aid, userToken))
                .enqueue(
                        new Callback<Data<ExperienceResponse>>() {
                            @Override
                            public void onResponse(@NonNull Call<Data<ExperienceResponse>> call, @NonNull Response<Data<ExperienceResponse>> response) {
                                try {
                                    Data<ExperienceResponse> body = Utils.validateResponse(response);
                                    if (body.errors.isEmpty())
                                        processExperienceResponse(request, body.data, eventTypeListeners, exceptionListener);
                                    else
                                        exceptionListener.onException(new ComposerException(buildErrorMessage(body.errors)));
                                } catch (Exception e) {
                                    ComposerException exception = e instanceof ComposerException ? (ComposerException) e : new ComposerException(e);
                                    exceptionListener.onException(exception);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Data<ExperienceResponse>> call, @NonNull Throwable t) {
                                exceptionListener.onException(new ComposerException(t));
                            }
                        }
                );
    }

    void processExperienceResponse(
            @NonNull final ExperienceRequest request,
            @NonNull final ExperienceResponse data,
            @NonNull final Collection<EventTypeListener<? extends EventType>> eventTypeListeners,
            @NonNull final ExceptionListener exceptionListener
    ) {
        httpHelper.processExperienceResponse(data);
        try {
            // Don't process any custom logic if there's no listeners
            if (eventTypeListeners.isEmpty())
                return;
            for (Event<? extends EventType> event : data.result.events) {
                if (event.eventData instanceof ShowTemplate) {
                    fillShowTemplateUrl((Event<ShowTemplate>) event, request);
                }
                for (EventTypeListener listener : eventTypeListeners) {
                    if (listener.canProcess(event.eventData))
                        try {
                            listener.onExecuted(event);
                        } catch (Throwable t) {
                            exceptionListener.onException(new ComposerException(t));
                        }
                }
            }
        } catch (Throwable throwable) {
            exceptionListener.onException(new ComposerException(throwable));
        }
    }

    /**
     * Tracks external event by id
     *
     * @param trackingId Tracking id
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public void trackExternalEvent(@NonNull String trackingId) {
        String url = (endpoint != null ? endpoint : Constants.BASE_URL_BUY) + Constants.URL_TRACK_EXTERNAL_EVENT;
        api.trackExternalEvent(url, httpHelper.buildEventTracking(trackingId))
                .enqueue(new Callback<ResponseBody>() {
                             @Override
                             public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                             }

                             @Override
                             public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                             }
                         }
                );
    }

    void fillShowTemplateUrl(
            @NonNull Event<ShowTemplate> showTemplateEvent,
            @NonNull final ExperienceRequest experienceRequest
    ) {
        String base = (endpoint != null ? endpoint : Constants.BASE_URL_BUY) + Constants.URL_TEMPLATE;
        Map<String, Object> parameters = httpHelper.buildShowTemplateParameters(showTemplateEvent, experienceRequest, aid, userToken, gaClientId);
        HttpUrl url = HttpUrl.parse(base);
        if (url == null)
            return;
        HttpUrl.Builder builder = url.newBuilder();
        for (Map.Entry<String, Object> p : parameters.entrySet()) {
            builder.addQueryParameter(p.getKey(), p.getValue().toString());
        }
        showTemplateEvent.eventData.url = builder.build().toString();
    }

    // mock in tests
    String buildErrorMessage(List<ErrorMessage> errorMessages) {
        List<String> msgs = new ArrayList<>();
        for (ErrorMessage errorMessage : errorMessages) {
            msgs.add(errorMessage.message);
        }
        return TextUtils.join("\n", msgs);
    }
}
