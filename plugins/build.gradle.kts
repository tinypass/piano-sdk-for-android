plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "io.piano.android.plugins"
version = "SNAPSHOT"

// Required since Gradle 4.10+.
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.versionUpdater)
    implementation(libs.android)
    implementation(libs.kotlin)
    implementation(libs.ktlint)
    implementation(libs.dokka)
    implementation(libs.mavenRelease)
}

gradlePlugin {
    plugins {
        register("dependencies-updater") {
            id = "dependencies-updater"
            implementationClass = "io.piano.android.dependencies.DependenciesUpdaterPlugin"
        }
        register("common-android-config") {
            id = "common-android-config"
            implementationClass = "io.piano.android.configuration.CommonAndroidConfigurationPlugin"
        }
        register("ktlint-config") {
            id = "ktlint-config"
            implementationClass = "io.piano.android.ktlint.KtlintConfigPlugin"
        }
    }
}
