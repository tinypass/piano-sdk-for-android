package io.piano.android.configuration

import org.gradle.api.JavaVersion

internal object AndroidConfig {
    const val androidBuildTools = "31.0.0"
    const val androidMinSdk = 19
    const val androidTargetSdk = 31
    const val androidCompileSdk = 31
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}
