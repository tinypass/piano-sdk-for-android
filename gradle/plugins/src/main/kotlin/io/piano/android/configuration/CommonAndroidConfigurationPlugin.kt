package io.piano.android.configuration

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.vanniktech.maven.publish.MavenPublishPlugin
import io.piano.android.ktlint.KtlintConfigPlugin
import kotlinx.validation.BinaryCompatibilityValidatorPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper

class CommonAndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
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
            if (this is LibraryExtension) {
                apply<MavenPublishPlugin>()
                apply<BinaryCompatibilityValidatorPlugin>()
            }
            extensions.configure(KotlinAndroidProjectExtension::class.java) {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                    freeCompilerArgs.set(listOf("-Xjvm-default=all"))
                }
                if (this@apply is LibraryExtension) {
                    explicitApi()
                }
            }

        } ?: logger.warn("Can't configure Android parameters")
    }

    private fun Project.configureKotlin() {
        apply<KotlinAndroidPluginWrapper>()
        apply<KtlintConfigPlugin>()
    }
}
