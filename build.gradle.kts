// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("dependencies-updater")
    id("ktlint-config")
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.mavenRelease) apply false
    alias(libs.plugins.moshiIR) apply false
}
