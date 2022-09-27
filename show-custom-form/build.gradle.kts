plugins {
    id("com.android.library")
    id("common-android-config")
    kotlin("kapt")
}

dependencies {
    api(project(":composer"))
    api(project(":id:id"))
    api(project(":show-helper"))
    implementation(libs.lifecycleCommon)
    implementation(libs.timber)
    implementation(libs.moshi)
    kapt(libs.moshiCodegen)
}
android {
    namespace = "io.piano.android.showform"
}
