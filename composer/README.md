# Piano Composer Android SDK

## Getting started

### Dependencies

The Piano Composer Android SDK is available as an AAR via jCenter. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:composer:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:composer:$VERSION")
}
```

### Initialization

Java
```java
// Use one of these
Composer.init(context, BuildConfig.PIANO_AID);
Composer.init(context, BuildConfig.PIANO_AID, true);
Composer.init(context, BuildConfig.PIANO_AID, customEndpoint);
```

Kotlin
```kotlin
// Use one of these
Composer.init(context, BuildConfig.PIANO_AID)
Composer.init(context, BuildConfig.PIANO_AID, true)
Composer.init(context, BuildConfig.PIANO_AID, customEndpoint)
```

### Set user access token

Java
```java
Composer.getInstance().userToken(accessToken);
```

Kotlin
```kotlin
Composer.getInstance().userToken(accessToken)
```

### Request user experience

Java
```java
ExperienceRequest request = new ExperienceRequest.Builder()
        .tag("tag")
        .debug(true)
        .customParams(customParameters)
        .build();

Collection<EventTypeListener<? extends EventType>> listeners = Arrays.asList(
        (UserSegmentListener) event -> {
            // process event here.
        },
        (MeterListener) event -> {
            // process event here.
        },
        (ShowTemplateListener) event -> {
            // process event here.
        },
        (NonSiteListener) event -> {
            // process event here.
        }
);
Composer.getInstance().getExperience(request, listeners, exception -> {
    // process exception here.
});
```

Kotlin
```kotlin
val request = ExperienceRequest.Builder()
    .tag("custom_tag")
    .debug(true)
    .customParams(customParameters)
    .build()

val listeners = listOf(
    UserSegmentListener { (eventModuleParams, eventExecutionContext, eventData) ->
        // process event here.
    },
    MeterListener { (_, _, eventData) ->
        // process event here.
    },
    ShowTemplateListener { event: Event<ShowTemplate> ->
        // process event here
    },
    NonSiteListener { (eventModuleParams, eventExecutionContext) ->
        // process event here
    }
)
Composer.getInstance().getExperience(request, listeners) { exception: ComposerException ->
    // process exception here.
}
```
