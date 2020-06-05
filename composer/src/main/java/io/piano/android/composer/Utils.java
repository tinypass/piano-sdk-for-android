package io.piano.android.composer;

import androidx.annotation.NonNull;

import retrofit2.HttpException;
import retrofit2.Response;

class Utils {
    private Utils() {}

    @NonNull
    static <T> T validateResponse(@NonNull Response<T> response) {
        if (!response.isSuccessful())
            throw new ComposerException(new HttpException(response));
        T result = response.body();
        if (result == null)
            throw new ComposerException();
        return result;
    }
}
