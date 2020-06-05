plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.appcompat)
    implementation(Libs.androidxActivity)
    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverter)
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

if ("SNAPSHOT" !in version.toString()) {
    apply {
        from("https://raw.github.com/tinypass/gradle-mvn-push/master/gradle-mvn-push.gradle")
    }
}
