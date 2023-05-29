@file:JvmName("KotlinExtensions")

package io.piano.android.composer

import io.piano.android.composer.listeners.EventTypeListener
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.events.EventType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Gets experiences from server
 *
 * @param request Prepared experience request
 * @return [Flow] which emits all experience events
 */
@Suppress("unused") // Public API.
fun Composer.getExperiences(
    request: ExperienceRequest
): Flow<Event<EventType>> = callbackFlow {
    getExperience(
        request,
        listOf(
            object : EventTypeListener<EventType> {
                override fun onExecuted(event: Event<EventType>) {
                    trySend(event)
                }

                override fun canProcess(event: Event<EventType>): Boolean = true
            }
        )
    ) {
        close(it)
    }
}
