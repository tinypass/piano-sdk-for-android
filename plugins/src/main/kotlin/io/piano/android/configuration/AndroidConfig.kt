package io.piano.android.configuration

import org.gradle.api.JavaVersion

internal object AndroidConfig {
    const val androidBuildTools = "33.0.0"
    const val androidMinSdk = 19
    const val androidTargetSdk = 33
    const val androidCompileSdk = 33
    val compileSourceVersion = JavaVersion.VERSION_1_8
    val compileTargetVersion = JavaVersion.VERSION_1_8
}
