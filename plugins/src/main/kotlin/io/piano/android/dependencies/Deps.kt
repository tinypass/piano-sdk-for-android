package io.piano.android.dependencies

private object Versions {
    const val kotlin = "1.6.21"

    // AndroidX libraries
    const val compatLibrary = "1.4.1"
    const val androidxActivity = "1.4.0"
    const val annotationsLibrary = "1.3.0"
    const val fragmentLibrary = "1.4.1"
    const val materialLibrary = "1.5.0"
    const val lifecycleLibrary = "2.4.1"
    const val prefsLibrary = "1.2.0"
    const val multidex = "2.0.1"

    // Third party Libraries
    const val facebookLogin = "13.1.0"
    const val googlePlayServices = "20.1.0"
    const val retrofit = "2.6.4"
    const val okhttp = "3.12.13"
    const val moshi = "1.13.0"
    const val timber = "5.0.1"
    const val cxense = "2.2.0"

    // Test Libraries
    const val junit = "4.13.2"
    const val androidxTestCore = "1.4.0"
    const val androidxTestExtJunit = "1.1.3"
    const val mockitoKotlin = "2.2.0"
    const val mockitoCore = "3.12.1"
    const val robolectric = "4.7.3"
}

object Libs {
    const val annotations = "androidx.annotation:annotation:${Versions.annotationsLibrary}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compatLibrary}"
    const val androidxActivity = "androidx.activity:activity:${Versions.androidxActivity}"
    const val fragments = "androidx.fragment:fragment:${Versions.fragmentLibrary}"
    const val material = "com.google.android.material:material:${Versions.materialLibrary}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleLibrary}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googlePlayServices}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val prefsKtx = "androidx.preference:preference-ktx:${Versions.prefsLibrary}"
    const val cxense = "com.cxpublic:cxense-android:${Versions.cxense}"


    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestCore = "androidx.test:core-ktx:${Versions.androidxTestCore}"
    const val androidxTestExtJunit = "androidx.test.ext:junit-ktx:${Versions.androidxTestExtJunit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}
