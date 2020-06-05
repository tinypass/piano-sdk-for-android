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
    implementation(Libs.appcompat)
    api(Libs.facebookLogin)
    implementation(project(":id:id"))
}

if ("SNAPSHOT" !in version.toString()) {
    apply {
        from("https://raw.github.com/tinypass/gradle-mvn-push/master/gradle-mvn-push.gradle")
    }
}
