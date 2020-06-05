package io.piano.android.id.models;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PianoIdApi {

    @GET
    Call<ResponseBody> signOut(
            @Url String url,
            @Query("client_id") String aid,
            @Query("token") String accessToken
    );

    @FormUrlEncoded
    @POST("api/v3/anon/mobile/sdk/id/deployment/host")
    Call<HostResponse> getDeploymentHost(@Field("aid") String aid);
}
