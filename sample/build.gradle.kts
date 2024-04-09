import java.io.FileReader
import java.util.Locale
import java.util.Properties

plugins {
    id("com.android.application")
    id("common-android-config")
}

val pianoProperties = Properties().apply {
    load(FileReader(file("piano.properties")))
}

val aid: String = pianoProperties.getProperty("io.piano.aid")
    ?: throw IllegalArgumentException("You missed 'io.piano.aid' in piano.properties")
val endpoint: String = pianoProperties.getProperty("io.piano.endpoint", "")
val qaPrefix: String = pianoProperties.getProperty("io.piano.qaPrefix", "")
val siteUrl: String = pianoProperties.getProperty("siteUrl", "https://example.com/")
val edgeUrl: String = pianoProperties.getProperty("edgeUrl", "https://example.com/")
val siteId: String = pianoProperties.getProperty("cxenseSiteid", "1111111111111111111")
val fbAppId: String = pianoProperties.getProperty("facebookAppId", "1111111111111111")
val fbAppToken: String = pianoProperties.getProperty("facebookClientToken", "00000000000000000000000000000000")

android {
    defaultConfig {
        applicationId = "io.piano.sample"
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "PIANO_AID", """"$aid"""")
        buildConfigField("String", "CX_SITE_ID", """"$siteId"""")
        buildConfigField("String", "PIANO_ENDPOINT", """"$endpoint"""")
        buildConfigField("String", "PIANO_QA_PREFIX", """"$qaPrefix"""")
        buildConfigField("String", "SITE_URL", """"$siteUrl"""")
        buildConfigField("String", "EDGE_URL", """"$edgeUrl"""")
        multiDexEnabled = true

        manifestPlaceholders += mapOf(
            "PIANO_AID" to aid.lowercase(Locale.getDefault()),
            "FB_APP_ID" to "fb$fbAppId",
            "FB_APP_SCHEME" to "fb$fbAppId",
            "FB_APP_TOKEN" to fbAppToken
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
        buildConfig = true
        viewBinding = true
    }
    lint {
        abortOnError = false
    }
    namespace = "io.piano.sample"
}

dependencies {
    implementation(libs.multidex)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.googleAuth)
    implementation(libs.timber)
    implementation(libs.moshi)
    implementation(libs.prefsKtx)
    implementation(libs.lifecycleKtx)

    implementation(project(":composer"))
    implementation(project(":composer-c1x"))
    implementation(project(":composer-show-template"))
    implementation(project(":id:id"))
    implementation(project(":id:id-oauth-google"))
    implementation(project(":id:id-oauth-facebook"))
    implementation(project(":show-custom-form"))
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.13")
}
