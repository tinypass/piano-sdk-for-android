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
    id("com.gradle.enterprise") version "3.16.1"
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
