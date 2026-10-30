# 문제 해결 (Android)

## 의존성을 찾지 못함

- `settings.gradle.kts`에 TipBox 저장소와 Pangle 저장소가 모두 있는지 확인합니다. Pangle 저장소가 없으면 `com.pangle.global` 의존성 오류가 납니다.
- 새 버전이 받아지지 않으면 `./gradlew build --refresh-dependencies`를 실행합니다.

## Kotlin 버전 오류

```
Module was compiled with an incompatible version of Kotlin ...
```

앱의 Kotlin을 2.3.0 이상으로 올립니다. 확인된 조합은 Kotlin 2.3.0, AGP 8.9.1, Gradle 8.11.1, compileSdk 36입니다.

## "requires compileSdk 37" 오류

androidx 라이브러리를 최신으로 올리면 compileSdk 37을 요구할 수 있습니다. 샘플의 [`app/build.gradle.kts`](../app/build.gradle.kts) 버전에 맞추세요.
