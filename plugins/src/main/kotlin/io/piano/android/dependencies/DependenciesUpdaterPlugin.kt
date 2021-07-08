package io.piano.android.dependencies

import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named
import java.util.Locale

class DependenciesUpdaterPlugin : Plugin<Project> {
    // We use okhttp 3.12.* and retrofit 2.6.*, because okhttp 3.13+ requires API 21 and retrofit 2.7+ requires API 21
    private val maxSupportedSquareLib = mapOf(
        "com.squareup.okhttp3" to arrayOf(3, 12),
        "com.squareup.retrofit2" to arrayOf(2, 6)
    )

    override fun apply(target: Project) = target.run {
        if (this == rootProject) {
            configureDependencyUpdates()
        } else {
            logger.warn("You should apply this plugin only to root project")
        }
    }

    private fun Project.configureDependencyUpdates() {
        apply<VersionsPlugin>()
        tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
            rejectVersionIf {
                isNotSupportedSquareLib(candidate.group, candidate.version) ||
                        isNonStable(candidate.version) && !isNonStable(currentVersion)
            }

            checkForGradleUpdate = true
            outputFormatter = "json"
            outputDir = "build/dependencyUpdates"
            reportfileName = "report"
        }
    }

    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.US).contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return !isStable
    }

    private fun isNotSupportedSquareLib(group: String, version: String): Boolean =
        maxSupportedSquareLib.any { (key, value) ->
            group == key && version.split(".")
                .map { it.toInt() }
                .zip(value)
                .any { it.first > it.second }
        }
}
