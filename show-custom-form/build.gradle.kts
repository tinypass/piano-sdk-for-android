plugins {
    id("com.android.library")
    id("common-android-config")
    alias(libs.plugins.moshiIR)
}

dependencies {
    api(project(":composer"))
    api(project(":id:id"))
    api(project(":show-helper"))
    implementation(libs.lifecycleCommon)
    implementation(libs.timber)
    implementation(libs.moshi)
}
android {
    namespace = "io.piano.android.showform"
}
