package io.piano.android.composer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import com.nhaarman.mockitokotlin2.*
import io.piano.android.composer.model.EventExecutionContext
import io.piano.android.composer.model.EventModuleParams
import io.piano.android.composer.model.events.*
import java.lang.reflect.Type
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EventDeserializerTest {
    private val moduleParams: EventModuleParams = mock()
    private val executionContext: EventExecutionContext = mock()
    private val context: JsonDeserializationContext = mock() {
        on {
            deserialize<EventModuleParams>(
                any(),
                eq(EventModuleParams::class.java)
            )
        } doReturn moduleParams
        on {
            deserialize<EventExecutionContext>(
                any(),
                eq(EventExecutionContext::class.java)
            )
        } doReturn executionContext
    }
    private val type: Type = mock()
    private val eventDeserializer = EventDeserializer()

    @Test
    fun deserialize() {
        deserializeType<ShowLogin>(EventDeserializer.EVENT_TYPE_SHOW_LOGIN, true)
        deserializeType<ShowTemplate>(EventDeserializer.EVENT_TYPE_SHOW_TEMPLATE, true)
        deserializeType<ExperienceExecute>(EventDeserializer.EVENT_TYPE_EXPERIENCE_EXECUTE, true)
        deserializeType<Meter>(EventDeserializer.EVENT_TYPE_METER_ACTIVE) {
            assertEquals(Meter.MeterState.ACTIVE, it.state)
        }
        deserializeType<Meter>(EventDeserializer.EVENT_TYPE_METER_EXPIRED) {
            assertEquals(Meter.MeterState.EXPIRED, it.state)
        }
        deserializeType<UserSegment>(EventDeserializer.EVENT_TYPE_USER_SEGMENT_TRUE) {
            assertTrue { it.state }
        }
        deserializeType<UserSegment>(EventDeserializer.EVENT_TYPE_USER_SEGMENT_FALSE) {
            assertFalse { it.state }
        }
        deserializeType<NonSite>(EventDeserializer.EVENT_TYPE_NON_SITE)

        verify(context, times(EVENT_TYPES_COUNT)).deserialize<EventModuleParams>(
            any(),
            eq(EventModuleParams::class.java)
        )
        verify(context, times(EVENT_TYPES_COUNT)).deserialize<EventExecutionContext>(
            any(),
            eq(EventExecutionContext::class.java)
        )
    }

    private inline fun <reified T : EventType> deserializeType(
        eventType: String,
        checkTypeDeserialization: Boolean = false,
        additionalChecks: (T) -> Unit = {}
    ) {
        whenever(context.deserialize<T>(any(), eq(T::class.java))).thenReturn(mock())
        val json = JsonObject().apply {
            add(EventDeserializer.EVENT_MODULE_PARAMS_FIELD, mock())
            add(EventDeserializer.EVENT_EXECUTION_CONTEXT_FIELD, mock())
            add(EventDeserializer.EVENT_PARAMS_FIELD, mock())
            addProperty(EventDeserializer.EVENT_TYPE_FIELD, eventType)
        }
        with(eventDeserializer.deserialize(json, type, context)) {
            assertEquals(moduleParams, eventModuleParams)
            assertEquals(executionContext, eventExecutionContext)
            assertTrue { eventData is T }
            additionalChecks(eventData as T)
        }
        if (checkTypeDeserialization)
            verify(context).deserialize<T>(any(), eq(T::class.java))
    }

    companion object {
        const val EVENT_TYPES_COUNT = 8
    }
}