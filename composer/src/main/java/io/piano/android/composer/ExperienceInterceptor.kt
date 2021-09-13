package io.piano.android.composer

import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse

interface ExperienceInterceptor {
    fun beforeExecute(request: ExperienceRequest) {}

    fun afterExecute(request: ExperienceRequest, response: ExperienceResponse) {}
}
