plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
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
    }

    compileOptions {
        sourceCompatibility = Config.compileSourceVersion
        targetCompatibility = Config.compileTargetVersion
    }
}

dependencies {
    implementation(Libs.appcompat)
    api(Libs.facebookLogin)
    api(project(":id:id"))
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
