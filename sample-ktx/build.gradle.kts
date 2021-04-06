import java.io.FileReader
import java.util.Properties

plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.ktlint)
}

val pianoProperties = Properties().apply {
    load(FileReader(file("piano.properties")))
}

val aid: String = pianoProperties["io.piano.aid"]?.toString()
    ?: throw IllegalArgumentException("You missed 'io.piano.aid' in piano.properties")
val endpoint: String = pianoProperties["io.piano.endpoint"]?.toString() ?: ""
val fbAppId: String = pianoProperties["facebookAppId"]?.toString() ?: "1111111111111111"

android {
    compileSdkVersion(Config.androidCompileSdk)
    buildToolsVersion = Config.androidBuildTools

    defaultConfig {
        applicationId = "io.piano.sample"
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "PIANO_AID", """"$aid"""")
        buildConfigField("String", "PIANO_ENDPOINT", """"$endpoint"""")
        multiDexEnabled = true

        manifestPlaceholders += mapOf<String, Any>(
            "PIANO_AID" to aid.toLowerCase(),
            "FB_APP_ID" to "fb$fbAppId",
            "FB_APP_SCHEME" to "fb$fbAppId"
        )
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
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}

ktlint {
    android.set(true)
}

dependencies {
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.googleAuth)
    implementation(Libs.timber)
    implementation(Libs.moshi)
    implementation(Libs.prefsKtx)

    implementation(project(":composer"))
    implementation(project(":composer-show-template"))
    implementation(project(":id:id"))
    implementation(project(":id:id-oauth-google"))
    implementation(project(":id:id-oauth-facebook"))
}
