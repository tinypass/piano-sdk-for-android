import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
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
