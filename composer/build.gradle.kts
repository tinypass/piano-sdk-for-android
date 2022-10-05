plugins {
    id("com.android.library")
    id("common-android-config")
    kotlin("kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
    namespace = "io.piano.android.composer"
}

dependencies {
    implementation(libs.okhttpLogging)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.moshi)
    kapt(libs.moshiCodegen)
    implementation(libs.annotations)
    implementation(libs.timber)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.junit)
    testImplementation(libs.okhttpMockServer)
}
