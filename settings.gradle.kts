pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.gradle.develocity") version "3.17.1"
}

develocity {
    buildScan {
        publishing.onlyIf { false }
    }
}

includeBuild("gradle/plugins")

include(
    ":composer",
    ":composer-c1x",
    ":composer-show-template",
    ":id:id",
    ":id:id-oauth-facebook",
    ":id:id-oauth-google",
    ":show-helper",
    ":show-custom-form",
    ":sample"
)
