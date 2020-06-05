package io.piano.android.id

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import io.piano.android.id.models.*
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.test.*

class PianoIdClientTest {
    private val bundle: Bundle = mock()
    private val hostResponseCall: Call<HostResponse> = mock()
    private val signOutResponseCall: Call<ResponseBody> = mock()
    private val api: PianoIdApi = mock {
        on { getDeploymentHost(any()) } doReturn hostResponseCall
        on { signOut(any(), any(), any()) } doReturn signOutResponseCall
    }
    private val gson: Gson = mock()
    private val url = HttpUrl.Builder().scheme("http").host("localhost").build()
    private val oauthIntent: Intent = mock {
        on { putExtras(any<Bundle>()) } doReturn mock
    }
    private val oAuthProvider: PianoIdOAuthProvider = mock {
        on { name } doReturn NAME
        on { buildIntent(any(), any()) } doReturn oauthIntent
    }
    private lateinit var pianoIdClient: PianoIdClient

    @BeforeTest
    fun setUp() {
        pianoIdClient = spy(PianoIdClient(api, gson, url, AID)) {
            doReturn(bundle).`when`(mock).buildBundle(any())
            doReturn(DUMMY).`when`(mock).join(any(), any())
        }
    }

    @Test
    fun withTokenCallback() {
        val callback: PianoIdCallback<PianoIdToken> = mock()
        assertEquals(pianoIdClient, pianoIdClient.with(callback))
        assertEquals(callback, pianoIdClient.tokenCallback)
    }

    @Test
    fun withOAuthProvider() {
        assertEquals(pianoIdClient, pianoIdClient.with(oAuthProvider))
        assertEquals(oAuthProvider, pianoIdClient.oauthProviders[NAME.toLowerCase()])
    }

    @Test
    fun signIn() {
        assertEquals(pianoIdClient, pianoIdClient.signIn().client)
    }

    @Test
    fun getAuthEndpointNotNull() {
        pianoIdClient.authEndpoint = url
        val callback: PianoIdCallback<HttpUrl> = mock()
        pianoIdClient.getAuthEndpoint(callback)
        verify(callback).onSuccess(pianoIdClient.authEndpoint)
        verify(callback, never()).onFailure(any())
    }

    private fun getAuthEndpoint(callbackTest: (PianoIdCallback<HttpUrl>, Callback<HostResponse>) -> Unit) {
        val callback: PianoIdCallback<HttpUrl> = mock()
        pianoIdClient.getAuthEndpoint(callback)
        verify(api).getDeploymentHost(AID)
        val callbackCaptor = argumentCaptor<Callback<HostResponse>>()
        verify(hostResponseCall).enqueue(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    @Test
    fun getAuthEndpointResponseException() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val response = Response.success<HostResponse>(null)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(pianoIdCallback, never()).onSuccess(any())
            verify(pianoIdCallback).onFailure(any())
        }

    @Test
    fun getAuthEndpointContainsErrors() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val data: HostResponse = mock {
                on { hasError() } doReturn true
                on { error } doReturn DUMMY
            }
            val response = Response.success<HostResponse>(data)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(data).hasError()
            verify(data).error
            verify(pianoIdCallback, never()).onSuccess(any())
            verify(pianoIdCallback).onFailure(any())
        }

