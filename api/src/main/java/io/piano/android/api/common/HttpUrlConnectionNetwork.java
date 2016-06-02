package io.piano.android.api.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUrlConnectionNetwork implements Network {

    public HttpUrlConnectionNetwork() {}

    @Override
    public Response execute(Request request) throws ApiException {
        try {
            HttpURLConnection connection = openConnection(request);
            prepareRequest(connection, request);
            return readResponse(connection);
        } catch (IOException e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    private HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        return connection;
    }

    private void prepareRequest(HttpURLConnection connection, Request request) throws IOException {
        connection.setRequestMethod(request.getMethod());

        Map<String, String> headers = request.getHeaders();
        if ((headers != null) && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header. getValue());
            }
        }

        RequestBody body = request.getBody();
        if (body != null) {
            connection.setDoOutput(true);
        }
    }

    private Response readResponse(HttpURLConnection connection) throws ApiException, IOException {
        int status = connection.getResponseCode();
        if (status != 200) {
            throw new ApiException(status, connection.getResponseMessage());
        }
        InputStream inputStream = connection.getInputStream();
        return new Response(inputStream);
    }
}
