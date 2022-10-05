# Piano Composer Android SDK
![Maven Central](https://img.shields.io/maven-central/v/io.piano.android/composer)

## Getting started

### Dependencies

The Piano Composer Android SDK is available as an AAR via Maven Central. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:composer:$VERSION")
}
```

### Endpoints
```kotlin
Endpoint.PRODUCTION // Production endpoint
Endpoint.PRODUCTION_EUROPE // Production endpoint for Europe region
Endpoint.PRODUCTION_AUSTRALIA // Production endpoint for Australia region
Endpoint.PRODUCTION_ASIA_PACIFIC // Production endpoint for Asia/Pacific region
Endpoint.SANDBOX // Sandbox endpoint
```

### Initialization

Kotlin
```kotlin
// Use one of these
Composer.init(context, BuildConfig.PIANO_AID, Endpoint.PRODUCTION) // use here one of endpoints listed above
Composer.init(context, BuildConfig.PIANO_AID, Endpoint(composerHost, apiHost))
```

### Set user access token

Kotlin
```kotlin
Composer.getInstance().userToken(accessToken)
```

### Request user experience

Note:
> We have helper for Show Template event [here](../composer-show-template/README.md)

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
