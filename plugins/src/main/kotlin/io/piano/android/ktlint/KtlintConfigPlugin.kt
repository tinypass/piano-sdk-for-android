package io.piano.android.ktlint

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

class KtlintConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<KtlintPlugin>()
        if (this != rootProject) {
            extensions.configure(KtlintExtension::class.java) {
                android.set(true)
            }
        }
    }
}
