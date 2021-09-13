pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.enterprise") version "3.0"
}

includeBuild("plugins")

include(
    ":composer",
    ":composer-c1x",
    ":composer-show-template",
    ":id:id",
    ":id:id-oauth-facebook",
    "id:id-oauth-google",
    ":sample"
)
