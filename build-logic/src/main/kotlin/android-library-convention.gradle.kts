plugins {
    id("com.android.library")
    kotlin("android")
    id("publication-convention")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    buildFeatures {
        buildConfig = false
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    publications {
        register<MavenPublication>("android") {
            groupId = "app.meetacy.ksm"
            artifactId = project.name
            afterEvaluate {
                version = versionFromProperties()
                from(components["release"])
            }
        }
    }
}

kotlin {
    explicitApi()
    jvmToolchain(8)
}
