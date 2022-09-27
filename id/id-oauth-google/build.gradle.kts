plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.googleAuth)
    api(project(":id:id"))
}
android {
    namespace = "io.piano.android.id.google"
}
