plugins {
    id("com.android.library")
    id("common-android-config")
    alias(libs.plugins.ksp)
    alias(libs.plugins.moshiIR)
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
    namespace = "io.piano.android.composer"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.pianoConsents)
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
