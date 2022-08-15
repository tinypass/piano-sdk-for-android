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
