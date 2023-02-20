package io.github.gdtknight.open_wifi_info.api;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
public class ApiCommunicationServiceImple implements ApiCommunicationService {

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public ApiCommunication.Response syncRequest(ApiCommunication.Request apiRequest) throws Exception {

    Request request = new Request.Builder()
        .url(String.join("/",
            apiRequest.getBaseUrl(),
            apiRequest.getApiKey(),
            apiRequest.getResponseType(),
            apiRequest.getServiceName(),
            apiRequest.getStartIdx() + "",
            apiRequest.getEndIdx() + ""))
        .build();

    try (Response response = client.newCall(request).execute()) {

      if (!response.isSuccessful())
        throw new IOException("Unexpected code " + response);

      String body = response.body().string();

      return ApiCommunication.Response.builder().body(body).build();

    } catch (IOException e) {
      log.info("{}", e.getMessage());
    }

    return null;
  }

  @Override
  public ApiCommunication.Response asyncRequest(ApiCommunication.Request apiRequest) throws Exception {

    Request request = new Request.Builder()
        .url(String.join("/",
            apiRequest.getBaseUrl(),
            apiRequest.getApiKey(),
            apiRequest.getResponseType(),
            apiRequest.getServiceName(),
            apiRequest.getStartIdx() + "",
            apiRequest.getEndIdx() + ""))
        .build();

    ApiCallback apiCallback = new ApiCallback();

    client.newCall(request).enqueue(apiCallback);

    return apiCallback.getResponse();
  }

}
