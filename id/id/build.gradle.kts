plugins {
    id("com.android.library")
    id("common-android-config")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
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
    kapt(libs.moshiCodegen)
    implementation(libs.annotations)
    implementation(libs.timber)

    testImplementation(libs.kotlinJunit)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.junit)
    testImplementation(libs.androidxTestCore)
    testImplementation(libs.androidxTestExtJunit)
    testImplementation(libs.robolectric)
}
