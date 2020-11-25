plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinParcelize)
    kotlin("kapt")
    id(Plugins.dokka)
    id(Plugins.ktlint)
}

group = rootProject.group
version = rootProject.version

android {
    buildToolsVersion = Config.androidBuildTools
    compileSdkVersion(Config.androidCompileSdk)
    defaultConfig {
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
        versionName = version.toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    compileOptions {
        sourceCompatibility = Config.compileSourceVersion
        targetCompatibility = Config.compileTargetVersion
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}

dependencies {
    implementation(Libs.appcompat)
    api(Libs.androidxActivity)
    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverter)
    implementation(Libs.moshi)
    kapt(Libs.moshiCodegen)
    implementation(Libs.annotations)
    implementation(Libs.timber)

    testImplementation(Libs.kotlinJunit)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
    testImplementation(Libs.androidxTestCore)
    testImplementation(Libs.androidxTestExtJunit)
    testImplementation(Libs.robolectric)
}

kotlin {
    explicitApi()
}

ktlint {
    android.set(true)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn(tasks.dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc.get().outputDirectory.get())
}

project.applyBintrayUpload(javadocJar)
