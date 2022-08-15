plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    api(project(":composer"))
    api(libs.appcompat)
    api(libs.fragments)
    implementation(libs.annotations)
    implementation(libs.timber)
}
