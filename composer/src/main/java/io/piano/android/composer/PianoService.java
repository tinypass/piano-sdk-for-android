package io.piano.android.composer;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface PianoService {

    @POST("xbuilder/experience/execute")
    @FormUrlEncoded
    Observable<String> execute(
            @Field("aid") String aid
            , @Field("customVariables") String customVariables
    );

    @GET("checkout/template/show")
    Observable<String> show(
            @Query("aid") String aid
            , @Query("templateId") String templateId
            , @QueryMap Map<String, String> params
    );
}
