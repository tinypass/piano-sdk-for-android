plugins {
    `kotlin-dsl`
}

// Required since Gradle 4.10+.
repositories {
    gradlePluginPortal()
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
}