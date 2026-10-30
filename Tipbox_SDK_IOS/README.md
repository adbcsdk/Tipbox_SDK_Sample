# TipBox SDK iOS 연동

## 요구 사항

- iOS 15.0 이상
- Xcode 15 이상
- 시뮬레이터는 Apple Silicon Mac에서만 동작합니다.

## 1. 패키지 추가

Xcode > File > Add Package Dependencies에서 아래 주소를 입력하고, 버전 `1.0.3` 이상의 `TipBoxSDK`를 앱 타깃에 추가합니다.

```
https://github.com/adbcsdk/tipbox-sdk-spm
```

광고 SDK는 패키지와 함께 설치됩니다.

## 2. 프로젝트 설정

### Other Linker Flags

앱 타깃의 Build Settings > Other Linker Flags에 `-ObjC`를 추가합니다. 없으면 앱 실행 시 광고 SDK에서 크래시가 납니다.

```
$(inherited) -ObjC
```

### Info.plist

| 키 | 필수 여부 | 용도 |
|---|---|---|
| `NSMotionUsageDescription` | 필수 | 만보기 걸음 수 측정 |
| `SKAdNetworkItems` | 필수 | 광고 네트워크 식별자 78개 |
| `NSUserTrackingUsageDescription` | 권장 | 추적 동의(ATT) 팝업 문구 |

전체 내용은 [`docs/Info.plist.snippet.xml`](docs/Info.plist.snippet.xml)에서 복사합니다.

ATT 팝업은 SDK가 오퍼월 진입 시 직접 띄우므로 앱에서 따로 요청하지 않아도 됩니다. 앱에서 이미 ATT를 요청했다면 다시 뜨지 않습니다. `NSUserTrackingUsageDescription`이 없으면 팝업 없이 진행합니다.

`SKAdNetworkItems`는 78개를 모두 넣어야 합니다. 빠진 식별자가 있으면 해당 광고 네트워크의 광고가 나가지 않거나 수익이 줄어듭니다. 앱에 이미 `SKAdNetworkItems`가 있으면 없는 항목만 추가하고, SDK를 업데이트할 때는 목록이 바뀌었는지 확인하세요.

## 3. 초기화

앱 시작 시 한 번 호출합니다.

```swift
import TipBoxSDKCore

TipBoxSdkIos.shared.start(channelKey: "발급받은_채널키", themeColor: "#78BAFF")
```

SwiftUI는 `App`의 `init()`, UIKit은 `application(_:didFinishLaunchingWithOptions:)`에서 호출하면 됩니다. `themeColor`에 `nil`을 넘기면 기본색을 씁니다.

## 4. 오퍼월 열기

```swift
// 스토어 포함
TipBoxSdkIos.shared.showOfferwall()

// 스토어 미포함
TipBoxSdkIos.shared.showOfferwallWithoutStore(userId: userId)
```

SDK가 현재 화면 위에 오퍼월을 전체 화면으로 띄우고, 사용자가 종료하면 오퍼월만 닫습니다. UIKit과 SwiftUI 모두 버튼 액션 등 메인 스레드에서 호출하면 됩니다.

스토어 미포함 방식은 서버 포스트백 연동이 먼저 되어 있어야 리워드가 지급됩니다. [포스트백 연동](../README.md#스토어-미포함-방식-포스트백-연동-먼저)을 참고하세요.

## 5. app-ads.txt

[루트 가이드](../README.md#app-adstxt)를 참고해 등록합니다.

## API

| API | 설명 |
|---|---|
| `start(channelKey:themeColor:)` | 초기화 |
| `showOfferwall()` | 오퍼월 열기 (스토어 포함) |
| `showOfferwallWithoutStore(userId:)` | 오퍼월 열기 (스토어 미포함) |

## 샘플

`Sample/ContentView.swift`의 채널 키를 발급받은 값으로 바꾼 뒤, `TipboxSample.xcodeproj`를 열어 실행합니다. 실기기에서는 Signing & Capabilities에서 Team을 지정해야 합니다.
