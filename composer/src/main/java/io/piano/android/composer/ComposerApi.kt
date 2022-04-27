package io.piano.android.composer

import io.piano.android.composer.model.Data
import io.piano.android.composer.model.ExperienceResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface ComposerApi {
    @POST("/xbuilder/experience/executeMobile")
    @FormUrlEncoded
    fun getExperience(
        @FieldMap fields: Map<String, String>
    ): Call<Data<ExperienceResponse>>
}
