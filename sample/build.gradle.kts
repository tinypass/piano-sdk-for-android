import java.io.FileReader
import java.util.*

plugins {
    id(Plugins.androidApp)
}

val pianoProperties = Properties().apply {
    load(FileReader(file("piano.properties")))
}

val aid: String = pianoProperties["io.piano.aid"]?.toString() ?: throw IllegalArgumentException("You missed 'io.piano.aid' in piano.properties")
val endpoint: String = pianoProperties["io.piano.endpoint"]?.toString() ?: ""

android {
    compileSdkVersion(Config.androidCompileSdk)
    buildToolsVersion(Config.androidBuildTools)

    defaultConfig {
        applicationId = "io.piano.android.sample"
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "PIANO_AID", """"$aid"""")
        buildConfigField("String", "PIANO_ENDPOINT", """"$endpoint"""")

        manifestPlaceholders = mutableMapOf<String, Any>("PIANO_AID" to aid.toLowerCase())
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("$rootDir/debug.keystore")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Config.compileSourceVersion
        targetCompatibility = Config.compileTargetVersion
    }
}

dependencies {
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.googleAuth)
    implementation(Libs.rxAndroid)
    implementation(Libs.rxJava)
    implementation(Libs.timber)
    implementation(Libs.gson)

    implementation(project(":api"))
    implementation(project(":composer"))
    implementation(project(":composer-show-template"))
    implementation(project(":id:id"))
    implementation(project(":id:id-oauth-google"))
    implementation(project(":id:id-oauth-facebook"))
}
