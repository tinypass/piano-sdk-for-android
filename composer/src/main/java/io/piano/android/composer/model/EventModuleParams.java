package io.piano.android.composer.model;

import org.json.JSONObject;

public class EventModuleParams {

    public String moduleId;
    public String moduleName;

    static EventModuleParams fromJson(JSONObject json) {
        EventModuleParams eventModuleParams = null;

        if (!json.isNull("eventModuleParams")) {
            eventModuleParams = new EventModuleParams();

            JSONObject eventModuleParamsJson = json.optJSONObject("eventModuleParams");

            eventModuleParams.moduleId = eventModuleParamsJson.optString("moduleId");
            eventModuleParams.moduleName = eventModuleParamsJson.optString("moduleName");
        }

        return eventModuleParams;
    }
}
