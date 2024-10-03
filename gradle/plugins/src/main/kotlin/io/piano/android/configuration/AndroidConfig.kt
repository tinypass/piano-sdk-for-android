package io.piano.android.configuration

import org.gradle.api.JavaVersion

internal object AndroidConfig {
    const val androidBuildTools = "35.0.0"
    const val androidMinSdk = 23
    const val androidTargetSdk = 35
    const val androidCompileSdk = 35
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}
