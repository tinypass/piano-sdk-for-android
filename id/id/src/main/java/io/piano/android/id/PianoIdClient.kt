package io.piano.android.id

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import com.squareup.moshi.Moshi
import io.piano.android.id.models.HostResponse
import io.piano.android.id.models.PianoIdApi
import io.piano.android.id.models.PianoIdAuthFailureResult
import io.piano.android.id.models.PianoIdAuthSuccessResult
import io.piano.android.id.models.PianoIdToken
import io.piano.android.id.models.SocialTokenData
import io.piano.android.id.models.SocialTokenResponse
import io.piano.android.id.models.PianoUserProfile
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.util.Locale

/**
 * Piano ID authorization
 */
class PianoIdClient internal constructor(
    private val api: PianoIdApi,
    private val moshi: Moshi,
    internal val aid: String
) {
    private val pianoIdTokenAdapter by lazy {
        moshi.adapter(PianoIdToken::class.java)
    }
    private val socialTokenResponseAdapter by lazy {
        moshi.adapter(SocialTokenResponse::class.java)
    }
    private val socialTokenDataAdapter by lazy {
        moshi.adapter(SocialTokenData::class.java)
    }
    internal var hostUrl: HttpUrl? = null
    private val exceptions = SparseArray<PianoIdException>()
    internal val oauthProviders: MutableMap<String, PianoIdOAuthProvider> = mutableMapOf()

    var authCallback: PianoIdAuthCallback? = null
        private set
    var javascriptInterface: PianoIdJs? = null

    init {
        getHostUrl { Timber.d("Getting host success = ${it.isSuccess}") }
    }

    /**
     * Sets callback for {@link PianoIdAuthSuccessResult} data
     *
     * @param callback {@link PianoIdCallback} for listening changes
     * @return {@link PianoIdClient} instance
     */
    @Suppress("unused") // Public API.
    fun with(callback: PianoIdCallback<PianoIdAuthSuccessResult>?) = apply {
        authCallback = callback?.run {
            {
                when (it) {
                    is PianoIdAuthSuccessResult -> onSuccess(it)
                    is PianoIdAuthFailureResult -> onFailure(it.exception)
                }
            }
        }
    }

    /**
     * Sets callback for {@link PianoIdToken} changes
     *
     * @param callback Callback with {@link kotlin.Result} for listening changes
     * @return {@link PianoIdClient} instance
     */
    @Suppress("unused") // Public API.
    fun with(callback: PianoIdAuthCallback?) = apply { authCallback = callback }

    /**
     * Sets javascript interface for processing custom events
     *
     * @param jsInterface {@link PianoIdJs} instance for processing events
     * @return {@link PianoIdClient} instance
     */
    fun with(jsInterface: PianoIdJs?) = apply { javascriptInterface = jsInterface }

    /**
     * Adds OAuth provider
     *
     * @param provider {@link PianoIdOAuthProvider} instance
     * @return {@link PianoIdClient} instance
     */
    @Suppress("unused") // Public API.
    fun with(provider: PianoIdOAuthProvider) = apply {
        oauthProviders[provider.name.lowercase(Locale.US)] = provider
    }

    @Suppress("unused") // Public API.
    fun signIn(): SignInContext = SignInContext(this)

    internal fun getHostUrl(callback: PianoIdFuncCallback<HttpUrl>) {
        hostUrl?.let { callback(Result.success(it)) } ?: run {
            api.getDeploymentHost(aid).enqueue(
                object : Callback<HostResponse> {
                    override fun onResponse(call: Call<HostResponse>, response: Response<HostResponse>) {
                        runCatching {
                            with(response.bodyOrThrow()) {
                                if (!hasError) {
                                    hostUrl = HttpUrl.get(host).also {
                                        callback(Result.success(it))
                                    }
                                } else callback(Result.failure(PianoIdException(error)))
                            }
                        }.onFailure {
                            callback(Result.failure(it.toPianoIdException()))
                        }
                    }

                    override fun onFailure(call: Call<HostResponse>, t: Throwable) =
                        callback(Result.failure(t.toPianoIdException()))
                }
            )
        }
    }

    internal fun signOut(accessToken: String, callback: PianoIdFuncCallback<Any>) = getHostUrl { r ->
        r.getOrNull()?.resolve(SIGN_OUT_PATH)?.let {
            api.signOut(it.toString(), aid, accessToken)
                .enqueue(callback.asRetrofitCallback())
        } ?: callback(Result.failure(r.exceptionOrNull() ?: PianoIdException("Can't resolve sign out url")))
    }

    internal fun getTokenByAuthCode(authCode: String, callback: PianoIdFuncCallback<PianoIdToken>) =
        getHostUrl { r ->
            r.getOrNull()?.let {
                api.exchangeAuthCode(
                    it.newBuilder().encodedPath(EXCHANGE_AUTH_CODE_PATH).build().toString(),
                    aid,
                    authCode
                ).enqueue(callback.asRetrofitCallback())
            } ?: callback(Result.failure(r.exceptionOrNull()!!))
        }

    internal fun refreshToken(refreshToken: String, callback: PianoIdFuncCallback<PianoIdToken>) =
        getHostUrl { r ->
            r.getOrNull()?.let {
                api.refreshToken(
                    it.newBuilder().encodedPath(REFRESH_TOKEN_PATH).build().toString(),
                    mapOf(
                        PARAM_CLIENT_ID to aid,
                        PARAM_GRANT_TYPE to VALUE_GRANT_TYPE,
                        PARAM_REFRESH_TOKEN to refreshToken
                    )
                )
                    .enqueue(callback.asRetrofitCallback())
            } ?: callback(Result.failure(r.exceptionOrNull()!!))
        }

    internal fun getSignInUrl(disableSignUp: Boolean, widget: String?, callback: PianoIdFuncCallback<String>) =
        getHostUrl { r ->
            callback(
                r.mapCatching { url ->
                    url.newBuilder()
                        .encodedPath(AUTH_PATH)
                        .addQueryParameter(PARAM_RESPONSE_TYPE, VALUE_RESPONSE_TYPE_TOKEN)
                        .addQueryParameter(PARAM_CLIENT_ID, aid)
                        .addQueryParameter(PARAM_FORCE_REDIRECT, VALUE_FORCE_REDIRECT)
                        .addQueryParameter(PARAM_DISABLE_SIGN_UP, disableSignUp.toString())
                        .addQueryParameter(PARAM_REDIRECT_URI, "$LINK_SCHEME_PREFIX$aid://$LINK_AUTHORITY")
                        .addQueryParameter(PARAM_SDK_FLAG, VALUE_SDK_FLAG)
                        .apply {
                            if (!widget.isNullOrEmpty())
                                addQueryParameter(PARAM_SCREEN, widget)
                            if (oauthProviders.isNotEmpty())
                                addQueryParameter(
                                    PARAM_OAUTH_PROVIDERS,
                                    oauthProviders.keys.joinToString(separator = ",")
                                )
                        }
                        .build()
                        .toString()
                }
            )
        }

    internal fun getUserInfo(accessToken: String, formName: String?, callback: PianoIdFuncCallback<PianoUserProfile>) {
        getHostUrl { r ->
            r.getOrNull()?.let {
                api.getUserInfo(
                    it.newBuilder().encodedPath(USERINFO_PATH).build().toString(),
                    aid,
                    accessToken,
                    formName
                ).enqueue(callback.asRetrofitCallback())
            } ?: callback(Result.failure(r.exceptionOrNull()!!))
        }
    }

    internal fun getFormUrl(formName: String?, hideCompletedFields: Boolean, trackingId: String) =
        hostUrl?.let { url ->
            url.newBuilder()
                .encodedPath(FORM_PATH)
                .addQueryParameter(PARAM_CLIENT_ID, aid)
                .addQueryParameter(PARAM_FORM_NAME, formName ?: "")
                .addQueryParameter(PARAM_HIDE_COMPLETE, hideCompletedFields.toString())
                .addQueryParameter(PARAM_TRACKING_ID, trackingId)
                .addQueryParameter(PARAM_SDK_FLAG, VALUE_SDK_FLAG)
                .build()
                .toString()
        } ?: "about:blank"

    internal fun saveException(exc: PianoIdException): Int =
        exc.hashCode().also {
            exceptions.append(it, exc)
        }

    internal fun getStoredException(code: Int): PianoIdException? = exceptions.get(code)

    internal fun parseToken(uri: Uri, callback: PianoIdFuncCallback<PianoIdToken>) {
        uri.runCatching {
            requireNotNull(getQueryParameter(PARAM_AUTH_CODE)) {
                "code must be filled"
            }
        }.onFailure {
            callback(Result.failure(it.toPianoIdException()))
        }.onSuccess {
            getTokenByAuthCode(it, callback)
        }
    }

    internal fun buildToken(jsPayload: String): PianoIdToken =
        pianoIdTokenAdapter.fromJson(jsPayload) ?: throw PianoIdException("Invalid payload '$jsPayload'")

    internal fun buildSocialAuthIntent(context: Context, jsPayload: String): Intent =
        socialTokenResponseAdapter.fromJson(jsPayload)?.let { r ->
            val bundle = r.toBundle()
            oauthProviders[r.oauthProvider.lowercase(Locale.US)]?.buildIntent(context, bundle)?.putExtras(bundle)
                ?: throw PianoIdException("OAuth provider '${r.oauthProvider}' is not registered")
        } ?: throw PianoIdException("Invalid payload '$jsPayload'")

    internal fun buildResultJsCommand(provider: String, token: String): String {
        val socialTokenData = socialTokenDataAdapter.toJson(
            SocialTokenData(provider.uppercase(Locale.US), token, aid)
        )
        return "(function(){window.PianoIDMobileSDK.socialLoginCallback('$socialTokenData')})()"
    }

    // mock in tests
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun SocialTokenResponse.toBundle() = Bundle().apply {
        putString(PianoId.KEY_CLIENT_ID, clientId)
    }

    private inline fun <reified T> PianoIdFuncCallback<T>.asRetrofitCallback(): Callback<T> = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            invoke(
                runCatching {
                    response.bodyOrThrow()
                }
            )
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            invoke(Result.failure(t.toPianoIdException()))
        }
    }

    class SignInContext internal constructor(
        internal val client: PianoIdClient
    ) {
        internal var disableSignUp: Boolean = false
        internal var widget: String? = null

        /**
         * Turns off the registration screen
         *
         * @return {@link SignInContext} instance
         */
        @Suppress("unused") // Public API.
        fun disableSignUp() = apply { disableSignUp = true }

        /**
         * Sets the screen when opening Piano ID. Use {@link PianoId#WIDGET_LOGIN} to open the login screen
         * or {@link PianoId#WIDGET_REGISTER} to open the registration screen.
         *
         * @param widget {@link PianoId#WIDGET_LOGIN}, {@link PianoId#WIDGET_REGISTER} or null
         * @return {@link SignInContext} instance
         */
        @Suppress("unused") // Public API.
        fun widget(widget: String?) = apply { this.widget = widget }
    }

    companion object {
        private const val AUTH_PATH = "/id/api/v1/identity/vxauth/authorize"
        private const val SIGN_OUT_PATH = "/id/api/v1/identity/logout?response_type=code"
        private const val EXCHANGE_AUTH_CODE_PATH = "/id/api/v1/identity/passwordless/authorization/code"
        private const val REFRESH_TOKEN_PATH = "/id/api/v1/identity/vxauth/token"
        private const val USERINFO_PATH = "/id/api/v1/identity/userinfo"
        private const val FORM_PATH = "/id/form"

        internal const val LINK_SCHEME_PREFIX = "piano.id.oauth."
        internal const val LINK_AUTHORITY = "success"

        internal const val PARAM_AUTH_CODE = "code"

        internal const val PARAM_RESPONSE_TYPE = "response_type"
        internal const val PARAM_CLIENT_ID = "client_id"
        internal const val PARAM_FORCE_REDIRECT = "force_redirect"
        internal const val PARAM_DISABLE_SIGN_UP = "disable_sign_up"
        internal const val PARAM_REDIRECT_URI = "redirect_uri"
        internal const val PARAM_SDK_FLAG = "is_sdk"
        internal const val PARAM_SCREEN = "screen"
        internal const val PARAM_OAUTH_PROVIDERS = "oauth_providers"
        internal const val PARAM_GRANT_TYPE = "grant_type"
        internal const val PARAM_REFRESH_TOKEN = "refresh_token"
        internal const val PARAM_FORM_NAME = "form_name"
        internal const val PARAM_HIDE_COMPLETE = "hide_if_complete"
        internal const val PARAM_TRACKING_ID = "trackingId"

        internal const val VALUE_RESPONSE_TYPE_TOKEN = "token"
        internal const val VALUE_FORCE_REDIRECT = "1"
        internal const val VALUE_SDK_FLAG = "true"
        internal const val VALUE_GRANT_TYPE = "refresh_token"

        private fun <T> Response<T>.bodyOrThrow(): T {
            if (!isSuccessful)
                throw PianoIdException(HttpException(this))
            return body() ?: throw PianoIdException()
        }

        internal fun Throwable.toPianoIdException(): PianoIdException =
            if (this is PianoIdException) this else PianoIdException(this)
    }
}
