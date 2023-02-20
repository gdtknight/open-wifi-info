package io.github.gdtknight.open_wifi_info.api.type;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TbPublicWifiInfo {
  int list_total_count;

  @ToString.Include
  Result RESULT;

  @ToString.Include
  List<WifiInfoJson> row;
}
