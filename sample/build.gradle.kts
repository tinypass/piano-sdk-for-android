import io.piano.android.dependencies.Libs
import java.io.FileReader
import java.util.Properties

plugins {
    id("com.android.application")
    id("common-android-config")
}

val pianoProperties = Properties().apply {
    load(FileReader(file("piano.properties")))
}

val aid: String = pianoProperties["io.piano.aid"]?.toString()
    ?: throw IllegalArgumentException("You missed 'io.piano.aid' in piano.properties")
val endpoint: String = pianoProperties["io.piano.endpoint"]?.toString() ?: ""
val siteId: String = pianoProperties["cxenseSiteid"]?.toString() ?: "1111111111111111111"
val fbAppId: String = pianoProperties["facebookAppId"]?.toString() ?: "1111111111111111"

android {
    defaultConfig {
        applicationId = "io.piano.sample"
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "PIANO_AID", """"$aid"""")
        buildConfigField("String", "CX_SITE_ID", """"$siteId"""")
        buildConfigField("String", "PIANO_ENDPOINT", """"$endpoint"""")
        multiDexEnabled = true

        manifestPlaceholders += mapOf<String, Any>(
            "PIANO_AID" to aid.toLowerCase(),
            "FB_APP_ID" to "fb$fbAppId",
            "FB_APP_SCHEME" to "fb$fbAppId"
        )
    }
    signingConfigs {
        named("debug") {
            storeFile = file("$rootDir/debug.keystore")
        }
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
        }
        named("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        abortOnError = false
    }
}

ktlint {
    android.set(true)
}

dependencies {
    implementation(Libs.multidex)
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.googleAuth)
    implementation(Libs.timber)
    implementation(Libs.moshi)
    implementation(Libs.prefsKtx)

    implementation(project(":composer"))
    implementation(project(":composer-c1x"))
    implementation(project(":composer-show-template"))
    implementation(project(":id:id"))
    implementation(project(":id:id-oauth-google"))
    implementation(project(":id:id-oauth-facebook"))
    implementation(project(":show-custom-form"))
}
