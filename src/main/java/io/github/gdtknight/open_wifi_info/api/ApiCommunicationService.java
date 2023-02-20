package io.github.gdtknight.open_wifi_info.api;

public interface ApiCommunicationService {
  ApiCommunication.Response syncRequest(ApiCommunication.Request request) throws Exception;

  ApiCommunication.Response asyncRequest(ApiCommunication.Request request) throws Exception;
}
