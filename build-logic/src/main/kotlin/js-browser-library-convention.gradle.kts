plugins {
    kotlin("multiplatform")
    id("publication-convention")
}

kotlin {
    explicitApi()

    js(IR) {
        browser()
    }
}
