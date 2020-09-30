package io.piano.android.composer

import io.piano.android.composer.model.Data
import io.piano.android.composer.model.ExperienceResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

internal interface Api {
    @POST
    @FormUrlEncoded
    fun getExperience(
        @Url url: String,
        @FieldMap fields: Map<String, String>
    ): Call<Data<ExperienceResponse>>

    @POST
    @FormUrlEncoded
    fun trackExternalEvent(
        @Url url: String,
        @FieldMap fields: Map<String, String>
    ): Call<ResponseBody>
}
