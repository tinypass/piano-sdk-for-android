import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
    kotlin("kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "SDK_VERSION", """"$version"""")
    }
}

dependencies {
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
    testImplementation(Libs.okhttpMockServer)
}
