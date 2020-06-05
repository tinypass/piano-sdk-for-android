package io.piano.android.composer;

import java.util.Map;

import io.piano.android.composer.model.Data;
import io.piano.android.composer.model.ExperienceResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

interface Api {
    @POST
    @FormUrlEncoded
    Call<Data<ExperienceResponse>> getExperience(@Url String url, @FieldMap Map<String, Object> fields);

    @POST
    @FormUrlEncoded
    Call<ResponseBody> trackExternalEvent(@Url String url, @FieldMap Map<String, Object> fields);
}
