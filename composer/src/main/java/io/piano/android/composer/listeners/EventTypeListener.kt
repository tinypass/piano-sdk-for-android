package io.piano.android.composer.listeners

import io.piano.android.composer.model.Event
import io.piano.android.composer.model.events.EventType
import io.piano.android.composer.model.events.ExperienceExecute
import io.piano.android.composer.model.events.Meter
import io.piano.android.composer.model.events.NonSite
import io.piano.android.composer.model.events.SetResponseVariable
import io.piano.android.composer.model.events.ShowForm
import io.piano.android.composer.model.events.ShowLogin
import io.piano.android.composer.model.events.ShowRecommendations
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.composer.model.events.UserSegment

/**
 * Generic event listener
 *
 * @param <T> event type
</T> */
interface EventTypeListener<T : EventType> {
    /**
     * Processes event
     *
     * @param event event object
     */
    fun onExecuted(event: Event<T>)

    /**
     * Asks is this event type supported by listener
     *
     * @param event event
     * @return True, if this listener can process this type of events, otherwise False. Default answer is False
     */
    fun canProcess(event: Event<EventType>): Boolean {
        return false
    }
}

fun interface ExperienceExecuteListener : EventTypeListener<ExperienceExecute> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ExperienceExecute
}

fun interface MeterListener : EventTypeListener<Meter> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is Meter
}

fun interface NonSiteListener : EventTypeListener<NonSite> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is NonSite
}

fun interface UserSegmentListener : EventTypeListener<UserSegment> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is UserSegment
}

fun interface ShowTemplateListener : EventTypeListener<ShowTemplate> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowTemplate
}

fun interface ShowLoginListener : EventTypeListener<ShowLogin> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowLogin
}

fun interface SetResponseVariableListener : EventTypeListener<SetResponseVariable> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is SetResponseVariable
}

fun interface ShowRecommendationsListener : EventTypeListener<ShowRecommendations> {
    override fun canProcess(event: Event<EventType>): Boolean =
        event.eventData is ShowRecommendations && event.eventData.type == "CXENSE"
}

fun interface ShowFormListener : EventTypeListener<ShowForm> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowForm
}
