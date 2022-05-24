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
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("com.android.tools.build:gradle:7.2.0")
    implementation(kotlin("gradle-plugin", "1.6.21"))
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.3.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.21")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.19.0")
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
