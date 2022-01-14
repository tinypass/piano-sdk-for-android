# Piano SDK for Android

Welcome to Piano SDK for Android.

## Getting started
This document details the process of integrating the Piano SDK with your Android application. If you have any questions, don't hesitate to email us at support@piano.io.

### Download the Piano SDK
#### Gradle
The Piano SDK is available as an AAR via MavenCentral. To add the `composer`, `composer-show-template`, `id`, etc. dependencies, open your project’s `build.gradle`/`build.gradle.kts` and update `dependencies` block.

### Usage
#### [API](https://docs.piano.io/api/)
Take a look at [API readme](api/README.md)

#### [Composer](https://piano.io/product/composer/)
Take a look at [Composer readme](composer/README.md)

#### [ID](https://piano.io/product/id/)
Take a look at [Piano ID readme](id/README.md)

### Sample
We created a sample app to showcase our SDK and highlight some of the features provided by each API.

Don't forget to specify `io.piano.aid=$YOUR_AID` in `piano.properties` in `sample`/`sample-ktx` before you start importing!

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
