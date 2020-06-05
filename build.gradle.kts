// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(Plugins.versions) version Versions.versionsPlugin
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.androidTools)
        classpath(kotlin(Plugins.kotlin, Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

val GROUP: String by project
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

// We use okhttp 3.12.* and retrofit 2.6.*, because okhttp 3.13+ requires API 21 and retrofit 2.7+ requires API 21
val maxSupportedSquareLib = mapOf(
    "com.squareup.okhttp3" to arrayOf(3, 12),
    "com.squareup.retrofit2" to arrayOf(2, 6)
)

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return !isStable
}

fun isNotSupportedSquareLib(group: String, version: String): Boolean =
    maxSupportedSquareLib.any { (key, value) ->
        group == key && version.split(".")
            .map { it.toInt() }
            .zip(value)
            .any { it.first > it.second }
    }

tasks {
    named<DependencyUpdatesTask>("dependencyUpdates") {
        rejectVersionIf {
            isNotSupportedSquareLib(candidate.group, candidate.version)
                    || isNonStable(candidate.version) && !isNonStable(currentVersion)
        }

        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}
//
//ext {
//    compileSdkVersion = 29
//
//    androidxLegacyVersion = '1.0.0'
//    androidxAppCompatVersion = '1.2.0-alpha03'
//    materialVersion = '1.0.0'
//
//    supportLibraryVersion = '28.0.0'
//    okHttpVersion = '3.12.6'
//    okioVersion = '2.4.2'
//    rxAndroidVersion = '1.2.1'
//    rxJavaVersion = '1.3.8'
//}
