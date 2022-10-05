plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    api(project(":composer"))
    api(project(":show-helper"))
    implementation(libs.lifecycleCommon)
    implementation(libs.timber)
}
android {
    namespace = "io.piano.android.composer.showtemplate"
}
