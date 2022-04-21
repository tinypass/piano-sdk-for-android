package io.piano.android.composer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.internal.Util
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.EventExecutionContext
import io.piano.android.composer.model.EventModuleParams
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
import java.lang.reflect.Type

class EventJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        takeIf { Event::class.java.isAssignableFrom(Types.getRawType(type)) }?.run {
            EventJsonAdapter(
                moshi.adapter(EventModuleParams::class.java),
                moshi.adapter(EventExecutionContext::class.java),
                listOf(
                    moshi.adapter(ExperienceExecute::class.java),
                    DelegateAdapter(moshi.adapter(Meter::class.java)) { copy(state = Meter.MeterState.ACTIVE) },
                    DelegateAdapter(moshi.adapter(Meter::class.java)) { copy(state = Meter.MeterState.EXPIRED) },
                    StubAdapter { NonSite },
                    moshi.adapter(SetResponseVariable::class.java),
                    moshi.adapter(ShowForm::class.java),
                    moshi.adapter(ShowLogin::class.java),
                    moshi.adapter(ShowRecommendations::class.java),
                    moshi.adapter(ShowTemplate::class.java),
                    StubAdapter { UserSegment(true) },
                    StubAdapter { UserSegment(false) }
                )
            ).nullSafe()
        }

    private class StubAdapter<T>(private val stubFunction: () -> T) : JsonAdapter<T>() {
        override fun fromJson(reader: JsonReader): T = stubFunction().also { reader.skipValue() }
        override fun toJson(writer: JsonWriter, value: T?) {
            TODO("Not supported")
        }
    }

    private class DelegateAdapter<T>(
        private val delegateAdapter: JsonAdapter<T>,
        private val postProcessAction: T.() -> T
    ) : JsonAdapter<T>() {
        override fun fromJson(reader: JsonReader): T? = delegateAdapter.fromJson(reader)?.run(postProcessAction)

        override fun toJson(writer: JsonWriter, value: T?) = delegateAdapter.toJson(writer, value)
    }

    class EventJsonAdapter(
        private val eventModuleParamsAdapter: JsonAdapter<EventModuleParams>,
        private val eventExecutionContextAdapter: JsonAdapter<EventExecutionContext>,
        private val eventDataAdapters: List<JsonAdapter<out EventType>>
    ) : JsonAdapter<Event<*>>() {
        private val options = JsonReader.Options.of(
            EVENT_MODULE_PARAMS,
            EVENT_EXECUTION_CONTEXT,
            EVENT_PARAMS
        )
        private val eventTypeKeyOptions = JsonReader.Options.of(EVENT_TYPE)
        private val eventSubtypesOptions = JsonReader.Options.of(
            EVENT_TYPE_EXPERIENCE_EXECUTE,
            EVENT_TYPE_METER_ACTIVE,
            EVENT_TYPE_METER_EXPIRED,
            EVENT_TYPE_NON_SITE,
            EVENT_TYPE_SET_RESPONSE_VARIABLE,
            EVENT_TYPE_SHOW_FORM,
            EVENT_TYPE_SHOW_LOGIN,
            EVENT_TYPE_SHOW_RECOMMENDATIONS,
            EVENT_TYPE_SHOW_TEMPLATE,
            EVENT_TYPE_USER_SEGMENT_TRUE,
            EVENT_TYPE_USER_SEGMENT_FALSE
        )

        override fun fromJson(reader: JsonReader): Event<*>? {
            var eventModuleParams: EventModuleParams? = null
            var eventExecutionContext: EventExecutionContext? = null
            var eventData: EventType? = null
            val eventType = reader.peekJson().use {
                it.setFailOnUnknown(false)
                it.findEventType()
            }
            return with(reader) {
                beginObject()
                while (hasNext()) {
                    when (selectName(options)) {
                        0 ->
                            eventModuleParams = eventModuleParamsAdapter.fromJson(this)
                                ?: throw Util.unexpectedNull(
                                    EVENT_MODULE_PARAMS,
                                    EVENT_MODULE_PARAMS,
                                    this
                                )
                        1 ->
                            eventExecutionContext = eventExecutionContextAdapter.fromJson(this)
                                ?: throw Util.unexpectedNull(
                                    EVENT_EXECUTION_CONTEXT,
                                    EVENT_EXECUTION_CONTEXT,
                                    this
                                )
                        2 ->
                            eventData = eventDataAdapters[eventType].fromJson(this)
                                ?: throw Util.unexpectedNull(
                                    EVENT_PARAMS,
                                    EVENT_PARAMS,
                                    this
                                )
                        -1 -> {
                            skipName()
                            skipValue()
                        }
                    }
                }
                endObject()
                Event(
                    eventModuleParams ?: throw Util.missingProperty(
                        EVENT_MODULE_PARAMS,
                        EVENT_MODULE_PARAMS,
                        this
                    ),
                    eventExecutionContext ?: throw Util.missingProperty(
                        EVENT_EXECUTION_CONTEXT,
                        EVENT_EXECUTION_CONTEXT,
                        this
                    ),
                    eventData ?: throw Util.missingProperty(
                        EVENT_PARAMS,
                        EVENT_PARAMS,
                        this
                    )
                )
            }
        }

        private fun JsonReader.findEventType(): Int {
            beginObject()
            while (hasNext()) {
                if (selectName(eventTypeKeyOptions) == -1) {
                    skipName()
                    skipValue()
                    continue
                }
                return selectString(eventSubtypesOptions).takeIf { it != -1 } ?: throw JsonDataException(
                    "Unknown event type '${nextString()}', expected one of $eventSubtypesOptions"
                )
            }
            throw JsonDataException("Can't find key $EVENT_TYPE in json")
        }

        override fun toJson(writer: JsonWriter, value: Event<*>?) {
            TODO("Not supported")
        }
    }

    companion object {
        private const val EVENT_MODULE_PARAMS = "eventModuleParams"
        private const val EVENT_EXECUTION_CONTEXT = "eventExecutionContext"
        private const val EVENT_PARAMS = "eventParams"

        private const val EVENT_TYPE = "eventType"
        private const val EVENT_TYPE_SHOW_LOGIN = "showLogin"
        private const val EVENT_TYPE_METER_ACTIVE = "meterActive"
        private const val EVENT_TYPE_METER_EXPIRED = "meterExpired"
        private const val EVENT_TYPE_USER_SEGMENT_TRUE = "userSegmentTrue"
        private const val EVENT_TYPE_USER_SEGMENT_FALSE = "userSegmentFalse"
        private const val EVENT_TYPE_EXPERIENCE_EXECUTE = "experienceExecute"
        private const val EVENT_TYPE_NON_SITE = "nonSite"
        private const val EVENT_TYPE_SHOW_TEMPLATE = "showTemplate"
        private const val EVENT_TYPE_SET_RESPONSE_VARIABLE = "setResponseVariable"
        private const val EVENT_TYPE_SHOW_RECOMMENDATIONS = "showRecommendations"
        private const val EVENT_TYPE_SHOW_FORM = "showForm"
    }
}
