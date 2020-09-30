package io.piano.android.composer

import android.content.Context
import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.listeners.ExceptionListener
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ShowTemplate
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class Composer internal constructor(
    private val api: Api,
    private val httpHelper: HttpHelper,
    private val aid: String,
    private val customEndpoint: String? = null
) {
    private var userToken: String? = null
    private var gaClientId: String? = null

    /**
     * Sets user token, which will be sent at each experience request
     * @param userToken User token
     * @return Composer instance
     */
    @Suppress("unused") // Public API.
    fun userToken(userToken: String?) = apply { this.userToken = userToken }

    /**
     * Sets Google Analytics client id
     *
     * @param gaClientId Client id
     * @return Composer instance
     */
    @Suppress("unused") // Public API.
    fun gaClientId(gaClientId: String?) = apply { this.gaClientId = gaClientId }

    /**
     * Gets experience from server
     *
     * @param request            Prepared experience request
     * @param eventTypeListeners Collection of event listeners
     * @param exceptionListener  Listener for exceptions
     */
    @Suppress("unused") // Public API.
    fun getExperience(
        request: ExperienceRequest,
        eventTypeListeners: Collection<EventTypeListener<out EventType>>,
        exceptionListener: ExceptionListener
    ) {
        val url = (customEndpoint ?: BASE_URL_EXPERIENCE) + URL_EXPERIENCE_EXECUTE
        api.getExperience(url, httpHelper.convertExperienceRequest(request, aid, userToken))
            .enqueue(
                object : Callback<Data<ExperienceResponse>> {
                    override fun onResponse(
                        call: Call<Data<ExperienceResponse>>,
                        response: Response<Data<ExperienceResponse>>
                    ) {
                        runCatching {
                            with(response.bodyOrThrow()) {
                                if (errors.isEmpty()) {
                                    // Don't process any custom logic if there's no listeners
                                    if (eventTypeListeners.isNotEmpty())
                                        processExperienceResponse(
                                            request,
                                            data,
                                            eventTypeListeners,
                                            exceptionListener
                                        )
                                } else {
                                    exceptionListener.onException(
                                        ComposerException(
                                            errors.joinToString(
                                                separator = "\n"
                                            ) { it.message }
                                        )
                                    )
                                }
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

    /**
     * Tracks external event by id
     *
     * @param trackingId Tracking id
     */
    @Suppress("unused") // Public API.
    fun trackExternalEvent(trackingId: String) {
        val url = (customEndpoint ?: BASE_URL_BUY) + URL_TRACK_EXTERNAL_EVENT
        api.trackExternalEvent(url, httpHelper.buildEventTracking(trackingId))
            .enqueue(
                object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {}

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
                }
            )
    }

    internal fun processExperienceResponse(
        request: ExperienceRequest,
        response: ExperienceResponse,
        eventTypeListeners: Collection<EventTypeListener<out EventType>>,
        exceptionListener: ExceptionListener
    ) {
        httpHelper.processExperienceResponse(response)
        response.result.events.forEach {
            val event = it.preprocess(request)
            eventTypeListeners.forEach { listener ->
                if (listener.canProcess(it)) {
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

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Event<EventType>.preprocess(request: ExperienceRequest): Event<EventType> {
        val data = if (eventData is ShowTemplate) {
            HttpUrl.parse((customEndpoint ?: BASE_URL_BUY) + URL_TEMPLATE)?.newBuilder()?.let {
                @Suppress("UNCHECKED_CAST")
                httpHelper.buildShowTemplateParameters(
                    this as Event<ShowTemplate>,
                    request,
                    aid,
                    userToken,
                    gaClientId
                ).forEach { (key, value) ->
                    it.addQueryParameter(key, value)
                }
                eventData.copy(url = it.build().toString())
            } ?: eventData
        } else eventData
        return copy(
            eventData = data
        )
    }

    private fun <T> Response<T>.bodyOrThrow(): T {
        if (!isSuccessful)
            throw ComposerException(HttpException(this))
        return body() ?: throw ComposerException()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Throwable.toComposerException() =
        if (this is ComposerException) this else ComposerException(this)

    companion object {
        /**
         * Initialize Composer
         * @param context Activity or Application context
         * @param aid Your AID
         * @param endpoint Custom API endpoint, null for use "production"
         */
        @JvmStatic
        @JvmOverloads
        @Suppress("unused") // Public API.
        fun init(context: Context, aid: String, endpoint: String? = null) {
            DependenciesProvider.init(context, aid, endpoint)
        }

        /**
         * Initialize Composer
         * @param context Activity or Application context
         * @param aid Your AID
         * @param sandbox Use sandbox environment if true, production otherwise
         */
        @JvmStatic
        @Suppress("unused") // Public API.
        fun init(context: Context, aid: String, sandbox: Boolean) =
            init(context, aid, if (sandbox) BASE_URL_SANDBOX else null)

        @JvmStatic
        @Suppress("unused") // Public API.
        fun getInstance(): Composer = DependenciesProvider.getInstance().composer

        @Suppress("unused") // Public API.
        const val USER_PROVIDER_TINYPASS_ACCOUNTS = "tinypass_accounts"

        @Suppress("unused") // Public API.
        const val USER_PROVIDER_PIANO_ID = "piano_id"

        @Suppress("unused") // Public API.
        const val USER_PROVIDER_JANRAIN = "janrain"

        internal const val BASE_URL_SANDBOX = "https://sandbox.tinypass.com"
        internal const val BASE_URL_EXPERIENCE = "https://experience.tinypass.com"
        internal const val BASE_URL_BUY = "https://buy.tinypass.com"
        internal const val URL_EXPERIENCE_EXECUTE = "/xbuilder/experience/executeMobile"
        internal const val URL_TRACK_EXTERNAL_EVENT = "/api/v3/conversion/logAutoMicroConversion"
        internal const val URL_TEMPLATE = "/checkout/template/show"
    }
}
