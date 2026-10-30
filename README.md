# TipBox SDK

TipBox 오퍼월 연동 가이드와 샘플 앱입니다.

| 플랫폼 | 가이드 | 샘플 |
|---|---|---|
| Android | [Tipbox_SDK_AOS](Tipbox_SDK_AOS/README.md) | `Tipbox_SDK_AOS/` |
| iOS | [Tipbox_SDK_IOS](Tipbox_SDK_IOS/README.md) | `Tipbox_SDK_IOS/TipboxSample.xcodeproj` |

## 연동 전 준비

채널 키(channelKey)는 TipBox 담당자에게 발급받습니다.

오퍼월은 두 가지 방식 중 하나로 엽니다.

| 방식 | API (Android / iOS) | 리워드 지급 |
|---|---|---|
| 스토어 포함 | `openOfferwall` / `showOfferwall` | 포인트가 TipBox 안에 쌓이고, 사용자가 스토어에서 사용 |
| 스토어 미포함 | `openOfferwallWithoutStore` / `showOfferwallWithoutStore` | TipBox 서버가 매체 서버로 S2S 포스트백 전송 |

### 스토어 미포함 방식: 포스트백 연동 먼저

스토어 미포함 방식은 SDK를 붙이기 전에 서버 포스트백 연동부터 끝내야 합니다. 포스트백이 연동되지 않은 상태로 출시하면 사용자가 미션을 완료해도 리워드가 지급되지 않습니다.

1. TipBox 담당자에게 포스트백 규격을 받습니다.
2. 매체 서버에 포스트백 수신 URL을 만들고 담당자에게 전달합니다.
3. 담당자와 테스트 포스트백 수신까지 확인한 뒤 앱 연동을 진행합니다.

SDK에 넘기는 `userId`는 포스트백에 그대로 담겨 오므로, 매체 서버가 사용자를 찾을 수 있는 값을 사용하세요.

## app-ads.txt

[`tipbox-app-ads.txt`](tipbox-app-ads.txt)의 내용을 매체 개발자 사이트의 `app-ads.txt` 끝에 추가합니다. 기존 내용은 그대로 둡니다.

- Google Play: Play Console > 스토어 등록정보 > 웹사이트의 도메인
- App Store: App Store Connect > 앱 정보 > 마케팅 URL(없으면 지원 URL)의 도메인

`https://도메인/app-ads.txt`로 열리면 됩니다. 반영까지 최대 24시간 걸립니다.
