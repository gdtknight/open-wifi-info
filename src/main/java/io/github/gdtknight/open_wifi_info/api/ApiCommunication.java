package io.github.gdtknight.open_wifi_info.api;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ApiCommunication {
  @Builder
  @Getter
  public static class Request {
    private String baseUrl;
    private String apiKey;
    private String responseType;
    private String serviceName;
    @Setter
    private int startIdx;
    @Setter
    private int endIdx;
  }

  @Builder
  @Getter
  @Setter
  public static class Response {
    private Map<String, String> header;
    private String body;
  }
}
