# TipBox SDK Android 연동

## 요구 사항

- minSdk 26, compileSdk 36
- Kotlin 2.3.0, AGP 8.9.1 이상
- JDK 17

## 1. 의존성 추가

`settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://nexus.adbc.io/repository/TipBoxSDK_AOS/") }
        maven { url = uri("https://artifact.bytedance.com/repository/pangle") }
    }
}
```

Pangle 저장소도 반드시 추가해야 합니다. SDK에 포함된 광고 라이브러리가 이 저장소에 있습니다.

`app/build.gradle.kts`

```kotlin
dependencies {
    implementation("com.adbc.tipboxsdk:tipbox_sdk:1.0.3")
}
```

권한과 Activity 선언, R8 규칙은 SDK에 포함되어 있어 따로 추가할 필요가 없습니다.

## 2. 초기화

`Application.onCreate()`에서 호출합니다.

```kotlin
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TipBoxSdkAndroid.init(
            context = this,
            themeColor = "#78BAFF",
            channelKey = "발급받은_채널키"
        )
    }
}
```

Java

```java
TipBoxSdkAndroid.init(this, "#78BAFF", "발급받은_채널키");
```

`themeColor`는 오퍼월 테마색이며 생략하면 기본색을 씁니다.

## 3. 오퍼월 열기

```kotlin
// 스토어 포함
TipBoxSdkAndroid.openOfferwall(context)

// 스토어 미포함
TipBoxSdkAndroid.openOfferwallWithoutStore(context, userId)
```

Java에서도 같은 형태로 호출합니다.

스토어 미포함 방식은 서버 포스트백 연동이 먼저 되어 있어야 리워드가 지급됩니다. [포스트백 연동](../README.md#스토어-미포함-방식-포스트백-연동-먼저)을 참고하세요.

## 4. app-ads.txt

[루트 가이드](../README.md#app-adstxt)를 참고해 등록합니다.

## API

| API | 설명 |
|---|---|
| `init(context, themeColor, channelKey)` | 초기화. 다시 호출하면 채널 키와 테마색이 바뀝니다. |
| `openOfferwall(context)` | 오퍼월 열기 (스토어 포함) |
| `openOfferwallWithoutStore(context, userId)` | 오퍼월 열기 (스토어 미포함) |

## 샘플

`MainActivity.kt`의 채널 키를 발급받은 값으로 바꾼 뒤, 이 폴더를 Android Studio로 열어 실행합니다.

## 문제 해결

빌드나 의존성 오류는 [문제 해결](docs/troubleshooting.md)을 참고하세요.
