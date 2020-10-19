package io.piano.android.id

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.squareup.moshi.Moshi
import io.piano.android.id.models.HostResponse
import io.piano.android.id.models.PianoIdApi
import io.piano.android.id.models.PianoIdToken
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PianoIdClientTest {
    private val hostResponseCall: Call<HostResponse> = mock()
    private val signOutResponseCall: Call<ResponseBody> = mock()
    private val api: PianoIdApi = mock {
        on { getDeploymentHost(any()) } doReturn hostResponseCall
        on { signOut(any(), any(), any()) } doReturn signOutResponseCall
    }
    private val moshi = Moshi.Builder()
        .build()
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
        pianoIdClient = spy(PianoIdClient(api, moshi, AID))
    }

    @Test
    fun withTokenCallback() {
        val callback: PianoIdFuncCallback<PianoIdToken> = mock()
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
        val callback: PianoIdFuncCallback<HttpUrl> = mock()
        pianoIdClient.getAuthEndpoint(callback)
        verify(callback).invoke(Result.success(url))
    }

    private fun getAuthEndpoint(callbackTest: (PianoIdFuncCallback<HttpUrl>, Callback<HostResponse>) -> Unit) {
        val callback: PianoIdFuncCallback<HttpUrl> = mock()
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
            val valueCaptor = argumentCaptor<Result<HttpUrl>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    @Test
    fun getAuthEndpointContainsErrors() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val data: HostResponse = mock {
                on { hasError } doReturn true
                on { error } doReturn DUMMY
            }
            val response = Response.success<HostResponse>(data)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(data).hasError
            verify(data).error
            val valueCaptor = argumentCaptor<Result<HttpUrl>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    @Test
    fun getAuthEndpointResponseSuccess() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val data: HostResponse = spy(
                HostResponse(url.toString(), 0, null, null)
            ) {
                on { hasError } doReturn false
                on { error } doReturn DUMMY
            }
            val response = Response.success<HostResponse>(data)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(data).hasError
            verify(data, never()).error
            val valueCaptor = argumentCaptor<Result<HttpUrl>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isSuccess }
            assertEquals(url, valueCaptor.lastValue.getOrNull())
        }

    @Test
    fun getAuthEndpointFailure() =
        getAuthEndpoint { pianoIdCallback, retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(hostResponseCall, exc)
            val valueCaptor = argumentCaptor<Result<HttpUrl>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    private fun signOut(callbackTest: (PianoIdFuncCallback<Any>, PianoIdFuncCallback<HttpUrl>) -> Unit) {
        doNothing().`when`(pianoIdClient).getAuthEndpoint(any())
        val callback: PianoIdFuncCallback<Any> = mock()
        pianoIdClient.signOut(DUMMY, callback)
        val callbackCaptor = argumentCaptor<PianoIdFuncCallback<HttpUrl>>()
        verify(pianoIdClient).getAuthEndpoint(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    private fun signOutAuthEndpointSuccess(callbackTest: (PianoIdFuncCallback<Any>, Callback<ResponseBody>) -> Unit) =
        signOut { signOutCallback, authUrlCallback ->
            authUrlCallback(Result.success(url))
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
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    @Test
    fun signOutResponseSuccess() =
        signOutAuthEndpointSuccess { signOutCallback, retrofitCallback ->
            val response = Response.success<ResponseBody>(ResponseBody.create(null, DUMMY))
            retrofitCallback.onResponse(signOutResponseCall, response)
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isSuccess }
        }

    @Test
    fun signOutResponseFailure() =
        signOutAuthEndpointSuccess { signOutCallback, retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(signOutResponseCall, exc)
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    @Test
    fun signOutAuthEndpointFailure() =
        signOut { signOutCallback, authUrlCallback ->
            val exc = PianoIdException()
            authUrlCallback(Result.failure(exc))
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
            verify(signOutResponseCall, never()).enqueue(any())
        }

    private fun getSignInUrl(callbackTest: (PianoIdFuncCallback<String>, PianoIdFuncCallback<HttpUrl>) -> Unit) {
        doNothing().`when`(pianoIdClient).getAuthEndpoint(any())
        val callback: PianoIdFuncCallback<String> = mock()
        pianoIdClient.oauthProviders[DUMMY] = oAuthProvider
        pianoIdClient.getSignInUrl(true, DUMMY, callback)
        val callbackCaptor = argumentCaptor<PianoIdFuncCallback<HttpUrl>>()
        verify(pianoIdClient).getAuthEndpoint(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    @Test
    fun getSignInUrlSuccess() =
        getSignInUrl { pianoIdCallback, authUrlCallback ->
            authUrlCallback(Result.success(url))
            val valueCaptor = argumentCaptor<Result<String>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            val result = HttpUrl.parse(valueCaptor.lastValue.getOrNull() ?: "")
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
                        PianoIdClient.PARAM_REDIRECT_URI,
                        PianoIdClient.PARAM_SDK_FLAG,
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
            authUrlCallback(Result.failure(exc))
            val valueCaptor = argumentCaptor<Result<String>>()
            verify(pianoIdCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
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
        assertNull(pianoIdClient.getResult(null))
    }

    @Test
    fun getResultFromIntent() {
        val token: PianoIdToken = mock()
        val intent: Intent = mock {
            on { getIntExtra(any(), any()) } doReturn 0
            on { getParcelableExtra<PianoIdToken>(any()) } doReturn token
        }
        assertEquals(token, pianoIdClient.getResult(intent))
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
        assertEquals(
            exc,
            assertFailsWith {
                pianoIdClient.getResult(intent)
            }
        )
    }

    @Test
    fun processUri() {
        val uri: Uri = mock {
            on { authority } doReturn PianoIdClient.LINK_AUTHORITY
            on { getQueryParameter(any()) } doReturn DUMMY
        }
        val token: PianoIdToken = mock()
        val callback: PianoIdFuncCallback<PianoIdToken> = mock()
        doReturn(token).`when`(pianoIdClient).buildToken(any())
        pianoIdClient.parseToken(uri, callback)
        verify(uri).getQueryParameter(any())
        verify(pianoIdClient).getTokenByAuthCode(any(), eq(callback))
    }

    @Test
    fun processUriException() {
        val uri: Uri = mock {
            on { authority } doReturn PianoIdClient.LINK_AUTHORITY
            on { getQueryParameter(any()) } doReturn null
        }
        val callback: PianoIdFuncCallback<PianoIdToken> = mock()
        pianoIdClient.parseToken(uri, callback)
        verify(uri).getQueryParameter(any())
        verify(pianoIdClient, never()).buildToken(any())
        val valueCaptor = argumentCaptor<Result<PianoIdToken>>()
        verify(callback).invoke(valueCaptor.capture())
        assertTrue { valueCaptor.lastValue.exceptionOrNull()?.cause is IllegalArgumentException }
    }

    companion object {
        const val AID = "aid"
        const val DUMMY = "dummy"
        const val DUMMY2 = "dummy2"
        const val NAME = "Name"
    }
}
