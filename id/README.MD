# Piano ID Android SDK

## Getting started

### Dependencies

The Piano ID Android SDK is available as an AAR via jCenter. To add dependencies, open your project’s build.gradle and update the repositories and dependencies blocks as follows:

```groovy
repositories {
    // ... other project repositories
    jcenter()
}

// ...

dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id:2.0.0-beta01'
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

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PianoId.init(PianoId.ENDPOINT_PRODUCTION, BuildConfig.PIANO_AID);
    }
}
```

### Sign in
```java
int PIANO_ID_REQUEST_CODE = 123456; // Use any your value
Intent authIntent = PianoId.signIn()
    // ... configure "Sign In" ...
   .getIntent(activity);
startActivityForResult(authIntent, PIANO_ID_REQUEST_CODE);
```

Check authorization result in your onActivityResult:
```java
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == PIANO_ID_REQUEST_CODE) {
        if (resultCode == RESULT_CANCELED) {
            // user cancelled Authorization process
        } else {
            try {
                PianoIdToken token = PianoId.getResultFromIntent(data);
                // process token, Authorization successful
            } catch (PianoIdException e) {
                // Authorization failed, check e.cause for details
            }
        }
    }
}
```

## Social sign in

### Dependencies

**Google:**
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id-oauth-google:2.0.0-beta01'
}
```

**Facebook:**
```groovy
dependencies {
    // ... other project dependencies
    implementation 'io.piano.android:id-oauth-facebook:2.0.0-beta01'
}
```

### Initialization

#### Google

[Configure your project for Google](https://developers.google.com/identity/sign-in/android/start-integrating#configure_a_project)

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