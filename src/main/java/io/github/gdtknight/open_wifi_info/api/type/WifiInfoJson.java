package io.github.gdtknight.open_wifi_info.api.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WifiInfoJson {
  String X_SWIFI_MGR_NO; // 관리번호
  String X_SWIFI_WRDOFC; // 자치구
  String X_SWIFI_MAIN_NM; // 와이파이명
  String X_SWIFI_ADRES1; // 도로명주소
  String X_SWIFI_ADRES2; // 상세주소
  String X_SWIFI_INSTL_FLOOR; // 설치위치(층)
  String X_SWIFI_INSTL_TY; // 설치유형
  String X_SWIFI_INSTL_MBY; // 설치기관
  String X_SWIFI_SVC_SE; // 서비스구분
  String X_SWIFI_CMCWR; // 망종류
  String X_SWIFI_CNSTC_YEAR; // 설치년도
  String X_SWIFI_INOUT_DOOR; // 실내외구분
  String X_SWIFI_REMARS3; // wifi접속환경
  String LAT; // Y좌표
  String LNT; // X좌표
  String WORK_DTTM; // 작업일자
}
