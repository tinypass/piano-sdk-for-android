package io.piano.android.api.common;

import com.squareup.okhttp.OkHttpClient;

public class OkHttpNetwork implements Network {

    private OkHttpClient client;

    public OkHttpNetwork() {
        this.client = new OkHttpClient();
    }

    public OkHttpNetwork(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    @Override
    public Response execute(Request request) throws ApiException {
        throw new UnsupportedOperationException();
    }
}
