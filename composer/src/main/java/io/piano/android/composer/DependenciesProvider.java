package io.piano.android.composer;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.piano.android.composer.model.Event;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestrictTo(RestrictTo.Scope.LIBRARY)
class DependenciesProvider {
    private static DependenciesProvider instance;

    private final Composer composer;

    private DependenciesProvider(@NonNull Context context, @NonNull String aid, @Nullable String endpoint) {
        Context appContext = context.getApplicationContext();

        PrefsStorage prefsStorage = new PrefsStorage(appContext);

        String userAgent = buildUserAgent(appContext);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new UserAgentInterceptor(userAgent))
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Event.class, new EventDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_SANDBOX)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api = retrofit.create(Api.class);
        ExperienceIdsProvider experienceIdsProvider = new ExperienceIdsProvider(prefsStorage);
        HttpHelper httpHelper = new HttpHelper(experienceIdsProvider, prefsStorage, gson, userAgent);
        composer = new Composer(api, httpHelper, aid, endpoint);
    }

    String buildUserAgent(Context context) {
        return String.format(
                "Piano composer SDK (Android %s (Build %s); %s %s/%s)",
                Build.VERSION.RELEASE,
                Build.ID,
                context.getResources().getBoolean(R.bool.isTablet) ? "Tablet" : "Mobile",
                Build.MANUFACTURER,
                Build.MODEL
        );
    }

    Composer getComposer() {
        return composer;
    }

    static void init(@NonNull Context context, @NonNull String aid, @Nullable String endpoint) {
        DependenciesProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (DependenciesProvider.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = new DependenciesProvider(context, aid, endpoint);
            }
        }
    }

    static DependenciesProvider getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Piano Composer SDK is not initialized! Make sure that you initialize it");
        }
        return instance;
    }
}
