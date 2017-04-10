package io.piano.android.composer.model;

import org.json.JSONObject;

public class ShowTemplate extends Event {

    public static final String DISPLAY_MODE_MODAL = "modal";
    public static final String DISPLAY_MODE_INLINE = "inline";

    public String url;
    public String endpointUrl;

    public String templateId;
    public String displayMode;
    public String containerSelector;
    public DelayBy delayBy;
    public boolean showCloseButton;

    public static ShowTemplate fromJson(JSONObject json) {
        ShowTemplate showTemplate = new ShowTemplate();

        JSONObject eventParams = json.optJSONObject("eventParams");

        showTemplate.templateId = eventParams.optString("templateId");
        showTemplate.displayMode = eventParams.optString("displayMode");
        showTemplate.containerSelector = eventParams.optString("containerSelector");
        showTemplate.delayBy = DelayBy.fromJson(eventParams.optJSONObject("delayBy"));
        showTemplate.showCloseButton = eventParams.optBoolean("showCloseButton");

        return showTemplate;
    }

    public boolean isInline() {
        return DISPLAY_MODE_INLINE.equals(displayMode);
    }

    public boolean isModal() {
        return DISPLAY_MODE_MODAL.equals(displayMode);
    }

    public boolean isDelayedByTime() {
        return DelayBy.TYPE_TIME.equals(delayBy.type) && (delayBy.value > 0);
    }

    public boolean isDelayedByScroll() {
        return DelayBy.TYPE_SCROLL.equals(delayBy.type) && (delayBy.value > 0);
    }
}
