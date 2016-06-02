package io.piano.android.api.common;

public interface Network {

    Response execute(Request request) throws ApiException;
}
