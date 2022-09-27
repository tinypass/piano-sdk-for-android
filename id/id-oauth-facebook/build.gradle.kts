plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    implementation(libs.appcompat)
    api(libs.facebookLogin)
    api(project(":id:id"))
}
android {
    namespace = "io.piano.android.id.facebook"
}
