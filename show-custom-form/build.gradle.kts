import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
    kotlin("kapt")
}

dependencies {
    api(project(":composer"))
    api(project(":id:id"))
    api(project(":show-helper"))
    implementation(Libs.lifecycleCommon)
    implementation(Libs.timber)
    implementation(Libs.moshi)
    kapt(Libs.moshiCodegen)
}
