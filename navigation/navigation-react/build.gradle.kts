plugins {
    id("js-browser-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.navigation)
    jsMainImplementation(libs.react)
    jsMainImplementation(libs.react.dom)
}