    @Test
    fun getAuthEndpointResponseSuccess() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val data: HostResponse = spy(
                HostResponse().apply { host = url.toString() }
            ) {
                on { hasError() } doReturn false
                on { error } doReturn DUMMY
            }
            val response = Response.success<HostResponse>(data)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(data).hasError()
            verify(data, never()).error
            verify(pianoIdCallback).onSuccess(url)
            verify(pianoIdCallback, never()).onFailure(any())
        }

    @Test
    fun getAuthEndpointFailure() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(hostResponseCall, exc)
            verify(pianoIdCallback).onFailure(any())
            verify(pianoIdCallback, never()).onSuccess(any())
        }

    private fun signOut(callbackTest: (PianoIdCallback<Any>, PianoIdCallback<HttpUrl>) -> Unit) {
        doNothing().`when`(pianoIdClient).getAuthEndpoint(any())
        val callback: PianoIdCallback<Any> = mock()
        pianoIdClient.signOut(DUMMY, callback)
        val callbackCaptor = argumentCaptor<PianoIdCallback<HttpUrl>>()
        verify(pianoIdClient).getAuthEndpoint(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    private fun signOutAuthEndpointSuccess(callbackTest: (PianoIdCallback<Any>, Callback<ResponseBody>) -> Unit) =
        signOut { signOutCallback, authUrlCallback ->
            authUrlCallback.onSuccess(url)
            verify(api).signOut(any(), any(), any())
            val callbackCaptor = argumentCaptor<Callback<ResponseBody>>()
            verify(signOutResponseCall).enqueue(callbackCaptor.capture())
            callbackTest(signOutCallback, callbackCaptor.lastValue)
        }

    @Test
    fun signOutResponseException() =
        signOutAuthEndpointSuccess { signOutCallback, retrofitCallback ->
            val response = Response.success<ResponseBody>(null)
            retrofitCallback.onResponse(signOutResponseCall, response)
            verify(signOutCallback, never()).onSuccess(any())
            verify(signOutCallback).onFailure(any())
        }

    @Test
    fun signOutResponseSuccess() =
        signOutAuthEndpointSuccess { signOutCallback, retrofitCallback ->
            val response = Response.success<ResponseBody>(ResponseBody.create(null, DUMMY))
            retrofitCallback.onResponse(signOutResponseCall, response)
            verify(signOutCallback).onSuccess(any())
            verify(signOutCallback, never()).onFailure(any())
        }

    @Test
    fun signOutResponseFailure() =
        signOutAuthEndpointSuccess { signOutCallback, retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(signOutResponseCall, exc)
            verify(signOutCallback).onFailure(any())
            verify(signOutCallback, never()).onSuccess(any())
        }

    @Test
    fun signOutAuthEndpointFailure() =
        signOut { signOutCallback, authUrlCallback ->
            val exc = PianoIdException()
            authUrlCallback.onFailure(exc)
            verify(signOutCallback).onFailure(exc)
            verify(signOutCallback, never()).onSuccess(any())
            verify(signOutResponseCall, never()).enqueue(any())
        }

    private fun getSignInUrl(callbackTest: (PianoIdCallback<String>, PianoIdCallback<HttpUrl>) -> Unit) {
        doNothing().`when`(pianoIdClient).getAuthEndpoint(any())
        val callback: PianoIdCallback<String> = mock()
        pianoIdClient.oauthProviders[DUMMY] = oAuthProvider
        pianoIdClient.getSignInUrl(true, DUMMY, callback)
        val callbackCaptor = argumentCaptor<PianoIdCallback<HttpUrl>>()
        verify(pianoIdClient).getAuthEndpoint(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    @Test
    fun getSignInUrlSuccess() =
        getSignInUrl { pianoIdCallback, authUrlCallback ->
            authUrlCallback.onSuccess(url)
            verify(pianoIdClient).join(any(), any())
            val valueCaptor = argumentCaptor<String>()
            verify(pianoIdCallback).onSuccess(valueCaptor.capture())
            val result = HttpUrl.parse(valueCaptor.lastValue)
            assertNotNull(result)
            with(result) {
                assertEquals(PianoIdClient.VALUE_RESPONSE_TYPE_TOKEN, queryParameter(PianoIdClient.PARAM_RESPONSE_TYPE))
                assertEquals(AID, queryParameter(PianoIdClient.PARAM_CLIENT_ID))
                assertEquals(PianoIdClient.VALUE_FORCE_REDIRECT, queryParameter(PianoIdClient.PARAM_FORCE_REDIRECT))
                assertEquals(true.toString(), queryParameter(PianoIdClient.PARAM_DISABLE_SIGN_UP))
                assertEquals(DUMMY, queryParameter(PianoIdClient.PARAM_SCREEN))
                assertEquals(DUMMY, queryParameter(PianoIdClient.PARAM_OAUTH_PROVIDERS))
                assertEquals(
                    setOf(
                        PianoIdClient.PARAM_RESPONSE_TYPE,
                        PianoIdClient.PARAM_CLIENT_ID,
                        PianoIdClient.PARAM_FORCE_REDIRECT,
                        PianoIdClient.PARAM_DISABLE_SIGN_UP,
                        PianoIdClient.PARAM_SCREEN,
                        PianoIdClient.PARAM_OAUTH_PROVIDERS
                    ),
                    queryParameterNames()
                )
            }
        }

    @Test
    fun getSignInUrlFailure() =
        getSignInUrl { pianoIdCallback, authUrlCallback ->
            val exc = PianoIdException()
            authUrlCallback.onFailure(exc)
            verify(pianoIdCallback).onFailure(exc)
            verify(pianoIdCallback, never()).onSuccess(any())
        }

    @Test
    fun saveException() {
        val exc = PianoIdException()
        val code = exc.hashCode()
        assertEquals(code, pianoIdClient.saveException(exc))
        assertEquals(exc, pianoIdClient.getStoredException(code))
    }

    @Test
    fun getResultFromNullIntent() {
        assertNull(pianoIdClient.getResultFromIntent(null))
    }

    @Test
    fun getResultFromIntent() {
        val token: PianoIdToken = mock()
        val intent: Intent = mock {
            on { getIntExtra(any(), any()) } doReturn 0
            on { getParcelableExtra<PianoIdToken>(any()) } doReturn token
        }
        assertEquals(token, pianoIdClient.getResultFromIntent(intent))
        verify(intent).getIntExtra(any(), any())
        verify(intent).getParcelableExtra<PianoIdToken>(any())
    }

    @Test
    fun getResultFromIntentException() {
        val exc = PianoIdException()
        val code = pianoIdClient.saveException(exc)
        val intent: Intent = mock {
            on { getIntExtra(any(), any()) } doReturn code
        }
        assertEquals(exc, assertFailsWith {
            pianoIdClient.getResultFromIntent(intent)
        })
    }

    @Test
    fun processUriNullOrNotSupported() {
        val uri: Uri = mock {
            on { authority } doReturn DUMMY
        }
        assertNull(pianoIdClient.processUri(null))
        assertNull(pianoIdClient.processUri(uri))
        verify(uri).authority
    }

    @Test
    fun processUri() {
        val uri: Uri = mock {
            on { authority } doReturn PianoIdClient.LINK_AUTHORITY
            on { getQueryParameter(any()) } doReturn DUMMY
        }
        val token: PianoIdToken = mock()
        val callback: PianoIdCallback<PianoIdToken> = mock()
        doReturn(token).`when`(pianoIdClient).buildToken(any(), any())
        assertEquals(token, pianoIdClient.with(callback).processUri(uri))
        verify(uri).authority
        verify(uri, times(2)).getQueryParameter(any())
        verify(pianoIdClient).buildToken(any(), any())
        verify(callback).onSuccess(token)
        verify(callback, never()).onFailure(any())
    }

    @Test
    fun processUriException() {
        val uri: Uri = mock {
            on { authority } doReturn PianoIdClient.LINK_AUTHORITY
            on { getQueryParameter(any()) } doReturn null
        }
        val callback: PianoIdCallback<PianoIdToken> = mock()
        val exc = assertFailsWith<PianoIdException> {
            pianoIdClient.with(callback).processUri(uri)
        }
        assertTrue { exc.cause is IllegalArgumentException }
        verify(uri).authority
        verify(uri, times(2)).getQueryParameter(any())
        verify(pianoIdClient, never()).buildToken(any(), any())
        verify(callback, never()).onSuccess(any())
        verify(callback).onFailure(exc)
    }

    @Test
    fun buildTokenFromTokens() {
        val data = TokenData().apply {
            exp = 123
        }
        whenever(gson.fromJson(any<String>(), eq(TokenData::class.java))).thenReturn(data)
        val token = "$DUMMY.$DUMMY2"
        with(pianoIdClient.buildToken(token, DUMMY2)) {
            assertEquals(token, accessToken)
            assertEquals(data.exp * 1000, expiresIn.time)
            assertEquals(DUMMY2, refreshToken)
        }
    }

    @Test
    fun buildTokenFromPayload() {
        val token: PianoIdToken = mock()
        doReturn(token).`when`(pianoIdClient).buildToken(any(), any())
        val response = TokenResponse().apply {
            accessToken = DUMMY
            refreshToken = DUMMY2
        }
        whenever(gson.fromJson(DUMMY, TokenResponse::class.java)).thenReturn(response)
        pianoIdClient.buildToken(DUMMY)
        verify(gson).fromJson(DUMMY, TokenResponse::class.java)
        verify(pianoIdClient).buildToken(DUMMY, DUMMY2)
    }

    @Test
    fun buildSocialAuthIntent() {
        val response = SocialTokenResponse().apply {
            oauthProvider = NAME
        }
        pianoIdClient.oauthProviders[NAME.toLowerCase()] = oAuthProvider
        whenever(gson.fromJson(DUMMY, SocialTokenResponse::class.java)).thenReturn(response)
        assertEquals(oauthIntent, pianoIdClient.buildSocialAuthIntent(mock(), DUMMY))
        verify(pianoIdClient).buildBundle(response)
        verify(oAuthProvider).buildIntent(any(), eq(bundle))
        verify(oauthIntent).putExtras(bundle)
        assertFailsWith<PianoIdException> {
            response.oauthProvider = DUMMY
            pianoIdClient.buildSocialAuthIntent(mock(), DUMMY)
        }
    }

    @Test
    fun buildResultJsCommand() {
        whenever(gson.toJson(any<SocialTokenData>())).thenReturn(DUMMY)
        assertEquals(
            PianoIdClient.SOCIAL_LOGIN_CALLBACK_TEMPLATE.format(Locale.US, DUMMY),
            pianoIdClient.buildResultJsCommand(DUMMY, DUMMY2)
        )
        verify(gson).toJson(any<SocialTokenData>())
    }

    companion object {
        const val AID = "aid"
        const val DUMMY = "dummy"
        const val DUMMY2 = "dummy2"
        const val NAME = "Name"
    }
}