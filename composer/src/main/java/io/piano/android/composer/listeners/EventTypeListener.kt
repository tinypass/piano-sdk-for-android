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

public typealias EventsListener = (List<Event<EventType>>) -> Unit

/**
 * Generic event listener.
 *
 * @param <T> event type.
*/
public interface EventTypeListener<T : EventType> {
    /**
     * Processes event.
     *
     * @param event event object.
     */
    public fun onExecuted(event: Event<T>)

    /**
     * Asks is this event type supported by listener.
     *
     * @param event event
     * @return True, if this listener can process this type of events, otherwise False. Default answer is False.
     */
    public fun canProcess(event: Event<EventType>): Boolean {
        return false
    }
}

/**
 * {#EventTypeListener} for {#ExperienceExecute} events.
 */
public fun interface ExperienceExecuteListener : EventTypeListener<ExperienceExecute> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ExperienceExecute
}

/**
 * {#EventTypeListener} for {#Meter} events.
 */
public fun interface MeterListener : EventTypeListener<Meter> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is Meter
}

/**
 * {#EventTypeListener} for {#NonSite} events.
 */
public fun interface NonSiteListener : EventTypeListener<NonSite> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is NonSite
}

/**
 * {#EventTypeListener} for {#UserSegment} events.
 */
public fun interface UserSegmentListener : EventTypeListener<UserSegment> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is UserSegment
}

/**
 * {#EventTypeListener} for {#ShowTemplate} events.
 */
public fun interface ShowTemplateListener : EventTypeListener<ShowTemplate> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowTemplate
}

/**
 * {#EventTypeListener} for {#ShowLogin} events.
 */
public fun interface ShowLoginListener : EventTypeListener<ShowLogin> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowLogin
}

/**
 * {#EventTypeListener} for {#SetResponseVariable} events.
 */
public fun interface SetResponseVariableListener : EventTypeListener<SetResponseVariable> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is SetResponseVariable
}

/**
 * {#EventTypeListener} for {#ShowRecommendations} events.
 */
public fun interface ShowRecommendationsListener : EventTypeListener<ShowRecommendations> {
    override fun canProcess(event: Event<EventType>): Boolean =
        event.eventData is ShowRecommendations && event.eventData.type == "CXENSE"
}

/**
 * {#EventTypeListener} for {#ShowForm} events.
 */
public fun interface ShowFormListener : EventTypeListener<ShowForm> {
    override fun canProcess(event: Event<EventType>): Boolean = event.eventData is ShowForm
}
