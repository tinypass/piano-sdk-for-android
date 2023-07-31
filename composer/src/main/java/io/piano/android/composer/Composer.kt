package io.piano.android.composer

import android.content.Context
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.EventsListener
import io.piano.android.composer.listeners.ExceptionListener
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ShowTemplate
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
 * @param endpoint The custom API endpoint. It should be one of the predefined endpoints in
 *                 [Endpoint].
 */
class Composer internal constructor(
    private val composerApi: ComposerApi,
    private val generalApi: GeneralApi,
    private val httpHelper: HttpHelper,
    private val prefsStorage: PrefsStorage,
    private val aid: String,
    private val endpoint: Endpoint,
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

    /**
     * Gets Composer's user access token for Edge CDN.
     *
     * @return Access token for Edge CDN.
     */
    val accessToken: String
        get() = prefsStorage.tpAccessCookie

    /**
     * Adds an experience interceptor to the Composer.
     *
     * This function adds a custom [ExperienceInterceptor] to the list of interceptors. Interceptors
     * can modify the experience request before it is sent to the server or handle the response.
     *
     * @param interceptor The custom [ExperienceInterceptor] to be added.
     */
    fun addExperienceInterceptor(interceptor: ExperienceInterceptor) = experienceInterceptors.add(interceptor)

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
    fun browserIdProvider(browserIdProvider: () -> String?) = apply { this.browserIdProvider = browserIdProvider }

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
    fun userToken(userToken: String?) = apply { this.userToken = userToken }

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
    fun gaClientId(gaClientId: String?) = apply { this.gaClientId = gaClientId }

    /**
     * Gets an experience from the server.
     *
     * This function is used to retrieve an experience from the server based on the provided
     * [request]. It also takes a collection of [eventTypeListeners] to handle events received from
     * the server and an [exceptionListener] to handle any exceptions that may occur during the
     * request.
     *
     * @param request The prepared [ExperienceRequest] to be sent to the server.
     * @param eventTypeListeners Collection of event listeners for handling received events.
     * @param exceptionListener Listener for handling exceptions that may occur during the request.
     */
    @Suppress("unused") // Public API.
    fun getExperience(
        request: ExperienceRequest,
        eventTypeListeners: Collection<EventTypeListener<out EventType>>,
        exceptionListener: ExceptionListener,
    ) = getExperience(
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
     * Gets experiences from server
     *
     * This function is used to retrieve an experience from the server based on the provided
     * [request]. It also takes a [eventsListener] to handle all events received from
     * the server and an [exceptionListener] to handle any exceptions that may occur during the
     * request.
     *
     * @param request            Prepared experience request
     * @param eventsListener     Listener for list of events
     * @param exceptionListener  Listener for exceptions
     */
    fun getExperience(
        request: ExperienceRequest,
        eventsListener: EventsListener,
        exceptionListener: ExceptionListener,
    ) = getExperience(
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
     * Tracks an external event by ID.
     *
     * @param trackingId The tracking ID of the external event.
     */
    @Suppress("unused") // Public API.
    @Deprecated("Renamed due to introducing other external events", ReplaceWith("trackCloseEvent(trackingId)"))
    fun trackExternalEvent(trackingId: String) = trackCloseEvent(trackingId)

    /**
     * Tracks a close event by ID.
     *
     * @param trackingId The tracking ID of the close event.
     */
    @Suppress("unused") // Public API.
    fun trackCloseEvent(trackingId: String) {
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_EVENT,
                EVENT_GROUP_CLOSE
            )
        ).enqueue(emptyCallback)
    }

    /**
     * Tracks the display of recommendations by ID.
     *
     * @param trackingId The tracking ID of the recommendations display event.
     */
    @Suppress("unused") // Public API.
    fun trackRecommendationsDisplay(trackingId: String) {
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_EVENT,
                EVENT_GROUP_INIT,
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
    fun trackRecommendationsClick(trackingId: String, url: String? = null) {
        val params = url?.let { CX_CUSTOM_PARAMS + Pair("href", it) } ?: CX_CUSTOM_PARAMS
        generalApi.trackExternalEvent(
            httpHelper.buildEventTracking(
                trackingId,
                EVENT_TYPE_EXTERNAL_LINK,
                EVENT_GROUP_CLICK,
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
    fun trackCustomFormImpression(customFormName: String, trackingId: String) =
        generalApi.customFormImpression(httpHelper.buildCustomFormTracking(aid, customFormName, trackingId, userToken))
            .enqueue(emptyCallback)

    /**
     * Tracks a custom form submission by name.
     *
     * @param customFormName The name of the custom form.
     * @param trackingId The tracking ID of the custom form submission.
     */
    @Suppress("unused") // Public API.
    fun trackCustomFormSubmission(customFormName: String, trackingId: String) =
        generalApi.customFormSubmission(httpHelper.buildCustomFormTracking(aid, customFormName, trackingId, userToken))
            .enqueue(emptyCallback)

    /**
     * Clears stored data, like cookies and visit data.
     */
    @Suppress("unused") // Public API.
    fun clearStoredData() = prefsStorage.clear()

    internal fun getExperience(
        request: ExperienceRequest,
        exceptionListener: ExceptionListener,
        processResponse: (ExperienceResponse) -> Unit,
    ) {
        experienceInterceptors.forEach { it.beforeExecute(request) }
        composerApi.getExperience(
            httpHelper.convertExperienceRequest(request, aid, browserIdProvider, userToken)
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

        // Don't process any custom logic if there are no listeners
        if (eventTypeListeners.isEmpty() && eventsListener == null) {
            return
        }

        val events = response.result.events.map {
            it.preprocess(request)
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
                gaClientId
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
    class Endpoint(
        composerHost: String,
        apiHost: String,
    ) {
        internal val composerHost: HttpUrl = composerHost.toHttpUrl()
        internal val apiHost: HttpUrl = apiHost.toHttpUrl()

        companion object {
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
            @JvmField
            @Suppress("unused") // Public API.
            val SANDBOX = Endpoint(COMPOSER_SANDBOX_URL, API_SANDBOX_URL)

            /**
             * Default production endpoint.
             */
            @JvmField
            @Suppress("unused") // Public API.
            val PRODUCTION = Endpoint(COMPOSER_DEFAULT_URL, API_DEFAULT_URL)

            /**
             * Australia production endpoint.
             */
            @JvmField
            @Suppress("unused") // Public API.
            val PRODUCTION_AUSTRALIA = Endpoint(COMPOSER_AU_URL, API_AU_URL)

            /**
             * Asia/Pacific production endpoint.
             */
            @JvmField
            @Suppress("unused") // Public API.
            val PRODUCTION_ASIA_PACIFIC = Endpoint(COMPOSER_AP_URL, API_AP_URL)

            /**
             * Europe production endpoint.
             */
            @JvmField
            @Suppress("unused") // Public API.
            val PRODUCTION_EUROPE = Endpoint(COMPOSER_EU_URL, API_EU_URL)
        }
    }

    companion object {
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
         */
        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        fun init(context: Context, aid: String, endpoint: Endpoint = Endpoint.PRODUCTION) =
            DependenciesProvider.init(context, aid, endpoint)

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
        fun getInstance(): Composer = DependenciesProvider.getInstance().composer

        /**
         * Constant for the user provider "tinypass_accounts".
         */
        @Suppress("unused") // Public API.
        const val USER_PROVIDER_TINYPASS_ACCOUNTS = "tinypass_accounts"

        /**
         * Constant for the user provider "piano_id".
         */
        @Suppress("unused") // Public API.
        const val USER_PROVIDER_PIANO_ID = "piano_id"

        /**
         * Constant for the user provider "janrain".
         */
        @Suppress("unused") // Public API.
        const val USER_PROVIDER_JANRAIN = "janrain"

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
         * Internal constant for custom parameters with the key "source" set to "CX".
         */
        internal val CX_CUSTOM_PARAMS = mapOf("source" to "CX")

        /**
         * Internal constant for the URL template used in the checkout process.
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
