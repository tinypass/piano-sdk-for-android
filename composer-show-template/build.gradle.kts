import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    api(project(":composer"))
    api(project(":show-helper"))
    implementation(Libs.lifecycleCommon)
    implementation(Libs.timber)
}
