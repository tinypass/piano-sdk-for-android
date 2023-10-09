package io.piano.android.configuration

import org.gradle.api.JavaVersion

internal object AndroidConfig {
    const val androidBuildTools = "34.0.0"
    const val androidMinSdk = 21
    const val androidTargetSdk = 34
    const val androidCompileSdk = 34
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}
