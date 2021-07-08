import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    implementation(Libs.appcompat)
    api(Libs.facebookLogin)
    api(project(":id:id"))
}
