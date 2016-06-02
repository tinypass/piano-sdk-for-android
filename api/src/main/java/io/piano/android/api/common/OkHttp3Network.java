package io.piano.android.api.common;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

public class OkHttp3Network implements Network {

    private Call.Factory client;

    public OkHttp3Network() {
        this.client = new OkHttpClient.Builder().build();
    }

    public OkHttp3Network(Call.Factory client) {
        this.client = client;
    }

    @Override
    public Response execute(Request request) throws ApiException {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .method(request.getMethod(), createRequestBody(request.getBody()))
                .headers(Headers.of(request.getHeaders()));
        okhttp3.Response response;
        try {
            response = client.newCall(builder.build()).execute();
        } catch (IOException e) {
            throw new ApiException(500, e.getMessage());
        }
        int responseCode = response.code();
        if (responseCode != 200) {
            response.body().close();
            throw new ApiException(responseCode, response.message());
        }
        ResponseBody responseBody = response.body();
        return new Response(responseBody.byteStream());
    }

    private okhttp3.RequestBody createRequestBody(RequestBody body) {
        return null;
    }
}
