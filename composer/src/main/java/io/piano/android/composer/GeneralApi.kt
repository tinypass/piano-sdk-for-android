package io.piano.android.composer

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

internal interface GeneralApi {
    @POST("/api/v3/conversion/logAutoMicroConversion")
    @FormUrlEncoded
    fun trackExternalEvent(
        @FieldMap fields: Map<String, String>
    ): Call<ResponseBody>

    @GET("/api/v3/customform/log/impression?custom_form_source=show_form")
    fun customFormImpression(
        @QueryMap fields: Map<String, String>
    ): Call<ResponseBody>

    @GET("/api/v3/customform/log/submission?custom_form_source=show_form")
    fun customFormSubmission(
        @QueryMap fields: Map<String, String>
    ): Call<ResponseBody>
}
