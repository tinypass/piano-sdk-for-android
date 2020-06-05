package io.piano.android.composer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.events.EventType;
import io.piano.android.composer.model.EventExecutionContext;
import io.piano.android.composer.model.EventModuleParams;
import io.piano.android.composer.model.events.ExperienceExecute;
import io.piano.android.composer.model.events.Meter;
import io.piano.android.composer.model.events.NonSite;
import io.piano.android.composer.model.events.ShowLogin;
import io.piano.android.composer.model.events.ShowTemplate;
import io.piano.android.composer.model.events.UserSegment;

class EventDeserializer implements JsonDeserializer<Event<? extends EventType>> {
    static final String EVENT_TYPE_FIELD = "eventType";
    static final String EVENT_MODULE_PARAMS_FIELD = "eventModuleParams";
    static final String EVENT_EXECUTION_CONTEXT_FIELD = "eventExecutionContext";
    static final String EVENT_PARAMS_FIELD = "eventParams";

    static final String EVENT_TYPE_SHOW_LOGIN = "showLogin";
    static final String EVENT_TYPE_METER_ACTIVE = "meterActive";
    static final String EVENT_TYPE_METER_EXPIRED = "meterExpired";
    static final String EVENT_TYPE_USER_SEGMENT_TRUE = "userSegmentTrue";
    static final String EVENT_TYPE_USER_SEGMENT_FALSE = "userSegmentFalse";
    static final String EVENT_TYPE_EXPERIENCE_EXECUTE = "experienceExecute";
    static final String EVENT_TYPE_NON_SITE = "nonSite";
    static final String EVENT_TYPE_SHOW_TEMPLATE = "showTemplate";

    @Override
    public Event<? extends EventType> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        EventModuleParams eventModuleParams = context.deserialize(obj.get(EVENT_MODULE_PARAMS_FIELD), EventModuleParams.class);
        EventExecutionContext eventExecutionContext = context.deserialize(obj.get(EVENT_EXECUTION_CONTEXT_FIELD), EventExecutionContext.class);
        JsonElement eventData = obj.get(EVENT_PARAMS_FIELD);
        String eventType = obj.get(EVENT_TYPE_FIELD).getAsString();
        switch (eventType) {
            case EVENT_TYPE_SHOW_LOGIN:
                return new Event<ShowLogin>(eventModuleParams, eventExecutionContext, context.deserialize(eventData, ShowLogin.class));
            case EVENT_TYPE_SHOW_TEMPLATE:
                return new Event<ShowTemplate>(eventModuleParams, eventExecutionContext, context.deserialize(eventData, ShowTemplate.class));
            case EVENT_TYPE_EXPERIENCE_EXECUTE:
                return new Event<ExperienceExecute>(eventModuleParams, eventExecutionContext, context.deserialize(eventData, ExperienceExecute.class));
            case EVENT_TYPE_METER_ACTIVE:
                Meter meterActive = context.deserialize(eventData, Meter.class);
                meterActive.state = Meter.MeterState.ACTIVE;
                return new Event<>(eventModuleParams, eventExecutionContext, meterActive);
            case EVENT_TYPE_METER_EXPIRED:
                Meter meterExpired = context.deserialize(eventData, Meter.class);
                meterExpired.state = Meter.MeterState.EXPIRED;
                return new Event<>(eventModuleParams, eventExecutionContext, meterExpired);
            case EVENT_TYPE_USER_SEGMENT_TRUE:
                return new Event<>(eventModuleParams, eventExecutionContext, new UserSegment(true));
            case EVENT_TYPE_USER_SEGMENT_FALSE:
                return new Event<>(eventModuleParams, eventExecutionContext, new UserSegment(false));
            case EVENT_TYPE_NON_SITE:
                return new Event<>(eventModuleParams, eventExecutionContext, new NonSite());
        }
        return null;
    }

}
