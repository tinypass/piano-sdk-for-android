# Piano ID Android SDK

## Getting started

### Dependencies

The Piano ID Android SDK is available as an AAR via jCenter. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:id:$VERSION")
}
```

### Endpoints
```
PianoId.ENDPOINT_PRODUCTION - Production endpoint
PianoId.ENDPOINT_PRODUCTION_AUSTRALIA - Australia production endpoint
PianoId.ENDPOINT_PRODUCTION_ASIA_PACIFIC - Asia/Pacific production endpoint
PianoId.ENDPOINT_SANDBOX - Sandbox endpoint
```

### Initialization

Java
```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PianoId.init(PianoId.ENDPOINT_PRODUCTION, BuildConfig.PIANO_AID);
    }
}
```

Kotlin
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PianoId.init(PianoId.ENDPOINT_PRODUCTION, BuildConfig.PIANO_AID)
    }
}
```

### Sign in
Use this code for open Sign in Activity

Java
```java
int PIANO_ID_REQUEST_CODE = 123456; // Use any your value
Intent authIntent = PianoId.signIn()
    // ... configure "Sign In" ...
   .getIntent(activity);
startActivityForResult(authIntent, PIANO_ID_REQUEST_CODE);
```

Kotlin
```kotlin
val PIANO_ID_REQUEST_CODE = 123456; // Use any your value
val authIntent = PianoId.signIn()
    // ... configure "Sign In" ...
   .getIntent(activity);
startActivityForResult(authIntent, PIANO_ID_REQUEST_CODE);
```
Note:
> The activity loads preconfigured template from dashboard. Links in the template are processed differently:  
> - normal link - opens within app instead current page, user can return back via system button (page will be reloaded)  
> - link with `target="_blank"` - opens externally in browser

Check authorization result in your onActivityResult:

Java
```java
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == PIANO_ID_REQUEST_CODE) {
        if (resultCode == RESULT_CANCELED) {
            // user cancelled Authorization process
        } else {
            try {
                PianoIdToken token = PianoId.getPianoIdTokenResult(data);
                // process token, Authorization successful
            } catch (PianoIdException e) {
                // Authorization failed, check e.cause for details
            }
        }
    } else super.onActivityResult(requestCode, resultCode, data);
}
```

Kotlin
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == PIANO_ID_REQUEST_CODE) {
        when (resultCode) {
            RESULT_OK ->
                runCatching {
                    val token = data.getPianoIdTokenResult()
                    // process token, Authorization successful
                }.onFailure {
                    // Authorization failed, check exception cause for details
                }
            RESULT_CANCELED ->
                {
                    // user cancelled Authorization process
                }
            PianoId.RESULT_ERROR ->
                runCatching {
                    data.getPianoIdTokenResult() // will throw exception
                }.onFailure {
                    // Authorization failed, check exception cause for details
                }
        }
    } else super.onActivityResult(requestCode, resultCode, data)
}
```

### Sign out

Java
```java
PianoId.signout(accessToken)
    // or
PianoId.signout(accessToken, callback)
```

Kotlin
```kotlin
PianoId.signout(accessToken)
    // or
PianoId.signout(accessToken) {
    // callback code here
}
```

### Refresh token

Java
```java
PianoId.refreshToken(refreshToken, callback)
```

Kotlin
```kotlin
PianoId.refreshToken(refreshToken) {
    // callback code here
}
```

## Social sign in

### Dependencies

**Google:**
Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id-oauth-google:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:id-oauth-google:$VERSION")
}
```

**Facebook:**
Groovy
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id-oauth-facebook:$VERSION'
}
```

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:id-oauth-facebook:$VERSION")
}
```

### Initialization

#### Google

[Configure your project for Google](https://developers.google.com/identity/sign-in/android/start-integrating#configure_a_project)

Java
```java
// ... other imports
import io.piano.android.id.GoogleOAuthProvider;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PianoId.init(...)
            .with(new GoogleOAuthProvider());
    }
}
```

Kotlin
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PianoId.init(...).with(GoogleOAuthProvider())
    }
}
```

#### Facebook

[Configure your project for Facebook](https://developers.facebook.com/docs/facebook-login/android/)

```java
// ... other imports
import io.piano.android.id.FacebookOAuthProvider;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PianoId.init(...)
            .with(new FacebookOAuthProvider());
    }
}
```

Kotlin
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PianoId.init(...).with(FacebookOAuthProvider())
    }
}
```

### Add Passwordless support
Add for your "log in" activity in `AndroidManifest.xml`:
```xml
<activity android:name=".MyExistingLoginActivity">
    <intent-filter
            android:autoVerify="true"
            tools:targetApi="m">
        <action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data
                android:host="success"
                android:scheme="piano.id.oauth.{YOUR_AID}" />
    </intent-filter>
</activity>
```

Add to your "log in" activity code into `onCreate`  
for Kotlin:
```kotlin
val uri = intent.data
if (uri.isPianoIdUri()) {
    // It's Piano paswordless auth link
    uri.parsePianoIdToken { r ->
        r.onFailure {
            // Auth unsuccessful, process it
        }.onSuccess {
            // Auth successful, save access token here
        }
    }
} else {
    // App deep link, process as usual
}
```

for Java  
```java
Uri uri = getIntent().getData();
if (PianoId.isPianoIdUri(uri)) {
    // It's Piano paswordless auth link
    PianoId.parsePianoIdToken(uri, PianoIdCallback.asResultCallback(new PianoIdCallback<PianoIdToken>() {
        @Override
        public void onSuccess(@NonNull PianoIdToken token) {
            // Auth successful, save access token here
        }

        @Override
        public void onFailure(@NonNull PianoIdException exception) {
            // Auth unsuccessful, process it
        }
    }));
} else {
    // App deep link, process as usual
}
```
