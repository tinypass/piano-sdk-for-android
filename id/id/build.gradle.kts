plugins {
    id("com.android.library")
    id("common-android-config")
    alias(libs.plugins.ksp)
    alias(libs.plugins.moshiIR)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    namespace = "io.piano.android.id"
}

dependencies {
    api(libs.pianoConsents)
    implementation(libs.appcompat)
    api(libs.androidxActivity)
    implementation(libs.okhttp)
    implementation(libs.okhttpLogging)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.moshi)
    implementation(libs.annotations)
    implementation(libs.timber)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.junit)
    testImplementation(libs.okhttpMockServer)
    testImplementation(libs.androidxTestCore)
    testImplementation(libs.androidxTestExtJunit)
}
