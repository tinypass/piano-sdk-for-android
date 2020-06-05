package io.piano.android.composer;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

class UserAgentInterceptor implements Interceptor {
    private final String userAgent;

    UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(
                chain.request()
                        .newBuilder()
                        .header("User-Agent", userAgent)
                        .build()
        );
    }
}
