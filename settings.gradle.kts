plugins {
    id("com.gradle.enterprise") version "3.0"
}

include(
    ":composer",
    ":composer-show-template",
    ":id:id",
    ":id:id-oauth-facebook",
    "id:id-oauth-google",
    ":sample",
    ":sample-ktx"
)
