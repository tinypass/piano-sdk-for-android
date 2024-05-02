plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.androidx.credentials.base)
    implementation(libs.androidx.credentials.playServices)
    implementation(libs.googleId)
    api(project(":id:id"))
}
android {
    namespace = "io.piano.android.id.google"
}
