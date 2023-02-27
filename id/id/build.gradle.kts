plugins {
    id("com.android.library")
    alias(libs.plugins.moshiIR)
    id("common-android-config")
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
        viewBinding = true
    }
    namespace = "io.piano.android.id"
}

dependencies {
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
