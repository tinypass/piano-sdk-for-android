# Piano SDK for Android

## v2.5.0
* Add getUserInfo API in ID (similar to `tp.pianoId.loadExtendedUser`)
* Add support for ShowForm event
* Add support for ShowRecommendations event
* Parse numbers in SetResponseVariable as Long instead of Double
* Add providing user access token for Edge CDN
* Fix bug with CustomParameters
* Updated dependencies:
    - Kotlin [1.5.31 -> 1.6.21]
    - androidx.appcompat:appcompat [1.3.1 -> 1.4.1]
      https://developer.android.com/jetpack/androidx/releases/appcompat#1.4.1
    - androidx.fragment:fragment [1.3.6 -> 1.4.1]
      https://developer.android.com/jetpack/androidx/releases/fragment#1.4.1
    - com.facebook.android:facebook-login [12.1.0 -> 13.1.0]
      https://github.com/facebook/facebook-android-sdk
    - com.squareup.moshi:moshi [1.12.0 -> 1.13.0]
      https://github.com/square/moshi/
    - androidx.lifecycle:lifecycle-common-java8 [2.4.0 -> 2.4.1]
      https://developer.android.com/jetpack/androidx/releases/lifecycle#2.4.1
    - com.google.android.gms:play-services-auth [19.2.0 -> 20.1.0]

## v2.4.0
* Add support for Composer SetResponseVariable event
* Add parsing `accessToken` JWT into map for `PianoIdToken`
* Remove deprecated code for Facebook Login (see https://github.com/facebook/facebook-android-sdk/issues/875)
* Updated dependencies:
    - Kotlin [1.5.30 -> 1.5.31]
    - androidx.activity:activity [1.3.1 -> 1.4.0]
      https://developer.android.com/jetpack/androidx/releases/activity#1.4.0
    - androidx.annotation:annotation [1.2.0 -> 1.3.0]
      https://developer.android.com/jetpack/androidx/releases/annotation#1.3.0
    - com.android.tools.build:gradle [7.0.2 -> 7.0.3]
      http://tools.android.com/
    - androidx.lifecycle:lifecycle-common-java8 [2.3.1 -> 2.4.0]
      https://developer.android.com/jetpack/androidx/releases/lifecycle#2.4.0
    - com.facebook.android:facebook-login [11.2.0 -> 12.1.0]
      https://github.com/facebook/facebook-android-sdk

## v2.3.0
* Add Piano C1X integration
* Add Android 12 support
* Updated dependencies:
    - Kotlin [1.4.32 -> 1.5.30]
    - androidx.activity:activity [1.2.3 -> 1.3.1]
      https://developer.android.com/jetpack/androidx/releases/activity#1.3.1
    - androidx.appcompat:appcompat [1.3.0 -> 1.3.1]
      https://developer.android.com/jetpack/androidx/releases/appcompat#1.3.1
    - androidx.fragment:fragment [1.3.5 -> 1.3.6]
      https://developer.android.com/jetpack/androidx/releases/fragment#1.3.6
    - com.android.tools.build:gradle [4.2.2 -> 7.0.2]
      http://tools.android.com/
    - com.facebook.android:facebook-login [11.1.0 -> 11.2.0]
      https://github.com/facebook/facebook-android-sdk
    - com.google.android.gms:play-services-auth [19.0.0 -> 19.2.0]

## v2.2.1
* Added email confirmation (double opt-in) flag in `PianoIdToken`

## v2.2.0
* Updated dependencies:
    - androidx.activity:activity [1.2.2 -> 1.2.3]
      https://developer.android.com/jetpack/androidx/releases/activity#1.2.3
    - androidx.appcompat:appcompat [1.2.0 -> 1.3.0]
      https://developer.android.com/jetpack/androidx/releases/appcompat#1.3.0
    - androidx.fragment:fragment [1.3.2 -> 1.3.5]
      https://developer.android.com/jetpack/androidx/releases/fragment#1.3.4
    - com.android.tools.build:gradle [4.1.3 -> 4.2.2]
      http://tools.android.com/
    - com.facebook.android:facebook-login [9.1.0 -> 11.0.0]
      https://github.com/facebook/facebook-android-sdk
* Removed deprecated Composer initialization
* Fixed Proguard issues
* Added alias for `javascriptInterface.close()` in `ShowTemplateController`

## v2.1.1
* Fixed small bug with production-australia, production-asia-pacific endpoints in Composer

## v2.1.0

#### Composer
* Changed endpoint structure
* Added predefined endpoints (production, production-australia, production-asia-pacific, sandbox)
* Added `meterName` and `incremented` parameters for `Meter` event type
* Added ability to set webview provider for inline show template

#### ID
* Migrated to [Activity Result API](https://developer.android.com/training/basics/intents/result)
* Added possibility to provide custom `JavascriptInterface` with `customEvent` handler
* Added flag `isNewUser`. If true, user has just registered, otherwise it's false
* Changed result type from `PianoIdToken` to `PianoIdAuthResult`, which contains `token` and flag `isNewUser`
