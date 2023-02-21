package io.github.gdtknight.open_wifi_info.service;

import org.springframework.stereotype.Service;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import io.github.gdtknight.open_wifi_info.api.ApiCommunication;
import io.github.gdtknight.open_wifi_info.api.type.JsonResponse;
import io.github.gdtknight.open_wifi_info.api.type.TbPublicWifiInfo;

@Service
public class JsonParsingService {
  public static TbPublicWifiInfo parseTbPublicWifiInfo(ApiCommunication.Response apiResponse) throws Exception {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<JsonResponse> jsonAdapter = moshi.adapter(JsonResponse.class);
    JsonResponse jsonResponse = jsonAdapter.fromJson(apiResponse.getBody());

    return jsonResponse.getTbPublicWifiInfo();
  }

}
