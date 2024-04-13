plugins {
    id("android-library-convention")
}

version = libs.versions.ksm.get()

android {
    namespace = "app.meetacy.ksm.androidx"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(projects.mdi)
    api(projects.kotlinxSerializationJson) // todo: remove; needed for example only
    implementation(libs.kotlinx.serialization)
    implementation(libs.mdi)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime.saveable)
}
