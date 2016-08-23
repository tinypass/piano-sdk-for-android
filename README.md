# Piano SDK for Android

Welcome to Piano SDK for Android.

## Getting started
This document details the process of integrating the Piano SDK with your Android application. If you have any questions, don't hesitate to email us at support@piano.io.

### Download the Piano SDK
#### Gradle
The Piano SDK is available as an AAR via jCenter. To add the `api`, `composer`, `oauth`, etc. dependencies, open your project’s `build.gradle` and update the `repositories` and `dependencies` blocks as follows:
```
repositories {
    // ... other project repositories
    jcenter()
}

// ...

dependencies {
    // ... other project dependencies

    compile 'io.piano.android:api:1.0.0-alpha5'

    compile 'io.piano.android:composer:1.0.0-alpha5'

    compile 'io.piano.android:oauth:1.0.0-alpha5'
}
```

### Usage
#### [API](https://api.tinypass.com/api-docs/dist/index.html)
Initialize `PianoClient` in your Application's `onCreate` method:
```
@Override
public void onCreate() {
    super.onCreate();

    pianoClient = new PianoClient(this, BuildConfig.PIANO_AID, BuildConfig.DEBUG);
}
```

You can configure `BuildConfig.PIANO_AID` in `build.gradle`:
```
defaultConfig {
    applicationId "io.piano.android.sample"
    // ... other project properties

    buildConfigField "String", "PIANO_AID", "\"$aid\""
}
```

`PianoClient` provides underlying methods to APIs. They can be accessed as follows:
```
PianoClient pianoClient = ((PianoSampleApplication) getApplication()).getPianoClient();
List<Access> list = pianoClient.getUserAccessApi().list(pianoClient.getAid());
```

#### [Composer](https://piano.io/composer/)
Take a look at [ComposerActivity](sample/src/main/java/io/piano/android/sample/feature/composer/ComposerActivity.java)

### Sample
We created a sample app to showcase our SDK and highlight some of the features provided by each API.

Don't forget to specify `io.piano.aid=$YOUR_AID` in `piano.properties` in `sample` before you start importing!

### License
```
Copyright 2016 Piano, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
