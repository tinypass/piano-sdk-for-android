plugins {
    id("com.android.library")
    id("common-android-config")
}

android {
    namespace = "io.piano.android.common"
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.timber)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.junit)
    testImplementation(libs.okhttpMockServer)
}
