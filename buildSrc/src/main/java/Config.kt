import org.gradle.api.JavaVersion

object Config {
    // Android config
    const val androidBuildTools = "30.0.2"
    const val androidMinSdk = 16
    const val androidTargetSdk = 30
    const val androidCompileSdk = 30
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val kotlin = "1.4.20"
    //Plugins
    const val versionsPlugin = "0.36.0"
    const val androidToolsPlugin = "4.0.1"
    const val ktlintPlugin = "9.4.1"
    const val dokkaPlugin = "1.4.10.2"

    // AndroidX libraries
    const val compatLibrary = "1.2.0"
    const val androidxActivity = "1.1.0"
    const val annotationsLibrary = "1.1.0"
    const val fragmentLibrary = "1.2.5"
    const val materialLibrary = "1.2.1"
    const val lifecycleLibrary = "2.2.0"
    const val prefsLibrary = "1.1.1"

    // Third party Libraries
    const val facebookLogin = "7.1.0"
    const val googlePlayServices = "19.0.0"
    const val retrofit = "2.6.4"
    const val okhttp = "3.12.12"
    const val moshi = "1.11.0"
    const val timber = "4.7.1"

    // Test Libraries
    const val junit = "4.13.1"
    const val androidxTestCore = "1.3.0"
    const val androidxTestExtJunit = "1.1.2"
    const val mockitoKotlin = "2.2.0"
    const val mockitoCore = "3.0.0"
    const val robolectric = "4.4"
}

object Plugins {
    const val kotlin = "gradle-plugin"
    const val versions = "com.github.ben-manes.versions"
    const val androidTools = "com.android.tools.build:gradle:${Versions.androidToolsPlugin}"
    const val androidApp = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val bintray = "com.jfrog.bintray"
    const val dokka = "org.jetbrains.dokka"
}

object Libs {
    const val annotations = "androidx.annotation:annotation:${Versions.annotationsLibrary}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compatLibrary}"
    const val androidxActivity = "androidx.activity:activity:${Versions.androidxActivity}"
    const val fragments = "androidx.fragment:fragment:${Versions.fragmentLibrary}"
    const val material = "com.google.android.material:material:${Versions.materialLibrary}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleLibrary}"

    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googlePlayServices}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val prefs = "androidx.preference:preference:${Versions.prefsLibrary}"
    const val prefsKtx = "androidx.preference:preference-ktx:${Versions.prefsLibrary}"


    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestCore = "androidx.test:core-ktx:${Versions.androidxTestCore}"
    const val androidxTestExtJunit = "androidx.test.ext:junit-ktx:${Versions.androidxTestExtJunit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}
