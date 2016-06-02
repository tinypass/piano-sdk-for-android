package io.piano.android.composer;

public interface ShowTemplateListener extends EventTypeListener {

    void onExecuted(String baseUrl, String htmlTemplate);
}
