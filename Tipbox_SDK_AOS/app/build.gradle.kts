import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.adbc.tipboxsample"
    // 전이 의존성(androidx.core 1.17.x)이 compileSdk 36 을 요구하므로 36 사용.
    // 주의: 라이브러리 버전을 더 올리면 core 1.19 / compose 1.12 가 딸려와 SDK 37/AGP 9.1 을 요구하니,
    //       아래처럼 "안정 버전으로 고정"하고 compileSdk 36 을 유지한다.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.adbc.tipboxsample"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    // ── TipBox SDK ──────────────────────────────────────────────────
    implementation("com.adbc.tipboxsdk:tipbox_sdk:1.0.3")

    // ── 샘플 UI (Compose) — 안정 버전 고정 (SDK 36/AGP 8.9.1 호환) ──────
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
}
