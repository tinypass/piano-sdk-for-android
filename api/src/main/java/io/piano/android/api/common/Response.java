package io.piano.android.api.common;

import java.io.InputStream;

public class Response {

    private InputStream inputStream;

    public Response(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
