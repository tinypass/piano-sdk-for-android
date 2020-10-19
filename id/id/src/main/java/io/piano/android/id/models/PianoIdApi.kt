package io.piano.android.id.models

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

internal interface PianoIdApi {
    @GET
    fun signOut(
        @Url url: String,
        @Query("client_id") aid: String,
        @Query("token") accessToken: String
    ): Call<ResponseBody>

    @POST
    fun exchangeAuthCode(
        @Url url: String,
        @Query("aid") aid: String,
        @Query("passwordless_token") authCode: String
    ): Call<PianoIdToken>

    @FormUrlEncoded
    @POST
    fun refreshToken(
        @Url url: String,
        @FieldMap tokenParams: Map<String, String>
    ): Call<PianoIdToken>

    @FormUrlEncoded
    @POST("api/v3/anon/mobile/sdk/id/deployment/host")
    fun getDeploymentHost(@Field("aid") aid: String): Call<HostResponse>
}
