package io.piano.android.composer

import android.content.Context
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.EventsListener
import io.piano.android.composer.listeners.ExceptionListener
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.EdgeCookies
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ExperienceExecute
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.consents.PianoConsents
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * Main entry point for the Piano Composer SDK.
 *
 * The `Composer` class is the main entry point for the Piano Composer SDK. It provides methods to
 * interact with the Piano Composer API and handle the display of templates. It allows setting user
 * tokens, tracking events, and retrieving experiences from the server.
 *
 * @param composerApi The instance of [ComposerApi] for making API requests.
 * @param generalApi The instance of [GeneralApi] for making general API requests.
 * @param httpHelper The instance of [HttpHelper] for handling HTTP-related tasks.
 * @param prefsStorage The instance of [PrefsStorage] for handling shared preferences.
 * @param aid Your Application ID (AID).
 * @param endpoint The custom API endpoint. It should be one of the predefined endpoints in [Endpoint].
 * @property pianoConsents [PianoConsents] instance for managing user consent.
 */
public class Composer internal constructor(
    private val composerApi: ComposerApi,
    private val generalApi: GeneralApi,
    private val httpHelper: HttpHelper,
    private val prefsStorage: PrefsStorage,
    private val aid: String,
    private val endpoint: Endpoint,
    private val edgeCookiesProvider: EdgeCookiesProvider,
    // Public API.
    @Suppress("unused")
    public val pianoConsents: PianoConsents?,
) {
    // Private properties
    private val templateUrl by lazy {
        endpoint.apiHost.newBuilder().addPathSegments(URL_TEMPLATE).build()
    }
    private var browserIdProvider: () -> String? = { null }
    private var userToken: String? = null
    private var gaClientId: String? = null
    private val experienceInterceptors = mutableListOf<ExperienceInterceptor>(httpHelper)

    init {
        require(aid.isNotEmpty()) {
            "AID can't be empty"
        }
    }

    public var browserId: String? = null
        private set

    /**
     * Gets Composer's user access token for Edge CDN.
     *
     * @return Access token for Edge CDN.
     */
    @Deprecated("Use `edgeCookies.tac`", ReplaceWith("edgeCookies.tac"))
    public val accessToken: String
        get() = prefsStorage.tpAccessCookie

    /**
     * Gets all required cookies for Edge CDN
     *
     * @return cookies for Edge CDN
     */
    public val edgeCookies: EdgeCookies
        get() = edgeCookiesProvider.edgeCookies

    /**
     * Adds an experience interceptor to the Composer.
     *
     * This function adds a custom [ExperienceInterceptor] to the list of interceptors. Interceptors
     * can verify the experience request before it is sent to the server and handle the response.
     *
     * @param interceptor The custom [ExperienceInterceptor] to be added.
     */
    public fun addExperienceInterceptor(interceptor: ExperienceInterceptor): Boolean =
        experienceInterceptors.add(interceptor)

    /**
     * Sets the browser ID provider for the Composer.
     *
     * This function allows setting a custom browser ID provider, which provides the browser ID to be
     * sent with the experience request. The provider should return a string containing the browser
     * ID or null if the browser ID is not available.
     *
     * @param browserIdProvider The custom browser ID provider function.
     * @return The instance of [Composer].
     */
    public fun browserIdProvider(browserIdProvider: () -> String?): Composer = apply {
        this.browserIdProvider = browserIdProvider
    }

    /**
     * Sets the user token to be sent with each experience request.
     *
     * This function sets the user token, which will be included in each experience request to the
     * server. The user token is used for personalization purposes.
     *
     * @param userToken The user token to be set.
     * @return The instance of [Composer].
     */
    @Suppress("unused") // Public API.
    public fun userToken(userToken: String?): Composer = apply {
        this.userToken = userToken
        edgeCookiesProvider.userToken(userToken)
    }

    /**
     * Sets the Google Analytics client ID.
     *
     * This function sets the Google Analytics client ID to be included in the experience request.
     * The client ID is used for tracking user interactions and events related to the Composer.
     *
     * @param gaClientId The Google Analytics client ID to be set.
     * @return The instance of [Composer].
     */
    @Suppress("unused") // Public API.
    public fun gaClientId(gaClientId: String?): Composer = apply { this.gaClientId = gaClientId }

    /**
     * Gets an experience from the server.
     *
     * This function is used to retrieve an experience from the server based on the provided
     * [request]. It also takes a collection of [eventTypeListeners] to handle events received from
     * the server and an [exceptionListener] to handle any exceptions that may occur during the
     * request.
     *
     * @param request The prepared [ExperienceRequest] to be sent to the server.
     * @param eventTypeListeners Collection of event listeners for handling received events separately.
     * @param exceptionListener Listener for handling exceptions that may occur during the request.
     */
    @Suppress("unused") // Public API.
    public fun getExperience(
        request: ExperienceRequest,
        eventTypeListeners: Collection<EventTypeListener<out EventType>>,
        exceptionListener: ExceptionListener,
    ): Unit = getExperience(
        request,
        exceptionListener
    ) { response ->
        processExperienceResponse(
            request,
            response,
            eventTypeListeners,
            null,
            exceptionListener
        )
    }

    /**
     * Gets an experience from server
     *
     * This function is used to retrieve an experience from the server based on the provided
     * [request]. It also takes a [eventsListener] to handle all events received from
     * the server as one list and an [exceptionListener] to handle any exceptions that may occur during the
     * request.
     *
     * @param request The prepared [ExperienceRequest] to be sent to the server.
     * @param eventsListener Listener for handling received list of events.
     * @param exceptionListener Listener for handling exceptions that may occur during the request.
     */
    public fun getExperience(
        request: ExperienceRequest,
        eventsListener: EventsListener,
        exceptionListener: ExceptionListener,
    ): Unit = getExperience(
        request,
        exceptionListener
    ) { response ->
        processExperienceResponse(
            request,
            response,
            emptyList(),
            eventsListener,
            exceptionListener
        )
    }

    /**
     * Tracks a close event by ID.
     *
     * @param trackingId The tracking ID of the close event.
     */
    @Suppress("unused") // Public API.
    public fun trackCloseEvent(trackingId: String) {
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_EVENT,
                EVENT_GROUP_CLOSE,
                pianoConsents?.consents.orEmpty()
            )
        ).enqueue(emptyCallback)
    }

    /**
     * Tracks the display of recommendations by ID.
     *
     * @param trackingId The tracking ID of the recommendations display event.
     */
    @Suppress("unused") // Public API.
    public fun trackRecommendationsDisplay(trackingId: String) {
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_EVENT,
                EVENT_GROUP_INIT,
                pianoConsents?.consents.orEmpty(),
                CX_CUSTOM_PARAMS
            )
        ).enqueue(emptyCallback)
    }

    /**
     * Tracks a click on a recommendation event by ID.
     *
     * @param trackingId The tracking ID of the recommendation click event.
     * @param url The URL of the clicked recommendation.
     */
    @Suppress("unused") // Public API.
    public fun trackRecommendationsClick(trackingId: String, url: String? = null) {
        val params = url?.let { CX_CUSTOM_PARAMS + Pair("href", it) } ?: CX_CUSTOM_PARAMS
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_LINK,
                EVENT_GROUP_CLICK,
                pianoConsents?.consents.orEmpty(),
                params
            )
        ).enqueue(emptyCallback)
    }

    /**
     * Tracks a custom form impression by name.
     *
     * @param customFormName The name of the custom form.
     * @param trackingId The tracking ID of the custom form impression.
     */
    @Suppress("unused") // Public API.
    public fun trackCustomFormImpression(
        customFormName: String,
        trackingId: String,
    ): Unit = generalApi.customFormImpression(
        httpHelper.buildCustomFormTracking(
            aid,
            customFormName,
            trackingId,
            userToken,
            pianoConsents?.consents.orEmpty()
        )
    ).enqueue(emptyCallback)

    /**
     * Tracks a custom form submission by name.
     *
     * @param customFormName The name of the custom form.
     * @param trackingId The tracking ID of the custom form submission.
     */
    @Suppress("unused") // Public API.
    public fun trackCustomFormSubmission(
        customFormName: String,
        trackingId: String,
    ): Unit = generalApi.customFormSubmission(
        httpHelper.buildCustomFormTracking(
            aid,
            customFormName,
            trackingId,
            userToken,
            pianoConsents?.consents.orEmpty()
        )
    ).enqueue(emptyCallback)

    /**
     * Clears stored data, like cookies and visit data.
     */
    @Suppress("unused") // Public API.
    public fun clearStoredData(): Unit = prefsStorage.clear()

    internal fun getExperience(
        request: ExperienceRequest,
        exceptionListener: ExceptionListener,
        processResponse: (ExperienceResponse) -> Unit,
    ) {
        experienceInterceptors.forEach { it.beforeExecute(request) }
        composerApi.getExperience(
            httpHelper.convertExperienceRequest(
                request,
                aid,
                browserIdProvider,
                userToken,
                pianoConsents?.consents.orEmpty(),
                pianoConsents?.productsToPurposesMapping.orEmpty()
            )
        ).enqueue(
            object : Callback<Data<ExperienceResponse>> {
                override fun onResponse(
                    call: Call<Data<ExperienceResponse>>,
                    response: Response<Data<ExperienceResponse>>,
                ) {
                    runCatching {
                        with(response.bodyOrThrow()) {
                            if (errors.isNotEmpty()) {
                                throw ComposerException(errors.joinToString(separator = "\n") { it.message })
                            }

                            processResponse(data)
                        }
                    }.onFailure {
                        exceptionListener.onException(it.toComposerException())
                    }
                }

                override fun onFailure(call: Call<Data<ExperienceResponse>>, t: Throwable) =
                    exceptionListener.onException(t.toComposerException())
            }
        )
    }

    internal fun processExperienceResponse(
        request: ExperienceRequest,
        response: ExperienceResponse,
        eventTypeListeners: Collection<EventTypeListener<out EventType>>,
        eventsListener: EventsListener?,
        exceptionListener: ExceptionListener,
    ) {
        experienceInterceptors.forEach { it.afterExecute(request, response) }
        browserId = response.browserId

        // Don't process any custom logic if there are no listeners
        if (eventTypeListeners.isEmpty() && eventsListener == null) {
            return
        }

        val events = response.result.events.map {
            it.preprocess(request)
        }
        events.firstOrNull {
            it.eventData is ExperienceExecute
        }?.eventExecutionContext?.also {
            edgeCookiesProvider.userSegments(it.userSegments)
        }
        if (eventsListener != null) {
            runCatching {
                eventsListener(events)
            }.onFailure {
                exceptionListener.onException(it.toComposerException())
            }
        } else {
            events.forEach { event ->
                eventTypeListeners.forEach { listener ->
                    if (listener.canProcess(event)) {
                        runCatching {
                            @Suppress("UNCHECKED_CAST")
                            (listener as EventTypeListener<EventType>).onExecuted(event)
                        }.onFailure {
                            exceptionListener.onException(it.toComposerException())
                        }
                    }
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Event<EventType>.preprocess(request: ExperienceRequest): Event<EventType> {
        val data = if (eventData is ShowTemplate) {
            val builder = templateUrl.newBuilder()
            @Suppress("UNCHECKED_CAST")
            httpHelper.buildShowTemplateParameters(
                this as Event<ShowTemplate>,
                request,
                aid,
                userToken,
                gaClientId,
                pianoConsents?.consents.orEmpty()
            ).forEach { (key, value) ->
                builder.addQueryParameter(key, value)
            }
            eventData.copy(url = builder.build().toString())
        } else {
            eventData
        }
        return copy(
            eventData = data
        )
    }

    private fun <T> Response<T>.bodyOrThrow(): T {
        if (!isSuccessful) {
            throw ComposerException(HttpException(this))
        }
        return body() ?: throw ComposerException()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Throwable.toComposerException() =
        if (this is ComposerException) this else ComposerException(this)

    /**
     * Represents the endpoints for the Composer SDK.
     *
     * The `Endpoint` class represents the various predefined endpoints for the Composer SDK. It
     * provides endpoints for different regions, such as sandbox, production, Australia, Asia/Pacific,
     * and Europe.
     *
     * @param composerHost The URL of the Composer host.
     * @param apiHost The URL of the API host.
     */
    public class Endpoint(
        composerHost: String,
        apiHost: String,
    ) {
        internal val composerHost: HttpUrl = composerHost.toHttpUrl()
        internal val apiHost: HttpUrl = apiHost.toHttpUrl()

        public companion object {
            // Predefined endpoints
            private const val COMPOSER_SANDBOX_URL = "https://c2-sandbox.piano.io"
            private const val API_SANDBOX_URL = "https://sandbox.piano.io"
            private const val COMPOSER_DEFAULT_URL = "https://c2.piano.io"
            private const val API_DEFAULT_URL = "https://buy.piano.io"
            private const val COMPOSER_AU_URL = "https://c2-au.piano.io"
            private const val API_AU_URL = "https://buy-au.piano.io"
            private const val COMPOSER_AP_URL = "https://c2-ap.piano.io"
            private const val API_AP_URL = "https://buy-ap.piano.io"
            private const val COMPOSER_EU_URL = "https://c2-eu.piano.io"
            private const val API_EU_URL = "https://buy-eu.piano.io"

            /**
             * Sandbox endpoint.
             */
            @JvmStatic
            @Suppress("unused") // Public API.
            public val SANDBOX: Endpoint = Endpoint(COMPOSER_SANDBOX_URL, API_SANDBOX_URL)

            /**
             * Default production endpoint.
             */
            @JvmStatic
            @Suppress("unused") // Public API.
            public val PRODUCTION: Endpoint = Endpoint(COMPOSER_DEFAULT_URL, API_DEFAULT_URL)

            /**
             * Australia production endpoint.
             */
            @JvmStatic
            @Suppress("unused") // Public API.
            public val PRODUCTION_AUSTRALIA: Endpoint = Endpoint(COMPOSER_AU_URL, API_AU_URL)

            /**
             * Asia/Pacific production endpoint.
             */
            @JvmStatic
            @Suppress("unused") // Public API.
            public val PRODUCTION_ASIA_PACIFIC: Endpoint = Endpoint(COMPOSER_AP_URL, API_AP_URL)

            /**
             * Europe production endpoint.
             */
            @JvmStatic
            @Suppress("unused") // Public API.
            public val PRODUCTION_EUROPE: Endpoint = Endpoint(COMPOSER_EU_URL, API_EU_URL)
        }
    }

    public companion object {
        /**
         * Initializes the Composer SDK.
         *
         * This function should be called to initialize the Composer SDK. It requires a valid [context],
         * which can be an Activity or Application context, and your AID (Application ID) [aid]. The optional
         * [endpoint] parameter allows you to specify a custom API endpoint, but if not provided, the SDK will
         * use the default production endpoint.
         *
         * @param context The Activity or Application context.
         * @param aid Your Application ID (AID).
         * @param endpoint Custom API endpoint. Default is [Endpoint.PRODUCTION].
         * @param pianoConsents [PianoConsents] instance for managing user consent. Default is null.
         */
        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        public fun init(
            context: Context,
            aid: String,
            endpoint: Endpoint = Endpoint.PRODUCTION,
            pianoConsents: PianoConsents? = null,
        ): Unit = DependenciesProvider.init(
            context.applicationContext,
            aid,
            endpoint,
            pianoConsents ?: runCatching { PianoConsents.getInstance() }.getOrNull()
        )

        /**
         * Retrieves the singleton instance of the Composer class.
         *
         * This function returns the singleton instance of the Composer class. It allows you to access the
         * Composer instance after it has been initialized with the `init` function.
         *
         * @return The singleton instance of the Composer class.
         */
        @JvmStatic
        @Suppress("unused") // Public API.
        public fun getInstance(): Composer = DependenciesProvider.getInstance().composer

        /**
         * Constant for the user provider "tinypass_accounts".
         */
        @Suppress("unused") // Public API.
        public const val USER_PROVIDER_TINYPASS_ACCOUNTS: String = "tinypass_accounts"

        /**
         * Constant for the user provider "piano_id".
         */
        @Suppress("unused") // Public API.
        public const val USER_PROVIDER_PIANO_ID: String = "piano_id"

        /**
         * Constant for the user provider "janrain".
         */
        @Suppress("unused") // Public API.
        public const val USER_PROVIDER_JANRAIN: String = "janrain"

        /**
         * Internal constant for the event type "EXTERNAL_EVENT".
         */
        internal const val EVENT_TYPE_EXTERNAL_EVENT = "EXTERNAL_EVENT"

        /**
         * Internal constant for the event type "EXTERNAL_LINK".
         */
        internal const val EVENT_TYPE_EXTERNAL_LINK = "EXTERNAL_LINK"

        /**
         * Internal constant for the event group "close".
         */
        internal const val EVENT_GROUP_CLOSE = "close"

        /**
         * Internal constant for the event group "init".
         */
        internal const val EVENT_GROUP_INIT = "init"

        /**
         * Internal constant for the event group "click".
         */
        internal const val EVENT_GROUP_CLICK = "click"

        /**
         * Internal constant for Cxense custom parameters.
         */
        internal val CX_CUSTOM_PARAMS = mapOf("source" to "CX")

        /**
         * Internal constant for the URL template used for "Show Template" card.
         */
        private const val URL_TEMPLATE = "checkout/template/show"

        /**
         * Internal empty callback used for certain API requests.
         */
        private val emptyCallback = object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {}
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
        }
    }
}
