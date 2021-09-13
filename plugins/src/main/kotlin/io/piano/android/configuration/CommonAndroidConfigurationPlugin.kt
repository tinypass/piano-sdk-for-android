package io.piano.android.configuration

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.vanniktech.maven.publish.MavenPublishPlugin
import io.piano.android.dependencies.DependenciesPlugin
import io.piano.android.ktlint.KtlintConfigPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper

class CommonAndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<DependenciesPlugin>()
        configureKotlin()
        configureAndroid()
    }

    private fun Project.configureAndroid() {
        val GROUP: String by rootProject
        val VERSION_NAME: String by rootProject
        group = GROUP
        version = VERSION_NAME

        extensions.findByType(BaseExtension::class.java)?.apply {
            buildToolsVersion = AndroidConfig.androidBuildTools
            compileSdkVersion(AndroidConfig.androidCompileSdk)
            defaultConfig {
                minSdk = AndroidConfig.androidMinSdk
                targetSdk = AndroidConfig.androidTargetSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = AndroidConfig.compileSourceVersion
                targetCompatibility = AndroidConfig.compileTargetVersion
            }
            (this as ExtensionAware).extensions.configure(KotlinJvmOptions::class.java) {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjvm-default=all")
            }
            if (this is LibraryExtension) {
                configureLibraryPublishing()
            }
        } ?: logger.warn("Can't configure Android parameters")
    }

    private fun Project.configureLibraryPublishing() {
        apply<DokkaPlugin>()
        apply<MavenPublishPlugin>()
    }

    private fun Project.configureKotlin() {
        apply<KotlinAndroidPluginWrapper>()
        apply<KtlintConfigPlugin>()
        extensions.configure(KotlinAndroidProjectExtension::class.java) {
            explicitApi()
        }
    }
}
