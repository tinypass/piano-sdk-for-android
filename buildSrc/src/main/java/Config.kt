import org.gradle.api.JavaVersion

object Config {
    // Android config
    const val androidBuildTools = "29.0.3"
    const val androidMinSdk = 16
    const val androidTargetSdk = 29
    const val androidCompileSdk = 29
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val kotlin = "1.3.72"
    //Plugins
    const val versionsPlugin = "0.28.0"
    const val androidToolsPlugin = "4.0.0"
    // AndroidX libraries
    const val compatLibrary = "1.1.0"
    const val androidxActivity = "1.1.0"
    const val annotationsLibrary = "1.1.0"
    const val fragmentLibrary = "1.2.4"
    const val customTabsLibrary = "1.2.0"
    const val materialLibrary = "1.1.0"

    // Third party Libraries
    const val facebookLogin = "5.15.3"
    const val googlePlayServices = "17.0.0"
    const val retrofit = "2.6.4"
    const val okhttp = "3.12.6"
    const val timber = "4.7.1"
    const val gson = "2.8.6"
    const val rxJava = "2.2.19"
    const val rxAndroid = "2.1.1"

    // Test Libraries
    const val junit = "4.13"
    const val androidxTestCore = "1.2.0"
    const val androidxTestExtJunit = "1.1.1"
    const val mockitoKotlin = "2.2.0"
    const val mockitoCore = "3.0.0"
    const val robolectric = "4.3.1"
}

object Plugins {
    const val kotlin = "gradle-plugin"
    const val versions = "com.github.ben-manes.versions"
    const val androidTools = "com.android.tools.build:gradle:${Versions.androidToolsPlugin}"
    const val androidApp = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val spotbugs = "com.github.spotbugs"
}

object Libs {
    const val annotations = "androidx.annotation:annotation:${Versions.annotationsLibrary}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compatLibrary}"
    const val androidxActivity = "androidx.activity:activity:${Versions.androidxActivity}"
    const val customTabs = "androidx.browser:browser:${Versions.customTabsLibrary}"
    const val fragments = "androidx.fragment:fragment:${Versions.fragmentLibrary}"
    const val material = "com.google.android.material:material:${Versions.materialLibrary}"

    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googlePlayServices}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"


    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestCore = "androidx.test:core-ktx:${Versions.androidxTestCore}"
    const val androidxTestExtJunit = "androidx.test.ext:junit-ktx:${Versions.androidxTestExtJunit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}