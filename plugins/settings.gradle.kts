dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
            // We add plugins as dependencies for our configuration plugins. Added here for not exposing them to project
            library("versionUpdater", "com.github.ben-manes", "gradle-versions-plugin").versionRef("versionUpdater")
            library("android", "com.android.tools.build", "gradle").versionRef("android")
            library("kotlin", "org.jetbrains.kotlin", "kotlin-gradle-plugin").versionRef("kotlin")
            library("dokka", "org.jetbrains.dokka", "dokka-gradle-plugin").versionRef("kotlin")
            library("ktlint", "org.jlleitschuh.gradle", "ktlint-gradle").versionRef("ktlint")
            library("mavenRelease", "com.vanniktech", "gradle-maven-publish-plugin").versionRef("mavenRelease")
        }
    }
}
