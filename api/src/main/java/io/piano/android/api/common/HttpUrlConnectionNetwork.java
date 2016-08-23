package io.piano.android.api.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
        connection.setConnectTimeout(10_000);
        connection.setReadTimeout(60_000);
        return connection;
    }

    private void prepareRequest(HttpURLConnection connection, Request request) throws IOException {
        connection.setRequestMethod(request.getMethod());

        Map<String, String> headers = request.getHeaders();
        if ((headers != null) && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.addRequestProperty(header.getKey(), header. getValue());
            }
        }

        RequestBody body = request.getBody();
        if (body != null) {
            Map<String, String> formParams = body.getFormParams();
            if ((formParams != null) && !formParams.isEmpty()) {
                connection.setDoOutput(true);

                ByteArrayOutputStream content = new ByteArrayOutputStream();

                for (Map.Entry<String, String> entry : formParams.entrySet()) {
                    if (content.size() > 0) {
                        content.write('&');
                    }

                    String name = URLEncoder.encode(entry.getKey(), "UTF-8");
                    String value = URLEncoder.encode(entry.getValue(), "UTF-8");
                    content.write(name.getBytes("UTF-8"));
                    content.write('=');
                    content.write(value.getBytes("UTF-8"));
                }

                connection.getOutputStream().write(content.toByteArray());
            }
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
