package io.github.gdtknight.open_wifi_info.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.gdtknight.open_wifi_info.dto.WifiInfoSummaryDto;
import io.github.gdtknight.open_wifi_info.service.WifiInfoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wifi-info")
public class WifiInfoController {

  private final WifiInfoService wifiInfoService;

  @GetMapping
  public String fetchWifiInfo(ModelMap modelMap) throws Exception {
    List<WifiInfoSummaryDto> summaries = wifiInfoService.fetchAllWifiInfo();

    modelMap.addAttribute("summaries", summaries);

    return "wifi-info-summary";
  }

  @GetMapping("/search")
  public String searchWifiInfo(@RequestParam Long lnt, @RequestParam Long lat, ModelMap modelMap) {

    return "search";
  }

}
