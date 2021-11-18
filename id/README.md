# Piano ID Android SDK

## Getting started

### Dependencies

The Piano ID Android SDK is available as an AAR via Maven Central. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

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
PianoId.ENDPOINT_PRODUCTION_EUROPE - Europe production endpoint
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
Register processing for auth result (see [here](https://developer.android.com/training/basics/intents/result) for more info)

Java
```java
private final ActivityResultLauncher<PianoIdClient.SignInContext> authResult = registerForActivityResult(
        new PianoIdAuthResultContract(),
        r -> {
            if (r == null) {
                // user cancelled Authorization process
            } else if (r.isSuccess()) {
                PianoIdAuthSuccessResult data = (PianoIdAuthSuccessResult) r;
                boolean isNewUserRegistered = data.isNewUser()
                PianoIdToken token = data.getToken();
                if (token.emailConfirmationRequired) {
                    // process enabled Double opt-in
                }
                // process successful authorization 
            } else {
                PianoIdAuthFailureResult data = (PianoIdAuthFailureResult) r;
                PianoIdException e = data.getException());
                // Authorization failed, check e.cause for details
            }
        }
);
```

Kotlin
```kotlin
private val authResult = registerForActivityResult(PianoIdAuthResultContract()) { r ->
    when (r) {
        null -> { /* user cancelled Authorization process */ }
        is PianoIdAuthSuccessResult -> {
            val token = r.token
            val isNewUserRegistered = r.isNewUser
            if (token.emailConfirmationRequired) {
                // process enabled Double opt-in
            }
            // process successful authorization 
        }
        is PianoIdAuthFailureResult -> {
            val e = r.exception
            // Authorization failed, check e.cause for details
        }
    }
}
```

Start auth process:
Check authorization result in your onActivityResult:

Java
```java
authResult.launch(
    PianoId.signIn()
    // ... configure "Sign In" ...
);
```

Kotlin
```kotlin
authResult.launch(
    PianoId.signIn()
    // ... configure "Sign In" ...
)
```
Note:
> The activity loads preconfigured template from dashboard. Links in the template are processed differently:
> - normal link - opens within app instead current page, user can return back via system button (page will be reloaded)
> - link with `target="_blank"` - opens externally in browser

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

[Configure your project for Google](https://docs.piano.io/how-to-set-up-google-social-login)

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

[Configure your project for Facebook](https://docs.piano.io/how-to-setup-facebook-social-login/)

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
