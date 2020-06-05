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
    }

    compileOptions {
        sourceCompatibility = Config.compileSourceVersion
        targetCompatibility = Config.compileTargetVersion
    }
}

dependencies {
    implementation(Libs.okhttpLogging)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverter)
    implementation(Libs.annotations)

    testImplementation(Libs.kotlinJunit)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
    testImplementation(Libs.okhttpMockServer)
}

if ("SNAPSHOT" !in version.toString()) {
    apply {
        from("https://raw.github.com/tinypass/gradle-mvn-push/master/gradle-mvn-push.gradle")
    }
}
