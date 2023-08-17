package io.piano.android.id

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.atLeastOnce
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
import io.piano.android.id.models.PianoUserInfo
import io.piano.android.id.models.PianoUserProfile
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class PianoIdClientTest {
    private val hostResponseCall: Call<HostResponse> = mock()
    private val signOutResponseCall: Call<ResponseBody> = mock()
    private val userProfileResponseCall: Call<PianoUserProfile> = mock()
    private val idTokenResponse: Call<PianoIdToken> = mock()
    private val api: PianoIdApi = mock {
        on { getDeploymentHost(any()) } doReturn hostResponseCall
        on { signOut(any(), any(), any()) } doReturn signOutResponseCall
        on { getUserInfo(any(), any(), any(), anyOrNull()) } doReturn userProfileResponseCall
        on { putUserInfo(any(), any(), any(), any()) } doReturn userProfileResponseCall
        on { exchangeAuthCode(any(), any(), any()) } doReturn idTokenResponse
    }
    private val moshi = Moshi.Builder()
        .build()
    private val url = HttpUrl.Builder().scheme("http").host("localhost").build()
    private val endpoint = PianoId.ENDPOINT_SANDBOX.toHttpUrl()
    private val oauthIntent: Intent = mock {
        on { putExtras(any<Bundle>()) } doReturn mock
    }
    private val oAuthProvider: PianoIdOAuthProvider = mock {
        on { name } doReturn NAME
        on { buildIntent(any(), any()) } doReturn oauthIntent
    }
    private val consentsDataProvider: ConsentsDataProvider = mock()
    private lateinit var pianoIdClient: PianoIdClient

    @BeforeTest
    fun setUp() {
        pianoIdClient = spy(PianoIdClient(api, moshi, AID, consentsDataProvider, endpoint))
    }

    @Test
    fun withAuthCallback() {
        val callback: PianoIdAuthCallback = mock()
        assertEquals(pianoIdClient, pianoIdClient.with(callback))
        assertEquals(callback, pianoIdClient.authCallback)
    }

    @Test
    fun withOAuthProvider() {
        assertEquals(pianoIdClient, pianoIdClient.with(oAuthProvider))
        assertEquals(oAuthProvider, pianoIdClient.oauthProviders[NAME.lowercase(Locale.getDefault())])
    }

    @Test
    fun signIn() {
        assertEquals(pianoIdClient, pianoIdClient.signIn().client)
    }

    private fun getAuthEndpoint(callbackTest: (Callback<HostResponse>) -> Unit) {
        pianoIdClient.loadHostUrl()
        verify(api, atLeastOnce()).getDeploymentHost(AID)
        val callbackCaptor = argumentCaptor<Callback<HostResponse>>()
        verify(hostResponseCall, atLeastOnce()).enqueue(callbackCaptor.capture())
        callbackTest(callbackCaptor.lastValue)
    }

    @Test
    fun getAuthEndpointResponseException() =
        getAuthEndpoint { retrofitCallback ->
            val response = Response.success<HostResponse>(null)
            retrofitCallback.onResponse(hostResponseCall, response)
            assertEquals(endpoint, pianoIdClient.hostUrl)
        }

    @Test
    fun getAuthEndpointContainsErrors() =
        getAuthEndpoint { retrofitCallback ->
            val data: HostResponse = mock {
                on { hasError } doReturn true
            }
            val response = Response.success<HostResponse>(data)
            retrofitCallback.onResponse(hostResponseCall, response)
            verify(data).hasError
            assertEquals(endpoint, pianoIdClient.hostUrl)
        }

    @Test
    fun getAuthEndpointResponseSuccess() =
        getAuthEndpoint { retrofitCallback ->
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
            assertNotEquals(endpoint, pianoIdClient.hostUrl)
        }

    @Test
    fun getAuthEndpointFailure() =
        getAuthEndpoint { retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(hostResponseCall, exc)
            assertEquals(endpoint, pianoIdClient.hostUrl)
        }

    private fun signOut(callbackTest: (PianoIdFuncCallback<Any>, Callback<ResponseBody>) -> Unit) {
        val callback: PianoIdFuncCallback<Any> = mock()
        pianoIdClient.signOut(DUMMY, callback)
        verify(api).signOut(any(), any(), any())
        val callbackCaptor = argumentCaptor<Callback<ResponseBody>>()
        verify(signOutResponseCall).enqueue(callbackCaptor.capture())
        callbackTest(callback, callbackCaptor.lastValue)
    }

    @Test
    fun signOutResponseException() =
        signOut { signOutCallback, retrofitCallback ->
            val response = Response.success<ResponseBody>(null)
            retrofitCallback.onResponse(signOutResponseCall, response)
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    @Test
    fun signOutResponseSuccess() =
        signOut { signOutCallback, retrofitCallback ->
            val response = Response.success<ResponseBody>(DUMMY.toResponseBody(null))
            retrofitCallback.onResponse(signOutResponseCall, response)
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isSuccess }
        }

    @Test
    fun signOutResponseFailure() =
        signOut { signOutCallback, retrofitCallback ->
            val exc = RuntimeException()
            retrofitCallback.onFailure(signOutResponseCall, exc)
            val valueCaptor = argumentCaptor<Result<Any>>()
            verify(signOutCallback).invoke(valueCaptor.capture())
            assertTrue { valueCaptor.lastValue.isFailure }
        }

    private fun getUserInfo(
        response: Response<PianoUserProfile>,
        check: (Result<PianoUserProfile>) -> Unit,
    ) {
        val callback: PianoIdFuncCallback<PianoUserProfile> = mock()
        pianoIdClient.getUserInfo(DUMMY, null, callback)
        verify(api).getUserInfo(any(), any(), any(), anyOrNull())
        val callbackCaptor = argumentCaptor<Callback<PianoUserProfile>>()
        verify(userProfileResponseCall).enqueue(callbackCaptor.capture())
        callbackCaptor.lastValue.onResponse(userProfileResponseCall, response)
        val valueCaptor = argumentCaptor<Result<PianoUserProfile>>()
        verify(callback).invoke(valueCaptor.capture())
        check(valueCaptor.lastValue)
    }

    @Test
    fun getUserInfoSuccess() =
        getUserInfo(Response.success<PianoUserProfile>(mock())) { value ->
            assertTrue { value.isSuccess }
        }

    @Test
    fun getUserInfoFailure() =
        getUserInfo(Response.error(400, mock())) { value ->
            assertTrue { value.isFailure }
        }

    private fun putUserInfoFields(
        response: Response<PianoUserProfile>,
        check: (Result<PianoUserProfile>) -> Unit,
    ) {
        val callback: PianoIdFuncCallback<PianoUserProfile> = mock()
        pianoIdClient.putUserInfo(DUMMY, PianoUserInfo(DUMMY), callback)
        verify(api).putUserInfo(any(), any(), any(), any())
        val callbackCaptor = argumentCaptor<Callback<PianoUserProfile>>()
        verify(userProfileResponseCall).enqueue(callbackCaptor.capture())
        callbackCaptor.lastValue.onResponse(userProfileResponseCall, response)
        val valueCaptor = argumentCaptor<Result<PianoUserProfile>>()
        verify(callback).invoke(valueCaptor.capture())
        check(valueCaptor.lastValue)
    }

    @Test
    fun putUserInfoFieldsSuccess() =
        putUserInfoFields(Response.success<PianoUserProfile>(mock())) { value ->
            assertTrue { value.isSuccess }
        }

    @Test
    fun putUserInfoFailure() =
        putUserInfoFields(Response.error(400, mock())) { value ->
            assertTrue { value.isFailure }
        }

    @Test
    fun saveException() {
        val exc = PianoIdException()
        val code = exc.hashCode()
        assertEquals(code, pianoIdClient.saveException(exc))
        assertEquals(exc, pianoIdClient.getStoredException(code))
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
        const val NAME = "Name"
    }
}
