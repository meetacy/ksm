plugins {
    kotlin("multiplatform")
    id("publication-convention")
}

kotlin {
    explicitApi()

    jvmToolchain(17)

    jvm()

    js(IR) {
        browser()
        nodejs()
    }
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.navigation)
    commonMainApi(projects.kotlinxCoroutines)
    commonMainApi(projects.navigation.navigationKotlinxJson)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.ktgbotapi)
}
