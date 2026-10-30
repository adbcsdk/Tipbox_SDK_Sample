// 루트 빌드 스크립트 — 플러그인 버전만 선언(apply false), 실제 적용은 app 모듈에서.
plugins {
    // SDK 가 Kotlin 2.3.0 으로 빌드되어, 소비 앱도 Kotlin 2.3.0 이상이어야 함
    // (코틀린 메타데이터는 하위호환만 — 소비자 컴파일러 >= 라이브러리 컴파일러).
    id("com.android.application") version "8.9.1" apply false
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0" apply false
}
