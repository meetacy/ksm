plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.plugin)
    commonMainImplementation(libs.kotlinx.coroutines.core)
}
