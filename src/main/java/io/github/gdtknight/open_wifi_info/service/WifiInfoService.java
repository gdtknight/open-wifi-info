package io.github.gdtknight.open_wifi_info.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.gdtknight.open_wifi_info.api.ApiCommunication;
import io.github.gdtknight.open_wifi_info.api.ApiCommunicationService;
import io.github.gdtknight.open_wifi_info.api.type.TbPublicWifiInfo;
import io.github.gdtknight.open_wifi_info.dto.WifiInfoDto;
import io.github.gdtknight.open_wifi_info.dto.WifiInfoSummaryDto;
import io.github.gdtknight.open_wifi_info.mapper.WifiInfoMapper;
import io.github.gdtknight.open_wifi_info.repository.WifiInfoRepository;

@Service
public class WifiInfoService {

  @Value("${openapi.url}")
  private String baseUrl;

  @Value("${openapi.key}")
  private String apiKey;

  @Value("${openapi.type}")
  private String responseType;

  @Value("${openapi.service}")
  private String serviceName;

  @Value("${openapi.maxRequest}")
  private int maxRequest;

  private final ApiCommunicationService apiCommunicationService;
  private final WifiInfoRepository wifiInfoRepository;

  WifiInfoService(
      @Autowired ApiCommunicationService apiCommunicationService,
      @Autowired WifiInfoRepository wifiInfoRepository) {

    this.apiCommunicationService = apiCommunicationService;
    this.wifiInfoRepository = wifiInfoRepository;
  }

  public List<WifiInfoSummaryDto> fetchAllWifiInfo() throws Exception {
    int startIdx = 1;
    int endIdx = startIdx + maxRequest;

    ApiCommunication.Request apiRequest = ApiCommunication.Request.builder()
        .baseUrl(baseUrl)
        .apiKey(apiKey)
        .responseType(responseType)
        .serviceName(serviceName)
        .startIdx(startIdx)
        .endIdx(endIdx)
        .build();

    ApiCommunication.Response apiResponse = apiCommunicationService.syncRequest(apiRequest);

    TbPublicWifiInfo tbPublicWifiInfo = JsonParsingService.parseTbPublicWifiInfo(apiResponse);

    List<WifiInfoDto> wifiInfoDtoList = tbPublicWifiInfo.getRow().stream()
        .map(WifiInfoMapper::jsonToDto)
        .collect(Collectors.toList());

    int totalCount = tbPublicWifiInfo.getList_total_count();

    while (endIdx < totalCount) {
      startIdx = endIdx + 1;
      endIdx = startIdx + maxRequest > totalCount ? totalCount : startIdx + maxRequest;

      apiRequest.setStartIdx(startIdx);
      apiRequest.setEndIdx(endIdx);

      apiResponse = apiCommunicationService.syncRequest(apiRequest);
      tbPublicWifiInfo = JsonParsingService.parseTbPublicWifiInfo(apiResponse);

      tbPublicWifiInfo.getRow()
          .stream()
          .map(WifiInfoMapper::jsonToDto)
          .forEach(wifiInfoDtoList::add);
    }

    wifiInfoDtoList.stream()
        .map(WifiInfoMapper::dtoToEntity)
        .forEach(wifiInfoRepository::save);

    return wifiInfoDtoList.stream()
        .map(WifiInfoMapper::dtoToSummary)
        .collect(Collectors.toList());
  }

  // public List<WifiInfoSummaryDto> fetchWifiInfo(LocationInfo location) {

  // return null;
  // }

}
