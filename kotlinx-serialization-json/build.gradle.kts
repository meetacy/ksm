plugins {
    id("kmp-library-convention")
    kotlin("plugin.serialization")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.core)
    commonMainImplementation(libs.kotlinx.serialization)
}
