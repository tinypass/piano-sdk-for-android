plugins {
    id("com.android.library")
    alias(libs.plugins.moshiIR)
    id("common-android-config")
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
    implementation(libs.annotations)
    implementation(libs.timber)
    compileOnly(libs.kotlinCoroutines)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.junit)
    testImplementation(libs.okhttpMockServer)
}
