plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.dokka)
    id(Plugins.ktlint)
    id(Plugins.publish)
}

group = rootProject.group
version = rootProject.version

android {
    buildToolsVersion = Config.androidBuildTools
    compileSdkVersion(Config.androidCompileSdk)
    defaultConfig {
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
    }

    compileOptions {
        sourceCompatibility = Config.compileSourceVersion
        targetCompatibility = Config.compileTargetVersion
    }
}

dependencies {
    api(project(":composer"))
    implementation(Libs.okhttp)
    implementation(Libs.appcompat)
    implementation(Libs.fragments)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.annotations)
    implementation(Libs.timber)
}

kotlin {
    explicitApi()
}

ktlint {
    android.set(true)
}
