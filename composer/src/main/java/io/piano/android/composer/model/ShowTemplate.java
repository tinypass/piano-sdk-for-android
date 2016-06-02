package io.piano.android.composer.model;

import org.json.JSONObject;

public class ShowTemplate extends Event {

    public String templateId;
    public String displayMode;
    public long delayByInSec;

    public static ShowTemplate fromJson(JSONObject json) {
        ShowTemplate showTemplate = new ShowTemplate();

        JSONObject eventParams = json.optJSONObject("eventParams");

        showTemplate.templateId = eventParams.optString("templateId");
        showTemplate.displayMode = eventParams.optString("displayMode");

        JSONObject delayBy = eventParams.optJSONObject("delayBy");
        if ((delayBy != null) && "time".equals(delayBy.optString("type"))) {
            showTemplate.delayByInSec = delayBy.optLong("value");
        }

        return showTemplate;
    }
}
