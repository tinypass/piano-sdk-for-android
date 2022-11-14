// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("dependencies-updater")
    id("ktlint-config")
    alias(libs.plugins.moshiIR) apply false
}
