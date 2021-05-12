# Piano SDK for Android

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
