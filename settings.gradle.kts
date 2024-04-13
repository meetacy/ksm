enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ksm"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://maven.pkg.github.com/meetacy/di")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

includeBuild("build-logic")

include(
    "core",
    "kotlinx-coroutines",
    "navigation",
    "navigation:navigation-compose",
    "navigation:navigation-ktgbotapi",
    "navigation:navigation-mdi",
    "navigation:navigation-kotlinx-json",
    "viewmodel"
)
