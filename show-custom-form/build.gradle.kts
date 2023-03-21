plugins {
    id("com.android.library")
    alias(libs.plugins.moshiIR)
    id("common-android-config")
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
