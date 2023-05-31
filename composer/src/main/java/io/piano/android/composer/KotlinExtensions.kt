@file:JvmName("KotlinExtensions")

package io.piano.android.composer

import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.events.EventType
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Gets experiences from server
 *
 * @param request Prepared experience request
 * @return List of all experience events
 */
@Suppress("unused") // Public API.
suspend fun Composer.getExperience(
    request: ExperienceRequest
) = suspendCancellableCoroutine { continuation ->
    getExperience(
        request,
        { events: List<Event<EventType>> -> continuation.resume(events) }
    ) {
        continuation.resumeWithException(it)
    }
}
