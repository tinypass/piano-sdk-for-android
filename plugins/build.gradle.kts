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
    implementation("com.github.ben-manes:gradle-versions-plugin:0.39.0")
    implementation("com.android.tools.build:gradle:4.2.2")
    implementation(kotlin("gradle-plugin", "1.4.32"))
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.1.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.32")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.15.1")
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
