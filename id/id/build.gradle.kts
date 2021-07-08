import io.piano.android.dependencies.Libs

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
}

dependencies {
    implementation(Libs.appcompat)
    api(Libs.androidxActivity)
    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverter)
    implementation(Libs.moshi)
    kapt(Libs.moshiCodegen)
    implementation(Libs.annotations)
    implementation(Libs.timber)

    testImplementation(Libs.kotlinJunit)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
    testImplementation(Libs.androidxTestCore)
    testImplementation(Libs.androidxTestExtJunit)
    testImplementation(Libs.robolectric)
}
