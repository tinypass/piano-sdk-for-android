# Piano Composer C1X integration for Android

## Getting started
This is C1X integration artifact for Composer

### Dependencies

The Piano Composer C1X is available as an AAR via Maven Central. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:composer-c1x:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:composer-c1x:$VERSION")
}
```

### Usage
1. Replace your `Composer.init(context, aid, endpoint)` with `PianoC1X.init(context, siteId, aid, endpoint)`, where `siteId` is Cxense Site ID
1. Add `url` parameter to all your experience's requests to Composer
1. Don't send Cxense PV events from screens with Composer
