package io.piano.android.composer

import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse

public interface ExperienceInterceptor {
    public fun beforeExecute(request: ExperienceRequest) {}

    public fun afterExecute(request: ExperienceRequest, response: ExperienceResponse) {}
}
