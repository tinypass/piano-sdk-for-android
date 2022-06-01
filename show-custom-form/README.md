# Piano Composer Show Custom form for Android

## Getting started
This is helper for simplifying "Show Custom form" event processing

### Dependencies

The Piano Composer Show Custom form is available as an AAR via Maven Central. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:show-custom-form:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:show-custom-form:$VERSION")
}
```

### Usage
Add code into your show custom form listener

Java
```java
ShowFormController controller = new ShowFormController(event, currentAccessToken, loginCallback);
controller.show(activity)
```

Kotlin
```kotlin
ShowFormController(event, currentAccessToken) {
    // access token is invalid and we should show login to user here
    signIn()
}.also {
    it.show(activity)
}
```
