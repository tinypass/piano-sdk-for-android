package io.piano.android.id;

import androidx.annotation.NonNull;

import retrofit2.HttpException;
import retrofit2.Response;

public class Utils {
    private Utils() {
    }

    @NonNull
    static <T> T validateResponse(@NonNull Response<T> response) {
        if (!response.isSuccessful())
            throw new PianoIdException(new HttpException(response));
        T result = response.body();
        if (result == null)
            throw new PianoIdException();
        return result;
    }

    @NonNull
    public static PianoIdException wrapException(Throwable throwable) {
        if (throwable instanceof PianoIdException)
            return (PianoIdException) throwable;
        return new PianoIdException(throwable);
    }
}
