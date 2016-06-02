package io.piano.android.api.common;

import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.URLEncoder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;

public class ApiInvoker {

  private Map<String, String> defaultHeaderMap = new HashMap<>();

  private String basePath;
  private Network client;

  public static String parameterToString(Object param) {
    if (param == null) {
      return "";
    } else if (param instanceof Date) {
      return String.valueOf(((Date) param).getTime() / 1000);
    } else if (param instanceof Collection) {
      StringBuilder b = new StringBuilder();
      for(Object o : (Collection)param) {
        if(b.length() > 0) {
          b.append(",");
        }
        b.append(String.valueOf(o));
      }
      return b.toString();
    } else {
      return String.valueOf(param);
    }
  }

  /*
    Format to {@code Pair} objects.
  */
  public static List<Pair<String, String>> parameterToPairs(String collectionFormat, String name, Object value){
    List<Pair<String, String>> params = new ArrayList<>();

    // preconditions
    if (name == null || name.isEmpty() || value == null) return params;

    Collection valueCollection = null;
    if (value instanceof Collection) {
      valueCollection = (Collection) value;
    } else {
      params.add(new Pair(name, parameterToString(value)));
      return params;
    }

    if (valueCollection.isEmpty()){
      return params;
    }

    // get the collection format
    collectionFormat = (collectionFormat == null || collectionFormat.isEmpty() ? "csv" : collectionFormat); // default: csv

    // create the params based on the collection format
    if (collectionFormat.equals("multi")) {
      for (Object item : valueCollection) {
        params.add(new Pair(name, parameterToString(item)));
      }

      return params;
    }

    String delimiter = ",";

    if (collectionFormat.equals("csv")) {
      delimiter = ",";
    } else if (collectionFormat.equals("ssv")) {
      delimiter = " ";
    } else if (collectionFormat.equals("tsv")) {
      delimiter = "\t";
    } else if (collectionFormat.equals("pipes")) {
      delimiter = "|";
    }

    StringBuilder sb = new StringBuilder() ;
    for (Object item : valueCollection) {
      sb.append(delimiter);
      sb.append(parameterToString(item));
    }

    params.add(new Pair(name, sb.substring(1)));

    return params;
  }

  public ApiInvoker(String basePath, Network client) {
    this.basePath = basePath;
    this.client = client == null ? getClient() : client;
  }

  public void addDefaultHeader(String key, String value) {
    if (value == null) {
      defaultHeaderMap.remove(key);
    } else {
      defaultHeaderMap.put(key, value);
    }
  }

  public String escapeString(String str) {
    return str;
  }

