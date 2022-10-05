plugins {
    id("com.android.library")
    id("common-android-config")
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
    namespace = "io.piano.android.composer.c1x"
}

dependencies {
    api(project(":composer"))
    api(project(":show-helper"))
    api(libs.cxense)
    implementation(libs.timber)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.junit)
}
