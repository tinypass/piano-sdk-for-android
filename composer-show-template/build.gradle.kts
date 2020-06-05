plugins {
    id(Plugins.androidLibrary)
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
    implementation(project(":composer"))
    implementation(Libs.okhttp)
    implementation(Libs.appcompat)
    implementation(Libs.fragments)
    implementation(Libs.annotations)
}

if ("SNAPSHOT" !in version.toString()) {
    apply {
        from("https://raw.github.com/tinypass/gradle-mvn-push/master/gradle-mvn-push.gradle")
    }
}
