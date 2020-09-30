# Piano Composer Show Template for Android

## Getting started
This is helper for simplifying "Show Template" event processing

### Dependencies

The Piano Composer Show Template is available as an AAR via jCenter. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:composer-show-template:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:composer-show-template:$VERSION")
}
```

### Usage
Add code into your show template listener

Java
```java
ShowTemplateController.show(activity, showTemplateEvent, customJavascriptInterface);
```

Kotlin
```kotlin
ShowTemplateController.show(activity, showTemplateEvent, customJavascriptInterface)
```