  public static Object deserialize(String json, String containerType, Class cls) throws ApiException {
    JSONObject jsonObject;
    try {
      jsonObject = new JSONObject(json);
    } catch (JSONException e) {
      throw new ApiException(500, "Unable to deserialize JSON: " + e.getMessage());
    }

    int code = jsonObject.optInt("code", -1);
    if (code != 0) {
      throw new ApiException(code == -1 ? 500 : code, jsonObject.optString("message"));
    }

    Object payload = null;
    Iterator<String> keys = jsonObject.keys();
    while (keys.hasNext()) {
      String key = keys.next();
      payload = jsonObject.optJSONObject(key);
      if (payload != null) {
        break;
      }

      payload = jsonObject.optJSONArray(key);
      if (payload != null) {
        break;
      }
    }

    if (payload == null) {
      throw new ApiException();
    }

    if ((payload instanceof JSONArray) && ("list".equalsIgnoreCase(containerType) || "array".equalsIgnoreCase(containerType))) {
      try {
        Method method = cls.getMethod("fromJson", JSONObject.class);

        List<Object> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) payload;
        int length = jsonArray.length();
        for (int ii = 0; ii < length; ii++) {
          JSONObject objectJson = jsonArray.optJSONObject(ii);
          Object object = method.invoke(null, objectJson);
          list.add(object);
        }
        return list;
      } catch (Exception e) {
        throw new ApiException(500, e.getMessage());
      }
    } else {
      try {
        Method method = cls.getMethod("fromJson", JSONObject.class);
        return method.invoke(null, payload);
      } catch (Exception e) {
        throw new ApiException(500, e.getMessage());
      }
    }
  }

  /*
  public static String serialize(Object obj) throws ApiException {
    try {
      if (obj != null)
        return JsonUtil.serialize(obj);
      else
        return null;
    }
    catch (Exception e) {
      throw new ApiException(500, e.getMessage());
    }
  }
  */

  public String invokeAPI(String path, String method, List<Pair<String, String>> queryParams, Object body, Map<String, String> headerParams, Map<String, String> formParams, String contentType) throws ApiException {
    StringBuilder b = new StringBuilder();
    b.append("?");
    if (queryParams != null){
      for (Pair<String, String> queryParam : queryParams){
        if (!queryParam.first.isEmpty()) {
          b.append(escapeString(queryParam.first));
          b.append("=");
          b.append(escapeString(queryParam.second));
          b.append("&");
        }
      }
    }

    String querystring = b.substring(0, b.length() - 1);
    String url = basePath + path + querystring;

    Map<String, String> headers = new HashMap<>();

    for(String key : headerParams.keySet()) {
      headers.put(key, headerParams.get(key));
    }

    for(String key : defaultHeaderMap.keySet()) {
      if(!headerParams.containsKey(key)) {
        headers.put(key, defaultHeaderMap.get(key));
      }
    }
    headers.put("Accept", "application/json");

    // URL encoded string from form parameters
    String formParamStr = null;

    // for form data
    if ("application/x-www-form-urlencoded".equals(contentType)) {
      StringBuilder formParamBuilder = new StringBuilder();

      // encode the form params
      for (String key : formParams.keySet()) {
        String value = formParams.get(key);
        if (value != null && !"".equals(value.trim())) {
          if (formParamBuilder.length() > 0) {
            formParamBuilder.append("&");
          }
          try {
            formParamBuilder.append(URLEncoder.encode(key, "utf8")).append("=").append(URLEncoder.encode(value, "utf8"));
          }
          catch (Exception e) {
            // move on to next
          }
        }
      }
      formParamStr = formParamBuilder.toString();
    }

    BufferedReader bufferedReader = null;
    Response response = null;
    try {
      Request request = new Request();
      request.setUrl(url);
      request.setMethod(method);
      request.setHeaders(headers);
      if ("GET".equals(method)) {
        response = client.execute(request);
      } else if ("POST".equals(method)) {
        if (formParamStr != null) {
          //post.setHeader("Content-Type", contentType);
          //post.setEntity(new StringEntity(formParamStr, "UTF-8"));
        } else if (body != null) {
          /*
          if (body instanceof HttpEntity) {
            // this is for file uploading
            post.setEntity((HttpEntity) body);
          } else {
            post.setHeader("Content-Type", contentType);
            post.setEntity(new StringEntity(serialize(body), "UTF-8"));
          }
          */
        }
        response = client.execute(request);
      }

      InputStream inputStream = response.getInputStream();
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      char[] buffer = new char[8192];
      StringBuilder resultBuilder = new StringBuilder();
      int read;
      while ((read = bufferedReader.read(buffer)) != -1) {
        resultBuilder.append(buffer, 0, read);
      }
      return resultBuilder.toString();
    }
    catch(IOException e) {
      throw new ApiException(500, e.getMessage());
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (Exception ignored) {}
      }
    }
  }

  private Network getClient() {
    try {
      Class.forName("okhttp3.OkHttpClient");
      return new OkHttp3Network();
    } catch (ClassNotFoundException ignored) {}
    try {
      Class.forName("com.squareup.okhttp.OkHttpClient");
      return new OkHttpNetwork();
    } catch (ClassNotFoundException ignored) {}
    return new HttpUrlConnectionNetwork();
  }
}
