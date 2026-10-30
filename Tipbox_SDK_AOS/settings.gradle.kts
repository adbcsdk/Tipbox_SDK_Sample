pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // TipBox SDK 저장소
        maven {
            url = uri("https://nexus.adbc.io/repository/TipBoxSDK_AOS/")
        }
        // 광고 네트워크(Pangle) 저장소
        maven {
            url = uri("https://artifact.bytedance.com/repository/pangle")
        }
    }
}

rootProject.name = "TipboxSampleAOS"
include(":app")
