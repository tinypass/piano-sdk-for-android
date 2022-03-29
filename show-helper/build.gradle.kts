import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    api(project(":composer"))
    api(Libs.appcompat)
    api(Libs.fragments)
    implementation(Libs.annotations)
    implementation(Libs.timber)
}
