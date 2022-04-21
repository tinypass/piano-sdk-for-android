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
    implementation("com.github.ben-manes:gradle-versions-plugin:0.41.0")
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation(kotlin("gradle-plugin", "1.6.21"))
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.20")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
}

gradlePlugin {
    plugins {
        register("dependencies") {
            id = "dependencies"
            implementationClass = "io.piano.android.dependencies.DependenciesPlugin"
        }
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
