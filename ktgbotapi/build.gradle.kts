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
    commonMainApi(projects.core)
    commonMainApi(projects.kotlinxCoroutines)
    commonMainApi(projects.kotlinxSerializationJson)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.ktgbotapi)
}
