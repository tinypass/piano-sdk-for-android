import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
}

dependencies {
    api(project(":composer"))
    api(project(":show-helper"))
    api(Libs.cxense)
    implementation(Libs.timber)

    testImplementation(Libs.kotlinJunit)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
}
