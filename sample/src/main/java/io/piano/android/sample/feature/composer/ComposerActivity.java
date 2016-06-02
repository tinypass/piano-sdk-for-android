package io.piano.android.sample.feature.composer;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import io.piano.android.composer.Composer;
import io.piano.android.composer.ComposerJS;
import io.piano.android.composer.ShowTemplateListener;
import io.piano.android.composer.WebViewDialogFragment;
import io.piano.android.sample.BuildConfig;
import io.piano.android.sample.R;

public class ComposerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Composer(this, BuildConfig.PIANO_AID, true)
                .tag("tag")
                .addListener(new ShowTemplateListener() {
                    @Override
                    public void onExecuted(String baseUrl, String htmlTemplate) {
                        DialogFragment dialogFragment = WebViewDialogFragment.newInstance(baseUrl, htmlTemplate);
                        dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
                    }
                })
                .execute();

        new Composer(this, BuildConfig.PIANO_AID, true)
                .customVariable("key", "value")
                .addListener(new ShowTemplateListener() {
                    @Override
                    public void onExecuted(String baseUrl, String htmlTemplate) {
                        WebView webView = (WebView) findViewById(R.id.webview);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.addJavascriptInterface(new ComposerJS(webView), ComposerJS.NAME);
                        webView.loadDataWithBaseURL(baseUrl, htmlTemplate, null, null, null);
                        webView.setVisibility(View.VISIBLE);
                    }
                })
                .execute();
    }
}
