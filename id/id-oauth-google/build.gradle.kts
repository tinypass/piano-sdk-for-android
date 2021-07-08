import io.piano.android.dependencies.Libs

plugins {
    id("com.android.library")
    id("common-android-config")
}

dependencies {
    implementation(Libs.appcompat)
    implementation(Libs.googleAuth)
    api(project(":id:id"))
}
